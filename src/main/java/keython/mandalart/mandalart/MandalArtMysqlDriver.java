package keython.mandalart.mandalart;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import keython.mandalart.domain.MandalArt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MandalArtMysqlDriver implements MandalArtRepository{

    private final EntityManager em;

    //생성
    @Override
    public Long createMandal(MandalArt mandalArt) {
        em.persist(mandalArt);
        return mandalArt.getId();
    }

    //조회
    @Override
    public Optional<MandalArt> findCurrentMandalArtByUserId(Long userId) {
        List<MandalArt> results = em.createQuery(
                        "select m from MandalArt m where m.user.id = :userId order by m.createdDate desc",
                        MandalArt.class)
                .setParameter("userId", userId)
                .setMaxResults(1) // 1개만 가져오기
                .getResultList();

        return results.isEmpty() ? Optional.empty() : Optional.of(results.getFirst());
    }

    @Override
    public List<MandalArt> findAllMandalArtsByUserId(Long userId) {
        return em.createQuery(
                        "select m from MandalArt m where m.user.id = :userId order by m.createdDate desc",
                        MandalArt.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public MandalArt findOne(Long mandalArtId) {
        return em.find(MandalArt.class, mandalArtId);
    }
}
