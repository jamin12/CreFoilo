package emyo.jamin.jej.crefoilo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import emyo.jamin.jej.crefoilo.entity.Contact;
import emyo.jamin.jej.crefoilo.repository.dslmodel.ContactDsl;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>, ContactDsl {

}
