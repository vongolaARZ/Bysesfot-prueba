package com.bysesoft.bysesoft.common.handler;

import com.bysesoft.bysesoft.common.error.ErrorCode;
import com.bysesoft.bysesoft.common.error.ErrorDetails;
import com.bysesoft.bysesoft.common.error.ErrorLocation;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.ClientAbortException;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.*;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.ServletException;
import javax.validation.ConstraintViolationException;
import java.net.BindException;
import java.net.ProtocolException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public abstract sealed class AbstractExceptionHandler permits GlobalExceptionHandler {

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class,
            MissingPathVariableException.class,
            MissingServletRequestParameterException.class,
            ServletRequestBindingException.class,
            ConversionNotSupportedException.class,
            TypeMismatchException.class,
            MissingServletRequestPartException.class,
            BindException.class,
            ConstraintViolationException.class,
            NoHandlerFoundException.class,
            AsyncRequestTimeoutException.class,
            ProtocolException.class,
            ResourceAccessException.class,
            ServletException.class,
            ClientAbortException.class,
            IllegalArgumentException.class,
            HttpClientErrorException.class})
    @Nullable
    public final ResponseEntity<Object> handleException(Throwable throwable, WebRequest request) throws Throwable {
        log.error("Response Entity Exception due " + throwable, throwable);
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status;
        if (throwable instanceof HttpRequestMethodNotSupportedException e) {
            status = HttpStatus.METHOD_NOT_ALLOWED;
            return this.handleHttpRequestMethodNotSupported(e, headers, status, request);
        } else if (throwable instanceof MissingPathVariableException e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            return this.handleMissingPathVariable(e, headers, status, request);
        } else if (throwable instanceof MissingServletRequestParameterException e) {
            status = HttpStatus.BAD_REQUEST;
            return this.handleMissingServletRequestParameter(e, headers, status, request);
        } else if (throwable instanceof TypeMismatchException e) {
            status = HttpStatus.BAD_REQUEST;
            return this.handleTypeMismatch(e, headers, status, request);
        } else if (throwable instanceof IllegalArgumentException e) {
            status = HttpStatus.BAD_REQUEST;
            return this.handleIllegalArgumentException(e, headers, status, request);
        } else if (throwable instanceof HttpClientErrorException e) {
            return this.handleHttpClientErrorException(e, headers, request);
        } else if (throwable instanceof MissingServletRequestPartException e) {
            status = HttpStatus.BAD_REQUEST;
            return this.handleMissingServletRequestPart(e, headers, status, request);
        } else if (throwable instanceof NoHandlerFoundException e) {
            status = HttpStatus.NOT_FOUND;
            return this.handleNoHandlerFoundException(e, headers, status, request);
        } else if (throwable instanceof ConstraintViolationException e) {
            return this.handleConstraintViolationException(e, headers);
        } else {
            throw throwable;
        }
    }

    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (log.isDebugEnabled())
            log.debug("handling HttpRequestMethodNotSupported with status={}", status);
        Set<HttpMethod> supportedMethods = ex.getSupportedHttpMethods();
        if (!CollectionUtils.isEmpty(supportedMethods)) {
            headers.setAllow(supportedMethods);
        }

        final String message = "%s. Unable to find method [%s], supported methods [%s]."
                .formatted(ErrorCode.METHOD_NOT_CURRENTLY_ALLOWED.getDefaultMessage(), ex.getMethod(), ex.getSupportedHttpMethods());
        return this.handleExceptionInternal(ex, headers, status, request, ErrorCode.METHOD_NOT_CURRENTLY_ALLOWED, message);
    }

    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (log.isDebugEnabled())
            log.debug("handling eMissingPathVariable with status={}", status);

        final String message = ErrorCode.MISSING_PATH_VARIABLE.getDefaultMessage() + " Missing variable [" + ex.getVariableName() + "].";
        return this.handleExceptionInternal(ex, headers, status, request, ErrorCode.MISSING_PATH_VARIABLE, message);
    }

    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (log.isDebugEnabled())
            log.debug("handling MissingServletRequestParameter with status={}", status);

        final String message = "%s Missing parameter [%s] with expected type [%s]."
                .formatted(ErrorCode.MISSING_REQUEST_PARAMETERS.getDefaultMessage(), ex.getParameterName(), ex.getParameterType());
        return this.handleExceptionInternal(ex, headers, status, request, ErrorCode.MISSING_REQUEST_PARAMETERS, message);
    }
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (log.isDebugEnabled())
            log.debug("handling handleTypeMismatch with status={}", status);

        final String message = "%s The offending value [%s] couldn't be converted."
                .formatted(ErrorCode.TYPE_MISMATCH.getDefaultMessage(), ex.getValue());
        return this.handleExceptionInternal(ex, headers, status, request, ErrorCode.TYPE_MISMATCH, message);
    }




    protected ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (log.isDebugEnabled())
            log.debug("handling IllegalArgument with status={}", status);

        return this.handleExceptionInternal(ex, headers, status, request, ErrorCode.ILLEGAL_ARGUMENT, null);
    }

    protected ResponseEntity<Object> handleHttpClientErrorException(HttpClientErrorException ex, HttpHeaders headers, WebRequest request) {
        if (log.isDebugEnabled())
            log.debug("handling HttpClientError with status={}", ex.getStatusCode());

        final String message = "%s Retrieved status [%s] with response [%s]."
                .formatted(ErrorCode.HTTP_CLIENT_ERROR.getDefaultMessage(), ex.getStatusText(), ex.getResponseBodyAsString());
        return this.handleExceptionInternal(ex, headers, ex.getStatusCode(), request, ErrorCode.HTTP_CLIENT_ERROR, message);
    }

    protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (log.isDebugEnabled())
            log.debug("handling MissingServletRequestPart with status={}", status);

        final String message = "%s The part [%s] of the multipart is missing."
                .formatted(ErrorCode.HTTP_CLIENT_ERROR.getDefaultMessage(), ex.getRequestPartName());
        return this.handleExceptionInternal(ex, headers, status, request, ErrorCode.MISSING_REQUEST_PART, message);
    }

    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (log.isDebugEnabled())
            log.debug("handling NoHandlerFound with status={}", status);

        final String message = "%s Unable to handle [%s %s]."
                .formatted(ErrorCode.NOT_HANDLER_FOUND.getDefaultMessage(), ex.getHttpMethod(), ex.getRequestURL());
        return this.handleExceptionInternal(ex, headers, status, request, ErrorCode.NOT_HANDLER_FOUND, message);
    }


    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, HttpHeaders headers, HttpStatus status, WebRequest request,
                                                             ErrorCode code, @Nullable String customMessage) {

        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute("javax.servlet.error.exception", ex, 0);
        }

        final String description = customMessage != null ? customMessage : code.getDefaultMessage();

        List<ErrorDetails> errors = List.of(ErrorDetails.builder().code(code).detail(description).build());

        return ResponseEntity.status(status).headers(headers).body(errors);
    }

    protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, HttpHeaders headers) {
        List<ErrorDetails> errors = ex.getConstraintViolations().stream()
                .map(constraintViolation -> ErrorDetails.builder()
                        .code(ErrorCode.INVALID_FIELD_VALUE)
                        .detail(constraintViolation.getMessage())
                        .field(constraintViolation.getPropertyPath().toString())
                        .location(ErrorLocation.BODY)
                        .build()
                ).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(headers).body(errors);
    }
}
