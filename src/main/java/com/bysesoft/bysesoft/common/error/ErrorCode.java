package com.bysesoft.bysesoft.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INVALID_FIELD_VALUE("The provided field is not valid"),
    RESOURCE_NOT_FOUND("The requested resource was not found"),
    BAD_REQUEST("The server cannot return a response due to an error on the client's end"),
    ILLEGAL_ARGUMENT("Has been passed an illegal or inappropriate argument"),
    HTTP_CLIENT_ERROR("The request failed because a 4xx error was received"),
    METHOD_NOT_CURRENTLY_ALLOWED("The requested method is not allowed in this api version"),
    MISSING_PATH_VARIABLE("The URI template may not match the path variable name declared on the method parameter"),
    MISSING_REQUEST_PARAMETERS("The request body may be missing mandatory parameters"),
    MISSING_REQUEST_PART("The request is not a multipart/form-data request"),
    NOT_HANDLER_FOUND("The server could not found a handler for the request"),
    TYPE_MISMATCH("A type mismatch occurred trying to set a property"),
    PARAMS_REQUIRED("The request body may be missing mandatory parameters"),
    RESOURCE_ALREADY_EXISTS("This resource already exists");

    private final String defaultMessage;

}
