import axios from 'axios'

let base = '';
axios.defaults.baseURL = '/im'

/**
 * http post请求，请求参数为json格式
 * @param url 请求url
 * @param params 请求参数
 */
export const postRequest = (url, params) => {
  return axios({
    method: 'post',
    url: `${base}${url}`,
    data: params,
    headers: {
      'Content-Type': 'application/json;charset=utf-8'
    }
  });
};

/**
 * http post请求，上传文件时使用
 * @param url 请求url
 * @param params 请求参数
 */
export const uploadFileRequest = (url, params) => {
  return axios({
    method: 'post',
    url: `${base}${url}`,
    data: params,
    processData: false,
    contentType: false,
    headers: {"Content-Type": "multipart/form-data;boundary=" + new Date().getTime()}
  });
};

/**
 * http put请求，请求参数为json格式
 * @param url 请求url
 * @param params 请求参数
 */
export const putRequest = (url, params) => {
  return axios({
    method: 'put',
    url: `${base}${url}`,
    data: params,
    headers: {
      'Content-Type': 'application/json;charset=utf-8'
    }
  });
};

/**
 * http delete请求
 * @param url 请求url
 */
export const deleteRequest = (url) => {
  return axios({
    method: 'delete',
    url: `${base}${url}`
  });
};

/**
 * http delete请求
 * @param url 请求url
 */
export const getRequest = (url) => {
  return axios({
    method: 'get',
    url: `${base}${url}`
  });
};
