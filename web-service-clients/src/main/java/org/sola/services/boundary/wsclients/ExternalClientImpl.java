package org.sola.services.boundary.wsclients;

import java.util.List;
import javax.xml.namespace.QName;
import org.sola.services.boundary.wsclients.exception.WebServiceClientException;
import org.sola.webservices.external.External;
import org.sola.webservices.external.ExternalService;
import org.sola.webservices.transferobjects.administrative.BaUnitTO;
import org.sola.webservices.transferobjects.external.ClientTO;
import org.sola.webservices.transferobjects.external.RequestTO;

/**
 * Implementation class of {@link ExternalClient}.
 */
public class ExternalClientImpl extends AbstractWSClientImpl implements ExternalClient {

    private static final String NAMESPACE_URI = "http://webservices.sola.org/external";
    private static final String LOCAL_PART = "external-service";
     
    public ExternalClientImpl(String url){
        super(url, new QName(NAMESPACE_URI, LOCAL_PART));
    }
    
    protected External getPort() {
        return getPort(External.class, ExternalService.class);
    }
    
    @Override
    public boolean checkConnection() throws WebServiceClientException {
        final String methodName = ExternalClient.CHECK_CONNECTION;
        boolean result = false;
        
        try {
            beforeWebMethod(methodName);
            result = getPort().checkConnection();
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result);
        }
        return result;
    }

    @Override
    public ClientTO getClient() throws WebServiceClientException {
       ClientTO result = null;
        final String methodName = ExternalClient.GET_CLIENT;
        try {
            beforeWebMethod (methodName);
            result = getPort().getClient();
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result);
        }
        return result;
    }

    @Override
    public List<RequestTO> getClientRequests() throws WebServiceClientException {
        List<RequestTO> result = null;
        final String methodName = ExternalClient.GET_CLIENT_REQUESTS;
        try {
             beforeWebMethod (methodName);
            result = getPort().getClientRequests();
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result);
        }
        return result;
    }

    @Override
    public ClientTO saveClient(ClientTO clientTO) throws WebServiceClientException {
        ClientTO result = null;
        final String methodName = ExternalClient.SAVE_CLIENT;
        try {
            beforeWebMethod (methodName, clientTO);
            result = getPort().saveClient(clientTO);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, clientTO);
        }
        return result;    }

    @Override
    public BaUnitTO makeRequest(String nameFirstPart, String nameLastPart) 
            throws WebServiceClientException {
        BaUnitTO result = null;
        final String methodName = ExternalClient.MAKE_REQUEST;
        try {
            beforeWebMethod (methodName, nameFirstPart, nameLastPart);
            result = getPort().makeRequest(nameFirstPart, nameLastPart);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, nameFirstPart, nameLastPart);
        }
        return result;
    }
}


