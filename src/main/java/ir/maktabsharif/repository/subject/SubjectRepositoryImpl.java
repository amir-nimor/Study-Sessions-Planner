package ir.maktabsharif.repository.subject;

import ir.maktabsharif.dao.Subject;
import ir.maktabsharif.exception.HibernateException;
import ir.maktabsharif.exception.RepositoryException;
import ir.maktabsharif.repository.base.BaseRepositoryImpl;
import ir.maktabsharif.util.HibernateUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubjectRepositoryImpl extends BaseRepositoryImpl<Subject, Long> implements SubjectRepository {

    public SubjectRepositoryImpl() {
        super(Subject.class);
    }

    @Override
    protected void updateEntity(Subject dbEntity, Subject newEntity) {
        dbEntity.setName(newEntity.getName());
        dbEntity.setUser(newEntity.getUser());
        dbEntity.setId(newEntity.getId());
    }

    @Override
    public List<Subject> getUserSubjects(Long userId) {
        try {
            return HibernateUtil.read(em -> {
                return em.createQuery("FROM Subject s where s.user.id = ?1")
                        .setParameter(1, userId)
                        .getResultList();
            });
        } catch (HibernateException e) {
            throw new RepositoryException("find By id is failed => " + e.getMessage());
        }
    }
}
