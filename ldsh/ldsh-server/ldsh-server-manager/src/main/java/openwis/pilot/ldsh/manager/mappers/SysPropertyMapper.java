package openwis.pilot.ldsh.manager.mappers;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import openwis.pilot.ldsh.common.dto.SysPropertyDTO;
import openwis.pilot.ldsh.manager.model.SysProperty;

import org.mapstruct.Mapper;

@Mapper(uses = { SysPropertyDTO.class })
public abstract class SysPropertyMapper {

	public SysPropertyDTO toSysPropertyDTO(List<SysProperty> spList) {

		if (spList == null) {
			return null;
		}

		SysPropertyDTO sysPropertyDTO = new SysPropertyDTO();

		try {

			for (SysProperty sp : spList) {
				Field field = SysPropertyDTO.class.getDeclaredField(sp.getName());

				if (field != null) {
					field.set(sysPropertyDTO, sp.getValue());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysPropertyDTO;
	}

	public List<SysProperty> toSysProperty(SysPropertyDTO spDto) {
		
		if (spDto == null) {
			return null;
		}

		ArrayList<SysProperty> sysList = new ArrayList<SysProperty>();
		Field[] fields = SysPropertyDTO.class.getDeclaredFields();
		
		for (Field field : Arrays.asList(fields)) {
			try {
				SysProperty sp = new SysProperty();

				// Dont save serialVersionUID or DTO's id as system property
				if (field.getName().equals("serialVersionUID") || field.getName().equals("id")) {
					continue;
				}
				
				sp.setName(field.getName());
				sp.setValue((String) field.get(spDto));
				sysList.add(sp);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return sysList;
	}

}
