package keython.mandalart.generalDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneralPostResponseDto {

    private Long id;

    public GeneralPostResponseDto(Long id) {
        this.id = id;
    }
}
