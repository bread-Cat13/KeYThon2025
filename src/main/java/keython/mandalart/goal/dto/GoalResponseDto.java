package keython.mandalart.goal.dto;


import keython.mandalart.domain.Goal;
import keython.mandalart.domain.GoalLevel;
import keython.mandalart.domain.GoalStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GoalResponseDto {

    private Long id;

    private String name;

    private GoalLevel level; //FINAL, MAIN, SUB

    private GoalStatus status;

    private LocalDateTime finishedDate;


    public GoalResponseDto(Long id, String name, GoalLevel level, GoalStatus status, LocalDateTime finishedDate) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.status = status;
        this.finishedDate = finishedDate;
    }

    public static GoalResponseDto toDto(Goal goal){
        return new GoalResponseDto(
                goal.getId(),
                goal.getName(),
                goal.getLevel(),
                goal.getStatus(),
                goal.getFinishedDate()
        );
    }
}
