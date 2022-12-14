package emyo.jamin.jej.crefoilo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import emyo.jamin.jej.crefoilo.entity.Contact;
import emyo.jamin.jej.crefoilo.repository.dslmodel.ContactDsl;

public interface ContactRepository extends JpaRepository<Contact, Long>, ContactDsl {

}
