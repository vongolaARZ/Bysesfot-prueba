package com.bysesoft.bysesoft.common.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Value;


    @Value
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({"code", "detail", "field", "value", "location"})
    public class ErrorDetails {

        /**
         * The unique and fine-grained application-level error code.
         */
        @NotNull
        ErrorCode code;

        /**
         * The human-readable description for an issue. Provide non-standard more granular error message.
         */
        @NotNull String detail;

        /**
         * The field that caused the error. If the field is in the body, set this value to the JSON pointer to that field.
         * Example: "field": {"$ref": "#/body-field"}
         */
        String field;

        /**
         * The value of the field that caused the error.
         */
        Object value;

        /**
         * The location of the field that caused the error. Value is `BODY`, `PATH`, `QUERY` or `HEADER`.
         */
        ErrorLocation location;

    }
