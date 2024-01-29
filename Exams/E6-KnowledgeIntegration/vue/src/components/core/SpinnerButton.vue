<template>
    <button type="submit" :disabled="loading">
        <div v-if="loading" class="spinner"></div>
        <p v-else>{{ label }}</p>
    </button>
</template>

<script setup>
import { defineProps } from 'vue'

defineProps({
    loading: {
        type    : Boolean,
        required: true
    },
    label  : {
        type    : String,
        required: true
    }
})

</script>

<style scoped lang="scss">

.spinner {
    font-size: 0.78rem;
    border: .4em solid var(--clr-white);
    border-top-color: rgba(255, 255, 255, .5);
    border-radius: 50%;
    width: 2em;
    height: 2em;
    animation: loading-spinner 2s linear infinite;
    margin: 0 auto;
    box-sizing: border-box;
}

@keyframes loading-spinner {
    0% {
        transform: rotate(0deg);
    }
    100% {
        transform: rotate(360deg);
    }
}

</style>