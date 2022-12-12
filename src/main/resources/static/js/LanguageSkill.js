/**
 * 언어 프레임워크 추가 기능
 * 
 * @param {Element} e 
 */
const addLangFramework = (e) => {
  const langFramewordSpan = document.createElement("span");
  const langFramewordInput = document.createElement("input");

  // 프레임워크 input
  langFramewordInput.type = 'text';
  langFramewordInput.addEventListener('keydown', (e) => completionAddLangFramework(e))

  // 프레임워크 span
  langFramewordSpan.appendChild(langFramewordInput);

  e.parentElement.insertBefore(langFramewordSpan, e.nextSibling);
}

/**
 * 엔터 누를 시 태그 변경
 * 
 * @param {KeyboardEvent} e 
 */
const completionAddLangFramework = (e) => {
  if (e.key === "Enter") {
    e.target.parentElement.onclick = function () {
      this.remove();
    }
    e.target.parentElement.innerText = e.target.value;
  }
}

/**
 * 언어 박스 삭제
 * 
 * @param {Element} e 
 */
const deleteLanguageBox = (e) => {
  e.parentElement.remove();
}

/**
 * 언어 박스 생성
 * 
 * @param {Element} e 
 */
const createLanguageBox = (e) => {
  const langBox = document.createElement("div");
  const langBoxDeleteBtn = document.createElement("button");
  const langBoxDeleteBtnIcon = document.createElement("ion-icon");
  const langNameInput = document.createElement("input");
  const langProgressDiv = document.createElement("div");
  const langProgressInput = document.createElement("input");
  const langFramworkDiv = document.createElement("div");
  const langFramworkButton = document.createElement("button");
  const langFramworkIcon = document.createElement("ion-icon");
  const langDetail = document.createElement("textarea");

  // 삭제 버튼 아이콘
  langBoxDeleteBtnIcon.setAttribute("name", "close-outline");

  // 삭제 버튼
  langBoxDeleteBtn.appendChild(langBoxDeleteBtnIcon);
  langBoxDeleteBtn.className = "btn-delete";
  langBoxDeleteBtn.onclick = function () {
    deleteLanguageBox(this)
  }

  // 언어 이름 적는 input
  langNameInput.type = "text";
  langNameInput.className = "input-langname";
  langNameInput.placeholder = "ex) javaScript";

  // 언어 프로그래스바 input
  langProgressInput.type = "range";
  langProgressInput.max = 100;
  langProgressInput.id = "progressbarInput";

  // 언어 프로그래스바
  langProgressDiv.className = "progressbar";
  langProgressDiv.appendChild(langProgressInput);

  // 프레임워크 "+" 아이콘
  langFramworkIcon.setAttribute("name", "add-circle");

  // 프레임워크 버튼
  langFramworkButton.appendChild(langFramworkIcon);
  langFramworkButton.onclick = function () {
    addLangFramework(this)
  }

  // 프레임워크 틀
  langFramworkDiv.className = "lang-framework-box";
  langFramworkDiv.appendChild(langFramworkButton);

  // 언어 상세 설명
  langDetail.className = "lang-textarea";
  langDetail.placeholder = "상세설명을 적으세요\n \n상세설명을 엔터를 기준으로 구분됩니다.";

  // 전체 틀
  langBox.className = "language-box";

  langBox.appendChild(langBoxDeleteBtn);
  langBox.appendChild(langNameInput);
  langBox.appendChild(langProgressDiv);
  langBox.appendChild(langFramworkDiv);
  langBox.appendChild(langDetail);

  e.parentElement.insertBefore(langBox, e.nextSibling);
}

const createLang = () => {
  const langBox = document.querySelectorAll(".language-box");
  const portfolioid = document.querySelector("#portfolioid").value;
  const data = [];
  let flag = false;
  langBox.forEach(lang => {
    const langName = lang.querySelector(".input-langname").value;
    const langFrameworkList = lang.querySelector(".lang-framework-box").querySelectorAll("span");
    let strLangFramework = "";

    langFrameworkList.forEach(langFramework => {
      strLangFramework += `${langFramework.innerText}/n`
    })

    langDetail = lang.querySelector(".lang-textarea").value.split('\n').join('/n');
    // 제목이 비었을 때 에러
    if (langName == "") {
      alert("빈칸을 채워주세요");
      flag = true;
      return;
    }

    data.push({
      langId: lang.querySelector("#langId")?.value,
      langName: langName,
      langFrequency: lang.querySelector("#progressbarInput").value,
      langSkillName: strLangFramework,
      langDetail: langDetail,
    });
  });
  if(flag){
    return;
  }
  $.ajax({
    url: `/setting/language/${portfolioid}`,
    contentType: "application/json; charset=utf-8",
    type: "POST",
    data: JSON.stringify(data),
    async: false,
    success: function (data) {
      document.location.href = data
    },
    error: function (error) {
    },
  })
}
