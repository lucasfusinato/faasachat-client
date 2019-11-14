package br.com.faasachat.core;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.faasachat.domain.utils.Configuration;

/**
 * Test class to client configuration proxy class.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 12/11/2019
 * @version 1.0
 */
public class ClientConfigurationTest {
    
    /**
     * Client configuration proxy to test.
     */
    private ClientConfiguration clientConfiguration;
    
    /**
     * Original configuration to test.
     */
    private Configuration configuration;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        this.configuration       = new Configuration();
        this.clientConfiguration = new ClientConfiguration(this.configuration);
    }

    /**
     * Tests server host configuration getter and setter.
     */
    @Test
    public void testServerHostConfiguration() {
        clientConfiguration.setServerHost("testServerHost");
        assertEquals("testServerHost", clientConfiguration.getServerHost(), configuration.getParameter("serverHost", String.class));
    }

    /**
     * Tests server port configuration getter and setter.
     */
    @Test
    public void testServerPortConfiguration() {
        clientConfiguration.setServerPort(56000);
        assertEquals((Integer) 56000, clientConfiguration.getServerPort(), clientConfiguration.getServerPort());
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        this.configuration = null;
    }

}
