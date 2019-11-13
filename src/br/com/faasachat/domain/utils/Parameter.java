package br.com.faasachat.domain.utils;

import java.util.Map;

/**
 * Interface to manipulate parameters map.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 13/11/2019
 * @version 1.0
 */
public interface Parameter {
    
    public Map<String, Object> getParameters();
    
    public void setParameters(Map<String, Object> parameters);
    
    public Object getParameter(String parameter);
    
    public <T> T getParameter(String parameter, Class<T> classOfT);
    
    public void setParameter(String parameter, Object value);

}
