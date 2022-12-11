/**
 * url 만들기
 * @param {string} startUrl
 * @param  {string[]} params
 * @returns
 */
const createUrl = (startUrl, params) => {
  let mainUrl = "http://39.120.8.109:3551/file";
  mainUrl += startUrl;
  for (let index = 0; index < params.length; index++) {
    mainUrl = mainUrl + "/" + params[index];
  }
  return mainUrl;
};
/**
 * toast ui editor
 */
const Editor = toastui.Editor;

const editor = new Editor({
  el: document.querySelector('#editor'),
  height: '500px',
  initialEditType: 'markdown',
  previewStyle: 'vertical',
});

/**
 * 엔터 누를 시 태그 변경
 * 
 * @param {KeyboardEvent} e 
 */
const completionTectStack = (e) => {
  if (e.key === "Enter") {
    e.target.parentElement.onclick = function () {
      this.remove();
    }
    e.target.parentElement.innerText = e.target.value;
  }
}

/**
 * 기술스택 추가 기능
 * 
 * @param {Element} e 
 */
const addTectStack = (e) => {
  const langFramewordSpan = document.createElement("span");
  const langFramewordInput = document.createElement("input");

  // 기술스택 input
  langFramewordInput.type = 'text';
  langFramewordInput.addEventListener('keydown', (e) => completionTectStack(e))

  // 기술스택 span
  langFramewordSpan.appendChild(langFramewordInput);

  e.parentElement.insertBefore(langFramewordSpan, e.nextSibling);
}

function setProejctContentsInfo() {

  const projectId = document.querySelector("#projectId")?.value;
  const portfolioId = document.querySelector("#portfolioId").value;

  /**
  * 기술 스택 리스트에서 문자열로 만들기
  */
  let thchnicalStack = "";
  const getTechnicalStack = document.querySelector(".tactstack-wrap");
  getTechnicalStack.querySelectorAll("span").forEach(e => {
    thchnicalStack = thchnicalStack + e.innerText + "/n";
  });

  // 타이틀 가져오기
  let projectTitile = document.querySelectorAll(".project_title")[0].value;
  // 서브타이틀 가져오기
  let projectSubTitile = document.querySelectorAll(".project_title")[1].value;
  // 설명 가져오기
  let projectIntro = document.querySelector(".project_intro").value;
  // 서브 인트로 가져오기
  let projectSubIntro = document.querySelector(".project_contribution").value;
  // 이미지 정보 가져오기
  let imginfo = document.querySelectorAll(".project_img_card");
  // 이미지 정보 삽입
  let imgDto = [];
  imginfo.forEach(imgInfoElemet => {
    imgDto.push({
      projectImgId: imgInfoElemet.querySelector("#imgId")?.value,
      projectImgUrl: imgInfoElemet.querySelector("img").src
    })
  })
  // 시작 날짜 가져오기
  let startDate = document.querySelector("#projectStrDate")?.value;
  // 마감 날짜 가져오기
  let endDate = document.querySelector("#projectEndDate")?.value;
  if (endDate === "" || startDate === "" || startDate > endDate) {
    alert("날짜를 바르게 입력해주세요");
    return;
  }
  // 대표 이미지 가져오기
  let representativeImgUrl = document.querySelector("#projectRepresentativeImgUrl")?.value;
  // 프로젝트 문서 가져오기
  let projectDoc = document.querySelectorAll(".projectDoc");
  let projectDocDto = []
  projectDoc.forEach(e => {
    projectDocDto.push({
      docuemntUrlId: e.querySelector("#docuemntUrlId").value,
      documentImgUrl: e.querySelector("#documentImgUrl").value,
      documentUrl: e.querySelector("#documentUrl").value
    })
  });

  // 에디터 정보 가져오기
  let htmlData = ""
  let mdData = ""
  // 백엔드에 정보 보내기

  mdData = editor.getMarkdown();
  htmlData = editor.getHTML();

  let data = {
    portfolioId: portfolioId,
    projectRepresentativeImgUrl: representativeImgUrl,
    projectTitle: projectTitile,
    proejctIntro: projectIntro,
    projectStrDate: startDate,
    projectEndDate: endDate,
    projectSubTitle: projectSubTitile,
    proejctSubIntro: projectSubIntro,
    projectMd: mdData,
    projectHtml: htmlData,
    proejctTechnicalStack: thchnicalStack,
    projectImg: imgDto,
    projectDocument: projectDocDto
  }
  // 새로 만들기
  // if (projectId === '') {
  //   $.ajax({
  //     url: `/setting/projectdetail/${portfolioId}`,
  //     contentType: "application/json; charset=utf-8",
  //     type: "POST",
  //     data: JSON.stringify(data),
  //     dataType: 'json',
  //     async: false,
  //     success: function (data) {
  //       location.href = data.responseText
  //     },
  //     // 이거 왜 error 이랑 success랑 바껴있는지 이해가 안간다.
  //     error: function (error) {
  //       location.href = error.responseText
  //     },
  //   })
  //   // 업데이트
  // } else {
  //   data.projectId = projectId;
  //   $.ajax({
  //     url: `/setting/projectdetail/${portfolioId}/${projectId}`,
  //     contentType: "application/json; charset=utf-8",
  //     type: "POST",
  //     data: JSON.stringify(data),
  //     dataType: 'json',
  //     async: false,
  //     success: function (data) {
  //     },
  //     // 이거 왜 error 이랑 success랑 바껴있는지 이해가 안간다.
  //     error: function (error) {
  //       location.href = error.responseText
  //     },
  //   })
  // }
}

