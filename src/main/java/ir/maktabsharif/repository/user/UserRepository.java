package ir.maktabsharif.repository.user;

import ir.maktabsharif.dao.User;
import ir.maktabsharif.repository.base.BaseRepository;

import java.util.List;

public interface UserRepository extends BaseRepository<User,Long> {

    User update1(User user,Long id);

    User findByUserName(String username);
}
