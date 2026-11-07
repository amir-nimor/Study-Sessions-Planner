package ir.maktabsharif.repository.subject;

import ir.maktabsharif.dao.Subject;
import ir.maktabsharif.repository.base.BaseRepositoryImpl;
import org.springframework.stereotype.Repository;

@Repository
public class SubjectRepositoryImpl extends BaseRepositoryImpl<Subject,Long> implements SubjectRepository {

    public SubjectRepositoryImpl() {
        super(Subject.class);
    }

    @Override
    protected void updateEntity(Subject dbEntity, Subject newEntity) {
        dbEntity.setName(newEntity.getName());
        dbEntity.setUser(newEntity.getUser());
        dbEntity.setId(newEntity.getId());
    }
}
