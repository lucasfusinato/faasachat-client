package br.com.faasachat.api.builder;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.faasachat.domain.model.Request;

/**
 * Test class to login request builder.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 11/11/2019
 * @version 1.0
 */
public class LoginRequestBuilderTest {

    /**
     * Login request builder instance to test.
     */
    private LoginRequestBuilder builder;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        builder = new LoginRequestBuilder("admin", "passw");
        builder.reset();
    }

    /**
     * Tests builder reset.
     */
    @Test
    public void testReset() {
        Request request = builder.getRequest();
        builder.reset();
        assertNotEquals(request, builder.getRequest());
    }
    
    /**
     * Tests builder resource definition.
     */
    @Test
    public void testBuildResource() {
        builder.buildResource();
        assertEquals("/sessions", builder.getRequest().getResource());
    }
    
    /**
     * Tests builder method definition.
     */
    @Test
    public void testBuildMethod() {
        builder.buildMethod();
        assertEquals("login", builder.getRequest().getMethod());
    }
    
    /**
     * Tests builder parameters definition.
     */
    @Test
    public void testBuildParameters() {
        builder.buildParameters();
        assertEquals("admin", builder.getRequest().getParameter("nickname"));
        assertEquals("passw", builder.getRequest().getParameter("password"));
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        this.builder = null;
    }

}
