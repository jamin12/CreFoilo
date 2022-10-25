package emyo.jamin.jej.crefoilo.service;

import java.util.List;

import emyo.jamin.jej.crefoilo.dto.OtherSkillListDto;
import emyo.jamin.jej.crefoilo.entity.OtherSkill;

public interface OtherSkillService {
    List<OtherSkillListDto> findOtherSkillList(Long portFolioId);
}
