package emyo.jamin.jej.crefoilo.service;

import java.util.List;

import emyo.jamin.jej.crefoilo.dto.ContactDto;

public interface ContactService {
    List<ContactDto> findContact(Long portfolioId);
}
