package openwis.pilot.rdsh.server.manager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "rdsh_ldsh")
public class Ldsh implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column
  private String id;

  @Column
  private String title;

  @Column(name = "system_id")
  private String systemId;

  @Column(name = "contact_email")
  private String contactEmail;

  @Column
  private String token;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getSystemId() {
    return systemId;
  }

  public void setSystemId(String systemId) {
    this.systemId = systemId;
  }

  public String getContactEmail() {
    return contactEmail;
  }

  public void setContactEmail(String contactEmail) {
    this.contactEmail = contactEmail;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
