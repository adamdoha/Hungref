package io.hungref.dangdol.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserInfo {
    String email;
    String nickname;

    @Builder
    public UserInfo(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }
}
