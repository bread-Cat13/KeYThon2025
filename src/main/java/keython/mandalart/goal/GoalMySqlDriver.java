package keython.mandalart.goal;

import jakarta.persistence.EntityManager;
import keython.mandalart.domain.Goal;
import keython.mandalart.domain.GoalLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GoalMySqlDriver implements GoalRepository{

    private final EntityManager em;

    @Override
    public Long createGoal(Goal goal) {
        em.persist(goal);
        return goal.getId();
    }

    @Override
    public void changeGoalStatus(Long goalId) {
        Goal findGoal = em.find(Goal.class, goalId);
        findGoal.finish();
    }

    @Override
    public List<Goal> getGoalsByType(Long mandalArtId, GoalRetrieveType type) {
        switch (type) {
            case ALL -> {
                return em.createQuery("select g from Goal g where g.mandalArt.id = :mandalId", Goal.class)
                        .setParameter("mandalId", mandalArtId)
                        .getResultList();
            }
            case FinalMain -> {
                return em.createQuery("select g from Goal g where g.mandalArt.id = :mandalId" +
                                " and (g.level = :final or g.level = :main)", Goal.class)
                        .setParameter("mandalId", mandalArtId)
                        .setParameter("final", GoalLevel.FINAL)
                        .setParameter("main", GoalLevel.MAIN)
                        .getResultList();
            }
            case MainSub -> {
                return em.createQuery("select g from Goal g where g.mandalArt.id = :mandalId" +
                                " and (g.level = :main or g.level = :sub)", Goal.class)
                        .setParameter("mandalId", mandalArtId)
                        .setParameter("main", GoalLevel.MAIN)
                        .setParameter("sub", GoalLevel.SUB)
                        .getResultList();
            }
            default -> throw new IllegalArgumentException("Invalid GoalRetrieveType: " + type);
        }
    }

    @Override
    public Goal findGoalById(Long goalId) {
        return em.find(Goal.class, goalId);
    }
}
