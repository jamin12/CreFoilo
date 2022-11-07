package emyo.jamin.jej.crefoilo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emyo.jamin.jej.crefoilo.dto.ContactDto;
import emyo.jamin.jej.crefoilo.entity.Contact;
import emyo.jamin.jej.crefoilo.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    /**
     * 아래 코드는 view page 작업입니다.
     */
    /**
     * 컨택트 조회
     * 
     * @param portfolioId
     */
    @Override
    public List<ContactDto> findContact(Long portfolioId) {
        List<Contact> findedContactList = contactRepository.findByPortfolioId(portfolioId);
        List<ContactDto> contactDtoList = new ArrayList<>();
        findedContactList.forEach((contact) -> {
            contactDtoList.add(new ContactDto(contact));
        });
        return contactDtoList;
    }

}
