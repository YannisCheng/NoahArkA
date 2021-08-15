// 引入axios
import axios from 'axios'
import {BASE_URL_PRODU} from '../../config/pro.env'

// 创建axios实例
const httpService = axios.create({
  baseURL: BASE_URL_PRODU, // url前缀
  timeout: 3000, // 请求超时时间
  headers: {
    'content-type': 'application/x-www-form-urlencoded',
  },
  withCredentials: false,
})

// request拦截器
httpService.interceptors.request.use(
  config => {
    // 根据条件加入token-安全携带
    /*if (true) { // 需自定义
        // 让每个请求携带token
        config.headers['User-Token'] = '';
    }*/
    console.log('baseURL -->    :' + config.baseURL)
    console.log('url--->        :' + config.url)
    return config
  },
  error => {
    // 请求错误处理
    Promise.reject(error)
  }
)

// respone拦截器
httpService.interceptors.response.use(
  response => {
    // 统一处理状态
    /*const res = response.data;
    if (res.statuscode !== 1) { // 需自定义
        // 返回异常
        return Promise.reject({
            status: res.statuscode,
            message: res.message
        });
    } else {
        return response.data;
    }*/
    console.log(response.data)
    console.log(response.status)
    console.log(response.statusText)
    console.log(response.headers)
    console.log(response.config)
    return response.data
  },
  // 处理处理
  error => {
    if (error && error.response) {
      switch (error.response.status) {
        case 400:
          error.message = '错误请求 -->'
          break
        case 401:
          error.message = '未授权，请重新登录 -->'
          break
        case 403:
          error.message = '拒绝访问 -->'
          break
        case 404:
          error.message = '请求错误,未找到该资源 -->'
          break
        case 405:
          error.message = '请求方法未允许 -->'
          break
        case 408:
          error.message = '请求超时 -->'
          break
        case 500:
          error.message = '服务器端出错 -->'
          break
        case 501:
          error.message = '网络未实现 -->'
          break
        case 502:
          error.message = '网络错误 -->'
          break
        case 503:
          error.message = '服务不可用 -->'
          break
        case 504:
          error.message = '网络超时 -->'
          break
        case 505:
          error.message = 'http版本不支持该请求 -->'
          break
        default:
          error.message = `未知错误 -->${error.response.status}`
      }
    } else {
      error.message = '连接到服务器失败 -->'
      alert('连接到服务器失败 -->')
    }
    return Promise.reject(error)
  }
)

export default httpService
