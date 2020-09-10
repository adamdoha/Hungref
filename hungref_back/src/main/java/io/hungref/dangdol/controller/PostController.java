package io.hungref.dangdol.controller;


import io.hungref.dangdol.domain.cook.Cook;
import io.hungref.dangdol.dto.user.UserInfo;
import io.hungref.dangdol.service.PostService;
import io.hungref.dangdol.service.UserService;
import io.hungref.dangdol.service.JwtService;
import io.hungref.dangdol.Exceptions.user.UnauthorizedException;
import io.hungref.dangdol.dto.post.PostRequestDto;
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
public class PostController {
    private final CookieManager cm;
    private final JwtService jwtService;
    private final UserService userService;
    private final PostService postService;

    @ApiOperation("요리법 업로드")
    @PostMapping("/post/upload")
    public ResponseEntity upload(@RequestBody PostRequestDto postRequestDto, HttpServletRequest req, HttpServletResponse res) {
        log.info("요리 포스팅 업로드 요청......");
        Cookie cookie=cm.getCookie(req,"userCookie");

        String jwt=cookie.getValue();
        log.info("jwt={}",jwt);

        //유효성 검사
        if (!jwtService.isUsable(jwt)) throw new UnauthorizedException(); // 예외

        UserInfo userInfo=jwtService.getUser(jwt);

        log.info("post 업로드를 한 유저는 =>"+userInfo.getEmail()+", 닉네임은 :"+userInfo.getNickname()+" 님입니다.");

        postService.upload(userInfo,postRequestDto);

        String message="업로드가 완료되었습니다.";
        return ResponseEntity.ok(userInfo);
    }

}
