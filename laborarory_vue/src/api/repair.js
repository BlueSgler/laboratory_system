import axios from 'axios'
export default {

    //获取设备列表
    getRepairRecords(query) {
        return axios({
            method: 'get',
            params: query,
            url: '/repair/selectByCondition',
        })
            .then((response) => response)
            .catch((error => {
                console.log(error)
            }))
    },

    //通过id修理记录
    getRepairRecordById(query) {
        return axios({
            method: 'get',
            url: '/repair/select/one/' + query
        })
            .then((response) => response)
            .catch((error => {
                console.log(error)
            }))
    },

    //添加修理记录
    addRepair(data) {
        return axios({
            method: 'post',
            url: '/repair/insert',
            data: data
        })
            .then((response) => response)
            .catch((error => {
                console.log(error)
            }))
    },

    // 修改修理记录
    putRepairRecord(id, data) {
        return axios({
            method: 'post',
            url: '/repair/update/' + id,
            data: data
        })
            .then((response) => response)
            .catch((error => {
                console.log(error)
            }))
    },

    // 删除单个修理记录
    deleteRepairRecord(query) {
        return axios({
            method: 'delete',
            url: '/repair/delete/' + query,
        })
            .then((response) => response)
            .catch((error => {
                console.log(error)
            }))
    },


}