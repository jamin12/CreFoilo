package emyo.jamin.jej.crefoilo.service;

import java.util.List;

import emyo.jamin.jej.crefoilo.dto.OtherSkillDto;
import emyo.jamin.jej.crefoilo.dto.OtherSkillListDto;

public interface OtherSkillService {
    List<OtherSkillListDto> findOtherSkillList(Long portFolioId);

    List<OtherSkillDto> findOtherSkillList(Long portFolioId, String userId);

    List<OtherSkillListDto> CUDOtherSkill(List<OtherSkillDto> otherSkillDtoList, Long portFolioId, String userId);
}
