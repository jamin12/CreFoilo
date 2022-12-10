
/**
 * baseOtherSkill 삭제
 * 
 * @param {Element} e 
 */
const deleteBaseOtherSkill = (e) => {
  const baseElement = e.parentElement.parentElement;
  baseElement.parentElement.removeChild(baseElement);
}

/**
 * subOtherSkill 삭제
 * 
 * @param {Element} e 
 */
const deleteSubBaseOtherSkill = (e) => {
  const subElement = e.parentElement;
  subElement.parentElement.removeChild(subElement);
}

/**
 * subOtherSkill 생성
 * 
 * @param {Element} e 
 */
const addSubOtherSkill = (e) => {
  const baseOtherSkillId = e.parentElement.childNodes[1].childNodes[6].value;
  const subOtherSKillDiv = document.createElement('div');
  const subOtherSKillDeleteIcon = document.createElement('ion-icon');
  const subOtherSKillName = document.createElement('input');
  const subOtherSKillsBaseId = document.createElement('input');

  // 서브 스킬 틀
  subOtherSKillDiv.className = "sub-skill-box";

  // 서브스킬 input 창
  subOtherSKillName.type = "text";
  subOtherSKillName.id = "subSkill";

  // 서브의 베이스 스킬 
  subOtherSKillsBaseId.type = "hidden";
  subOtherSKillsBaseId.id = "baseSkillId";
  subOtherSKillsBaseId.value = baseOtherSkillId;

  // 서브스킬 삭제 버튼
  subOtherSKillDeleteIcon.setAttribute('name', "close-circle-outline");
  subOtherSKillDeleteIcon.onclick = function () {
    deleteSubBaseOtherSkill(this);
  }

  subOtherSKillDiv.appendChild(subOtherSKillDeleteIcon);
  subOtherSKillDiv.appendChild(subOtherSKillName);
  subOtherSKillDiv.appendChild(subOtherSKillsBaseId);

  e.parentElement.insertBefore(subOtherSKillDiv, e.previousSibling)
}

/**
 * other skill 저장기능
 */
const saveOtherSkill = () => {
  const otherSkillDtos = []
  const mainOtherSkills = document.querySelectorAll('.main-skill-box');
  const subOtherSkills = document.querySelectorAll('.sub-skill-box');
  const portfolioId = document.querySelector('#portfolioid').value;
  mainOtherSkills.forEach(mainOtherSkill => {
    otherSkillDtos.push({
      otherSkillID: mainOtherSkill.querySelector("#mainSkillId")?.value,
      otherSkillName: mainOtherSkill.querySelector("#mainSkill").value,
      portfolioId: portfolioId
    });
  });
  subOtherSkills.forEach(subOtherSkill => {
    otherSkillDtos.push({
      otherSkillName: subOtherSkill.querySelector("#subSkill").value,
      otherSkillID: subOtherSkill.querySelector("#subSkillId")?.value,
      baseOtherSkillId: subOtherSkill.querySelector("#baseSkillId").value,
      portfolioId: portfolioId
    })
  });

  $.ajax({
    url: `/setting/other/${portfolioId}`,
    contentType: "application/json; charset=utf-8",
    type: "POST",
    data: JSON.stringify(otherSkillDtos),
    dataType: 'json',
    async: false,
    success: function (data) {
    },
    error: function (error) {
    },
  })
}