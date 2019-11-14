package br.com.faasachat.domain.utils;

import java.util.HashMap;
import java.util.Map;

import br.com.faasachat.domain.adapter.GsonAdapter;

/**
 * Generic configuration class.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 13/11/2019
 * @version 1.0
 */
public class Configuration implements Parameter {
    
    /**
     * Map with all parameters.
     */
    private Map<String, Object> parameters;
    
    /**
     * Instantiates configuration.
     */
    public Configuration() {
        this.parameters = new HashMap<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Object> getParameters() {
        return parameters;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getParameter(String parameter) {
        return parameters.get(parameter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T getParameter(String parameter, Class<T> classOfT) {
        return GsonAdapter.getInstance().fromJson(GsonAdapter.getInstance().toJson(parameters.get(parameter)), classOfT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setParameter(String parameter, Object value) {
        this.parameters.put(parameter, value);
    }

}
