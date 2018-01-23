package openwis.pilot.rdsh.server.manager.mappers;

import openwis.pilot.rdsh.server.common.dto.LDSHDTO;
import openwis.pilot.rdsh.server.manager.model.Ldsh;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface LDSHMapper {
  LDSHDTO toLDSHDTO(Ldsh ldsh);
  Ldsh toLDSH(LDSHDTO ldshDTO);
  List<LDSHDTO> toLDSHDTOList(List<Ldsh> ldshList);
}
