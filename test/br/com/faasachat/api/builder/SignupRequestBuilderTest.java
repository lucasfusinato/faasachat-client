package br.com.faasachat.api.builder;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.faasachat.domain.model.Request;

/**
 * Test class to signup request builder.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 11/11/2019
 * @version 1.0
 */
public class SignupRequestBuilderTest {

    /**
     * Signup request builder instance to test.
     */
    private SignupRequestBuilder builder;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        builder = new SignupRequestBuilder("admin", "admin@email.com", "passw", 2019);
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
        assertEquals("/users", builder.getRequest().getResource());
    }
    
    /**
     * Tests builder method definition.
     */
    @Test
    public void testBuildMethod() {
        builder.buildMethod();
        assertEquals("signup", builder.getRequest().getMethod());
    }
    
    /**
     * Tests builder parameters definition.
     */
    @Test
    public void testBuildParameters() {
        builder.buildParameters();
        assertEquals("admin",           builder.getRequest().getParameter("nickname"));
        assertEquals("admin@email.com", builder.getRequest().getParameter("email"));
        assertEquals("passw",           builder.getRequest().getParameter("password"));
        assertEquals(2019,              builder.getRequest().getParameter("yearOfBirth"));
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        this.builder = null;
    }

}
