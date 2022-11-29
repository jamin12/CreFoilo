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

function setProejctContentsInfo() {

  const projectId = document.querySelector("#projectId").value;
  const portfolioId = document.querySelector("#portfolioId").value;

  /**
  * 기술 스택 리스트에서 문자열로 만들기
  */
  let thchnicalStack = "";
  const getTechnicalStack = document.querySelectorAll(".tectstack");
  getTechnicalStack.forEach(e => {
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
  imginfo.forEach(e => {
    imgDto.push({
      projectImgId: e.querySelector("#imgId").value,
      projectImgUrl: e.querySelector("#imgUrl").value
    })
  })
  // 시작 날짜 가져오기
  let startDate = document.querySelector("#projectStrDate").value;
  // 마감 날짜 가져오기
  let endDate = document.querySelector("#projectEndDate").value;
  // 대표 이미지 가져오기
  let representativeImgUrl = document.querySelector("#projectRepresentativeImgUrl").value;
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
    projectId: projectId,
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
  if (projectId === '') {

    // 업데이트
  } else {
    $.ajax({
      url: `/setting/projectdetail/${projectId}`,
      contentType: "application/json; charset=utf-8",
      type: "POST",
      data: JSON.stringify(data),
      dataType: 'json',
      async: false,
      success: function (data) {
      },
      // 이거 왜 error 이랑 success랑 바껴있는지 이해가 안간다.
      error: function (error) {
        location.href = error.responseText;
      },
    })
  }

}


const addImg = (e) => {
  console.log(e);
  let imgTag = document.createElement("img");
}