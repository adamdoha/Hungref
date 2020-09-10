package io.hungref.dangdol.Exceptions;

import io.hungref.dangdol.Exceptions.ingredient.DuplicateIngredientException;
import io.hungref.dangdol.Exceptions.user.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailNeedException.class)
    protected ResponseEntity<ErrorResponse> handleException(EmailNeedException e) {
        log.error("EmailNeedException", e);
        final ErrorResponse errorResponse = new ErrorResponse(ErrorCode.EMAIL_NEED_EXCEPTION);
        return new ResponseEntity<>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(EmailDuplicateException.class)
    protected ResponseEntity<ErrorResponse> handleException(EmailDuplicateException e) {
        log.error("EmailDuplicateException", e);
        final ErrorResponse errorResponse = new ErrorResponse(ErrorCode.EMAIL_DUPLICATE_EXCEPTION);
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotExistException.class)
    protected ResponseEntity<ErrorResponse> handleException(UserNotExistException e) {
        log.error("UserNotExistException", e);
        final ErrorResponse errorResponse = new ErrorResponse(ErrorCode.USER_NOT_EXIST_EXCEPTION);
        return new ResponseEntity<>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(CanNotLoginException.class)
    protected ResponseEntity<ErrorResponse> handleException(CanNotLoginException e) {
        log.error("CanNotLoginException", e);
        final ErrorResponse errorResponse = new ErrorResponse(ErrorCode.CANNOT_LOGIN_EXCEPTION);
        return new ResponseEntity<>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);
    }


    @ExceptionHandler(UnauthorizedException.class)
    protected ResponseEntity<ErrorResponse> handleException(UnauthorizedException e) {
        log.error("UnauthorizedException", e);
        final ErrorResponse errorResponse = new ErrorResponse(ErrorCode.JWT_UNAUTHORIZED_EXCEPTION);
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(DuplicateIngredientException.class)
    protected ResponseEntity<ErrorResponse> handleException(DuplicateIngredientException e) {
        log.error("DuplicateIngredientException", e);
        final ErrorResponse errorResponse = new ErrorResponse(ErrorCode.DUPLICATE_INGREDIENT_EXCEPTION);
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CookiesNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleException(CookiesNotFoundException e) {
        log.error("CookiesNotFoundException", e);
        final ErrorResponse errorResponse = new ErrorResponse(ErrorCode.COOKIES_NOT_FOUND_EXCEPTION);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserCookieNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleException(UserCookieNotFoundException e) {
        log.error("UserCookieNotFoundException", e);
        final ErrorResponse errorResponse = new ErrorResponse(ErrorCode.USER_COOKIE_NOT_FOUND_EXCEPTION);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}