package keython.mandalart.user;

import keython.mandalart.domain.User;
import keython.mandalart.user.dto.UserRequestDto;
import keython.mandalart.user.dto.UserResponseDto;

public interface UserService {

    UserResponseDto findUserById(Long id);
    UserResponseDto findUserByEmail(String email);
    Long saveUser(UserRequestDto dto);
}
