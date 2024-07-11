<template>
    <StoreLayout>
        <form @submit.prevent="filterOrders" class="consult">
            <div class="opa">
                <div class="glass-input">
                    <label for="startDate">{{ $t("label.start-date") }}</label>
                    <input type="datetime-local" id="startDate" v-model="startDate">
                </div>
                <div class="glass-input">
                    <label for="endDate">{{ $t("label.end-date") }}</label>
                    <input type="datetime-local" id="endDate" v-model="endDate">
                </div>
                <div class="glass-input">
                    <label for="orderState">{{ $t("label.order-state") }}</label>
                    <select style="width: auto" class="glass-select" v-model="orderState">
                        <option value="">{{ $t("label.option-state") }}</option>
                        <option :value="orderS.code" v-for="orderS in orderStateList" :key="orderS.code">
                            {{ $t(orderS.ref) }}
                        </option>
                    </select>
                </div>

                <div class="glass-input">
                    <label for="minPrice">{{ $t("label.min-price") }}</label>
                    <input type="number" step="any" id="minPrice" v-model="minPrice">
                </div>
                <div class="glass-input">
                    <label for="maxPrice">{{ $t("label.max-price") }}</label>
                    <input type="number" step="any" id="maxPrice" v-model="maxPrice">
                </div>
            </div>
            <SpinnerButton class="button-search" :loading="loading" :label="$t('label.filter')"/>

        </form>

        <table>
            <thead>
            <tr>
                <th>{{ $t("label.table.order-date") }}</th>
                <th>{{ $t("label.table.order-state") }}</th>
                <th>{{ $t("label.table.total-price") }}</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="order in orders" :key="order.id">
                <td>{{ order.date }}</td>
                <td>{{ $t(order.state) }}</td>
                <td>{{ $t("product.price", {price: order.totalPrice}) }}</td>
            </tr>
            </tbody>
        </table>
    </StoreLayout>
</template>

<script setup>
import StoreLayout            from '@/layouts/StoreLayout.vue'
import SpinnerButton          from '@/components/core/SpinnerButton.vue'
import { onBeforeMount, ref } from 'vue'
import api                    from '@/api'

const startDate = ref('')
const endDate = ref('')
const orderState = ref('')
const minPrice = ref('')
const maxPrice = ref('')

const orderStateList = ref([])

const loading = ref(false)
const orders = ref([])

onBeforeMount(async () => {
    api.get('/order-states')
       .then(response => orderStateList.value = response.data)

    api.get('/orders/access')
       .then(response => orders.value = response.data)
})

const filterOrders = async () => {
    loading.value = true
    api.post('/orders/filter', {
        'user_id'    : 0,
        'start_date' : startDate.value,
        'end_date'   : endDate.value,
        'order_state': orderState.value || null,
        'min_price'  : minPrice.value,
        'max_price'  : maxPrice.value
    }).then(response => orders.value = response.data)
       .finally(() => loading.value = false)
}
</script>

<style scoped lang="scss">

.button-search {
    align-self: center;
    width: 100%;
}

.consult {
    padding: 1.6rem;
    margin-bottom: 1rem;
    display: flex;
    flex-direction: column;
    row-gap: 1rem;
}

.opa {
    display: flex;
    flex-direction: row;
    column-gap: 1rem;
}

table {
    width: 100%;
    background: transparent;
    border: 0.125rem solid var(--clr-black);
    border-radius: 0.7rem;
    border-spacing: 0;
}

td {
    background-color: var(--clr-transin);
    text-align: center;
}

th, td {
    padding: 0.5rem 1rem;
    border: 0.125rem solid var(--clr-black);
}

th {
    @extend %glass-effect;
    color: var(--clr-black);
    padding: 1rem;
}

thead tr th:first-child {
    border-top-left-radius: 0.5rem;
}

thead tr th:last-child {
    border-top-right-radius: 0.5rem;
}

tbody tr:last-child td:first-child {
    border-bottom-left-radius: 0.5rem;
}

tbody tr:last-child td:last-child {
    border-bottom-right-radius: 0.5rem;
}

</style>
