package keython.mandalart.mandalart;

import keython.mandalart.mandalart.dto.MandalRequestDto;
import keython.mandalart.mandalart.dto.MandalResponseDto;

import java.util.List;

public interface MandalArtService {
    //생성
    Long createMandalArt(Long userId, MandalRequestDto mandalDto);

    //조회
    MandalResponseDto getCurrentMandalArtByUserId(Long userId);
    List<MandalResponseDto> getAllMandalArtsByUserId(Long userId);
}
