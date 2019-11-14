package br.com.faasachat.domain.model;

import java.util.Map;

import br.com.faasachat.api.builder.RequestBuilder;
import br.com.faasachat.domain.adapter.GsonAdapter;
import br.com.faasachat.domain.utils.Configuration;
import br.com.faasachat.domain.utils.Parameter;

/**
 * Model that represents request data.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 12/11/2019
 * @version 1.0
 */
public class Request implements Parameter {
    
    /**
     * Resource name.
     * Indicates resource type that will be accessed.
     */
    private String resource;
    
    /**
     * Method name.
     * Indicates which method will be called on resource to process request.
     */
    private String method;
    
    /**
     * Request parameters.
     * Contains all necessary parameters to process request.
     */
    private Configuration parameters;
    
    /**
     * Instantiates request.
     */
    public Request() {
        this.parameters = new Configuration();
    }
    
    /**
     * Instantiates request from JSON data.
     * @param json
     */
    public Request(String json) {
        this();
        this.from(GsonAdapter.getInstance().fromJson(json, Request.class));
    }

    /**
     * Instantiates request from builder.
     * @param requestBuilder
     */
    public Request(RequestBuilder requestBuilder) {
        this();
        requestBuilder.reset();
        requestBuilder.buildResource();
        requestBuilder.buildMethod();
        requestBuilder.buildParameters();
        this.from(requestBuilder.getRequest());
    }
    
    /**
     * Instantiates request from another request.
     * @param request
     */
    public Request(Request request) {
        this();
        this.from(request);
    }

    /**
     * Sets request data from another request.
     * @param request
     */
    public void from(Request request) {
        this.setResource(request.getResource());
        this.setMethod(request.getMethod());
        this.setParameters(request.getParameters());
    }

    /**
     * Returns resource name.
     * @return
     */
    public String getResource() {
        return resource;
    }

    /**
     * Defines resource name.
     * @param resource
     */
    public void setResource(String resource) {
        this.resource = resource;
    }

    /**
     * Returns method name.
     * @return
     */
    public String getMethod() {
        return method;
    }

    /**
     * Defines method name.
     * @param method
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Object> getParameters() {
        return parameters.getParameters();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setParameters(Map<String, Object> parameters) {
        this.parameters.setParameters(parameters);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getParameter(String parameter) {
        return parameters.getParameter(parameter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T getParameter(String parameter, Class<T> classOfT) {
        return parameters.getParameter(parameter, classOfT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setParameter(String parameter, Object value) {
        this.parameters.setParameter(parameter, value);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return GsonAdapter.getInstance().toJson(this);
    }
    
}
