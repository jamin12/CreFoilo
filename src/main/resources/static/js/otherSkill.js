const createBaseOtherSkill = () => {
  const header = $("meta[name='_csrf_header']").attr('content');
  const token = $("meta[name='_csrf']").attr('content');

  const portfolioid = document.querySelector('#portfolioid').value
  const baseOtherSkillName = document.querySelector("#baseotherskill")
  const osWrap = document.querySelector('.os-wrap');
  const data = {
    portfolioId: portfolioid,
    otherSkillName: baseOtherSkillName.value,
  }
  let createdBaseOtherSKillId = 0
  let createdBaseOtherSKillName = ""
  $.ajax({
    url: `/setting/baseother/${portfolioid}`,
    contentType: "application/json; charset=utf-8",
    type: "POST",
    data: JSON.stringify(data),
    async: false,
    beforeSend: function (xhr) {
      xhr.setRequestHeader(header, token);
    },
    success: function (data) {
      createdBaseOtherSKillId = data.otherSkillID;
      createdBaseOtherSKillName = data.otherSkillName;
    },
    error: function (error) {
    },
  })

  const baseOtherSKillEelement = document.createElement('div');
  const baseOtherSKill_osBox = document.createElement('div');
  baseOtherSKillEelement.className = "main-skill-box"
  baseOtherSKill_osBox.className = "os-box"

  baseOtherSKillEelement.innerHTML = `
      <ion-icon name="close-circle-outline" onclick="deleteBaseOtherSkill(this)"></ion-icon>
      <input id="mainSkill" type="text" name="MainSkill" value=${createdBaseOtherSKillName} 
          autofocus><br>
      <input id="mainSkillId" type="hidden" name="mainSkillId" value=${createdBaseOtherSKillId}>
  `;

  baseOtherSKill_osBox.appendChild(baseOtherSKillEelement);
  baseOtherSKill_osBox.innerHTML += `      
              <button class="btn-subskill-add" type="button" onclick="addSubOtherSkill(this)">
                <ion-icon name="add-circle"></ion-icon>
              </button>
  `;

  osWrap.appendChild(baseOtherSKill_osBox);
  baseOtherSkillName.value = "";
};

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
  const baseOtherSkillId = e.parentElement.querySelector(".main-skill-box").querySelector("#mainSkillId").value;
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
  const header = $("meta[name='_csrf_header']").attr('content');
  const token = $("meta[name='_csrf']").attr('content');

  const otherSkillDtos = []
  const mainOtherSkills = document.querySelectorAll('.main-skill-box');
  const subOtherSkills = document.querySelectorAll('.sub-skill-box');
  const portfolioId = document.querySelector('#portfolioid').value;
  let flag = false;

  // 메인 스킬 정보
  mainOtherSkills.forEach(mainOtherSkill => {
    const mainSkillName = mainOtherSkill.querySelector("#mainSkill").value;
    if (mainSkillName === "") {
      alert("빈칸을 채워주세요");
      flag = true;
      return;
    }
    otherSkillDtos.push({
      otherSkillID: mainOtherSkill.querySelector("#mainSkillId")?.value,
      otherSkillName: mainSkillName,
      portfolioId: portfolioId
    });
  });

  // 서브 스킬 정보
  subOtherSkills.forEach(subOtherSkill => {
    const subSkillName = subOtherSkill.querySelector("#subSkill").value;
    if (subSkillName === "") {
      alert("빈칸을 채워주세요");
      flag = true;
      return;
    }
    otherSkillDtos.push({
      otherSkillName: subSkillName,
      otherSkillID: subOtherSkill.querySelector("#subSkillId")?.value,
      baseOtherSkillId: subOtherSkill.querySelector("#baseSkillId").value,
      portfolioId: portfolioId
    })
  });

  if (flag) {
    return;
  }

  $.ajax({
    url: `/setting/other/${portfolioId}`,
    contentType: "application/json; charset=utf-8",
    type: "POST",
    data: JSON.stringify(otherSkillDtos),
    async: false,
    beforeSend: function (xhr) {
      xhr.setRequestHeader(header, token);
    },
    success: function (data) {
      document.location.href = data;
    },
    error: function (error) {
    },
  })
}