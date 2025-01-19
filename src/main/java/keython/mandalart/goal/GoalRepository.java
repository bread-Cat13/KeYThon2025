package keython.mandalart.goal;

import keython.mandalart.domain.Goal;
import keython.mandalart.domain.GoalLevel;

import java.util.List;

public interface GoalRepository {
    //생성
    Long createGoal(Goal goal);

    //완료상태 변경
    void changeGoalStatus(Long goalId);

    //조회
    List<Goal> getGoalsByType(Long mandalArtId, GoalRetrieveType type);

    Goal findGoalById(Long goalId);

}
