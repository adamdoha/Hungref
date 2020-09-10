package io.hungref.dangdol.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserJwtResponseDto {
    private String jwt;
    private String nickname;

    @Builder
    public UserJwtResponseDto(String jwt,String nickname) {
        this.jwt=jwt;
        this.nickname = nickname;
    }
}
