package openwis.pilot.rdsh.server.manager.service;

import java.util.List;

import openwis.pilot.rdsh.server.common.dto.ChannelDTO;

public interface ChannelService {

	ChannelDTO saveChannel(ChannelDTO channelDTO);
	
	ChannelDTO getChannel(Long id);
	
	ChannelDTO getChannel(String channelName, String channelUri);

	List<ChannelDTO> getAllChannels();
	
	Boolean deleteChannel(Long id);

	ChannelDTO purgeChannel(Long id);

}
