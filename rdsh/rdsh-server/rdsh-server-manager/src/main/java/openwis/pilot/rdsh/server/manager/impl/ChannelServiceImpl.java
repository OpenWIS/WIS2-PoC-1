package openwis.pilot.rdsh.server.manager.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;

import com.querydsl.jpa.impl.JPAQueryFactory;

import openwis.pilot.rdsh.server.manager.model.Channel;
import openwis.pilot.rdsh.server.manager.model.QChannel;
import openwis.pilot.rdsh.server.manager.mappers.ChannelMapperImpl;
import openwis.pilot.rdsh.server.common.dto.ChannelDTO;
import openwis.pilot.rdsh.server.manager.service.ChannelService;

@Singleton
@Transactional
@OsgiServiceProvider(classes = { ChannelService.class })
public class ChannelServiceImpl implements ChannelService {

	// Entity Manager ref.
	@PersistenceContext(unitName = "rdsh-pu")
	private EntityManager em;

	private static final Logger logger = Logger.getLogger(ChannelServiceImpl.class.getName());
	
	private static QChannel qChannel = QChannel.channel;

	@Override
	public ChannelDTO saveChannel(ChannelDTO channelDTO) {

		Channel  c = new ChannelMapperImpl().toChannel(channelDTO);
		
		try {
			if (channelDTO.getId()!= null){
				logger.log(Level.INFO, "Updating... "+channelDTO.getChannelName() + " - " +channelDTO.getChannelUri());
				em.merge(new ChannelMapperImpl().toChannel(channelDTO));
			}else{
				logger.log(Level.INFO, "Saving... "+channelDTO.getChannelName() + " - " +channelDTO.getChannelUri());
				em.persist(new ChannelMapperImpl().toChannel(channelDTO));
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
		} 
		return channelDTO;
	}
	

	@Override
	public ChannelDTO getChannel(Long id) {

		final  Channel channel =  new JPAQueryFactory(em).selectFrom(qChannel).where(qChannel.id.eq(id)).fetchOne();
		return new ChannelMapperImpl().toChannelDTO(channel);
	}

	
	@Override
	public List<ChannelDTO> getAllChannels() {

		ArrayList<Channel> channelsList =(ArrayList<Channel>) new JPAQueryFactory(em).selectFrom(qChannel).fetch();
		ArrayList<ChannelDTO> channels = new ArrayList<ChannelDTO>();
		
		for (Channel c :channelsList) {
			channels.add(new ChannelMapperImpl().toChannelDTO(c));
		}		
		return channels;
	}


	@Override
	public ChannelDTO purgeChannel(Long id) {
		
		final  Channel channel =  new JPAQueryFactory(em).selectFrom(qChannel).where(qChannel.id.eq(id)).fetchOne();
		if (channel != null){
			channel.setBytesSent(0);
			channel.setFailedConnections(0);
			channel.setMsessagesSent(0);
			em.merge(channel);
		}
		return new ChannelMapperImpl().toChannelDTO(channel);
	}


	@Override
	public Boolean deleteChannel(Long id) {
		Channel channel =  new JPAQueryFactory(em).selectFrom(qChannel).where(qChannel.id.eq(id)).fetchOne();
		
		try {
			if (channel!= null){
				logger.log(Level.INFO, "*** Deleting ... "+channel.getChannelName() + " - " +channel.getChannelUri());
				em.remove(channel);
			}else{
				return false;
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
		} 
		return true;
	}


	@Override
	public ChannelDTO getChannel(String channelName, String channelUri) {

		final  Channel channel =  new JPAQueryFactory(em).selectFrom(qChannel).where(qChannel.channelName.eq(channelName), qChannel.channelUri.eq(channelUri)).fetchOne();
		return new ChannelMapperImpl().toChannelDTO(channel);
	
	}

}
