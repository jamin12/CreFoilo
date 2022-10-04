package emyo.jamin.jej.crefoilo.repository.dslmodel;

import java.util.List;
import java.util.Optional;

import emyo.jamin.jej.crefoilo.entity.User;

public interface UserDsl {
    List<User> getUserByEmail(String email);
}
