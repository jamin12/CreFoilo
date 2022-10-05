package emyo.jamin.jej.crefoilo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import emyo.jamin.jej.crefoilo.entity.Users;
import emyo.jamin.jej.crefoilo.repository.dslmodel.UserDsl;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>, UserDsl {

}
