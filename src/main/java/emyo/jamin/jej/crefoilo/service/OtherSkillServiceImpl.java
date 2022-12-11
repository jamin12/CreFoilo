package emyo.jamin.jej.crefoilo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import emyo.jamin.jej.crefoilo.dto.OtherSkillBaseList;
import emyo.jamin.jej.crefoilo.dto.OtherSkillDto;
import emyo.jamin.jej.crefoilo.dto.OtherSkillListDto;
import emyo.jamin.jej.crefoilo.dto.OtherSkillSubList;
import emyo.jamin.jej.crefoilo.entity.OtherSkill;
import emyo.jamin.jej.crefoilo.repository.OtherSkillRepository;
import emyo.jamin.jej.crefoilo.utils.CustomException;
import emyo.jamin.jej.crefoilo.utils.ErrorCode;
import emyo.jamin.jej.crefoilo.utils.Validation;

/**
 * @author 강경민
 */
@Service
public class OtherSkillServiceImpl implements OtherSkillService {

    @Autowired
    private OtherSkillRepository otherSkillRepository;

    @Autowired
    private Validation validation;

    /**
     * 아래 코드는 view page 작업입니다.
     */
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

    /**
     * 아래 코드는 setting page 작업입니다.
     */
    /**
     * setting 페이지 otherskill 리스트 가져오기
     * 
     * @param portFolioId
     * @param userId
     */
    @Override
    public List<OtherSkillBaseList> findOtherSkillList(Long portFolioId, String userId) {
        validation.checkUserHasPortfolio(portFolioId, userId);
        List<OtherSkill> findedOtherSkills = otherSkillRepository.findByPortfolioId(portFolioId);
        List<OtherSkillBaseList> otherSkillListDtos = new ArrayList<>();
        for (OtherSkill findOtherSkill : findedOtherSkills) {
            OtherSkillBaseList otherSkillListDto = new OtherSkillBaseList();
            List<OtherSkillSubList> subOtherSkillNames = new ArrayList<>();

            if (findOtherSkill.getBaseOtherSkillId() == null) {
                otherSkillListDto.setOtherSkillBaseId(findOtherSkill.getOtherSkillID());
                otherSkillListDto.setOtherSkillBaseName(findOtherSkill.getOtherSkillName());

                // subOtherSkillNames을 얻기 위해 중첩 for 사용
                for (OtherSkill otherSkill : findedOtherSkills) {
                    if (findOtherSkill.getOtherSkillID() == otherSkill.getBaseOtherSkillId()) {
                        subOtherSkillNames.add(new OtherSkillSubList(otherSkill));
                    }
                }
                otherSkillListDto.setOtherSkillSubList(subOtherSkillNames);

                otherSkillListDtos.add(otherSkillListDto);
            }
        }
        return otherSkillListDtos;
    }

    /**
     * other skill 생성 삭제 수정
     * 
     * @param otherSkillDtoList
     * @param portFolioId
     * @param userId
     * 
     */
    @Override
    @Transactional
    public List<OtherSkillListDto> CUDOtherSkill(List<OtherSkillDto> otherSkillDtoList, Long portFolioId,
            String userId) {
        validation.checkUserHasPortfolio(portFolioId, userId);
        List<OtherSkill> findedOtherSkill = otherSkillRepository.findByPortfolioId(portFolioId);
        // 같은게 없으면 삭제
        for (OtherSkill otherSkill : findedOtherSkill) {
            int flag = 0;
            for (OtherSkillDto otherSkillDto : otherSkillDtoList) {
                if (otherSkillDto.getOtherSkillID() != null) {
                    if (otherSkill.getOtherSkillID() == otherSkillDto.getOtherSkillID()) {
                        flag = 1;
                        break;
                    }
                }
            }
            if (flag == 0) {
                otherSkillRepository.delete(otherSkill);
            }
        }
        // 같은게 나오면 수정하고 새로운건 생성
        for (OtherSkillDto otherSkillDto : otherSkillDtoList) {
            // 베이트 other스킬이 이미 있으면 에러
            if (otherSkillDto.getBaseOtherSkillId() != null) {
                for (OtherSkill otherSkill : findedOtherSkill) {
                    if (otherSkill.getOtherSkillID() == otherSkillDto.getBaseOtherSkillId()) {
                        if (otherSkill.getBaseOtherSkillId() != null) {
                            throw new CustomException(ErrorCode.BASE_OTHER_SKILL_ALREADY_EXIST);
                        }
                    }
                }
            }
            otherSkillRepository.save(OtherSkill.createAndUpdateEntity(portFolioId, otherSkillDto));
        }
        // TODO: return 바꾸기
        return null;
    }

    /**
     * base other skill 생성
     * 
     * @param otherSkillDtoList
     * @param portFolioId
     * @param userId
     */
    @Override
    public OtherSkillDto createBaseOtherSkill(OtherSkillDto otherSkillDto, Long portFolioId,
            String userId) {
        return new OtherSkillDto(
                otherSkillRepository.save(OtherSkill.createAndUpdateEntity(portFolioId, otherSkillDto)));
    }

}
