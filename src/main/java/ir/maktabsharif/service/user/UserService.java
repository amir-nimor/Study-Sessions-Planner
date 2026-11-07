package ir.maktabsharif.service.user;

import ir.maktabsharif.dao.base.BaseModel;

import java.util.List;

public interface UserService <T extends BaseModel<ID>,ID extends Number,Q,S>{
    S create(Q q);

    S update(Q q);

    ID delete(ID id);

    S read (ID id);

    List<S> findAll();
}
