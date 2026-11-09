package ir.maktabsharif.repository.studySession;

import ir.maktabsharif.dao.StudySession;
import ir.maktabsharif.exception.HibernateException;
import ir.maktabsharif.exception.RepositoryException;
import ir.maktabsharif.repository.base.BaseRepositoryImpl;
import ir.maktabsharif.util.HibernateUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudySessionRepositoryImpl extends BaseRepositoryImpl<StudySession, Long> implements StudySessionRepository {

    public StudySessionRepositoryImpl() {
        super(StudySession.class);
    }

    @Override
    protected void updateEntity(StudySession dbEntity, StudySession newEntity) {
        dbEntity.setUser(newEntity.getUser());
        dbEntity.setNotes(newEntity.getNotes());
        dbEntity.setSubject(newEntity.getSubject());
        dbEntity.setMinutes(newEntity.getMinutes());
        dbEntity.setId(newEntity.getId());
    }

    @Override
    public List<StudySession> userStudySession(Long userId) {
        try {
            return HibernateUtil.read(em -> {
                return em.createQuery("FROM StudySession s where s.user.id = ?1", StudySession.class)
                        .setParameter(1, userId)
                        .getResultList();
            });
        } catch (HibernateException e) {
            throw new RepositoryException("your user find studySession is failed "+e.getMessage());
        }
    }
}
