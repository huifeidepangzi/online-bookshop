package com.yi.online_bookshop.api_response.suberrors;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiValidationError implements ApiSubError {

    private String field;
    private String message;
}
