<template>
    <FormLayout>
        <form @submit.prevent="forgotUser">
            <h2 v-if="store.forgoter">{{ $t("login-page.forgot.commit-header") }}</h2>
            <p v-if="store.forgoter" id="hint-forgot">
                {{ $t("login-page.forgot.commit-hint") }}
                <a @click="store.resendRecovery()">{{ $t("login-page.forgot.commit-resend") }}</a>
                , or
                <a @click="toggle">{{ $t("login-page.forgot.commit-different") }}</a>
                .
            </p>
            <h2 v-if="!store.forgoter">{{ $t("login-page.forgot.header") }}</h2>
            <p v-if="!store.forgoter" id="hint-forgot">{{ $t("login-page.forgot.hint") }}</p>

            <div v-if="!store.forgoter" class="glass-input">
                <label for="email">{{ $t("label.email") }}</label>
                <input type="email" id="email" required="required" v-model="email">
                <ul v-if="store.errors.email" class="errors">
                    <li v-for="error in store.errors.email" :key="error">{{ $t(error) }}</li>
                </ul>
            </div>

            <SpinnerButton v-if="!store.forgoter" :loading="store.loading" :label="$t('label.button')"/>

            <span v-if="!store.forgoter">
                <router-link :to="{name: 'LoginView'}">{{ $t("login-page.forgot.return") }}</router-link>
                </span>
        </form>
    </FormLayout>
</template>

<script setup>
import { ref }       from 'vue'
import FormLayout    from '@/layouts/FormLayout.vue'
import useAuth       from '@/store/auth'
import SpinnerButton from '@/components/core/SpinnerButton.vue'

const store = useAuth()
const email = ref('')

const forgotUser = async () => store.forgot(email.value)

const toggle = () => store.forgoter = !store.forgoter

</script>

<style scoped lang="scss">

a {
    cursor: pointer;
}

#hint-forgot {
    font-size: 0.875rem;
}

</style>