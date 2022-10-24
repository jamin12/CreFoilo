document.addEventListener("DOMContentLoaded", function(){
    // 더보기 버튼 이벤트 리스너
    document.querySelector('.btn-open').addEventListener('click', function(e){

        let classList = document.querySelector('#otherSkill').classList;
        let contentHeight = document.querySelector('#otherSkill > .contents').offsetHeight;

        // 현재 보이는 상태라면 감추기
        if(classList.contains('show')){
            classList.remove('show');
            classList.add('hide')
        }else if(classList.contains('hide')){
            classList.remove('hide');
            classList.add('show')
        }

        // 감추기 버튼 이벤트 리스너
        document.querySelector('.btn-close').addEventListener('click', function(e){
            e.target.classList.add('hide');
            document.querySelector('.btn-open').classList.remove('hide');
            document.querySelector('#otherSkill').classList.add('show')
        })

        // 전체보기 시 더보기 버튼 감추기(+감추기 버튼 표시)
        if(!classList.contains('show')){
            e.target.classList.add('hide');
            document.querySelector('.btn-close').classList.add('hide')
        }
    })
})