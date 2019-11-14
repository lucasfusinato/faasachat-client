package br.com.faasachat.api.executor;

import br.com.faasachat.domain.model.Request;
import br.com.faasachat.domain.model.Session;

public class SessionRequestExecutor implements RequestExecutor {

    private RequestExecutor requestExecutor;
    private Session session;

    public SessionRequestExecutor(RequestExecutor requestExecutor, Session session) {
        this.requestExecutor = requestExecutor;
        this.session = session;
    }

    @Override
    public void execute(Request request, RequestExecutorCallback callback) {
        request.setParameter("token", session.getToken());
        requestExecutor.execute(request, callback);
    }

}
