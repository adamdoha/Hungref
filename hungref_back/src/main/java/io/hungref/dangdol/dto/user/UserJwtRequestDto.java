package io.hungref.dangdol.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserJwtRequestDto {
    private String email;
    private String password;

    @Builder
    public UserJwtRequestDto(String email, String password){
        this.email=email;
        this.password=password;
    }

    @Override
    public String toString() {
        return "UserJwtRequestDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
