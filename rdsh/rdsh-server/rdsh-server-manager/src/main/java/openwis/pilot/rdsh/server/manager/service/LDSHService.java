package openwis.pilot.rdsh.server.manager.service;

import openwis.pilot.rdsh.server.common.dto.LDSHDTO;

import java.util.List;

public interface LDSHService {

  /**
   * Obtain the list of all registered LDSHs.
   * @return Returns the list of all LDSHs registered in the system.
   */
  List<LDSHDTO> getLDSH();

  /**
   * Finds a specific LDSH by Id.
   * @param ldshId The Id of the LDSH to fetch.
   * @return Returns the requested LDSDH or null.
   */
  LDSHDTO getLDSH(String ldshId);

  /**
   * Deletes a previously registered LDSH.
   * @param id The Id of the LDSH to delete.
   */
  void deleteLDSH(String id);

  /**
   * Registers or updates an LDSH into the system.
   * @param ldshDTO The DTO with the information of the LDSH to save or update.
   * @return Returns the LDSH that was saved or created.
   */
  LDSHDTO saveLDSH(LDSHDTO ldshDTO);
}
