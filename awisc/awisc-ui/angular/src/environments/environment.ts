// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.

export const environment = {
  production: false,

  CONSTANTS: {
    // The context root of the back-end API.
    API_ROOT: '/cxf/awisc-api',

    // The name under which the JWT is kept.
    JWT_STORAGE_NAME: 'rdshJWT'
  }
};
