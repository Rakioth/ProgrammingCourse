<template>
    <StoreLayout>
        <div class="main-content">
            <div class="main-content">
                <div v-if="store && Object.keys(store).length !== 0" class="store-card">
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
                        <div>
                            <h4>{{
                                $t("coords", {
                                  degrees : store.coordinate.latitudeDegrees,
                                  minutes : store.coordinate.latitudeMinutes,
                                  seconds : store.coordinate.latitudeSeconds,
                                  cardinal: store.coordinate.latitudeCardinalPoint
                                })
                                }}</h4>
                            <h4>{{
                                $t("coords", {
                                  degrees : store.coordinate.longitudeDegrees,
                                  minutes : store.coordinate.longitudeMinutes,
                                  seconds : store.coordinate.longitudeSeconds,
                                  cardinal: store.coordinate.longitudeCardinalPoint
                                })
                                }}</h4>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </StoreLayout>
</template>

<script setup>
import StoreLayout            from '@/layouts/StoreLayout.vue'
import { onBeforeMount, ref } from 'vue'
import api                    from '@/api'
import { useRoute }           from 'vue-router'

const store = ref([])

onBeforeMount(async () =>
    api.get(`/stores/${ useRoute().params.id }`)
       .then(response => store.value = response.data)
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
}

.store-card {
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
