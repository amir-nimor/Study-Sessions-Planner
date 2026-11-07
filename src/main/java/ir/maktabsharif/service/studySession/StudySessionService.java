package ir.maktabsharif.service.studySession;

import ir.maktabsharif.dao.base.BaseModel;

import java.util.List;

public interface StudySessionService <T extends BaseModel<ID>,ID extends Number,Q,S>{
    S create(Q q);

    S update(Q q);

    ID delete(ID id);

    S read (ID id);

    List<S> findAll();
}
