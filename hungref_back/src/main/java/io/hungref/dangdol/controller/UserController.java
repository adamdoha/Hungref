package io.hungref.dangdol.controller;

import io.hungref.dangdol.dto.user.UserJwtResponseDto;
import io.hungref.dangdol.service.UserService;
import io.hungref.dangdol.dto.user.UserJwtRequestDto;
import io.hungref.dangdol.dto.user.UserSignUpRequestDto;
import io.hungref.dangdol.util.CookieManager;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;
    private final CookieManager cm;

    @ApiOperation("회원 가입")
    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody UserSignUpRequestDto userSignUpRequestDto) {
        log.info("회원가입 요청이 들어왔습니다..=>{}",userSignUpRequestDto);
        userService.signUp(userSignUpRequestDto);
        String message="회원 가입이 완료되었습니다.";
        return ResponseEntity.ok(message);
    }

    @ApiOperation("로그인하면서 토큰을 발행")
    @PostMapping("/signin")
    public ResponseEntity signIn(@RequestBody UserJwtRequestDto userJwtRequestDto, HttpServletResponse res) {
        log.info("로그인 요청이 들어왔습니다.. =>"+ userJwtRequestDto.toString());
        UserJwtResponseDto userJwtResponseDto=userService.signIn(userJwtRequestDto);
        cm.setCookie(res,userJwtResponseDto.getJwt());
        return ResponseEntity.ok(userJwtResponseDto);
    }

    @ApiOperation("로그아웃하면서 쿠키삭제")
    @DeleteMapping("/signout")
    public ResponseEntity signout(HttpServletRequest req,HttpServletResponse res) {
        log.info("로그아웃 요청이 들어왔습니다.. ");
        Cookie cookie=cm.checkCookie(req,"userCookie");
        cm.deleteCookie(res,cookie);
        String message="쿠키 삭제 완료.";
        return ResponseEntity.ok(message);
    }

}