package com.jacr.gravityapp.model.api.manager;

import com.google.gson.Gson;
import com.jacr.gravityapp.model.api.ModelError;
import com.jacr.gravityapp.model.api.manager.http.CancelableCallback;
import com.jacr.gravityapp.model.api.manager.listener.ManagerListener;
import com.jacr.gravityapp.utilities.helpers.LogHelper;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Jesus Castro on 11/22/2015.
 */
public abstract class ApiManager {

    public Gson gson = new Gson();

    //<editor-fold desc="Abstract Methods">

    protected abstract Class<?> getLogTag();

    protected abstract void manageResponse(String url, byte[] response, ManagerListener listener);

    //</editor-fold>

    public Callback createHttpCallback(final String url, final ManagerListener listener) {
        return createHttpCallback("default_tag", url, listener);
    }

    public Callback createHttpCallback(String tag, final String url, final ManagerListener listener) {
        final Class<?> logTag = getLogTag();
        return new CancelableCallback(tag) {

            @Override
            public void onError(Request request, IOException e) {
                ModelError error;
                String exception = e.toString();
                if (exception.contains("ConnectException") || exception.contains("UnknownHostException")) {
                    error = ModelError.CONNECTIVITY_FAILURE;
                } else if (exception.contains("SocketTimeoutException")) {
                    error = ModelError.TIMEOUT_FAILURE;
                } else {
                    error = ModelError.WEBSERVICE_FAILURE;
                }
                LogHelper.getInstance().exception(logTag, e, exception);
                // Notifies the controller that there was an error
                listener.onError(error);
            }

            @Override
            public void onSuccess(Response response) throws IOException {
                String requestType = response.request().method();
                byte[] responseStream = response.body().bytes();
                if (response.isSuccessful()) { // status code: [200, 300)
                    // Delegate the concrete manager how it should manage this successful response
                    LogHelper.getInstance().debugResponse(logTag, url, requestType, response.code(), responseStream);
                    manageResponse(url, responseStream, listener);
                } else {
                    LogHelper.getInstance().errorResponse(logTag, url, requestType, response.code(), responseStream);
                    // Notifies the controller that there was an error
                    listener.onError(ModelError.WEBSERVICE_FAILURE);
                }
            }

        };
    }

}
