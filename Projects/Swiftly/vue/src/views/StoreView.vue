<template>
    <StoreLayout>
        <div class="main-content">
            <router-link :to="{name: 'StoView', params: {id: store.id}}" class="store-card"
                         v-for="(store, index) in stores" :key="index">
                <div class="store-image">
                    <img :src="store.image.path"/>
                </div>
                <div class="store-body">
                    <div>
                        <h3>{{ $t("store-page.this-is", {name: store.id}) }}</h3>
                        <h5>{{
                            $t("address", {
                              via       : $t(store.address.via.ref),
                              name      : store.address.name,
                              number    : store.address.number,
                              floor     : store.address.floor,
                              portal    : store.address.portal,
                              postalCode: store.address.postalCode,
                              locality  : store.address.locality,
                              region    : store.address.region
                            })
                            }}</h5>
                    </div>
                </div>
            </router-link>
        </div>
    </StoreLayout>
</template>

<script setup>
import StoreLayout            from '@/layouts/StoreLayout.vue'
import { onBeforeMount, ref } from 'vue'
import api                    from '@/api'

const stores = ref([])

onBeforeMount(async () =>
    api.get("/stores")
       .then(response => stores.value = response.data)
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
}

.main-content {
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    gap: 2rem;

}

.store-card {
    @extend %glass-effect;
    color: var(--font-inverse-color);
    display: flex;
    flex-direction: column;
    border-radius: 0.5rem;
    height: auto;
    text-decoration: none;

    &:hover {
        background-color: var(--clr-glass-hover);
    }

    > div {
        padding: 1.5rem;
    }

    .store-image {
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

    .store-body {
        display: flex;
        align-items: center;
        justify-content: space-between;
        column-gap: 0.5rem;
        border: 1px solid rgba(255, 255, 255, 0.125);
    }

}

</style>
