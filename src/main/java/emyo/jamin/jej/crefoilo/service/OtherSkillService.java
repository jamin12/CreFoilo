package emyo.jamin.jej.crefoilo.service;

import java.util.List;

import emyo.jamin.jej.crefoilo.dto.OtherSkillBaseList;
import emyo.jamin.jej.crefoilo.dto.OtherSkillDto;
import emyo.jamin.jej.crefoilo.dto.OtherSkillListDto;

public interface OtherSkillService {
    List<OtherSkillListDto> findOtherSkillList(Long portFolioId);

    List<OtherSkillBaseList> findOtherSkillList(Long portFolioId, String userId);

    void CUDOtherSkill(List<OtherSkillDto> otherSkillDtoList, Long portFolioId, String userId);

    OtherSkillDto createBaseOtherSkill(OtherSkillDto otherSkillDto, Long portFolioId,
            String userId);
}
