package com.openai.common.api.model;

import org.springframework.http.HttpStatusCode;

import java.util.List;

public class ApiErrorMessage {

    private HttpStatusCode status;

    private Integer code;
    private List<String> errors;

    public ApiErrorMessage(HttpStatusCode status, Integer code, List<String> errors) {
        super();
        this.status = status;
        this.errors = errors;
        this.code = code;
    }

    public HttpStatusCode getStatus() {
        return status;
    }

    public void setStatus(HttpStatusCode status) {
        this.status = status;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}