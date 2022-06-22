import { defineStore } from 'pinia'

export const repair = defineStore({
    id: 'repair',
    state: () => {
        return {
            editId: null,
            editInfo: {
                equipmentId: null,
                equipmentName: "",
                repairFactory: "",
                repairPrice: null,
                principal: "",
                status: null,
                repairTime: ''
            },
            addForm: {
                equipmentId: null,
                repairFactory: "",
                repairPrice: null,
                principal: "",
                equipmentName: "",
                repairTime: ""
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
                key: 'repair_id',
                storage: localStorage,
            }]
    }
})