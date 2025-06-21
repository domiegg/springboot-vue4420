import { getRequest, postRequest, putRequest, postBodyRequest, getNoAuthRequest, postNoAuthRequest } from '@/libs/axios';

export const getDishOrderOne = (params) => {
    return getRequest('/dishOrder/getOne', params)
}
export const getDishOrderList = (params) => {
    return getRequest('/dishOrder/getByPage', params)
}
export const getDishOrderCount = (params) => {
    return getRequest('/dishOrder/count', params)
}
export const addDishOrder = (params) => {
    return postRequest('/dishOrder/insert', params)
}
export const editDishOrder = (params) => {
    return postRequest('/dishOrder/update', params)
}
export const addOrEditDishOrder = (params) => {
    return postRequest('/dishOrder/insertOrUpdate', params)
}
export const deleteDishOrder = (params) => {
    return postRequest('/dishOrder/delByIds', params)
}
export const orderOrder = (params) => {
    return postRequest('/dishOrder/order', params)
}
export const payOrder = (params) => {
    return postRequest('/dishOrder/pay', params)
}