package keython.mandalart.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import keython.mandalart.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Slf4j
@RequiredArgsConstructor
public class UserMySqlDriver implements UserRepository {

    private final EntityManager em;

    @Override
    public Optional<User> findUserById(Long id) {
        log.info("method [findUserById] is called with id: {}", id);
        User user = em.find(User.class, id);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        log.info("method [findUserByEmail] is called with email: {}", email);
        try {
            User user = em.createQuery("select u from User u where u.email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
            return Optional.of(user);
        } catch (NoResultException e) {
            log.warn("No user found with email: {}", email);
            return Optional.empty();
        }
    }

    @Override
    public Long save(User user) {
        em.persist(user);
        return user.getId();
    }
}
