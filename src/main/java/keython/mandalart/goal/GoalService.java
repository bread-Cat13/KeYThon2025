package keython.mandalart.goal;

import keython.mandalart.goal.dto.GoalRequestDto;
import keython.mandalart.goal.dto.GoalResponseDto;

import java.util.List;

public interface GoalService {
    //1.Goal 생성
    Long createGoal(Long mandalArtId, GoalRequestDto goalDto);

    //1-2. Goal 9개 생성
    void createParentandChildren(Long mandalArtId, List<GoalRequestDto> goalDtoList);

    //2.상태 변경 (Incomplete -> Complete)
    void changeGoalStatus(Long goalId);

    //3.Type에 따라 조회
    List<GoalResponseDto> getGoalListByType(Long mandalArtId, GoalRetrieveType type);

}
