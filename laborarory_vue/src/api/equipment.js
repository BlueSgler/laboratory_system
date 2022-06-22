import axios from 'axios'
export default {

    //获取设备列表
    getEquipments(data) {
        return axios({
            method: 'post',
            url: '/equipment/get/list',
            data: data
        })
            .then((response) => response)
            .catch((error => {
                console.log(error)
            }))
    },

    // 修改设备信息
    putEquipment(id,data) {
        return axios({
            headers: {
                'Content-Type': 'application/json;charset=UTF-8'
            },
            method: 'put',
            url: '/equipment/modify/one/'+id,
            data: data
        })
            .then((response) => response)
            .catch((error => {
                console.log(error)
            }))
    },


}