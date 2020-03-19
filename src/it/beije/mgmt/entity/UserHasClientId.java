package it.beije.mgmt.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class UserHasClientId implements Serializable {
	

	@Column(name = "user_id")
    private Long userId;
 
    @Column(name = "client_company_id")
    private Long clientId;
	
	
	
	
  @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserHasClientId that = (UserHasClientId) o;
        return Objects.equals(userId, that.userId) &&
               Objects.equals(clientId, that.clientId);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(userId, clientId);
    }
}
