import { defineStore } from 'pinia'

export const login = defineStore({
    id: 'login',
    state: () => {
        return {
            loginForm: {
                username: '',
                password: ''
            }

        }
    },
    getters: {

    },
    actions: {
        // changeAge(val) {
        //     this.age += val
        // }

    },
})