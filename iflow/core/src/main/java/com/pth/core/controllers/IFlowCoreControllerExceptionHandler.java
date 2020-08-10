package com.pth.core.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.pth.iflow.common.enums.EModule;
import com.pth.iflow.common.exceptions.EIFlowErrorType;
import com.pth.iflow.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.iflow.common.response.IFlowErrorRestResponse;
import com.pth.iflow.core.storage.dao.exception.IFlowOptimisticLockException;
import com.pth.iflow.core.storage.dao.exception.IFlowStorageException;

@ControllerAdvice
public class IFlowCoreControllerExceptionHandler {

  @ExceptionHandler(IFlowMessageConversionFailureException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  protected ResponseEntity<IFlowErrorRestResponse> handleConflict(final IFlowMessageConversionFailureException ex,
      final HttpServletRequest request) {
    // return new FBRestResponse(HttpStatus.CONFLICT, ex);
    final HttpHeaders headers = new HttpHeaders();
    headers.set("Content-Type", MediaType.APPLICATION_XML_VALUE + ";charset=UTF-8");
    return new ResponseEntity<>(
        new IFlowErrorRestResponse(HttpStatus.CONFLICT, ex, EModule.CORE.getModuleName(), EIFlowErrorType.MESSAGE_CONVERSION_FAILURE),
        headers, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(IFlowOptimisticLockException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  protected ResponseEntity<IFlowErrorRestResponse> handleConflict(final IFlowOptimisticLockException ex,
      final HttpServletRequest request) {
    // return new FBRestResponse(HttpStatus.CONFLICT, ex);
    final HttpHeaders headers = new HttpHeaders();
    headers.set("Content-Type", MediaType.APPLICATION_XML_VALUE + ";charset=UTF-8");
    return new ResponseEntity<>(
        new IFlowErrorRestResponse(HttpStatus.CONFLICT, ex, EModule.CORE.getModuleName(), EIFlowErrorType.OPTIMISTICLOCK_FAILURE),
        headers, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(IFlowStorageException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  protected ResponseEntity<IFlowErrorRestResponse> handleConflict(final IFlowStorageException ex, final HttpServletRequest request) {
    // return new FBRestResponse(HttpStatus.CONFLICT, ex);
    final HttpHeaders headers = new HttpHeaders();
    headers.set("Content-Type", MediaType.APPLICATION_XML_VALUE + ";charset=UTF-8");
    return new ResponseEntity<>(
        new IFlowErrorRestResponse(HttpStatus.CONFLICT, ex, EModule.CORE.getModuleName(), EIFlowErrorType.DAO_STORAGE_FAILURE),
        headers, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  protected ResponseEntity<IFlowErrorRestResponse> handleConflict(final Exception ex, final HttpServletRequest request) {
    // return new FBRestResponse(HttpStatus.CONFLICT, ex);
    final HttpHeaders headers = new HttpHeaders();
    headers.set("Content-Type", MediaType.APPLICATION_XML_VALUE + ";charset=UTF-8");
    return new ResponseEntity<>(
        new IFlowErrorRestResponse(HttpStatus.CONFLICT, ex, EModule.CORE.getModuleName(), EIFlowErrorType.RUNTIME_UNKNOWN), headers,
        HttpStatus.CONFLICT);
  }
}
