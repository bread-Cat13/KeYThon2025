package keython.mandalart.mandalart.dto;

import keython.mandalart.domain.MandalArt;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class MandalResponseDto {
    private Long id;

    private String title;

    private String description;

    private LocalDateTime createdDate;

    private LocalDateTime deadline;

    public MandalResponseDto(Long id, String title, String description, LocalDateTime createdDate, LocalDateTime deadline) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdDate = createdDate;
        this.deadline = deadline;
    }

    public static MandalResponseDto toDto(MandalArt mandalArt){
        return new MandalResponseDto(
                mandalArt.getId(),
                mandalArt.getTitle(),
                mandalArt.getDescription(),
                mandalArt.getCreatedDate(),
                mandalArt.getDeadline()
        );
    }
}
