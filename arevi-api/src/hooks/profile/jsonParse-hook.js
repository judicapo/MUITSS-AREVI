const errors = require('@feathersjs/errors');

module.exports = function () { // eslint-disable-line no-unused-vars
  return function jsonParse(hook) {
    if (!hook.params.provider) {
      return hook;
    }

    if (hook.result && hook.result.data) {
      hook.result.data.map(x => {
        x.configuration = JSON.parse(x.configuration);
      });
    }

    return hook;
  };
};
