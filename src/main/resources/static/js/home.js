/**
 * url 만들기
 * @param {string} startUrl
 * @param  {string[]} params
 * @returns
 */
const createUrl = (startUrl, params) => {
  let mainUrl = "https://www.jaminimg.shop/file";
  mainUrl += startUrl;
  for (let index = 0; index < params.length; index++) {
    mainUrl = mainUrl + "/" + params[index];
  }
  return mainUrl;
};

var exampleModal = document.getElementById('exampleModal')
exampleModal.addEventListener('show.bs.modal', function (event) {
  const inputImgBtn = document.querySelector("#img-url");
  const typeInput = document.querySelector("#typeinput");
  inputImgBtn.value = '';

  var button = event.relatedTarget

  var type = button.getAttribute('data-bs-whatever')
  typeInput.value = type;
  var modalTitle = exampleModal.querySelector('.modal-title')

  if (type === '2') {
    exampleModal.querySelector('.modal-select-img-box').style.display = 'none';
  }
  else {
    exampleModal.querySelector('.modal-select-img-box').style.display = 'block';
  }

  modalTitle.textContent = '예시 템플릿 ' + type + '정보 입력하기'
})

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
  const inputimgurl = document.querySelector("#home-img-url");
  inputimgurl.value = createUrl("", [imgFile]);
}

const savePortfolioHome = () => {
  const header = $("meta[name='_csrf_header']").attr('content');
  const token = $("meta[name='_csrf']").attr('content');
  const data = {
    portfolioName: document.querySelector("#title")?.value,
    portfolioHomeText: document.querySelector("#home-text")?.value,
    portfolioImgUrl: document.querySelector("#home-img-url")?.value,
    portfolioType: document.querySelector("#typeinput").value,
    portfolioId: document.querySelector("#portfolioid")?.value
  }
  
  $.ajax({
    url: `/setting/home`,
    contentType: "application/json; charset=utf-8",
    type: "POST",
    data: JSON.stringify(data),
    dataType: 'json',
    beforeSend: function (xhr) {
        xhr.setRequestHeader(header, token);
    },
    async: false,
    success: function (data) {
      document.location.href = `/setting/aboutme/${data}`
    },
    error: function (error) {
    },
  })
}