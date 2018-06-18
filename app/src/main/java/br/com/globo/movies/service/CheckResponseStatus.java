package br.com.globo.movies.service;

import android.util.Log;

import rx.Subscriber;

public class CheckResponseStatus implements rx.Observable.Operator<ResponseObject, ResponseObject> {
    private static final String TAG = "CheckResponseStatus";

    @Override
    public Subscriber<? super ResponseObject> call(Subscriber<? super ResponseObject> subscriber) {
        return new Subscriber<ResponseObject>() {
            private boolean hasError = false;

            @Override
            public void onCompleted() {
                if (!hasError)
                    subscriber.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                hasError = true;
                if(subscriber!=null) {
                    Log.e(TAG, "CheckResponseStatus error - " + e.getMessage());
                    Log.e(TAG, "CheckResponseStatus error - ", e);
                    subscriber.onError(e);
                }
            }

            @Override
            public void onNext(ResponseObject responseObject) {
                if(responseObject.getResponse().getHttpResponseStatus().getSuccess()){
                    subscriber.onNext(responseObject);
                }else{
                    hasError = true;
                    Log.e(TAG, "CheckResponseStatus error - " + responseObject.getResponse().getHttpResponseStatus().getStatus());
                }
            }

        };
    }
}