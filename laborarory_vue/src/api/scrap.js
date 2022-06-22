import axios from 'axios'
export default {

    //获取报废列表
    getScrapRecords(query) {
        return axios({
            method: 'get',
            params: query,
            url: '/scrap/select/condition',
        })
            .then((response) => response)
            .catch((error => {
                console.log(error)
            }))
    },

    //通过id获取报废记录
    getScrapRecordById(query) {
        return axios({
            method: 'get',
            url: '/scrap/select/oneUser/' + query
        })
            .then((response) => response)
            .catch((error => {
                console.log(error)
            }))
    },

    //添加报废记录
    addScrap(data) {
        return axios({
            method: 'post',
            url: '/scrap/insert/oneUser',
            data: data
        })
            .then((response) => response)
            .catch((error => {
                console.log(error)
            }))
    },

    // 修改报废信息
    putScrapRecord(id, data) {
        return axios({
            method: 'post',
            url: '/scrap/update/oneUser/' + id,
            data: data
        })
            .then((response) => response)
            .catch((error => {
                console.log(error)
            }))
    },

    // 删除单个报废记录
    deleteScrapRecord(query) {
        return axios({
            method: 'delete',
            url: '/scrap/delete/oneUser/' + query,
        })
            .then((response) => response)
            .catch((error => {
                console.log(error)
            }))
    },

}