package com.example.exception;

public class CommonApiException extends RuntimeException {

  public String errorCode;

  public CommonApiException(String msg) {
    super(msg);
  }

  public CommonApiException(String msg, String errorCode) {
    super(msg);
    this.errorCode = errorCode;
  }

  public String getErrorCode() {
    return this.errorCode;
  }
}