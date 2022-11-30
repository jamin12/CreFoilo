const saveAboutMe = () => {
    // console.log(document.querySelector(".aboutMeTemplate1"));
    const aboutMeType = document.querySelector("#aboutMeType").value;
    // aboutMeType 1일때
    if (aboutMeType == 1) {
        const aboutMeId = document.querySelector("#aboutMeId").value;
        console.log(document.querySelector("#aboutMeId").value);
        const portfolioId = document.querySelector("#portfolioId").value;

        // 타이틀 1,2,3 가져오기 html에서 타이틀id는intro 맞음!
        let aboutMeTitle1 = document.querySelector("#intro1").value;
        let aboutMeTitle2 = document.querySelector("#intro2").value;
        let aboutMeTitle3 = document.querySelector("#intro3").value;
        // 인트로 1,2 가져오기 html에서 id는 detail 이다.
        let aboutMeIntro1 = document.querySelector("#detail2").value;
        let aboutMeIntro2 = document.querySelector("#detail3").value;

        // 이미지 가져오기
        let aboutMeImg = document.querySelector(".btn-img").value;
        // console.log(document.querySelector("#aboutMeType"));
    
    // aboutMeType 2일때
    } else {
        // 타이틀 1 가져오기
        let aboutMeTitle1 = document.querySelector("#intro1").value;
        // 인트로 1 가져오기
        let aboutMeIntro1 = document.querySelector("#detail1").value;
        //이미지 가져오기
        let aboutMeImg = document.querySelector(".btn-img").value;
    }
}