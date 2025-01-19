package keython.mandalart.user.dto;

import keython.mandalart.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;

    public UserResponseDto(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public static UserResponseDto toDto(User user) {
       return new UserResponseDto(user.getName(), user.getEmail());
    }
}
