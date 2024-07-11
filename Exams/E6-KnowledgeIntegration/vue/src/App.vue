<template>
    <TheSettings/>
    <router-view/>
</template>

<script setup>
import TheSettings from '@/components/TheSettings.vue'
</script>

<style lang="scss">

#app {
    width: 100%;
    min-height: 100vh;
    display: flex;
    flex-direction: column;
}

</style>