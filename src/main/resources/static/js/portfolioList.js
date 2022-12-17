/**
 * 링크 복사
 * 
 * @param {Element} e 
 */
const copyPortfolioLink = (e) => {
  const portfolioId = e.childNodes[3].value;
  // TODO: 도메인 적용 시 포트번호 없애기
  navigator.clipboard.writeText(`${document.location.hostname}:${document.location.port}/portfolio/${portfolioId}`)
}

/**
 * 포트폴리오 삭제
 * 
 * @param {Element} e 
 */
const deletePortfolio = (e) => {
  const header = $("meta[name='_csrf_header']").attr('content');
  const token = $("meta[name='_csrf']").attr('content');

  const portfolioid = e.querySelector("#porfolioid").value;
  $.ajax({
    url: `/myportfolio/d/${portfolioid}`,
    contentType: "application/json; charset=utf-8",
    method: "DELETE",
    data: {},
    async: false,
    beforeSend: function (xhr) {
      xhr.setRequestHeader(header, token);
    },
    success: function (data) {
    },
    error: function (error) {
    },
  });
  e.parentElement.parentElement.remove();
}