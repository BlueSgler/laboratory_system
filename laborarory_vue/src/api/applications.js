import axios from 'axios'
export default {

    //获取申请列表
    getApplications(query) {
        return axios({
            method: 'get',
            params: query,
            url: '/apply/select/condition',
        })
            .then((response) => response)
            .catch((error => {
                console.log(error)
            }))
    },

    // 修改申请信息
    putApplication(data) {
        return axios({
            method: 'post',
            url: '/apply/save/oneUser',
            data: data
        })
            .then((response) => response)
            .catch((error => {
                console.log(error)
            }))
    },

    // 删除单个申请
    deleteApplication(query) {
        return axios({
            method: 'delete',
            url: '/apply/delete/oneUser/' + query,
        })
            .then((response) => response)
            .catch((error => {
                console.log(error)
            }))
    },

    //导出申请表
    downLoadAppliacaion(id) {
        return axios({
            method: 'get',
            url: '/apply/download',
            responseType: 'blob',
            params: {
                id
            }
        })
            .then((response) => response)
            .catch((error => {
                console.log(error)
            }))
    }

}