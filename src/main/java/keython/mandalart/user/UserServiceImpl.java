package keython.mandalart.user;

import keython.mandalart.domain.User;
import keython.mandalart.exception.UserNotFoundException;
import keython.mandalart.user.dto.UserRequestDto;
import keython.mandalart.user.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDto findUserById(Long id) {
        log.info("method [findUserById] is called with id: {}", id);
        return userRepository.findUserById(id)
                .map(UserResponseDto::toDto)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    @Override
    public UserResponseDto findUserByEmail(String email) {
        log.info("method [findUserByEmail] is called with email: {}", email);
        return userRepository.findUserByEmail(email)
                .map(UserResponseDto::toDto)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
    }

    @Transactional
    @Override
    public Long saveUser(UserRequestDto dto){
        User user = UserRequestDto.toEntity(dto);

        return userRepository.save(user);
    }
}
