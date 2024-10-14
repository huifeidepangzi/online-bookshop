package com.yi.online_bookshop.api_response.suberrors;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ApiValidationError implements ApiSubError {

    private String field;
    private String message;
}
