package keython.mandalart.goal;


import io.swagger.v3.oas.annotations.Operation;
import keython.mandalart.goal.dto.GoalRequestDto;
import keython.mandalart.goal.dto.GoalResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/goals")
public class GoalController {

    private final GoalService goalService;

    // 생성된 MandalArt에 Goal 생성하기(FINAL,MAIN, SUB)
    //api/goals?key={mandalArtId}
    @PostMapping
    @Operation(summary = "생성된 MandalArt에 Goal 생성하기", description = "/api/goals?key={mandalArtId}")
    public ResponseEntity<String> createGoals(
            @RequestParam("key") Long mandalArtId, @RequestBody List<GoalRequestDto> goalDtoList){
        if (goalDtoList == null || goalDtoList.isEmpty()) {
            return ResponseEntity.badRequest().body("Goal list cannot be empty");
        }
        goalService.createParentandChildren(mandalArtId, goalDtoList);

        return ResponseEntity.status(HttpStatus.CREATED).body("9 goals created");

    }

    //조회
    // /api/goals?key={mandalArtId}&type={ALL/FINALMAIN/MAINSUB}
    @GetMapping("/list")
    @Operation(summary = "타입으로 Goal리스트 조회", description = "/api/goals?key={mandalArtId}&type={ALL/FINALMAIN/MAINSUB}")
    public ResponseEntity<?> getGoalsListByType(@RequestParam("key") Long mandalArtId, @RequestParam("type") String type){
        //type -> ENUM으로 변경
        GoalRetrieveType goalRetrieveType = GoalRetrieveType.fromString(type.toUpperCase());
        List<GoalResponseDto> goalListByType = goalService.getGoalListByType(mandalArtId, goalRetrieveType);

        return ResponseEntity.ok(goalListByType);
    }


    //goal status 변경
    //api/goals/finish?key={goalId}
    @PostMapping("/finish")
    @Operation(summary = "goal status 변경", description = "/api/goals/finish?key={goalId}")
    public ResponseEntity<String> changeGoalStatus(@RequestParam("key") Long goalId){
        goalService.changeGoalStatus(goalId);
        return ResponseEntity.ok("status changed into \"COMPLETED\"");
    }
}
