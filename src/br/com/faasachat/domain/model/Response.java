package br.com.faasachat.domain.model;

import br.com.faasachat.domain.adapter.GsonAdapter;

/**
 * Model that represents resposne data.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 12/11/2019
 * @version 1.0
 */
public class Response {
    
    /**
     * Defines response as successful.
     */
    private boolean success;
    
    /**
     * Response data.
     */
    private Object data;
    
    /**
     * Instantiates a response.
     */
    public Response() {
        this.setSuccess(false);
        this.setData(null);
    }

    /**
     * Instantiates a response from JSON data.
     * @param json
     */
    public Response(String json) {
        Response response = GsonAdapter.getInstance().fromJson(json, Response.class);
        this.setSuccess(response.getSuccess());
        this.setData(response.getData());
    }

    /**
     * Instantiates a response with error.
     * @param e
     */
    public Response(Exception e) {
        this.setSuccess(false);
        this.setData(e);
    }

    /**
     * Returns success response.
     * @return
     */
    public boolean getSuccess() {
        return success;
    }

    /**
     * Returns success response.
     * @return
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Defines success response.
     * @param success
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * Returns resposne data.
     * @return
     */
    public Object getData() {
        return data;
    }
    
    /**
     * Returns response data.
     * @param classOfT
     * @return
     */
    public <T> T getData(Class<T> classOfT) {
        return GsonAdapter.getInstance().fromJson(GsonAdapter.getInstance().toJson(data), classOfT);
    }
    
    /**
     * Defines resposne data.
     * @param data
     * @param data
     */
    public void setData(Object data) {
        this.data = data;
    }

}
