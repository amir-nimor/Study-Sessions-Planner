package ir.maktabsharif.repository.base;



import ir.maktabsharif.dao.baseMode.BaseModel;

import java.util.List;

public interface BaseRepository <T extends BaseModel<ID>,ID extends Number>{

    T create(T t);

    T update(T t);

    ID delete(ID id);

    T read (ID id);

    List<T> findAll();
}
