package openwis.pilot.rdsh.server.manager.mappers;

import openwis.pilot.rdsh.server.common.dto.ChannelDTO;
import openwis.pilot.rdsh.server.manager.model.Channel;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class ChannelMapper {
	
	
	private final LDSHMapper ldshMapper =  Mappers.getMapper(LDSHMapper.class);
	
	//Channel toChannel(ChannelDTO channelDTO);
	  public Channel toChannel(ChannelDTO channelDTO) {
	        if ( channelDTO == null ) {
	            return null;
	        }

	        Channel channel = new Channel();

	        channel.setId( channelDTO.getId() );
	        channel.setChannelName( channelDTO.getChannelName() );
	        channel.setChannelUri( channelDTO.getChannelUri() );
	        channel.setMsessagesSent( channelDTO.getMsessagesSent() );
	        channel.setBytesSent( channelDTO.getBytesSent() );
	        channel.setFailedConnections( channelDTO.getFailedConnections() );
	        channel.setChannelTopic(channelDTO.getChannelTopic());
	        channel.setLdsh(ldshMapper.toLDSH(channelDTO.getLdshDto()));

	        return channel;
	    }
	
	
	//ChannelDTO toChannelDTO(Channel channel);
	  public ChannelDTO toChannelDTO(Channel channel) {
	        if ( channel == null ) {
	            return null;
	        }

	        ChannelDTO channelDTO = new ChannelDTO();

	        channelDTO.setId( channel.getId() );
	        channelDTO.setChannelName( channel.getChannelName() );
	        channelDTO.setChannelUri( channel.getChannelUri() );
	        channelDTO.setBytesSent( channel.getBytesSent() );
	        channelDTO.setMsessagesSent( channel.getMsessagesSent() );
	        channelDTO.setFailedConnections( channel.getFailedConnections() );
	        channelDTO.setChannelTopic(channel.getChannelTopic());
	        channelDTO.setLdshDto(ldshMapper.toLDSHDTO(channel.getLdsh()));

	        return channelDTO;
	    }
	  
	  

}
