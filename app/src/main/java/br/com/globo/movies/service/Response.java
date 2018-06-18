package br.com.globo.movies.service;

public class Response {
    private HttpResponseStatus details;
    private ResultMap result;

    public HttpResponseStatus getHttpResponseStatus() {
        return details;
    }

    public void setHttpResponseStatus(HttpResponseStatus status) {
        this.details = details;
    }

    public ResultMap getResult() {
        return result;
    }

    public void setResult(ResultMap result) {
        this.result = result;
    }
}
