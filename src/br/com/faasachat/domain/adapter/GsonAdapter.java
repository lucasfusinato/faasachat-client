package br.com.faasachat.domain.adapter;

import com.google.gson.Gson;

/**
 * Adapter to google gson library.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 11/11/2019
 * @version 1.0
 */
public class GsonAdapter {

    /**
     * Unique self instance.
     */
    private static GsonAdapter instance;
    
    /**
     * Gson instance.
     */
    private Gson gson;
    
    /**
     * Returns unique self instance.
     * @return
     */
    public static synchronized GsonAdapter getInstance() {
        if(instance == null) {
            instance = new GsonAdapter();
        }
        return instance;
    }
    
    /**
     * Instantiates adapter.
     */
    private GsonAdapter() {
        this.gson = new Gson();
    }

    /**
     * Returns a object instance from json data.
     * @param json
     * @param classOfT
     * @return
     */
    public <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    /**
     * Converts a object data into string json.
     * @param src
     * @return
     */
    public String toJson(Object src) {
        return gson.toJson(src);
    }

}
