import request from '@/utils/request'

// 查询booking列表
export function listBooking(query) {
  return request({
    url: '/fuhang_booking/booking/list',
    method: 'get',
    params: query
  })
}

// 查询booking详细
export function getBooking(id) {
  return request({
    url: '/fuhang_booking/booking/' + id,
    method: 'get'
  })
}

// 新增booking
export function addBooking(data) {
  return request({
    url: '/fuhang_booking/booking',
    method: 'post',
    data: data
  })
}

// 修改booking
export function updateBooking(data) {
  return request({
    url: '/fuhang_booking/booking',
    method: 'put',
    data: data
  })
}

// 删除booking
export function delBooking(id) {
  return request({
    url: '/fuhang_booking/booking/' + id,
    method: 'delete'
  })
}

// 请求图形验证码
export function getgraphcode(phone){
  return request({
    url: '/fuhang_booking/graphcode/' + phone,
    method: 'get'
  })
}

// 请求短信验证码
export function getsmscode(graphCode,phone){
  return request({
    url: '/fuhang_booking/smscode/' + graphCode + '/' + phone,
    method: 'get'
  })
}
// 付款
export function pay(orderid){
  return request({
    url: '/fuhang_booking/pay/' + orderid,
    method: 'get'
  })
}

// 付款
export function transferorder(data){
  return request({
    url: '/fuhang_booking/transfer',
    method: 'post',
    data: data
  })
}



