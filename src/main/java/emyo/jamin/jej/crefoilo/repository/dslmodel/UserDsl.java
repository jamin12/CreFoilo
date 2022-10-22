package emyo.jamin.jej.crefoilo.repository.dslmodel;

import java.util.List;

import emyo.jamin.jej.crefoilo.entity.Users;

public interface UserDsl {
    List<Users> findByEmail(String email);
}
