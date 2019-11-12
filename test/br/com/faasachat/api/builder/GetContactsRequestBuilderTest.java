package br.com.faasachat.api.builder;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.faasachat.domain.model.Request;

/**
 * Test class to get contacts request builder.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 11/11/2019
 * @version 1.0
 */
public class GetContactsRequestBuilderTest {

    /**
     * Get contacts request builder instance to test.
     */
    private GetContactsRequestBuilder builder;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        builder = new GetContactsRequestBuilder(1L);
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
        assertEquals("/contacts", builder.getRequest().getResource());
    }
    
    /**
     * Tests builder method definition.
     */
    @Test
    public void testBuildMethod() {
        builder.buildMethod();
        assertEquals("getContacts", builder.getRequest().getMethod());
    }
    
    /**
     * Tests builder parameters definition.
     */
    @Test
    public void testBuildParameters() {
        builder.buildParameters();
        assertEquals(1L, builder.getRequest().getParameter("userId"));
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        this.builder = null;
    }

}
