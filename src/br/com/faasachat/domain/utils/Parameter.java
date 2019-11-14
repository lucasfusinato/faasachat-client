package br.com.faasachat.domain.utils;

import java.util.Map;

/**
 * Interface to manipulate parameters map.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 13/11/2019
 * @version 1.0
 */
public interface Parameter {
    
    /**
     * Returns all parameters.
     * @return
     */
    public Map<String, Object> getParameters();
    
    /**
     * Defines all parameters.
     * @param parameters
     */
    public void setParameters(Map<String, Object> parameters);
    
    /**
     * Returns a specific parameter value.
     * @param parameter
     * @return
     */
    public Object getParameter(String parameter);
    
    /**
     * Returns a specific parameter value.
     * @param parameter
     * @param classOfT
     * @return
     */
    public <T> T getParameter(String parameter, Class<T> classOfT);
    
    /**
     * Defines a specific parameter value.
     * @param parameter
     * @param value
     */
    public void setParameter(String parameter, Object value);

}
