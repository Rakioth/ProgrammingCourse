<template>
    <FormLayout startNumber="3" finishNumber="3">
        <form @submit.prevent="clientOther">
            <h2>{{ $t("client-page.header-other") }}</h2>

            <div class="grid-of-2">

                <div class="glass-input">
                    <label for="cardType">{{ $t("client-page.label.credit-card") }}</label>
                    <select v-model="cardType" style="width: auto" class="glass-select">
                        <option :value="card.code" v-for="card in cardTypeList"
                                :key="card.code">
                            {{ $t(card.ref) }}
                        </option>
                    </select>
                    <ul v-if="store.errors.cardTypeCode" class="errors">
                        <li v-for="error in store.errors.cardTypeCode" :key="error">{{ $t(error) }}</li>
                    </ul>
                </div>

                <div class="glass-input">
                    <label for="cardNumber">{{ $t("client-page.label.card-number") }}</label>
                    <input type="number" id="cardNumber" required="required" v-model="cardNumber">
                    <ul v-if="store.errors.cardNumber" class="errors">
                        <li v-for="error in store.errors.cardNumber" :key="error">{{ $t(error) }}</li>
                    </ul>
                </div>

                <div class="glass-input">
                    <label for="cardCcv">{{ $t("client-page.label.card-ccv") }}</label>
                    <input type="number" id="cardCvv" required="required" v-model="cardCcv">
                    <ul v-if="store.errors.cardCcv" class="errors">
                        <li v-for="error in store.errors.cardCcv" :key="error">{{ $t(error) }}</li>
                    </ul>
                </div>

                <div class="glass-input">
                    <label for="cardExpiredDate">{{ $t("client-page.label.expired-date") }}</label>
                    <input type="date" id="cardExpiredDate" required="required" v-model="cardExpiredDate">
                    <ul v-if="store.errors.cardExpiredDate" class="errors">
                        <li v-for="error in store.errors.cardExpiredDate" :key="error">{{ $t(error) }}</li>
                    </ul>
                </div>

                <div class="glass-input" style="grid-column: span 2;">
                    <label for="comments">{{ $t("client-page.label.comments") }}</label>
                    <textarea v-model="comments"></textarea>
                    <ul v-if="store.errors.comments" class="errors">
                        <li v-for="error in store.errors.comments" :key="error">{{ $t(error) }}</li>
                    </ul>
                </div>

                <div class="glass-checkbox"
                     style="grid-column: span 2; align-self: center; justify-self: center; flex-direction: column">
                    <div style="display: flex; column-gap: 0.5rem">
                        <input id="license" type="checkbox" v-model="license"/>
                        <label for="license">{{ $t("client-page.label.license") }}</label>
                    </div>
                    <ul v-if="store.errors.license" class="errors">
                        <li v-for="error in store.errors.license" :key="error">{{ $t(error) }}</li>
                    </ul>
                </div>

            </div>
            <div class="form-buttons">
                <router-link :to="{name: 'FormDetailsView'}" class="my-button">
                    {{ $t("client-page.go-back") }}
                </router-link>
                <SpinnerButton :loading="store.loading" :label="$t('client-page.send')"/>
            </div>
        </form>
    </FormLayout>
</template>

<script setup>
import { onBeforeMount, ref } from 'vue'
import FormLayout             from '@/layouts/FormLayout.vue'
import useAuth                from '@/store/auth'
import SpinnerButton          from '@/components/core/SpinnerButton.vue'
import { useRoute }           from 'vue-router'
import api                    from '@/api'

const store = useAuth()
const cardTypeList = ref([])

const cardType = ref(store.clientForm.data?.card_type)
const cardNumber = ref(store.clientForm.data?.card_number)
const cardCcv = ref(store.clientForm.data?.card_ccv)
const cardExpiredDate = ref(store.clientForm.data?.card_expired_date)
const comments = ref(store.clientForm.data?.comments)
const license = ref(store.clientForm.data?.license)

const clientOther = async () =>
    store.clientOther(cardType.value, cardNumber.value, cardCcv.value, cardExpiredDate.value, comments.value, license.value)

onBeforeMount(async () => {
    api.get('/card-types')
       .then(response => cardTypeList.value = response.data)

    await store.formClient(useRoute().name, 3)
})

</script>

<style scoped lang="scss">

.grid-of-2 {
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    grid-template-rows: repeat(2, 1fr);
    row-gap: 2rem;
    column-gap: 1rem;

}

.form-buttons {
    display: flex;
    column-gap: 1rem;
}

</style>