package org.sola.services.boundary.wsclients.mock;

import java.util.ArrayList;
import java.util.List;
import org.sola.services.boundary.wsclients.ExternalClient;
import org.sola.webservices.external.*;
import org.sola.webservices.external.External;
import org.sola.webservices.transferobjects.administrative.BaUnitTO;
import org.sola.webservices.transferobjects.external.ClientTO;
import org.sola.webservices.transferobjects.external.RequestTO;

public class MockExternalPort implements External {

    /**
     *
     * @return A reference to the MockServiceManager
     */
    private MockServiceManager getManager() {
        return MockServiceManager.getInstance();
    }

    /**
     * Processes the mock response exception and throws the appropriate service exception or a
     * MockResponseException if the response exception is not a recognized type.
     *
     * @param ex The Mock response exception to process
     */
    private void processExceptionBasic(Exception ex) throws SOLAFault, UnhandledFault {
        if (SOLAFault.class.isAssignableFrom(ex.getClass())) {
            throw (SOLAFault) ex;
        } else if (UnhandledFault.class.isAssignableFrom(ex.getClass())) {
            throw (UnhandledFault) ex;
        } else if (RuntimeException.class.isAssignableFrom(ex.getClass())) {
            throw (RuntimeException) ex;
        } else {
            // The type of the exception does not match any recognized exception type used by this 
            // web service. Throw a MockResponseException to fail the test. 
            throw new MockResponseException("Unable to convert the mocked response exception to "
                    + "recognized exception type: " + ex.getClass().getName());
        }
    }

    /**
     * Processes the mock response exception and throws the appropriate service exception or a
     * MockResponseException if the response exception is not a recognized type. Extends {@linkplain #processExceptionBasic(java.lang.Exception) processExceptionBasic}
     * to include the SOLAAccessFault;
     *
     * @param ex The Mock response exception to process
     */
    private void processExceptionAccess(Exception ex) throws SOLAFault, SOLAAccessFault,
            UnhandledFault, MockResponseException {
        if (SOLAAccessFault.class.isAssignableFrom(ex.getClass())) {
            throw (SOLAAccessFault) ex;
        } else {
            processExceptionBasic(ex);
        }
    }

    /**
     * Processes the mock response exception and throws the appropriate service exception or a
     * MockResponseException if the response exception is not a recognized type. Extends {@linkplain #processExceptionBasic(java.lang.Exception) processExceptionBasic}
     * to include the OptimisticLockingFault;
     *
     * @param ex The Mock response exception to process
     */
    private void processExceptionUpdate(Exception ex) throws SOLAFault, SOLAAccessFault,
            UnhandledFault, OptimisticLockingFault, MockResponseException {
        if (OptimisticLockingFault.class.isAssignableFrom(ex.getClass())) {
            throw (OptimisticLockingFault) ex;
        } else {
            processExceptionBasic(ex);
        }
    }

    /**
     * Processes the mock response exception and throws the appropriate service exception or a
     * MockResponseException if the response exception is not a recognized type. Extends {@linkplain #processExceptionUpdate(java.lang.Exception) processExceptionUpdate}
     * to include the OptimisticLockingFault and SOLAValidationFault;
     *
     * @param ex The Mock response exception to process
     */
    private void processExceptionAll(Exception ex) throws OptimisticLockingFault, SOLAAccessFault,
            SOLAFault, SOLAValidationFault, UnhandledFault, MockResponseException {
        if (SOLAValidationFault.class.isAssignableFrom(ex.getClass())) {
            throw (SOLAValidationFault) ex;
        } else {
            processExceptionUpdate(ex);
        }
    }

    @Override
    public boolean checkConnection() {
        try {
            return getManager().getResponse(ExternalClient.CHECK_CONNECTION, Boolean.class, true);
        } catch (Exception ex) {
            if (RuntimeException.class.isAssignableFrom(ex.getClass())) {
                throw (RuntimeException) ex;
            } else {
                return false;
            }
        }
    }

    @Override
    public List<RequestTO> getClientRequests() throws SOLAFault, UnhandledFault {
        List defaultResponse = new ArrayList<RequestTO>();
        try {
            return getManager().getResponse(ExternalClient.GET_CLIENT_REQUESTS, List.class,
                    defaultResponse);
        } catch (Exception ex) {
            processExceptionBasic(ex);
            return null;
        }
    }

    @Override
    public ClientTO getClient() throws SOLAAccessFault, SOLAFault, UnhandledFault {
        ClientTO defaultResponse = new ClientTO();
        try {
            return getManager().getResponse(ExternalClient.GET_CLIENT, ClientTO.class,
                    defaultResponse);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    @Override
    public ClientTO saveClient(ClientTO client) throws OptimisticLockingFault, SOLAAccessFault, SOLAFault, SOLAValidationFault, UnhandledFault {
        ClientTO defaultResponse = client;
        try {
            return getManager().getResponse(ExternalClient.SAVE_CLIENT, ClientTO.class,
                    defaultResponse, client);
        } catch (Exception ex) {
            processExceptionAll(ex);
            return null;
        }
    }

    @Override
    public BaUnitTO makeRequest(String nameFirstPart, String nameLastPart) throws SOLAFault, UnhandledFault {
        BaUnitTO defaultResponse = new BaUnitTO();
        try {
            return getManager().getResponse(ExternalClient.MAKE_REQUEST, BaUnitTO.class,
                    defaultResponse, nameFirstPart, nameLastPart);
        } catch (Exception ex) {
            processExceptionBasic(ex);
            return null;
        }
    }
}