const addImg = (e) => {
  // formdata에 삽입
  const formdata = new FormData();
  formdata.append("file", e[0]);
  // axios로 formdata 넣어서 전송
  let imgFile;
  $.ajax({
    url: createUrl("", []),
    type: "POST",
    data: formdata,
    async: false,
    contentType: false,
    processData: false,
    mimeType: 'multipart/form-data',
    success: function (data) {
      data = JSON.parse(data);
      imgFile = data.result_data.fid;
    },
    error: function (error) {
      // TODO: 에러처리
    }
  });
  const imgs = document.querySelector(".project_imgs")
  const addImgInputButton = document.querySelector(".add_img_btn")

  const imgCard = document.createElement("div")
  let imgTag = document.createElement("img");
  let imgUrlInput = document.createElement("input");

  imgUrlInput.setAttribute("id", "imgUrl");
  imgUrlInput.setAttribute("type", "hidden");
  imgUrlInput.value = createUrl("", [imgFile]);

  imgTag.src = createUrl("", [imgFile]);

  imgCard.onclick = function () {
    this.remove();
  }
  imgCard.setAttribute("class", "project_img_card img-card")
  imgCard.appendChild(imgTag);
  imgCard.appendChild(imgUrlInput);

  imgs.appendChild(imgCard);

  addImgInputButton.value = '';
}

const addImgRepresent = (e) => {
  // formdata에 삽입
  const formdata = new FormData();
  formdata.append("file", e[0]);
  // axios로 formdata 넣어서 전송
  let imgFile;
  $.ajax({
    url: createUrl("", []),
    type: "POST",
    data: formdata,
    async: false,
    contentType: false,
    processData: false,
    mimeType: 'multipart/form-data',
    success: function (data) {
      data = JSON.parse(data);
      imgFile = data.result_data.fid;
    },
    error: function (error) {
      // TODO: 에러처리
    }
  });
  const representImgInput = document.querySelector("#projectRepresentativeImgUrl")
  const representImgtag = document.querySelector("#representImgtag")
  const addRepresentImgBtn = document.querySelector(".add_represent_img_btn")

  representImgInput.value = createUrl("", [imgFile]);

  representImgtag.src = createUrl("", [imgFile]);

  addRepresentImgBtn.value = '';
}

const mdData = document.querySelector("#projectMd").value;
editor.setMarkdown(mdData);


/**
 * 모달창
 */
$('#exampleModal').on('show.bs.modal', function (event) {
  var button = $(event.relatedTarget) // Button that triggered the modal
  var type = button.data('whatever') // Extract info from data-* attributes
  const iconCount = document.getElementById('iconBox')
  if(iconCount.childElementCount >= 5){

  }
  var modal = $(this)
  if (type === 'addIcon') {
    modal.find('.modal-title').text('아이콘 추가하기')
    modal.find('.btn-cancle-icon').css("display", "flex")
    modal.find('.btn-delete-icon').css("display", "none")
    event.currentTarget.querySelector(".btn-primary").onclick = function (){
      updateDocLink(event.relatedTarget);
    }
  } else {
    modal.find('.modal-title').text('아이콘 수정하기')
    modal.find('.btn-cancle-icon').css("display", "none")
    modal.find('.btn-delete-icon').css("display", "flex")
    event.currentTarget.querySelector(".btn-delete-icon").onclick = function (){
      deleteIcon(event.relatedTarget);
    }
    event.currentTarget.querySelector(".btn-primary").onclick = function (){
      updateDocLink(event.relatedTarget);
    }
  }
})

$('#exampleModal').on('hidden.bs.modal', function (e) {
  // e.currentTarget.remove();
})


const modalIconDic = {
  iconName: [
    'logo-github',
    "desktop-outline",
    "cloud-outline",
    "document-text-outline",
    "logo-firebase",
    "logo-google",
    "logo-instagram",
    "logo-facebook",
    "logo-slack",
    "logo-youtube",
  ],
  iconValue: [
    'github',
    'desktop',
    "cloud",
    "document",
    "firebase",
    "google",
    "instagram",
    "facebook",
    "slack",
    "youtube",
  ]
}
const modalBody = document.querySelector('.modal-body .label-wrap')
for (var i = 0; i < modalIconDic.iconName.length; i++) {
  const iconLabel = document.createElement('label');
  iconLabel.innerHTML = `<input type="radio" name="select-icon" value="${modalIconDic.iconName[i]}" />
                          <div class="icon-radio-box">
                              <ion-icon name="${modalIconDic.iconName[i]}"></ion-icon>
                              <p>${modalIconDic.iconValue[i]}</p>
                          </div>`;
  modalBody.append(iconLabel)

}

/**
 * 모달창 아이콘 수정
 * 
 * @param {Element} e 선택된 엘리먼트
 */
const updateDocLink = (e) => {
  var radiovalue = $('input[name=select-icon]:checked').val();
  const docUrlText = document.querySelector('#docUrlText').value;
  const docUrlTextInput = e.querySelector('#docUrl');
  docUrlTextInput.value = docUrlText;
  e.querySelector("ion-icon").setAttribute("name", radiovalue);
}

/**
 * 모달창 아이콘 생성
 * 
 * @param {Element} e 선택된 엘리먼트
 */
const createDocLink = (e) => {
  var radiovalue = $('input[name=select-icon]:checked').val();
  const docUrlText = document.querySelector('#docUrlText').value;

  
}

/**
 * Link 삭제
 * 
 * @param {Element} e 
 */
const deleteIcon = (e) => {
  e.remove();
}
