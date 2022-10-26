package emyo.jamin.jej.crefoilo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emyo.jamin.jej.crefoilo.dto.OtherSkillListDto;
import emyo.jamin.jej.crefoilo.entity.OtherSkill;
import emyo.jamin.jej.crefoilo.repository.OtherSkillRepository;

/**
 * @author 강경민
 */
@Service
public class OtherSkillServiceImpl implements OtherSkillService {

    @Autowired
    private OtherSkillRepository otherSkillRepository;

    /**
     * 포트폴리에오 맞는 OtherSKill 리스트 조회
     * 
     * @param portFolioId 포트폴리오 아이디
     * @return List<OtherSkill>
     */
    @Override
    public List<OtherSkillListDto> findOtherSkillList(Long portFolioId) {
        List<OtherSkill> findedOtherSkills = otherSkillRepository.findByPortfolioId(portFolioId);
        List<OtherSkillListDto> otherSkillListDtos = new ArrayList<OtherSkillListDto>();
        for (OtherSkill findOtherSkill : findedOtherSkills) {
            OtherSkillListDto otherSkillListDto = new OtherSkillListDto();
            List<String> subOtherSkillNames = new ArrayList<>();

            if (findOtherSkill.getBaseOtherSkillId() == null) {
                otherSkillListDto.setBaseOtherSkillName(findOtherSkill.getOtherSkillName());

                // subOtherSkillNames을 얻기 위해 중첩 for 사용
                for (OtherSkill otherSkill : findedOtherSkills) {
                    if (findOtherSkill.getOtherSkillID() == otherSkill.getBaseOtherSkillId()) {
                        subOtherSkillNames.add(otherSkill.getOtherSkillName());
                    }
                }
                otherSkillListDto.setSubOtherSkillName(subOtherSkillNames);

                otherSkillListDtos.add(otherSkillListDto);
            }
            continue;
        }
        return otherSkillListDtos;
    }

}
