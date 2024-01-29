<template>
    <FormLayout startNumber="2" finishNumber="3">
        <form @submit.prevent="verifyUser">
            <h2>{{ $t("register-page.verify.header") }}</h2>

            <div id="code-hint">
                <h3>{{ $t("register-page.verify.sent-code") }}</h3>
                <span>{{ store.registerForm.data?.email }}</span>
            </div>

            <div id="glass-code">
                <div class="column-wrapper">
                    <label>{{ $t("register-page.verify.hint") }}</label>
                    <a @click="store.resendConfirmation()">{{ $t("register-page.verify.resend") }}</a>
                </div>
                <div id="input-code">
                    <input @input="focusNextInput" type="text" required="required" maxlength="1" pattern="[0-9]"
                           v-model="code1">
                    <input @input="focusNextInput" type="text" required="required" maxlength="1" pattern="[0-9]"
                           v-model="code2">
                    <input @input="focusNextInput" type="text" required="required" maxlength="1" pattern="[0-9]"
                           v-model="code3">
                    <input @input="focusNextInput" type="text" required="required" maxlength="1" pattern="[0-9]"
                           v-model="code4">
                </div>
                <ul v-if="store.errors.code" class="errors">
                    <li v-for="error in store.errors.code" :key="error">{{ $t(error) }}</li>
                </ul>
            </div>
            <SpinnerButton :loading="store.loading" :label="$t('label.button')"/>
        </form>
    </FormLayout>
</template>

<script setup>
import { onBeforeMount, ref } from 'vue'
import FormLayout             from '@/layouts/FormLayout.vue'
import useAuth                from '@/store/auth'
import SpinnerButton          from '@/components/core/SpinnerButton.vue'
import api                    from '@/api'
import router                 from '@/router'

const store = useAuth()
const code1 = ref('')
const code2 = ref('')
const code3 = ref('')
const code4 = ref('')

const focusNextInput = (event) => {
    const nextInput = event.currentTarget.nextElementSibling

    if (nextInput)
        nextInput.focus()
}

const verifyUser = async () => store.verify(`${ code1.value }${ code2.value }${ code3.value }${ code4.value }`)

onBeforeMount(async () => {
    api.get('/auth/form/register')
       .then(response => {
           store.registerForm = response.data
           if (store.registerForm.step !== 'VerifyView')
               router.push({name: 'RegisterView'})
       })
})


</script>

<style scoped lang="scss">

a {
    cursor: pointer;
}

#glass-code {
    display: flex;
    flex-direction: column;
    row-gap: 0.75rem;
    font-size: 0.875rem;
}

#code-hint {
    display: flex;
    flex-direction: column;
    row-gap: 0.5rem;
    font-size: 0.875rem;
}

#input-code {
    display: flex;
    flex-direction: row;
    column-gap: 0.5rem;

    input {
        width: 5.9375rem;
        height: 2.75rem;
        background: transparent;
        border: 0.125rem solid var(--clr-black);
        border-radius: 0.5rem;
        outline: none;
        text-align: center;
        text-transform: uppercase;
    }
}

</style>