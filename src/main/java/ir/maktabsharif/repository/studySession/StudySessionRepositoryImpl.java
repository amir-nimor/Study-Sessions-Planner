package ir.maktabsharif.repository.studySession;

import ir.maktabsharif.dao.StudySession;
import ir.maktabsharif.repository.base.BaseRepositoryImpl;
import org.springframework.stereotype.Repository;

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
}
