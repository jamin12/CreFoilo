package emyo.jamin.jej.crefoilo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emyo.jamin.jej.crefoilo.dto.ContactDto;
import emyo.jamin.jej.crefoilo.entity.Contact;
import emyo.jamin.jej.crefoilo.repository.ContactRepository;
import emyo.jamin.jej.crefoilo.utils.Validation;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private Validation validation;

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

    /**
     * 아래 코드는 setting page 작업입니다.
     */
    /**
     * 컨택트 조회
     * 
     * @param portfolioId
     * @param userId
     */
    @Override
    public List<ContactDto> findContact(Long portfolioId, String userId) {
        validation.checkUserHasPortfolio(portfolioId, userId);
        List<Contact> findedContactList = contactRepository.findByPortfolioId(portfolioId);
        List<ContactDto> contactDtoList = new ArrayList<>();
        findedContactList.forEach((contact) -> {
            contactDtoList.add(new ContactDto(contact));
        });
        return contactDtoList;
    }

    /**
     * contact 생성 업데이트 삭제 작업
     * 
     * @param portfolioId
     * @param userId
     * @param contactDtoList
     */
    @Override
    public String CUDContact(Long portfolioId, String userId, List<ContactDto> contactDtoList) {
        List<Contact> findedContactList = contactRepository.findByPortfolioId(portfolioId);
        for (Contact findedContact : findedContactList) {
            int flag = 0;
            for (ContactDto contactDto : contactDtoList) {
                if (contactDto.getContactId() != null) {
                    if (findedContact.getContactId().equals(contactDto.getContactId())) {
                        flag = 1;
                        break;
                    }
                }
            }
            if (flag == 0) {
                contactRepository.delete(findedContact);
            }
        }
        contactDtoList.forEach((contactDto) -> {
            contactRepository.save(Contact.createAndUpdateEntity(portfolioId, contactDto));
        });
        return "success";
    }

}
