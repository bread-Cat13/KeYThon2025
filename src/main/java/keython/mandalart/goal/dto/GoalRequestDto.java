package keython.mandalart.goal.dto;

import keython.mandalart.domain.Goal;
import keython.mandalart.domain.GoalLevel;
import keython.mandalart.domain.GoalStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class GoalRequestDto {

    private String name;

    private GoalLevel level; //FINAL, MAIN, SUB

    private GoalStatus status;

    private LocalDateTime finishedDate;

    public GoalRequestDto(String name, GoalLevel level, LocalDateTime finishedDate) {
        this.name = name;
        this.level = level;
        this.status = GoalStatus.INCOMPLETE;
        this.finishedDate = finishedDate;
    }

    public GoalRequestDto(String name, GoalLevel level, GoalStatus status, LocalDateTime finishedDate) {
        this.name = name;
        this.level = level;
        this.status = status;
        this.finishedDate = finishedDate;
    }

    public static Goal toEntity(GoalRequestDto dto){
        return new Goal(dto.name, dto.level, dto.status, dto.finishedDate);
    }
}
