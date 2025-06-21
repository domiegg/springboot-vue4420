import { getRequest, postRequest, putRequest, postBodyRequest, getNoAuthRequest, postNoAuthRequest } from '@/libs/axios';

export const getDishCollectOne = (params) => {
    return getRequest('/dishCollect/getOne', params)
}
export const getDishCollectList = (params) => {
    return getRequest('/dishCollect/getByPage', params)
}
export const getDishCollectCount = (params) => {
    return getRequest('/dishCollect/count', params)
}
export const addDishCollect = (params) => {
    return postRequest('/dishCollect/insert', params)
}
export const editDishCollect = (params) => {
    return postRequest('/dishCollect/update', params)
}
export const addOrEditDishCollect = (params) => {
    return postRequest('/dishCollect/insertOrUpdate', params)
}
export const deleteDishCollect = (params) => {
    return postRequest('/dishCollect/delByIds', params)
}