import { defineStore } from 'pinia'

export const scrap = defineStore({
    id: 'scrap',
    state: () => {
        return {
            editId: null,
            editInfo: {
                equipmentId: null,
                handler: null,
                reason: null,
                category: null,
                name: null,
                scrapTime: '',
                specification: ''
            },
            addForm: {
                equipmentId: null,
                handler: null,
                reason: null,
                category: null,
                name: '',
                scrapTime: '',
                specification: ''
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
    // 开启数据缓存
    persist: {
        enabled: true,
        strategies: [
            {
                key: 'scrap_id',
                storage: localStorage,
            }]
    }
})