import { getRequest, postRequest, putRequest, postBodyRequest, getNoAuthRequest, postNoAuthRequest } from '@/libs/axios';

export const getNewsOne = (params) => {
    return getRequest('/news/getOne', params)
}
export const getNewsList = (params) => {
    return getRequest('/news/getByPage', params)
}
export const getNewsCount = (params) => {
    return getRequest('/news/count', params)
}
export const addNews = (params) => {
    return postRequest('/news/insert', params)
}
export const editNews = (params) => {
    return postRequest('/news/update', params)
}
export const addOrEditNews = (params) => {
    return postRequest('/news/insertOrUpdate', params)
}
export const deleteNews = (params) => {
    return postRequest('/news/delByIds', params)
}