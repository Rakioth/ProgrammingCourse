<template>
    <FormLayout startNumber="1" finishNumber="2">
        <form @submit.prevent="loginUser">
            <div class="column-wrapper">
                <h2 id="login-select">{{ $t("login-page.login.header") }}</h2>
                <select style="width: auto" class="glass-select" v-if="$cookies.get('_luser') != null" v-model="account"
                        @change="loginAccount">
                    <option value="">{{ $t("label.account") }}</option>
                    <option v-for="username in $cookies.get('_luser').split('~')" :key="username" :value="username">
                        {{ username }}
                    </option>
                </select>
            </div>

            <div class="glass-input">
                <label for="username">{{ $t("label.username") }}</label>
                <input type="text" id="username" required="required" v-model="username">
                <ul v-if="store.errors.username" class="errors">
                    <div v-for="error in store.errors.username" :key="error">
                        <li v-if="error.includes('error')">{{ $t(error) }}</li>
                        <li v-else>{{ $t("error.username.block", {minutes: error}) }}</li>
                    </div>
                </ul>
            </div>

            <SpinnerButton :loading="store.loading" :label="$t('label.button')"/>

            <span>
                {{ $t("login-page.login.hint-dont-have") }}
                <router-link :to="{name: 'RegisterView'}">{{ $t("login-page.login.hint-sign-up") }}</router-link>
            </span>
        </form>
    </FormLayout>
</template>

<script setup>
import { onBeforeMount, ref } from 'vue'
import FormLayout             from '@/layouts/FormLayout.vue'
import useAuth                from '@/store/auth'
import SpinnerButton          from '@/components/core/SpinnerButton.vue'
import api                    from '@/api'

const store = useAuth()
const username = ref('')
const account = ref('')

const loginUser = async () => store.login(username.value)
const loginAccount = async () => store.login(account.value)

onBeforeMount(async () => api.get('/auth/form/login'))


</script>

<style scoped lang="scss">

#login-select {
    width: auto;
}

span {
    align-self: center;
}

</style>