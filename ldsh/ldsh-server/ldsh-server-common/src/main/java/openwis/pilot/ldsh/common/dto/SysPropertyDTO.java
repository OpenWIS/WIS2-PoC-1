package openwis.pilot.ldsh.common.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SysPropertyDTO implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6590649343502192920L;

	public SysPropertyDTO(){
		
	}
	
	
	public SysPropertyDTO(Long id, String name, String title, String email,
			String copyright, String footerTxt, String homeTxt,
			String ftpPassword, String ftpUrl, String ftpUsername) {
		super();
		this.id = id;
		this.systemid = name;
		this.title = title;
		this.email = email;
		this.copyright = copyright;
		this.footerTxt = footerTxt;
		this.homeTxt = homeTxt;
		this.ftpPassword = ftpPassword;
		this.ftpUrl = ftpUrl;
		this.ftpUsername = ftpUsername;
	}

	@Override
	public String toString() {
		return "SysPropertyDTO [id=" + id + ", System Id=" + systemid + ", title="
				+ title + ", email=" + email + ", copyright=" + copyright
				+ ", footerTxt=" + footerTxt + ", homeTxt=" + homeTxt
				+ ", ftpPassword=" + ftpPassword + ", ftpUrl=" + ftpUrl
				+ ", ftpUsername=" + ftpUsername + "]";
	}

	@XmlElement
	public Long id;
	
	@XmlElement
	public String systemid;

	@XmlElement
	public String title;

	@XmlElement
	public String email;
	
	@XmlElement
	public String copyright;

	@XmlElement
	public String footerTxt; 
	
	@XmlElement
	public String homeTxt;
	
	@XmlElement
	public String ftpPassword;
	
	@XmlElement
	public String ftpUrl;
	
	@XmlElement
	public String ftpUsername;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSystemId() {
		return systemid;
	}

	public void setSystemId(String name) {
		this.systemid = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public String getFooterTxt() {
		return footerTxt;
	}

	public void setFooterTxt(String footerTxt) {
		this.footerTxt = footerTxt;
	}

	public String getHomeTxt() {
		return homeTxt;
	}

	public void setHomeTxt(String homeTxt) {
		this.homeTxt = homeTxt;
	}

	public String getFtpPassword() {
		return ftpPassword;
	}

	public void setFtpPassword(String ftpPassword) {
		this.ftpPassword = ftpPassword;
	}

	public String getFtpUrl() {
		return ftpUrl;
	}

	public void setFtpUrl(String ftpUrl) {
		this.ftpUrl = ftpUrl;
	}

	public String getFtpUsername() {
		return ftpUsername;
	}

	public void setFtpUsername(String ftpUsername) {
		this.ftpUsername = ftpUsername;
	}
	
}
