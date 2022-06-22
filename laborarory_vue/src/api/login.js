import axios from 'axios'
export default {

    //登录
    login(data) {
        return axios({
            method: 'post',
            data: data,
            url: '/user/login',
        })
            .then((response) => response)
            .catch((error => {
                console.log(error)
            }))
    },

}