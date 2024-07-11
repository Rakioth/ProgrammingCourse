<template>
    <StoreLayout>
        <table>
            <thead>
            <tr>
                <th>{{ $t("label.table.model") }}</th>
                <th>{{ $t("label.table.price") }}</th>
                <th style="width: 7rem;">{{ $t("label.table.units") }}</th>
                <th style="width: 4rem">{{ $t("label.table.remove") }}</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="detail in shoppingCart.shoppingCartDetails" :key="detail.product.id">
                <td>{{ detail.product.model }}</td>
                <td>{{ $t("product.price", {price: detail.product.price}) }}</td>
                <td>
                    <div class="glass-input units-cart">
                        <input type="number" min="1" id="units" :value="detail.units"
                               @input="modifyUnits(detail.product.id, $event.target.value)">
                    </div>
                </td>
                <td style="display: flex; justify-content: center; align-items: center;">
                    <button class="delete-button" @click="removeItem(detail.product.id)">
                        <svg viewBox="0 0 448 512">
                            <path fill="#000000"
                                  d="M135.2 17.7L128 32H32C14.3 32 0 46.3 0 64S14.3 96 32 96H416c17.7 0 32-14.3 32-32s-14.3-32-32-32H320l-7.2-14.3C307.4 6.8 296.3 0 284.2 0H163.8c-12.1 0-23.2 6.8-28.6 17.7zM416 128H32L53.2 467c1.6 25.3 22.6 45 47.9 45H346.9c25.3 0 46.3-19.7 47.9-45L416 128z"/>
                        </svg>
                    </button>
                </td>
            </tr>
            <tr>
                <td colspan="2">{{ $t("label.total-price") }}</td>
                <td colspan="2">{{ $t("product.price", {price: shoppingCart.price}) }}</td>
            </tr>
            </tbody>
        </table>
        <div class="cart-options">
            <button class="cart-button btn-inverse" @click="clearCart">{{ $t("label.clear-cart") }}</button>
            <button class="cart-button btn-inverse" @click="buyCart">{{ $t("label.buy-cart") }}</button>
        </div>
    </StoreLayout>
</template>

<script setup>
import StoreLayout            from '@/layouts/StoreLayout.vue'
import { onBeforeMount, ref } from 'vue'
import api                    from '@/api'
import { i18n }               from '@/main'

const shoppingCart = ref({})

const removeItem = async (itemId) => {
    if (confirm(i18n.global.t('confirm.remove-item'))) {
        await api.delete(`/shopping-carts/remove/${ itemId }`)
                 .finally(() => window.location.reload())
    }
}

const modifyUnits = async (itemId, units) => {
    if (units < 1) units = 1
    await api.put(`/shopping-carts/units/${ itemId }/${ units }`)
             .finally(() => window.location.reload())
}

const clearCart = async () => {
    if (confirm(i18n.global.t('confirm.clear-cart'))) {
        await api.get('/shopping-carts/clear')
                 .finally(() => window.location.reload())
    }
}

const buyCart = async () => {
    if (confirm(i18n.global.t('confirm.buy-cart'))) {
        await api.get('/shopping-carts/buy')
                 .finally(() => window.location.reload())
    }
}


onBeforeMount(async () =>
    api.get('/shopping-carts/access')
       .then(response => shoppingCart.value = response.data)
)

</script>

<style scoped lang="scss">

.cart-options {
    padding-top: 1rem;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
}

.cart-button {
    width: auto;
    height: 2.75rem;
    font-size: 1rem;
    background: var(--clr-black);
    border-radius: 0.5rem;
    border: none;
    outline: none;
    color: var(--clr-white);
    padding: 0.5rem 1rem;
    cursor: pointer;
}

.delete-button {
    @extend %glass-effect;
    display: flex;
    align-items: center;
    justify-content: center;
    width: 2.75rem;
    height: 2.75rem;
    //background: var(--clr-black);
    border-radius: 0.5rem;
    border: none;
    outline: none;
    color: var(--clr-white);
    padding: 0.8rem;
    cursor: pointer;
}

.units-cart {
    width: auto;
}

table {
    width: 100%;
    background: transparent;
    border: 0.125rem solid var(--clr-black);
    border-radius: 0.7rem;
    border-spacing: 0;
}

td {
    text-align: center;
    background-color: var(--clr-transin);
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

.btn-inverse {
    background-color: var(--clr-inversof);
    color: var(--clr-inverse);
}

</style>
