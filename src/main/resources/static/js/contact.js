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
 * 모달창
 */
$('#exampleModal').on('show.bs.modal', function (event) {
  var button = $(event.relatedTarget) // Button that triggered the modal
  var type = button.data('whatever') // Extract info from data-* attributes

  var modal = $(this)
  const contactUrlText = event.relatedTarget.querySelector("#contactUrl")?.innerText;
  if (contactUrlText === undefined) {
    document.querySelector(".form-control").value = "";
  } else {
    document.querySelector(".form-control").value = contactUrlText;
  }

  if (type === 'addContact') {
    modal.find('.modal-title').text('Contact 정보 추가하기')
    modal.find('.btn-secondary').css('display', 'flex')
    modal.find('.btn-danger').css('display', 'none')
    event.currentTarget.querySelector(".btn-primary").onclick = function () {
      createDocLink(event.relatedTarget);
    }
  } else {
    modal.find('.modal-title').text('Contact 정보 수정하기')
    modal.find('.btn-danger').css('display', 'flex')
    modal.find('.btn-secondary').css('display', 'none')

    event.currentTarget.querySelector(".btn-delete-icon").onclick = function () {
      deleteIcon(event.relatedTarget);
    }
    event.currentTarget.querySelector(".btn-primary").onclick = function () {
      updateDocLink(event.relatedTarget);
    }
  }
})


/**
 * 새 Contact 생성
 * @param {Element} e 
 * @returns 
 */
const createDocLink = (e) => {
  var radiovalue = $('input[name=select-icon]:checked').val()
  if (radiovalue === undefined) {
    alert("아이콘을 선택해주세요");
    return;
  }

  // url 주소 text
  const ContactUrlText = document.querySelector('#contact-name');

  const contactButton = document.createElement('button');

  contactButton.className = "item-box";
  contactButton.setAttribute('data-toggle', "modal");
  contactButton.setAttribute("data-target", "#exampleModal");
  contactButton.setAttribute("data-whatever", "updateContact");

  // 아이콘 box 생성
  contactButton.innerHTML = `
  <input type="hidden" value="${radiovalue}" id='contactImgUrl'>
  <ion-icon name="${radiovalue}"></ion-icon>
  <span id="contactUrl">${ContactUrlText.value}</span>
  `;

  e.parentElement.appendChild(contactButton);
  ContactUrlText.value = '';
}

/**
 * contact수정시에 url input태그 채워주는 함수
 * @param {Element} e 
 */
const getUrlText = (e) => {
  const contactSpanValue = e.querySelector('#contactUrl').innerText
  const contactUrlInput = document.querySelector('.form-control');

  contactUrlInput.value = contactSpanValue;
}


/**
 * contact 수정
 * @param {Element} e 
 */
const updateDocLink = (e) => {
  var radiovalue = $('input[name=select-icon]:checked').val();
  if (radiovalue === undefined) {
    alert('아이콘을 선택해주세요')
    return;
  }

  const contactUrlInput = document.querySelector('.form-control');
  const contactUrl = e.querySelector('span');
  contactUrl.innerText = contactUrlInput.value;
  document.querySelector('#contactImgUrl').value = radiovalue;
  e.querySelector('ion-icon').setAttribute("name", radiovalue);

  contactUrlInput.value = "";
}

/**
 * 링크 삭제
 */
const deleteIcon = (e) => {
  if (!confirm("정말 삭제하시겠습니까?")) {
    return;
  } else {
    e.remove();
  }
}


/**
 * 저장버튼 onClick
 */
const onClickSave = () => {
  const header = $("meta[name='_csrf_header']").attr('content');
  const token = $("meta[name='_csrf']").attr('content');

  const contactDtos = [];
  const itemBoxs = document.querySelectorAll('button.item-box');
  const contactImgs = document.querySelectorAll('#contactImgUrl');
  const contactUrls = document.querySelectorAll('span#contactUrl');
  const portfolioId = document.getElementById('portfolioid').value;

  itemBoxs.forEach(itemBox => {
    contactDtos.push({
      contactId: itemBox.querySelector('#contactId')?.value,
      portfolioId: portfolioId,
      contactImgUrl: itemBox.querySelector('#contactImgUrl').value,
      contactInfo: itemBox.querySelector('#contactUrl').innerText
    })
  })


  $.ajax({
    url: `/setting/contact/${portfolioId}`,
    contentType: "application/json; charset=utf-8",
    type: "POST",
    data: JSON.stringify(contactDtos),
    async: false,
    beforeSend: function (xhr) {
      xhr.setRequestHeader(header, token);
    },
    success: function (data) {
      document.location.href = data;
      location.href = `/portfolio/${portfolioId}`
    },
    error: function (error) {
    },
  })
}