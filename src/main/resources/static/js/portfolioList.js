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