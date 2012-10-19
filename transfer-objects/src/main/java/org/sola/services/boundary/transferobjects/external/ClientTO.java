package org.sola.services.boundary.transferobjects.external;

import java.math.BigDecimal;
import java.util.List;
import org.sola.services.common.contracts.AbstractIdTO;

/**
 * Transfer object representing structure of Client entity.
 */
public class ClientTO extends AbstractIdTO {

    String userId;
    String name;
    String typeCode;
    BigDecimal balance;
    List<ContactTO> contacts;

    public ClientTO() {
        super();
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<ContactTO> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactTO> contacts) {
        this.contacts = contacts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
}
