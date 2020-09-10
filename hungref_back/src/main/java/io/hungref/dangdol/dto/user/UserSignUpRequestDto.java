package io.hungref.dangdol.dto.user;


import io.hungref.dangdol.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserSignUpRequestDto {
    private String email;
    private String password;
    private String nickname;
    private String phone;

    @Builder
    public UserSignUpRequestDto(String email,String password, String nickname, String phone){
        this.email=email;
        this.password=password;
        this.nickname=nickname;
        this.phone=phone;
    }

    @Override
    public String toString() {
        return "UserSignUpRequestDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public User toEntity(){
        return User.builder()
                .password(password)
                .nickname(nickname)
                .phone(phone)
                .email(email)
                .build();
    }
}