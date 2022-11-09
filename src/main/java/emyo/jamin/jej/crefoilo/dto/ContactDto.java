package emyo.jamin.jej.crefoilo.dto;

import emyo.jamin.jej.crefoilo.entity.Contact;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ContactDto {
    private Long contactId;
    private Long portfolioId;
    private String contactImgUrl;
    private String contactInfo;

    public ContactDto(Contact contact) {
        this.contactId = contact.getContactId();
        this.contactImgUrl = contact.getContactImgUrl();
        this.contactInfo = contact.getContactInfo();
    }

    public ContactDto(Long portfolioId, String contactImgUrl, String contactInfo) {
        this.portfolioId = portfolioId;
        this.contactImgUrl = contactImgUrl;
        this.contactInfo = contactInfo;
    }

}
