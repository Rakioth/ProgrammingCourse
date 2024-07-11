<template>
    <FormLayout startNumber="1" finishNumber="3">
        <form @submit.prevent="clientInfo">
            <h2>{{ $t("client-page.header-data") }}</h2>

            <div class="grid-of-2">

                <div class="glass-input">
                    <label for="name">{{ $t("client-page.label.name") }}</label>
                    <input type="text" id="name" required="required" v-model="name">
                    <ul v-if="store.errors.name" class="errors">
                        <li v-for="error in store.errors.name" :key="error">{{ $t(error) }}</li>
                    </ul>
                </div>

                <div class="glass-input">
                    <label for="surnames">{{ $t("client-page.label.surnames") }}</label>
                    <input type="text" id="surnames" required="required" v-model="surnames">
                    <ul v-if="store.errors.surnames" class="errors">
                        <li v-for="error in store.errors.surnames" :key="error">{{ $t(error) }}</li>
                    </ul>
                </div>

                <div class="glass-input">
                    <label for="gender">{{ $t("client-page.label.gender") }}</label>
                    <select style="width: auto" class="glass-select" v-model="gender">
                        <option :value="genderType.code" v-for="genderType in genderList" :key="genderType.code">
                            {{ $t(genderType.ref) }}
                        </option>
                    </select>
                    <ul v-if="store.errors.genderCode" class="errors">
                        <li v-for="error in store.errors.genderCode" :key="error">{{ $t(error) }}</li>
                    </ul>
                </div>

                <div class="glass-input">
                    <label for="birthdate">{{ $t("client-page.label.birthdate") }}</label>
                    <input type="date" id="birthdate" required="required" v-model="birthdate">
                    <ul v-if="store.errors.birthdate" class="errors">
                        <li v-for="error in store.errors.birthdate" :key="error">{{ $t(error) }}</li>
                    </ul>
                </div>

            </div>

            <SpinnerButton :loading="store.loading" :label="$t('client-page.go-next')"/>
        </form>
    </FormLayout>
</template>

<script setup>
import { onBeforeMount, ref } from 'vue'
import FormLayout             from '@/layouts/FormLayout.vue'
import useAuth                from '@/store/auth'
import SpinnerButton          from '@/components/core/SpinnerButton.vue'
import api                    from '@/api'
import { useRoute }           from 'vue-router'

const store = useAuth()
const genderList = ref([])

const name = ref(store.clientForm.data?.name)
const surnames = ref(store.clientForm.data?.surnames)
const gender = ref(store.clientForm.data?.gender_code)
const birthdate = ref(store.clientForm.data?.birthdate)

const clientInfo = async () =>
    store.clientInfo(name.value, surnames.value, gender.value, birthdate.value)

onBeforeMount(async () => {
    api.get('/genders')
       .then(response => genderList.value = response.data)

    await store.formClient(useRoute().name, 1)
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

</style>