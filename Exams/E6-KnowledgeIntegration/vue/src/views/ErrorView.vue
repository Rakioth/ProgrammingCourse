<template>
    <div class="error-page">
        <h1>404</h1>
        <p>{{ $t("not-found") }}</p>
    </div>
</template>

<script setup>

</script>

<style scoped lang="scss">

.error-page {
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
    height: 100vh;
    width: 100%;
    font-weight: bold;
    mix-blend-mode: screen;
    background: var(--clr-white);

    h1 {
        font-size: 30vh;
    }

    p {
        font-size: 8vh;
        margin-bottom: 9vh;
    }
}

</style>
