import { getRequest, postRequest, putRequest, postBodyRequest, getNoAuthRequest, postNoAuthRequest } from '@/libs/axios';

export const getDishTypeOne = (params) => {
    return getRequest('/dishType/getOne', params)
}
export const getDishTypeList = (params) => {
    return getRequest('/dishType/getByPage', params)
}
export const getDishTypeCount = (params) => {
    return getRequest('/dishType/count', params)
}
export const addDishType = (params) => {
    return postRequest('/dishType/insert', params)
}
export const editDishType = (params) => {
    return postRequest('/dishType/update', params)
}
export const addOrEditDishType = (params) => {
    return postRequest('/dishType/insertOrUpdate', params)
}
export const deleteDishType = (params) => {
    return postRequest('/dishType/delByIds', params)
}