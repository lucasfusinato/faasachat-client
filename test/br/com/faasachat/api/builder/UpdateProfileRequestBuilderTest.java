package br.com.faasachat.api.builder;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.faasachat.domain.model.Request;

/**
 * Test class to update profile request builder.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 11/11/2019
 * @version 1.0
 */
public class UpdateProfileRequestBuilderTest {

    /**
     * Update profile request builder instance to test.
     */
    private UpdateProfileRequestBuilder builder;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        builder = new UpdateProfileRequestBuilder(1L, "lucas", "lucas@lucas.com", "lucaspassword", 1993);
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
        assertEquals("updateProfile", builder.getRequest().getMethod());
    }
    
    /**
     * Tests builder parameters definition.
     */
    @Test
    public void testBuildParameters() {
        builder.buildParameters();
        assertEquals(1L,                builder.getRequest().getParameter("id"));
        assertEquals("lucas",           builder.getRequest().getParameter("nickname"));
        assertEquals("lucas@lucas.com", builder.getRequest().getParameter("email"));
        assertEquals("lucaspassword",   builder.getRequest().getParameter("password"));
        assertEquals(1993,              builder.getRequest().getParameter("yearOfBirth"));
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        this.builder = null;
    }

}
