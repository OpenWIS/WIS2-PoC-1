package openwis.pilot.ldsh.manager.impl;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import openwis.pilot.ldsh.db.dao.IDaoFactory;
import openwis.pilot.ldsh.dto.DatasetDTO;
import openwis.pilot.ldsh.manager.service.DatasetService;

import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;
import org.ops4j.pax.cdi.api.OsgiService;
import org.ops4j.pax.cdi.api.OsgiServiceProvider;

@Singleton
@OsgiServiceProvider(classes = { DatasetService.class })
public class DatasetServiceImpl implements DatasetService {

	@OsgiService
	@Inject
	private IDaoFactory iDaoFactory;

	private DatasetDTO datasetDTO;
	// private GenericDao<UserAccount> userAccountDAO;

	private static final Logger logger = Logger.getLogger(DatasetServiceImpl.class.getName());


	public DatasetDTO saveDataset(DatasetDTO datasetDTO) {


		System.out.println("SAVE DATASET CALLED");

		// userAccountDAO = iDaoFactory.getDao(UserAccount.class);
		 String serviceResponse = "messge from SAVE DATASET service..";
		
		 try {
		// 	boolean userExist = datasetDTO.userExists(travellerDto.getEmail());

		// 	if (userExist) 
		// 	{
		// 		serviceResponse = "User already exists";
		// 	} 
		// 	else 
		// 	{

		// 		travellerDto.setType("user");
		// 		travellerDto.setStatus(false);
		// 		UserAccount userAccount = new UserAccount(travellerDto.getType(), travellerDto.getLoginName(),
		// 				travellerDto.getPassword(), travellerDto.getStatus());
		// 		userAccountDAO.create(userAccount);
				
		// 		Mapper mapper = DozerBeanMapperBuilder.buildDefault();
		// 		Traveller modelTraveller = mapper.map(travellerDto, Traveller.class);
				
		// 		travellerDAO.create(modelTraveller);
		// 		modelTraveller.setUserAccount(userAccount);
				
		// 		serviceResponse = "The user was created";
				/**
				 * Email configuration
				 */
				
				// Set up the SMTP server.
			// 	java.util.Properties props = new java.util.Properties();
				
			// 	props.put("mail.smtp.host", "zmail.eurodyn.com");
			// 	props.put("mail.smtps.port", 587);
			// 	props.put("mail.smtp.auth", "true");
			// 	props.put("mail.smtps.starttls.enable", "true");
				

			// 	Session emailSession = Session.getInstance(props,
			// 	         new javax.mail.Authenticator() {
			// 	            protected PasswordAuthentication getPasswordAuthentication() {
			// 	               return new PasswordAuthentication(
			// 	                  "Asimakis.Zormpas", "!1Azomp!!");
			// 	            }
			// 	         });
				
			// 	String to = "Asimakis.Zormpas@eurodyn.com";
			
			// 	String from = "iBorderCtrl";
			// 	String subject = "Activation link";
			// 	Message msg = new MimeMessage(emailSession);

			// 	msg.setFrom(new InternetAddress(from));
			// 	msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			// 	msg.setSubject(subject);
						
			//     String activationLink = "http://localhost:8100/#/user-verification/";

			//     String uuid = UUID.randomUUID().toString();
			//    // UserAccount user = travellerDAO.userId(travellerDto.getEmail());
			//    // String verificationUrl =  uuid + user.getId();
			//     userAccount.setVerificationUrl(uuid);
			//     userAccountDAO.update(userAccount);
			    
			//     activationLink = activationLink + uuid;
				
			// 	msg.setText("Hi,\n\nHow are you?Welcome to iBorderCtrl \n" + activationLink);

			// 	// Send the message.
			// 	Transport.send(msg);
				
			// }

		} catch (Exception e) {
			logger.log(Level.INFO, "error", e);
			serviceResponse = "Something went wrong";

		}
		
		return datasetDTO;
	}

}
