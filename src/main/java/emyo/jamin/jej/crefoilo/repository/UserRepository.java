package emyo.jamin.jej.crefoilo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import emyo.jamin.jej.crefoilo.entity.Users;
import emyo.jamin.jej.crefoilo.repository.dslmodel.UserDsl;

public interface UserRepository extends JpaRepository<Users, Long>, UserDsl {

}
