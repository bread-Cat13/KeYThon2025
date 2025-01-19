package keython.mandalart.goal;

import keython.mandalart.domain.Goal;
import keython.mandalart.domain.MandalArt;
import keython.mandalart.exception.MandalArtNotFoundException;
import keython.mandalart.goal.dto.GoalRequestDto;
import keython.mandalart.goal.dto.GoalResponseDto;
import keython.mandalart.mandalart.MandalArtRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GoalServiceImpl implements GoalService {

    private final GoalRepository goalRepository;
    private final MandalArtRepository mandalArtRepository;


    @Override
    @Transactional
    public Long createGoal(Long mandalArtId, GoalRequestDto goalDto) {
        MandalArt findMandal = mandalArtRepository.findOne(mandalArtId);
        if(findMandal==null)
            throw new MandalArtNotFoundException("MandalArt not found with id: "+mandalArtId);

        Goal addingGoal = GoalRequestDto.toEntity(goalDto);
        findMandal.addGoal(addingGoal);

        return goalRepository.createGoal(addingGoal);
    }

    @Override
    @Transactional
    public void createParentandChildren(Long mandalArtId, List<GoalRequestDto> goalDtoList) {
        // Parent 저장
        GoalRequestDto parentGoalDto = goalDtoList.get(4);
        Goal parentGoal = GoalRequestDto.toEntity(parentGoalDto);
	
	//mandalart
        MandalArt findMandal = mandalArtRepository.findOne(mandalArtId);
        findMandal.addGoal(parentGoal);

        // 자식 저장 및 관계 설정
        for (int i = 0; i < goalDtoList.size(); i++) {
            if (i == 4) continue;
            Goal childGoal = GoalRequestDto.toEntity(goalDtoList.get(i));
	    findMandal.addGoal(childGoal);
            parentGoal.addChild(childGoal); // 관계 설정 (자식 저장은 Cascade에 의해 자동으로 처리)
        }

        // 부모 저장 (Cascade.ALL에 의해 자식도 저장됨)
        goalRepository.createGoal(parentGoal);
    }


    @Override
    @Transactional
    public void changeGoalStatus(Long goalId) {
        goalRepository.changeGoalStatus(goalId);
    }

    @Override
    public List<GoalResponseDto> getGoalListByType(Long mandalArtId, GoalRetrieveType type) {
        List<Goal> goalList = goalRepository.getGoalsByType(mandalArtId, type);
        List<GoalResponseDto> returnList = new ArrayList<>();
        for (Goal goal : goalList) {
            returnList.add(GoalResponseDto.toDto(goal));
        }
        return returnList;
    }
}
