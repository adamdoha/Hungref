package io.hungref.dangdol.util;

import io.hungref.dangdol.Exceptions.user.CookiesNotFoundException;
import io.hungref.dangdol.Exceptions.user.UnauthorizedException;
import io.hungref.dangdol.Exceptions.user.UserCookieNotFoundException;
import io.hungref.dangdol.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequiredArgsConstructor
@Component
public class CookieManager {
    private final JwtService jwtService;

    public void setCookie(HttpServletResponse res,String jwt){
        Cookie cookie = new Cookie("userCookie", jwt); // 쿠키 이름 지정하여 생성( key, value 개념)
        cookie.setMaxAge(60*60*24); //쿠키 유효 기간: 하루로 설정(60초 * 60분 * 24시간)
        cookie.setPath("/"); //모든 경로에서 접근 가능하도록 설정
        res.addCookie(cookie); //response에 Cookie 추가
    }

    public Cookie getCookie(HttpServletRequest req,String cookieId){
        Cookie[] cookies=req.getCookies(); // 모든 쿠키 가져오기
        if(cookies!=null){
            for (Cookie c : cookies) {
                String name = c.getName(); // 쿠키 이름 가져오기
                String value = c.getValue(); // 쿠키 값 가져오기
                if (name.equals(cookieId)) {
                    return c;
                }
            }
        }else throw new CookiesNotFoundException();

        throw new UserCookieNotFoundException();
    }

    public void deleteCookie(HttpServletResponse res,Cookie cookie){
        cookie.setValue(null);
        cookie.setMaxAge(0); // 유효시간을 0으로 설정해서 바로 만료시킨다.
        res.addCookie(cookie); // 응답에 추가해서 없어지도록 함
    }


    public void deleteAllCookies(HttpServletRequest req,HttpServletResponse res) {
        Cookie[] cookies = req.getCookies(); // 모든 쿠키의 정보를 cookies에 저장
        if (cookies != null) { // 쿠키가 한개라도 있으면 실행
            for (int i = 0; i < cookies.length; i++) {
                cookies[i].setMaxAge(0); // 유효시간을 0으로 설정
                res.addCookie(cookies[i]); // 응답에 추가하여 만료시키기.
            }
        }
    }

    /**
     * 쿠키 존재 여부 & 쿠키 유효성 검사
     * @param req
     * @param cookieId
     * @return cookie
     */
    public Cookie checkCookie(HttpServletRequest req,String cookieId){
        Cookie cookie=getCookie(req,cookieId); //전처리는 getCookie에서 하므로 반드시 값이 있을거라고 가정함.
        if (!jwtService.isUsable(cookie.getValue())) throw new UnauthorizedException(); // 예외
        return cookie;
    }

}
