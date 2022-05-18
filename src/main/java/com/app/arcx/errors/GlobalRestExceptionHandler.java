package com.app.arcx.errors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.rest.core.RepositoryConstraintViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mediatype.vnderrors.VndErrors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@Slf4j
@SuppressWarnings("unused")
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@RequestMapping(produces = "application/vnd.error+json")
public class GlobalRestExceptionHandler extends ResponseEntityExceptionHandler {


/*    @ExceptionHandler({MissingSubstanceValueException.class})
    ResponseEntity<VndErrors> compoundNotFoundExceptionHandler(MissingSubstanceValueException ex, WebRequest com.app.arcx.request){
        log.info("Exception called {} on path {}", ex.getClass().getName(), com.app.arcx.request.getContextPath());

        String msg = ex.getMessage();

        VndErrors vndErrors = error(ex.getClass().getSimpleName(), msg, com.app.arcx.request);

        return new ResponseEntity<VndErrors>(vndErrors, HttpStatus.NOT_FOUND);
    }*/

    // for this exception to be called make sure spring.resources.add-mapping=false in application properties
    // if I do false then I won't be able to show any static contents
    @ExceptionHandler({ResourceNotFoundException.class})
    ResponseEntity<VndErrors> notFoundIdHandler(ResourceNotFoundException ex, WebRequest request) {
        log.info("Exception called {} on path {}", ex.getClass().getName(), request.getContextPath());

        // defined error message is confusing
        // String msg = ex.getMessage();
        String msg = "Requested resource not found.";

        VndErrors vndErrors = error(ex.getClass().getSimpleName(), msg, request);

        return new ResponseEntity<VndErrors>(vndErrors, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(RepositoryConstraintViolationException.class)
    protected ResponseEntity<VndErrors> handleConstraintViolation(RepositoryConstraintViolationException ex, WebRequest request) {
        log.info("Exception called {}, Global Errors={}, Field Errors = {} ", ex.getClass().getName(), ex.getErrors().getGlobalErrorCount(), ex.getErrors().getFieldErrorCount());

        String errorMsgs = ex.getErrors().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(";"));

        VndErrors vndErrors = error(ex.getClass().getSimpleName(), errorMsgs, request);

        return new ResponseEntity<VndErrors>(vndErrors, HttpStatus.BAD_REQUEST);
    }

/*    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    ResponseEntity<VndErrors>  httpMediaTypeNoSupportedHandler (HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest com.app.arcx.request){
        log.info("Exception called {} ", ex.getClass().getName());

        VndErrors vndErrors = error(ex.getClass().getSimpleName(), ex.getMessage());

        return new ResponseEntity<>(vndErrors,status);
    }*/

/*    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest com.app.arcx.request) {
        log.info("Exception called {} ", ex.getClass().getName());

        VndErrors vndErrors = error(ex.getClass().getSimpleName(), ex.getMessage());

        return new ResponseEntity<>(vndErrors,status);
    }*/


    // Following are override methods for different error situations.
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info("Exception called {} ", ex.getClass().getName());

        VndErrors vndErrors = error(ex.getClass().getSimpleName(), ex.getMessage(), request);

        return new ResponseEntity<>(vndErrors, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info("Exception called {} ", ex.getClass().getName());

        VndErrors vndErrors = error(ex.getClass().getSimpleName(), ex.getMessage(), request);

        return new ResponseEntity<>(vndErrors, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info("Exception called {} ", ex.getClass().getName());

        VndErrors vndErrors = error(ex.getClass().getSimpleName(), ex.getMessage(), request);

        return new ResponseEntity<>(vndErrors, status);

    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info("Exception called {} ", ex.getClass().getName());

        VndErrors vndErrors = error(ex.getClass().getSimpleName(), ex.getMessage(), request);

        return new ResponseEntity<>(vndErrors, status);

    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info("Exception called {} ", ex.getClass().getName());

        VndErrors vndErrors = error(ex.getClass().getSimpleName(), ex.getMessage(), request);

        return new ResponseEntity<>(vndErrors, status);

    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info("Exception called {} ", ex.getClass().getName());

        VndErrors vndErrors = error(ex.getClass().getSimpleName(), ex.getMessage(), request);

        return new ResponseEntity<>(vndErrors, status);

    }

    @Override
    protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info("Exception called {} ", ex.getClass().getName());

        VndErrors vndErrors = error(ex.getClass().getSimpleName(), ex.getMessage(), request);

        return new ResponseEntity<>(vndErrors, status);

    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info("Exception called {} ", ex.getClass().getName());

        VndErrors vndErrors = error(ex.getClass().getSimpleName(), ex.getMessage(), request);

        return new ResponseEntity<>(vndErrors, status);

    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info("Exception called {} ", ex.getClass().getName());

        VndErrors vndErrors = error(ex.getClass().getSimpleName(), ex.getMessage(), request);

        return new ResponseEntity<>(vndErrors, status);

    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info("Exception called {} ", ex.getClass().getName());

        VndErrors vndErrors = error(ex.getClass().getSimpleName(), ex.getMessage(), request);

        return new ResponseEntity<>(vndErrors, status);

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info("Exception called {} ", ex.getClass().getName());

        VndErrors vndErrors = error(ex.getClass().getSimpleName(), ex.getMessage(), request);

        return new ResponseEntity<>(vndErrors, status);

    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info("Exception called {} ", ex.getClass().getName());

        VndErrors vndErrors = error(ex.getClass().getSimpleName(), ex.getMessage(), request);

        return new ResponseEntity<>(vndErrors, status);

    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info("Exception called {} ", ex.getClass().getName());

        VndErrors vndErrors = error(ex.getClass().getSimpleName(), ex.getMessage(), request);

        return new ResponseEntity<>(vndErrors, status);

    }

    @Override
    protected ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
        log.info("Exception called {} ", ex.getClass().getName());

        VndErrors vndErrors = error(ex.getClass().getSimpleName(), ex.getMessage(), webRequest);

        return new ResponseEntity<>(vndErrors, status);

    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info("Exception called {} ", ex.getClass().getName());

        VndErrors vndErrors = error(ex.getClass().getSimpleName(), ex.getMessage(), request);

        return new ResponseEntity<>(vndErrors, status);

    }

    // Here is link for vnd.error specification (https://github.com/blongden/vnd.error)
    private VndErrors error(String logRef, String message, WebRequest request) {

        return new VndErrors(logRef, message, getAboutLink(request), getHelpLink());
    }


    private Link getHelpLink() {
        String helpUrl = "https://www.google.com";

        return new Link(helpUrl).withRel(IanaLinkRelations.HELP);
    }

    private Link getAboutLink(WebRequest request) {
        String currentUrl = ((ServletWebRequest) request).getRequest().getRequestURL().toString();

        log.info("About link = ", ((ServletWebRequest) request).getRequest().getRequestURL().toString());

        return new Link(currentUrl).withRel(IanaLinkRelations.ABOUT);
    }
}

