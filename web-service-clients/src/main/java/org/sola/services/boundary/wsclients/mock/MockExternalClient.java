package org.sola.services.boundary.wsclients.mock;

import org.sola.services.boundary.wsclients.AdminClientImpl;
import org.sola.services.boundary.wsclients.ExternalClient;
import org.sola.services.boundary.wsclients.ExternalClientImpl;
import org.sola.webservices.external.External;

public class MockExternalClient extends ExternalClientImpl implements ExternalClient {

    private MockExternalPort port = new MockExternalPort();

    /**
     * Constructor for the mock class.
     */
    public MockExternalClient() {
        // The URL is irrelevant for the mock client class
        super("");
    }

    /**
     * Overrides the default getPort method on {@linkplain AdminClientImpl} to return a mock port
     * object - {@linkplain MockAdminPort}.
     *
     * @return
     */
    @Override
    protected External getPort() {
        return port;
    }

    /**
     * Overridden to help avoid leakage of password details during testing. i.e. Only username is
     * saved.
     */
    @Override
    public void setCredentials(String userName, char[] password) {
        super.setCredentials(userName, null);
    }
}
