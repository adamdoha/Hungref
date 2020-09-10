package io.hungref.dangdol.service;

import io.hungref.dangdol.Exceptions.user.CanNotLoginException;
import io.hungref.dangdol.Exceptions.user.EmailDuplicateException;
import io.hungref.dangdol.Exceptions.user.UserNotExistException;
import io.hungref.dangdol.domain.user.User;
import io.hungref.dangdol.dto.user.UserInfo;
import io.hungref.dangdol.dto.user.UserJwtRequestDto;
import io.hungref.dangdol.dto.user.UserJwtResponseDto;
import io.hungref.dangdol.dto.user.UserSignUpRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class UserService {
    private final DBService db;
    private final JwtService jwtService;
    @Autowired
    JavaMailSender javaMailSender;

    // 회원 가입
    public void signUp(UserSignUpRequestDto userSignUpRequestDto) {
        String email=userSignUpRequestDto.getEmail();
        User user=db.findUserByEmail(email);

        if(user!=null) throw new EmailDuplicateException();

        String secPass = encrypt(userSignUpRequestDto.getPassword());
        userSignUpRequestDto.setPassword(secPass);

        MailService mailService = new MailService();
        mailService.setJavaMailSender(javaMailSender);
        mailService.sendSimpleMessage(userSignUpRequestDto.getEmail(), "[Hungref] 회원가입 완료!!", "안녕하세요. 환영합니다. DangDol Duo.");

        db.signUp(userSignUpRequestDto.toEntity());
    }

    //비밀번호 암호화
    public String encrypt(String rawpass) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(rawpass.getBytes());
            byte byteData[] = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                String hex = Integer.toHexString(0xff & byteData[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    //로그인
    public UserJwtResponseDto signIn(UserJwtRequestDto userJwtRequestDto){
        User user = db.findUserByEmail(userJwtRequestDto.getEmail());

        if (user == null) throw new UserNotExistException();

        log.info("현재 로그인하려는 user는 ==>"+userJwtRequestDto.getEmail());
        log.info("입력하신 비밀번호는 ==>"+userJwtRequestDto.getPassword());
        String cryptPassword=encrypt(userJwtRequestDto.getPassword());
        log.info("변환된 암호화 비밀번호는 ==>"+cryptPassword);

        log.info("DB상에 저장된 비밀번호는 ==>"+user.getPassword());

        if (user.getPassword().equals(cryptPassword)) {
            UserInfo userInfo= UserInfo.builder()
                    .email(user.getEmail())
                    .nickname(user.getNickname())
                    .build();

            String jwt = jwtService.create(userInfo);



            return UserJwtResponseDto.builder()
                    .nickname(user.getNickname())
                    .jwt(jwt)
                    .build();
        } else throw new CanNotLoginException();
    }

    public User findByEmail(String email){
        return db.findUserByEmail(email);
    }


    // 비밀번호 생성 메소드
    public String generatePass(int length) {
        StringBuffer sb = new StringBuffer();
        char[] charSet = new char[]{
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
                , 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M'
                , 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
                , 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm'
                , 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
        };
        for (int i = 0; i < length; i++) {
            int index = (int) (charSet.length * Math.random());
            sb.append(charSet[index]);
        }

        return sb.toString();
    }

}