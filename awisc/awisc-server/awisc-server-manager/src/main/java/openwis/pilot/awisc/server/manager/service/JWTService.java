package openwis.pilot.awisc.server.manager.service;

import openwis.pilot.awisc.server.common.exception.ServiceException;

/**
 * Provides utility services to authenticate users and create JWT.
 */
public interface JWTService {

  /**
   * Tries to authenticate a user. If authentication is successful, it returns a JWT.
   * @param username The username to authenticate with.
   * @param password The password to authenticate with.
   * @return A JWT if authentication was successful.
   */
  String authenticate(String username, String password) throws ServiceException;
}
