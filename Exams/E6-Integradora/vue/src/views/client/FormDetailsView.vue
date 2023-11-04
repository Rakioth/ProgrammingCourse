<template>
    <FormLayout startNumber="2" finishNumber="3">
        <form @submit.prevent="clientDetails">
            <h2>{{ $t("client-page.header-details") }}</h2>

            <div class="grid-of-2">

                <div class="glass-input" style="grid-column: span 2;">
                    <label for="documentType">{{ $t("client-page.label.document-type") }}</label>
                    <select v-model="documentType" style="width: auto;" class="glass-select">
                        <option :value="documentType.code" v-for="documentType in documentTypeList"
                                :key="documentType.code">
                            {{ $t(documentType.ref) }}
                        </option>
                    </select>
                    <ul v-if="store.errors.documentTypeCode" class="errors">
                        <li v-for="error in store.errors.documentTypeCode" :key="error">{{ $t(error) }}</li>
                    </ul>
                </div>

                <div class="glass-input" style="grid-column: span 2;">
                    <label for="document">{{ $t("client-page.label.document") }}</label>
                    <input type="text" id="document" required="required" v-model="document">
                    <ul v-if="store.errors.document" class="errors">
                        <li v-for="error in store.errors.document" :key="error">{{ $t(error) }}</li>
                    </ul>
                </div>

                <div class="glass-input" style="grid-column: span 2;">
                    <label for="countryCode">{{ $t("client-page.label.country") }}</label>
                    <select v-model="countryCode" style="width: auto" class="glass-select">
                        <option :value="country.code" v-for="country in countryList"
                                :key="country.code">
                            {{ $t(country.ref) }}
                        </option>
                    </select>
                    <ul v-if="store.errors.countryTypeCode" class="errors">
                        <li v-for="error in store.errors.countryTypeCode" :key="error">{{ $t(error) }}</li>
                    </ul>
                </div>

                <div class="glass-input" style="grid-column: span 2;">
                    <label for="phoneNumber">{{ $t("client-page.label.phone-number") }}</label>
                    <input type="text" id="phoneNumber" required="required" v-model="phoneNumber">
                    <ul v-if="store.errors.phoneNumber" class="errors">
                        <li v-for="error in store.errors.phoneNumber" :key="error">{{ $t(error) }}</li>
                    </ul>
                </div>

                <div class="flex-wrapper">
                    <div class="glass-input" style="width: 10rem">
                        <label for="addrVia">{{ $t("client-page.label.address") }}</label>
                        <select v-model="addrVia" style="width: auto" class="glass-select">
                            <option :value="via.code" v-for="via in viaTypeList"
                                    :key="via.code">
                                {{ $t(via.ref) }}
                            </option>
                        </select>
                        <ul v-if="store.errors.addrViaCode" class="errors">
                            <li v-for="error in store.errors.addrViaCode" :key="error">{{ $t(error) }}</li>
                        </ul>
                    </div>

                    <div class="glass-input">
                        <label for="addrNumber">{{ $t("client-page.label.number") }}</label>
                        <input type="number" id="addrNumber" required="required" v-model="addrNumber">
                        <ul v-if="store.errors.addrNumber" class="errors">
                            <li v-for="error in store.errors.addrNumber" :key="error">{{ $t(error) }}</li>
                        </ul>
                    </div>

                    <div class="glass-input">
                        <label for="addrPortal">{{ $t("client-page.label.portal") }}</label>
                        <input type="text" id="addrPortal" required="required" v-model="addrPortal">
                        <ul v-if="store.errors.addrPortal" class="errors">
                            <li v-for="error in store.errors.addrPortal" :key="error">{{ $t(error) }}</li>
                        </ul>
                    </div>

                    <div class="glass-input">
                        <label for="addrFloor">{{ $t("client-page.label.floor") }}</label>
                        <input type="text" id="addrFloor" required="required" v-model="addrFloor">
                        <ul v-if="store.errors.addrFloor" class="errors">
                            <li v-for="error in store.errors.addrFloor" :key="error">{{ $t(error) }}</li>
                        </ul>
                    </div>
                </div>


                <div class="glass-input" style="grid-column: span 2;">
                    <label for="addrLocality">{{ $t("client-page.label.locality") }}</label>
                    <input type="text" id="addrLocality" required="required" v-model="addrLocality">
                    <ul v-if="store.errors.addrLocality" class="errors">
                        <li v-for="error in store.errors.addrLocality" :key="error">{{ $t(error) }}</li>
                    </ul>
                </div>

                <div class="glass-input" style="grid-column: span 2;">
                    <label for="addrRegion">{{ $t("client-page.label.region") }}</label>
                    <input type="text" id="addrRegion" required="required" v-model="addrRegion">
                    <ul v-if="store.errors.addrRegion" class="errors">
                        <li v-for="error in store.errors.addrRegion" :key="error">{{ $t(error) }}</li>
                    </ul>
                </div>

                <div class="glass-input" style="grid-column: span 2;">
                    <label for="addrName">{{ $t("client-page.label.address-name") }}</label>
                    <input type="text" id="addrName" required="required" v-model="addrName">
                    <ul v-if="store.errors.addrName" class="errors">
                        <li v-for="error in store.errors.addrName" :key="error">{{ $t(error) }}</li>
                    </ul>
                </div>

                <div class="glass-input" style="grid-column: span 2;">
                    <label for="addrPostalCode">{{ $t("client-page.label.postal-code") }}</label>
                    <input type="text" id="addrPostalCode" required="required" v-model="addrPostalCode">
                    <ul v-if="store.errors.addrPostalCode" class="errors">
                        <li v-for="error in store.errors.addrPostalCode" :key="error">{{ $t(error) }}</li>
                    </ul>
                </div>


            </div>
            <div class="form-buttons">
                <router-link :to="{name: 'FormInfoView'}" class="my-button">
                    {{ $t("client-page.go-back") }}
                </router-link>
                <SpinnerButton :loading="store.loading" :label="$t('client-page.go-next')"/>
            </div>
        </form>
    </FormLayout>
