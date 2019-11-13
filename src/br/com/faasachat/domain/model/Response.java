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
     * Instantiates a response.
     */
    public Response() {
        this.success = false;
    }

    /**
     * Instantiates a response from JSON data.
     * @param json
     */
    public Response(String json) {
        Response response = GsonAdapter.getInstance().fromJson(json, Response.class);
        this.setSuccess(response.getSuccess());
    }

    /**
     * Instantiates a response with error.
     * @param e
     */
    public Response(Exception e) {
        this.success = false;
    }

    /**
     * Returns success response.
     * @return
     */
    public boolean getSuccess() {
        return success;
    }

    /**
     * Defines success response.
     * @param success
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }
    
}
