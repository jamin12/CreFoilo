const baseUrl = "http://127.0.0.1:3000/";

/**
 * 링크 복사
 * 
 * @param {Element} e 
 */
const copyPortfolioLink = (e) => {
  const portfolioId = e.childNodes[3].value;
  navigator.clipboard.writeText(`${baseUrl}portfolio/${portfolioId}`)
}