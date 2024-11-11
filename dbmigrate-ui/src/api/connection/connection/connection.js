import request from "@/utils/request";

// 查询数据源列表
export function listConnection(query) {
  return request({
    url: "/connection/connection/list",
    method: "get",
    params: query,
  });
}

// 查询数据源详细
export function getConnection(id) {
  return request({
    url: "/connection/connection/" + id,
    method: "get",
  });
}

// 新增数据源
export function addConnection(data) {
  return request({
    url: "/connection/connection",
    method: "post",
    data: data,
  });
}

// 修改数据源
export function updateConnection(data) {
  return request({
    url: "/connection/connection",
    method: "put",
    data: data,
  });
}

// 删除数据源
export function delConnection(id) {
  return request({
    url: "/connection/connection/" + id,
    method: "delete",
  });
}
