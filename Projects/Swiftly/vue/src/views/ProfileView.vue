<template>
    <StoreLayout>
        <div class="main-content">
            <div class="main-content">
                <div v-if="profile && Object.keys(profile).length !== 0" class="profile-detail">
                    <div class="profile-left">
                        <div class="profile-image">
                            <img src="https://www.pngkit.com/png/full/302-3022217_roger-berry-avatar-placeholder.png"/>
                        </div>
                        <div class="profile-body">
                            <h3>{{ profile.name }}</h3>
                            <h5>{{ profile.surnames }}</h5>
                            <h3>{{ $t("profile-page.client-type") }}</h3>
                            <h5>{{ $t(profile.type.ref) }}</h5>
                            <h3>{{ $t("profile-page.address") }}</h3>
                            <h5>{{
                                $t("address", {
                                  via       : $t(profile.address.via.ref),
                                  name      : profile.address.name,
                                  number    : profile.address.number,
                                  floor     : profile.address.floor,
                                  portal    : profile.address.portal,
                                  postalCode: profile.address.postalCode,
                                  locality  : profile.address.locality,
                                  region    : profile.address.region
                                })
                                }}</h5>
                        </div>
                    </div>
                    <div class="profile-right">
                        <h4>{{ $t("profile-page.username") }}</h4>
                        <p style="word-wrap: break-word">{{ profile.user.username }}</p>
                        <h4>{{ $t("profile-page.email") }}</h4>
                        <p style="word-wrap: break-word">{{ profile.user.email }}</p>
                        <h4>{{ $t("profile-page.gender") }}</h4>
                        <p style="word-wrap: break-word">{{ $t(profile.gender) }}</p>
                        <h4>{{ $t("profile-page.birthdate") }}</h4>
                        <p style="word-wrap: break-word">{{ profile.birthdate }}</p>
                        <h4>{{ $t("profile-page.country") }}</h4>
                        <p style="word-wrap: break-word">{{ $t(profile.countryCode.ref) }}</p>
                        <h4>{{ $t("profile-page.phone-number") }}</h4>
                        <p style="word-wrap: break-word">{{ profile.phoneNumber }}</p>
                    </div>
                </div>
                <SpinnerButton class="my-button btn-inverse" :loading="loading" :label="$t('profile-page.remove-button')"
                               @click="open"/>
            </div>
        </div>
        <div class="modal-window">
            <form style="background-color: white">
                <h3>{{ $t("profile-page.remove-confirm") }}</h3>
                <div class="client-options">
                    <a class="modal-button" style="background-color: #d81e5b" @click="hide">
                        <svg viewBox="0 0 384 512">
                            <path d="M342.6 150.6c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0L192 210.7 86.6 105.4c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3L146.7 256 41.4 361.4c-12.5 12.5-12.5 32.8 0 45.3s32.8 12.5 45.3 0L192 301.3 297.4 406.6c12.5 12.5 32.8 12.5 45.3 0s12.5-32.8 0-45.3L237.3 256 342.6 150.6z"/>
                        </svg>
                    </a>
                    <a class="modal-button" style="background-color: #13a987 " @click="commit">
                        <svg viewBox="0 0 448 512">
                            <path d="M438.6 105.4c12.5 12.5 12.5 32.8 0 45.3l-256 256c-12.5 12.5-32.8 12.5-45.3 0l-128-128c-12.5-12.5-12.5-32.8 0-45.3s32.8-12.5 45.3 0L160 338.7 393.4 105.4c12.5-12.5 32.8-12.5 45.3 0z"/>
                        </svg>
                    </a>
                </div>
            </form>
        </div>
    </StoreLayout>
</template>

<script setup>
import StoreLayout            from '@/layouts/StoreLayout.vue'
import { onBeforeMount, ref } from 'vue'
import api                    from '@/api'
import app                    from '@/main'
import SpinnerButton          from '@/components/core/SpinnerButton.vue'

const profile = ref({})
const loading = ref(false)
const hide = () => {
    const modal = document.querySelector('.modal-window')
    modal.style.visibility = 'hidden'
    modal.style.opacity = '0'
}
const open = () => {
    const modal = document.querySelector('.modal-window')
    modal.style.visibility = 'visible'
    modal.style.opacity = '1'
}
const commit = async () => {
    loading.value = true
    api.put('/clients/destroy')
       .finally(() => {
           loading.value = false
           app.$cookies.remove('access_token')
           window.location.reload()
       })
}

onBeforeMount(async () =>
    api.get('/clients/access')
       .then(response => profile.value = response.data)
)

</script>

<style scoped lang="scss">

main {
    padding-top: 1rem;
    max-width: 67.5rem;
    height: 20rem;
    width: 90%;
    margin-left: auto;
    margin-right: auto;

    a {
        color: inherit;
        text-decoration: inherit;
    }
}

.main-content {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    row-gap: 1rem;
}

.profile-body {
    display: flex;
    flex-direction: column;
    row-gap: 0.4rem;
}

.profile-detail {
    @extend %glass-effect;
    color: var(--font-inverse-color);
    display: flex;
    flex-direction: row;
    border-radius: 0.5rem;
    height: auto;

    > * {
        padding: 1.5rem;
        flex-basis: 100%;
    }

}

.profile-right {
    width: 20rem;
    display: flex;
    flex-direction: column;
    row-gap: 0.4rem;
}


.profile-left {
    position: relative;
    display: flex;
    flex-direction: column;
    border-radius: 0.5rem;
    width: 20rem;
    row-gap: 0.4rem;
    height: auto;

    .profile-image {
        display: flex;
        align-items: center;
        justify-content: center;
        height: 11.9rem;

        img {
            width: 100%;
            height: 100%;
            object-fit: contain;
        }
    }


    .profile-buy {
        height: 2.5rem;
        width: 2.5rem;
        padding: 0.5rem;
        border-radius: 0.5rem;
        border: none;
        cursor: pointer;
        background: var(--clr-green);

        path {
            fill: var(--font-color);
        }
    }
}

.modal-window {
    position: absolute;
    display: flex;
    top: 0;
    left: 0;
    background-color: rgba(255, 255, 255, 0.25);
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 100%;
    z-index: 999;
    visibility: hidden;
    opacity: 0;
    transition: all 0.3s;
}

.modal-window > div {
    display: flex;
    flex-direction: column;
    row-gap: 2rem;
    border-radius: 0.5rem;
    padding: 3.5rem 3rem;
    background-color: var(--clr-white);
}

.client-options {
    display: flex;
    justify-content: space-evenly;
}

.modal-button {
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 0.6rem;
    width: 2.75rem;
    height: 2.75rem;
    border-radius: 0.5rem;
    border: none;
    outline: none;
    cursor: pointer;
}

.btn-inverse {
    background-color: var(--clr-inversof);
    color: var(--clr-inverse);
}
</style>
