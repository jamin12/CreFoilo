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

const imgUtil = {
  /**
   * 이미지 생성 URL [POST]
   * @returns {String}
   */
  createImg: (...params) => createUrl("", params),
  /**
   * 이미지 조회 URL 반환 [GET]
   * @param  {...any} params Path Variables 값들
   * @returns {String}
   */
  getImg: (...params) => createUrl("", params),
};
export default imgUtil;
