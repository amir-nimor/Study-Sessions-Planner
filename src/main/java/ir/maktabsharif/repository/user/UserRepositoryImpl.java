package ir.maktabsharif.repository.user;

import ir.maktabsharif.dao.User;
import ir.maktabsharif.exception.HibernateException;
import ir.maktabsharif.exception.RepositoryException;
import ir.maktabsharif.repository.base.BaseRepositoryImpl;
import ir.maktabsharif.util.HibernateUtil;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl extends BaseRepositoryImpl<User, Long> implements UserRepository {

    public UserRepositoryImpl() {
        super(User.class);
    }

    @Override
    protected void updateEntity(User dbEntity, User newEntity) {
        dbEntity.setFullname(newEntity.getFullname());
        dbEntity.setUsername(newEntity.getUsername());
        dbEntity.setPassword(newEntity.getPassword());
        dbEntity.setSubjects(newEntity.getSubjects());
        dbEntity.setStudySessions(newEntity.getStudySessions());
        dbEntity.setId(newEntity.getId());
    }

    @Override
    public User update1(User user, Long id) {
        try {
            return HibernateUtil.InTxReturn(em -> {
                User userdb = em.find(User.class, id);
                updateEntity(userdb, user);
                return user;
            });
        } catch (HibernateException e) {
            throw new RepositoryException("cant not update => " + e.getMessage());
        }
    }


    @Override
    public User findByUserName(String username) {
        try {
            return HibernateUtil.read(em -> {

                User user = em.createQuery("SELECT u FROM User u LEFT JOIN FETCH u.studySessions where u.username = ?1",User.class)
                        .setParameter(1,username)
                        .getSingleResult();

                em.createQuery("SELECT u FROM User u LEFT JOIN FETCH u.subjects WHERE u = ?1", User.class)
                        .setParameter(1, user)
                        .getSingleResult();
                return user;
            });
        } catch (HibernateException e) {
            throw new RepositoryException("find By Username is failed => " + e.getMessage());
        }
    }
}
