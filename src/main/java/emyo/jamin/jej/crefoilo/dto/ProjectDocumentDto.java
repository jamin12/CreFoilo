package emyo.jamin.jej.crefoilo.dto;

import org.springframework.lang.Nullable;

import emyo.jamin.jej.crefoilo.entity.DocumentUrl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDocumentDto {
    private Long docuemntUrlId;
    private String documentImgUrl;
    private String documentUrl;

    public ProjectDocumentDto(@Nullable DocumentUrl documentUrl) {
        this.docuemntUrlId = documentUrl.getDocumentId();
        this.documentImgUrl = documentUrl.getDocumentImgUrl();
        this.documentUrl = documentUrl.getDocumentUrl();
    }

    @Override
    public int hashCode() {
        return (this.documentImgUrl + this.documentUrl).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        // p1.equals(p2)
        if (obj instanceof ProjectDocumentDto) {
            ProjectDocumentDto documentDto = (ProjectDocumentDto) obj;
            return this.hashCode() == documentDto.hashCode();

        }
        return false;
    }
}