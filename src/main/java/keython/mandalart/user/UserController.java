package keython.mandalart.user;

import io.swagger.v3.oas.annotations.Operation;
import keython.mandalart.generalDto.GeneralPostResponseDto;
import keython.mandalart.user.dto.UserRequestDto;
import keython.mandalart.user.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    //[User 정보 관련]

    // User 개인 정보 조회 (Mandal 없음)
    // /api/user?key={}
    @GetMapping
    @Operation(summary = "user 조회", description = "/api/user?key={userId or email}")
    public ResponseEntity<UserResponseDto> findUser(@RequestParam("key") String key) {
        log.info("Received request to find user with key: {}", key);

        UserResponseDto findUserDto;
        try {
            if (isNumeric(key)) {
                log.info("Key identified as ID: {}", key);
                findUserDto = userService.findUserById(Long.parseLong(key));
            } else {
                log.info("Key identified as Email: {}", key);
                findUserDto = userService.findUserByEmail(key);
            }
        } catch (Exception e) {
            log.error("Error finding user with key: {}", key, e);
            return ResponseEntity.notFound().build();
        }

        log.info("User found: {}", findUserDto);
        return ResponseEntity.ok(findUserDto);
    }

    // User 생성
    //api/user
    @PostMapping
    @Operation(summary = "user 생성", description = "/api/user")
    public ResponseEntity<GeneralPostResponseDto> saveUser(@RequestBody UserRequestDto requestDto){
        Long savedId = userService.saveUser(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new GeneralPostResponseDto(savedId));
    }


    // 키가 숫자인지 확인
    private boolean isNumeric(String key) {
        try {
            Long.parseLong(key);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
