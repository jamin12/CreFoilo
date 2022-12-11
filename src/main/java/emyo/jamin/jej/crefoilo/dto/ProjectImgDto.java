package emyo.jamin.jej.crefoilo.dto;

import org.springframework.lang.Nullable;

import emyo.jamin.jej.crefoilo.entity.ProjectImg;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectImgDto {
    private Long projectImgId;
    private String projectImgUrl;

    public ProjectImgDto(@Nullable ProjectImg projectImg) {
        this.projectImgUrl = "";
        if (projectImg != null) {
            this.projectImgId = projectImg.getProjectImgId();
            this.projectImgUrl = projectImg.getProjectImgUrl();
        }
    }

    @Override
    public int hashCode() {
        return this.projectImgUrl.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        // p1.equals(p2)
        if (obj instanceof ProjectImgDto) {
            ProjectImgDto p = (ProjectImgDto) obj;
            return this.hashCode() == p.hashCode();

        }
        return false;
    }
}
