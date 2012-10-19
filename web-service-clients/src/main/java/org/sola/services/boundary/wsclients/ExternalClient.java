package org.sola.services.boundary.wsclients;

import java.util.List;
import javax.jws.WebMethod;
import org.sola.webservices.transferobjects.administrative.BaUnitTO;
import org.sola.webservices.transferobjects.external.ClientTO;
import org.sola.webservices.transferobjects.external.RequestTO;
/**
 * External Web-service client interface.
 */
public interface ExternalClient extends AbstractWSClient {
      /**
     * External. - Service name prefix for the External Web Service
     */
    public static final String SERVICE_NAME = "External.";
    /**
     * CaseManagement.checkConnection - Identifier for the checkConnection method
     */
    public static final String CHECK_CONNECTION = SERVICE_NAME + "checkConnection";
  
     /**
     * External.getClient - Identifier for the getClient method
     */
    public static final String GET_CLIENT = SERVICE_NAME + "getClient";
    
    /**
    * External.getClient - Identifier for the getClientRequests method
    */
    public static final String GET_CLIENT_REQUESTS = SERVICE_NAME + "getClientRequests";
   
    
     /**
     * External.saveClient - Identifier for the saveClient method
     */
    public static final String SAVE_CLIENT = SERVICE_NAME + "saveClient";
   
     /**
     * External.makeRequest - Identifier for the makeRequest method
     */
    public static final String MAKE_REQUEST = SERVICE_NAME + "makeRequest";
   
   
    /**
     * Dummy method to check the web service instance is working.
     */
    @Override
    public boolean checkConnection();
    
    /**
     * Returns Client object, related to the currently logged in user.
     */
    ClientTO getClient();
    
    /**
     * Returns Client requests.
     */
    List<RequestTO> getClientRequests();
    
    /**
     * Saves Client data.
     */
    ClientTO saveClient(ClientTO clientTO);
    
    /**
     * Registers Client request and returns information on requested property
     * object.
     */
    BaUnitTO makeRequest(String nameFirstPart, String nameLastPart);
}


