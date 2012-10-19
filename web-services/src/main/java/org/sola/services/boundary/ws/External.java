package org.sola.services.boundary.ws;

import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import org.sola.services.boundary.transferobjects.administrative.BaUnitTO;
import org.sola.services.boundary.transferobjects.external.ClientTO;
import org.sola.services.boundary.transferobjects.external.RequestTO;
import org.sola.services.common.ServiceConstants;
import org.sola.services.common.contracts.GenericTranslator;
import org.sola.services.common.faults.*;
import org.sola.services.common.webservices.AbstractWebService;
import org.sola.services.ejbs.external.businesslogic.ExternalEJBLocal;
import org.sola.services.ejbs.external.repository.entities.Client;
import org.sola.services.ejbs.admin.businesslogic.AdminEJBLocal;
import org.sola.services.ejbs.admin.businesslogic.repository.entities.User;

/**
 * Web service, exposing ExternalEJB methods
 */
@WebService(serviceName = "external-service", targetNamespace = ServiceConstants.EXTERNAL_WS_NAMESPACE)
public class External extends AbstractWebService {

    @Resource
    private WebServiceContext wsContext;
    @EJB
    ExternalEJBLocal externalEJB;
    @EJB
    AdminEJBLocal adminEJB;

    /**
    * Dummy method to check the web service instance is working
    */
    @WebMethod(operationName="checkConnection")
    public boolean checkConnection(){
	   return true;
    }

    /**
     * Returns Client object, related to the currently logged in user.
     */
    @WebMethod(operationName = "getClient")
    public ClientTO getClient() throws SOLAAccessFault, SOLAFault, UnhandledFault {
        final Object[] result = {null};

        runOpenQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                User user = getCurrentUser();
                if (user != null) {
                    result[0] = GenericTranslator.toTO(
                            externalEJB.getClientByUser(user.getId()), ClientTO.class);
                }
            }
        });

        return (ClientTO) result[0];
    }

    /**
     * Returns Client requests.
     */
    @WebMethod(operationName = "getClientRequests")
    public List<RequestTO> getClientRequests() throws SOLAFault, UnhandledFault {
        final Object[] result = {null};

        runOpenQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                User user = getCurrentUser();
                Client client = externalEJB.getClientByUser(user.getId());
                if (user != null) {
                    result[0] = GenericTranslator.toTOList(
                            externalEJB.getClientRequests(client.getId()), RequestTO.class);
                }
            }
        });

        return (List<RequestTO>) result[0];
    }    /**
     * Saves Client data.
     */
    @WebMethod(operationName = "saveClient")
    public ClientTO saveClient(@WebParam(name = "client") final ClientTO clientTO)
            throws SOLAFault, UnhandledFault, SOLAAccessFault,
            OptimisticLockingFault, SOLAValidationFault {
        final Object[] result = {null};

        runUpdate(wsContext, new Runnable() {

            @Override
            public void run() {
                User user = getCurrentUser();
                if (user != null && clientTO != null) {
                    Client client = GenericTranslator.fromTO(clientTO, Client.class, null);

                    // Check client to have the same User ID and Client ID. 
                    // Not to allow inserts and updates of system user ID.
                    if (client.getId().equals(clientTO.getId())
                            && client.getUserId().equals(clientTO.getUserId())) {
                        result[0] = GenericTranslator.toTO(
                                externalEJB.saveClient(client), ClientTO.class);
                    }
                }
            }
        });

        return (ClientTO) result[0];
    }

    /**
     * Registers Client request and returns information on requested property
     * object. In the training purposes we return BaUnitTO object, which
     * normally should be returned by AdministrativeEJB. This method considered
     * to return a cut version of property object.
     */
    @WebMethod(operationName = "makeRequest")
    public BaUnitTO makeRequest(@WebParam(name = "nameFirstPart") final String nameFirstPart,
            @WebParam(name = "nameLastPart") final String nameLastPart)
            throws SOLAFault, UnhandledFault {
        
        final Object[] result = {null};

        runOpenQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                User user = getCurrentUser();
                
                if (user != null) {
                    Client client = externalEJB.getClientByUser(user.getId());
                    result[0] = GenericTranslator.toTO(
                            externalEJB.makeRequest(client.getId(), 
                            nameFirstPart, nameLastPart), BaUnitTO.class);
                }
            }
        });

        return (BaUnitTO) result[0];
    }

    /**
     * Returns currently logged in user.
     */
    private User getCurrentUser() {
        return adminEJB.getCurrentUser();
    }
}

