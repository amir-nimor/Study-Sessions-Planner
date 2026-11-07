package ir.maktabsharif.repository.user;

import ir.maktabsharif.dao.User;
import ir.maktabsharif.repository.base.BaseRepositoryImpl;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl extends BaseRepositoryImpl<User,Long> implements UserRepository {

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
}
