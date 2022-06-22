import { defineStore } from 'pinia'

export const application = defineStore({
    id: 'application',
    state: () => {
        return {
            editId: null,
            editInfo: {
                category: "",
                name: "",
                manufacturer: "",
                warrantyPeriod: null,
                specification: "hss",
                price: null,
                applicant: "",
                approverName: "",
                applyStatus: "",
                id: null,
                reason: null
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