import axios from 'axios'
export default {

    //获取设备列表
    getRepair() {
        return axios({
            method: 'get',
            url: '/repair/select/total',
        })
            .then((response) => response)
            .catch((error => {
                console.log(error)
            }))
    },

    getEquipment(query) {
        return axios({
            params: query,
            method: 'get',
            url: '/equipment/get/numbers?field=' + query,
        })
            .then((response) => response)
            .catch((error => {
                console.log(error)
            }))
    },
    getApply() {
        return axios({
            method: 'get',
            url: '/apply/select/total',
        })
            .then((response) => response)
            .catch((error => {
                console.log(error)
            }))
    }


}