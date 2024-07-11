<template>
    <FormLayout startNumber="1" finishNumber="3">
        <form @submit.prevent="registerUser">
            <h2>{{ $t("register-page.register.header") }}</h2>
            <div class="glass-input">
                <label for="email">{{ $t("label.email") }}</label>
                <input type="email" id="email" required="required" v-model="email">
                <ul v-if="store.errors.email" class="errors">
                    <li v-for="error in store.errors.email" :key="error">{{ $t(error) }}</li>
                </ul>
            </div>
            <SpinnerButton :loading="store.loading" :label="$t('label.button')"/>
        </form>
    </FormLayout>
</template>

<script setup>
import { onBeforeMount, ref } from 'vue'
import FormLayout             from '@/layouts/FormLayout.vue'
import SpinnerButton          from '@/components/core/SpinnerButton.vue'
import useAuth                from '@/store/auth'
import api                    from '@/api'

const store = useAuth()
const email = ref('')
const registerUser = async () => store.register(email.value)

onBeforeMount(async () => api.get('/auth/form/register'))

</script>

<style scoped lang="scss">


</style>