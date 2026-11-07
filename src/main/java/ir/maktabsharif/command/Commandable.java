package ir.maktabsharif.command;

import ir.maktabsharif.dao.base.BaseModel;

public interface Commandable <T extends BaseModel,Q,S>{

    T requestToDao(Q q);

    S daoToResponse(T t);
}