</template>

<script setup>
import { onBeforeMount, ref } from 'vue'
import FormLayout             from '@/layouts/FormLayout.vue'
import SpinnerButton          from '@/components/core/SpinnerButton.vue'
import useAuth                from '@/store/auth'
import api                    from '@/api'
import { useRoute }           from 'vue-router'

const store = useAuth()
const documentTypeList = ref([])
const countryList = ref([])
const viaTypeList = ref([])

const documentType = ref(store.clientForm.data?.document_type)
const document = ref(store.clientForm.data?.document)
const countryCode = ref(store.clientForm.data?.country_type)
const phoneNumber = ref(store.clientForm.data?.phone_number)
const addrVia = ref(store.clientForm.data?.addr_via)
const addrName = ref(store.clientForm.data?.addr_name)
const addrNumber = ref(store.clientForm.data?.addr_number)
const addrPortal = ref(store.clientForm.data?.addr_portal)
const addrFloor = ref(store.clientForm.data?.addr_floor)
const addrLocality = ref(store.clientForm.data?.addr_locality)
const addrRegion = ref(store.clientForm.data?.addr_region)
const addrPostalCode = ref(store.clientForm.data?.addr_postal_code)
const clientDetails = async () =>
    store.clientDetails(documentType.value, document.value, countryCode.value, phoneNumber.value, addrVia.value, addrName.value, addrNumber.value, addrPortal.value, addrFloor.value, addrLocality.value, addrRegion.value, addrPostalCode.value)


onBeforeMount(async () => {
    api.get('/client-documents')
       .then(response => documentTypeList.value = response.data)
    api.get('/country-types')
       .then(response => countryList.value = response.data)
    api.get('/via-types')
       .then(response => viaTypeList.value = response.data)

    await store.formClient(useRoute().name, 2)
})

</script>

<style scoped lang="scss">

.grid-of-2 {
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    grid-template-rows: repeat(4, 1fr);
    row-gap: 2rem;
    column-gap: 1rem;

}

.form-buttons {
    display: flex;
    column-gap: 1rem;
}

.flex-wrapper {
    grid-column: span 4;
    display: flex;
    column-gap: 1rem;
}

</style>