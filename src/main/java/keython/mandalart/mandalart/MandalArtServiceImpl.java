package keython.mandalart.mandalart;

import keython.mandalart.domain.MandalArt;
import keython.mandalart.domain.User;
import keython.mandalart.exception.UserNotFoundException;
import keython.mandalart.mandalart.dto.MandalRequestDto;
import keython.mandalart.mandalart.dto.MandalResponseDto;
import keython.mandalart.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MandalArtServiceImpl implements MandalArtService{

    private final MandalArtRepository mandalArtRepository;
    private final UserRepository userRepository;

    //생성
    @Override
    @Transactional
    public Long createMandalArt(Long userId, MandalRequestDto mandalDto) {
        Optional<User> userOpt = userRepository.findUserById(userId);
        User findUser;
        if(userOpt.isPresent()){
            findUser = userOpt.get();
        }else{
            throw new UserNotFoundException("User not found with id: " + userId);
        }
        MandalArt mandalArt = MandalRequestDto.toEntity(mandalDto);
        findUser.addMandalArtByUser(mandalArt);

        return mandalArtRepository.createMandal(mandalArt);
    }


    //조회
    @Override
    public MandalResponseDto getCurrentMandalArtByUserId(Long userId) {
        Optional<MandalArt> currentMandalOpt = mandalArtRepository.findCurrentMandalArtByUserId(userId);
        MandalArt currentMandal;
        if(currentMandalOpt.isPresent()){
            currentMandal = currentMandalOpt.get();
            return MandalResponseDto.toDto(currentMandal);
        }else{
            return null;
        }
    }

    @Override
    public List<MandalResponseDto> getAllMandalArtsByUserId(Long userId) {
        List<MandalArt> mandalList = mandalArtRepository.findAllMandalArtsByUserId(userId);
        List<MandalResponseDto> returnList = new ArrayList<>();
        for (MandalArt mandalArt : mandalList) {
            returnList.add(MandalResponseDto.toDto(mandalArt));
        }
        return returnList;
    }
}
