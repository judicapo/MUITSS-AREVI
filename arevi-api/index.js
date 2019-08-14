/* eslint-disable no-console */
const logger = require('./src/logger');
const app = require('./src/app');
const port = app.get('port');
const server = app.listen(process.env.PORT || port);

process.on('unhandledRejection', (reason, p) =>
  logger.error('Unhandled Rejection at: Promise ', p, reason)
);

server.on('listening', () =>
  logger.info('Feathers application started on http://%s:%d', app.get('host'), port)
);