import { defineStore } from 'pinia'

export const equipment = defineStore({
    id: 'equipment',
    state: () => {
        return {
            editId: null,
            editInfo: {
                category: "",
                name: "",
                manufacturer: "",
                warrantyPeriod: "",
                specification: "",
                price: ""
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
                key: 'equipment_id',
                storage: localStorage,
            }]
    }
})