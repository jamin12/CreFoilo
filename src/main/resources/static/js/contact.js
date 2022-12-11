const modalIconDic = {
  iconName: [
    'logo-github',
    "desktop",
    "cloud",
    "document-text",
    "logo-firebase",
    "logo-google",
    "logo-instagram",
    "logo-facebook",
    "logo-slack",
    "logo-youtube",
    "business",
    "call",
    "mail",
    "logo-discord",
    "logo-twitter",
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
    "business",
    "call",
    "mail",
    "logo-discord",
    "logo-twitter"
  ]
}
const modalBody = document.querySelector('.modal-body .label-wrap');
for (var i = 0; i < modalIconDic.iconName.length; i++) {
  const iconLabel = document.createElement('label');
  iconLabel.innerHTML = `<input type="radio" name="select-icon" value="${modalIconDic.iconName[i]}" />
                            <div class="icon-radio-box">
                                <ion-icon name="${modalIconDic.iconName[i]}"></ion-icon>
                                <p>${modalIconDic.iconValue[i]}</p>
                            </div>`;
  modalBody.append(iconLabel);
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



/**
 * 모달창
 */
$('#exampleModal').on('show.bs.modal', function (event) {
  var button = $(event.relatedTarget) // Button that triggered the modal
  var type = button.data('whatever') // Extract info from data-* attributes

  var modal = $(this)

  console.log(type)

  if (type === 'addContact') {
    modal.find('.modal-title').text('Contact 정보 추가하기')
    modal.find('.btn-secondary').css('display', 'flex')
    modal.find('.btn-danger').css('display', 'none')
    event.currentTarget.querySelector(".btn-primary").onclick = function (){
      updateDocLink(event.relatedTarget);
    }
  } else {
    modal.find('.modal-title').text('Contact 정보 수정하기')
    modal.find('.btn-danger').css('display', 'flex')
    modal.find('.btn-secondary').css('display', 'none')
    event.currentTarget.querySelector(".btn-delete-icon").onclick = function (){
      deleteIcon(event.relatedTarget);
    }
    event.currentTarget.querySelector(".btn-primary").onclick = function (){
      updateDocLink(event.relatedTarget);
    }
  }
})