package br.com.faasachat.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class to client application main class.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 12/11/2019
 * @version 1.0
 */
public class ApplicationTest {
    
    /**
     * Client application main class.
     */
    private Application application;
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        this.application = Application.getInstance();
    }
    
    /**
     * Tests application get unique instance.
     */
    @Test
    public void testGetInstance() {
        assertEquals(application, Application.getInstance());
    }
    
    /**
     * Tests application get default request executor.
     */
    @Test
    public void testGetRequestExecutor() {
        assertNotNull(application.getRequestExecutor());
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        this.application = null;
    }

}
