<template>
    <FormLayout>
        <form>
            <h3>{{ $t("client-page.confirm") }}</h3>
            <div class="client-options">
                <router-link :to="{name: 'StoreView'}" class="modal-button" style="background-color: #d81e5b">
                    <svg viewBox="0 0 384 512">
                        <path d="M342.6 150.6c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0L192 210.7 86.6 105.4c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3L146.7 256 41.4 361.4c-12.5 12.5-12.5 32.8 0 45.3s32.8 12.5 45.3 0L192 301.3 297.4 406.6c12.5 12.5 32.8 12.5 45.3 0s12.5-32.8 0-45.3L237.3 256 342.6 150.6z"/>
                    </svg>
                </router-link>
                <router-link :to="{name: 'FormInfoView'}" class="modal-button" style="background-color: #13a987">
                    <svg viewBox="0 0 448 512">
                        <path d="M438.6 105.4c12.5 12.5 12.5 32.8 0 45.3l-256 256c-12.5 12.5-32.8 12.5-45.3 0l-128-128c-12.5-12.5-12.5-32.8 0-45.3s32.8-12.5 45.3 0L160 338.7 393.4 105.4c12.5-12.5 32.8-12.5 45.3 0z"/>
                    </svg>
                </router-link>
            </div>
        </form>
    </FormLayout>
</template>

<script setup>
import FormLayout from '@/layouts/FormLayout.vue'

</script>

<style scoped lang="scss">

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

</style>