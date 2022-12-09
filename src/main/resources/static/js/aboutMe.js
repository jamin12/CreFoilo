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


const saveAboutMe = () => {
    const aboutMeId = document.querySelector("#aboutMeId")?.value;
    const portfolioId = document.querySelector("#portfolioid").value;

    const aboutMeType = document.querySelector("#aboutMeType").value;
    // 타이틀 1 가져오기
    let aboutMeTitle1 = document.querySelector("#intro1").value;
    // 인트로 1 가져오기
    let aboutMeIntro1 = document.querySelector("#detail1").value;
    //이미지 가져오기
    let aboutMeImg = document.querySelector(".btn-img").value;

    let data = {
        portfolioId: portfolioId,
        aboutMeType: aboutMeType,
        aboutMeTitle1: aboutMeTitle1,
        aboutMeIntro1: aboutMeIntro1,
        aboutMeImg: aboutMeImg
    }
    // aboutMeType 1일때
    if (aboutMeType == 1) {
        // 타이틀 1,2,3 가져오기 html에서 타이틀id는intro 맞음!
        data.aboutMeTitle2 = document.querySelector("#intro2").value;
        data.aboutMeTitle3 = document.querySelector("#intro3").value;
        // 인트로 1,2 가져오기 html에서 id는 detail 이다.
        data.aboutMeIntro2 = document.querySelector("#detail2").value;
    }

    // 새로 만들기
    if (aboutMeId !== '') {
        data.aboutMeId = aboutMeId;
    } 

    $.ajax({
        url: `/setting/aboutme/${portfolioId}`,
        contentType: "application/json; charset=utf-8",
        type: "POST",
        data: JSON.stringify(data),
        dataType: 'json',
        async: false,
        success: function (data) {
            
        },
        error: function (error) {
        },
    })
}