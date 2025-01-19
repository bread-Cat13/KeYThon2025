package keython.mandalart.user.dto;

import keython.mandalart.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRequestDto {
    private String name;
    private String email;

    public static User toEntity(UserRequestDto dto) {
        return new User(dto.name, dto.email);
    }
}
