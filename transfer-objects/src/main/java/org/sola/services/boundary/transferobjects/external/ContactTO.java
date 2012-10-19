package org.sola.services.boundary.transferobjects.external;

import org.sola.services.common.contracts.AbstractIdTO;

/**
 * Transfer object representing structure of Contact entity.
 */
public class ContactTO extends AbstractIdTO {
    private String clientId;
    private String name;
    private String phoneNumber;
    
    public ContactTO(){
        super();
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

