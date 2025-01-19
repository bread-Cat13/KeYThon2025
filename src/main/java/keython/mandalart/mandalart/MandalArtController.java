package keython.mandalart.mandalart;

import io.swagger.v3.oas.annotations.Operation;
import keython.mandalart.generalDto.GeneralPostResponseDto;
import keython.mandalart.mandalart.dto.MandalRequestDto;
import keython.mandalart.mandalart.dto.MandalResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/mandalArts")
public class MandalArtController {

    private final MandalArtService mandalArtService;


    //[User의 MandalArt 정보 관련]
    // User의 MandalArt 생성
    //api/mandalArts?key={}
    @PostMapping
    @Operation(summary = "User의 MandalArt 생성", description = "/api/mandalArts?key={userId}")
    public ResponseEntity<GeneralPostResponseDto> createMandalArt(@RequestBody MandalRequestDto mandalDto, @RequestParam("key") Long userId){
        Long mandalArtId = mandalArtService.createMandalArt(userId, mandalDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new GeneralPostResponseDto(mandalArtId));
    }

    // User의 MandalArt
    //api/mandalArts/recent?key={}
    @GetMapping("/recent")
    @Operation(summary = "user의 최근 만다라트 조회", description = "/api/mandalArts/recent?key={userId}")
    public ResponseEntity<MandalResponseDto> getRecentMandalArt(@RequestParam("key") Long userId){
        MandalResponseDto currentMandalDto = mandalArtService.getCurrentMandalArtByUserId(userId);
        if(currentMandalDto==null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(currentMandalDto);
    }


    //api/mandalArts/list?key={}
    @GetMapping("/list")
    @Operation(summary = "user의 모든 만다라트 조회", description = "/api/mandalArts/list?key={userId}")
    public ResponseEntity<List<MandalResponseDto>> getMandalArtList(@RequestParam("key") Long userId){
        List<MandalResponseDto> mandalList = mandalArtService.getAllMandalArtsByUserId(userId);
        return ResponseEntity.ok(mandalList);
    }


}


