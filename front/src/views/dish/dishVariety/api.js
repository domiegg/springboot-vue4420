import { getRequest, postRequest, putRequest, postBodyRequest, getNoAuthRequest, postNoAuthRequest } from '@/libs/axios';

export const getDishVarietyOne = (params) => {
    return getRequest('/dishVariety/getOne', params)
}
export const getDishVarietyList = (params) => {
    return getRequest('/dishVariety/getByPage', params)
}
export const getDishVarietyCount = (params) => {
    return getRequest('/dishVariety/count', params)
}
export const addDishVariety = (params) => {
    return postRequest('/dishVariety/insert', params)
}
export const editDishVariety = (params) => {
    return postRequest('/dishVariety/update', params)
}
export const addOrEditDishVariety = (params) => {
    return postRequest('/dishVariety/insertOrUpdate', params)
}
export const deleteDishVariety = (params) => {
    return postRequest('/dishVariety/delByIds', params)
}
export const getDishTypeList = (params) => {
    return getRequest('/dishType/getAll', params)
}
export const addCollect = (params) => {
    return getRequest('/dishCollect/addOne', params)
}
export const cancelCollect = (params) => {
    return getRequest('/dishCollect/cancelOne', params)
}
export const addOrder = (params) => {
    return getRequest('/dishOrder/addOne', params)
}