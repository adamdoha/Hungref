package io.hungref.dangdol.Exceptions;

import lombok.Getter;

@Getter
public enum ErrorCode {
    COOKIES_NOT_FOUND_EXCEPTION(400,"C001","쿠키 목록이 존재하지 않습니다."),

    EMAIL_NEED_EXCEPTION(400,"E001","이메일이 필요합니다."),
    EMAIL_DUPLICATE_EXCEPTION(400,"E002","이메일이 이미 존재합니다."),

    DUPLICATE_INGREDIENT_EXCEPTION(400,"I001","이미 재료가 존재합니다."),

    JWT_UNAUTHORIZED_EXCEPTION(400,"J001","인증되지 않은 값입니다."),

    CANNOT_LOGIN_EXCEPTION(400,"L001","이메일이나 비밀번호가 잘못되었습니다."),

    BAD_REQUEST_EXCEPTION(400,"R001","잘못된 요청입니다."),

    USER_NOT_EXIST_EXCEPTION(400,"U001","유저가 존재하지 않습니다."),
    USER_COOKIE_NOT_FOUND_EXCEPTION(400,"U002","유저 토큰이 확인되지 않습니다."),

            ;

    private final int status;
    private final String code;
    private final String message;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}