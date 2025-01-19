package keython.mandalart.user;

import keython.mandalart.domain.User;

import java.util.Optional;

public interface UserRepository {

    // ID로 사용자 찾기
    Optional<User> findUserById(Long id);

    // 이메일로 사용자 찾기
    Optional<User> findUserByEmail(String email);

    // 사용자 저장
    Long save(User user);
}
