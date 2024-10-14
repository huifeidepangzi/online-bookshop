package com.yi.online_bookshop.api_response;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.yi.online_bookshop.api_response.suberrors.ApiSubError;

import lombok.Getter;

@Getter
public class GeneralApiResponse {

    private HttpStatus status;
    private String message;
    private Map<String, String> extraData;
    private List<ApiSubError> subErrors;

    public GeneralApiResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public GeneralApiResponse(HttpStatus status, String message, Map<String, String> extraData) {
        this.status = status;
        this.message = message;
        this.extraData = extraData;
    }

    public GeneralApiResponse(HttpStatus status, String message, List<ApiSubError> subErrors) {
        this.status = status;
        this.message = message;
        this.subErrors = subErrors;
    }
}
