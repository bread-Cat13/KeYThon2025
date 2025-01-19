package keython.mandalart.mandalart.dto;

import keython.mandalart.domain.MandalArt;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MandalRequestDto {

    private String title;

    private String description;

    private LocalDateTime createdDate;

    private LocalDateTime deadline;

    public MandalRequestDto(String title, String description, LocalDateTime createdDate, LocalDateTime deadline) {
        this.title = title;
        this.description = description;
        this.createdDate = createdDate;
        this.deadline = deadline;
    }

    public static MandalArt toEntity(MandalRequestDto dto){
        return new MandalArt(dto.title, dto.description, dto.createdDate, dto.deadline);
    }
}


