package br.com.faasachat.api.executor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import br.com.faasachat.domain.model.Request;
import br.com.faasachat.domain.model.Response;

/**
 * Socket request executor.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 12/11/2019
 * @version 1.0
 */
public class SocketRequestExecutor implements RequestExecutor {

    /**
     * Host to connect socket.
     */
    private final String host;
    
    /**
     * Port to connect socket.
     */
    private final int port;
    
    /**
     * Instantiates socket request executor.
     * @param host
     * @param port
     */
    public SocketRequestExecutor(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(Request request, RequestExecutorCallback callback) {
        Socket socket = null;
        Response response;
        try {
            socket                = new Socket(host, port);
            PrintWriter writer    = new PrintWriter(socket.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String responseData   = null;
            
            writer.println(request);
            writer.flush();
            
            while(true) {
                responseData = reader.readLine();
                if(!responseData.isEmpty()) {
                    break;
                }
            }
            response = new Response(responseData);
        } catch(Exception e) {
            response = new Response(e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                //Does nothing
            }
        }
        callback.call(response);
    }

}
