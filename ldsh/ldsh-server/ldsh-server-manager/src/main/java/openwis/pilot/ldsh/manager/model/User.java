package openwis.pilot.ldsh.manager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "ldsh_user")
public class User implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "user_name")
  private String username;

  @Column(name = "user_password")
  private String password;

  @Column
  private String salt;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }
}

