<template>
    <StoreLayout>
        <div class="main-content">
            <div class="main-content">
                <div class="product-detail">
                    <div class="product-left">
                        <div class="product-image" v-if="product.productImages">
                            <img :src="product?.productImages[0].path"/>
                        </div>
                        <div class="product-price">
                            <h2 :style="{ 'text-decoration': product.onSale ? 'line-through' : 'none' }"
                                class="price-discount">
                                {{ $t("product.price", {price: product.price}) }}</h2>
                            <h3 v-if="product.onSale" class="price-discount">
                                {{ $t("product.price", {price: (product.price * 0.85).toFixed(2)}) }}</h3>
                            <div>
                                <button class="product-buy" @click="addCart(product.id)" :disabled="loading">
                                    <div v-if="loading" class="spinner"></div>
                                    <svg v-else viewBox="0 0 20 20">
                                        <path d="M3 1a1 1 0 000 2h1.22l.305 1.222a.997.997 0 00.01.042l1.358 5.43-.893.892C3.74 11.846 4.632 14 6.414 14H15a1 1 0 000-2H6.414l1-1H14a1 1 0 00.894-.553l3-6A1 1 0 0017 3H6.28l-.31-1.243A1 1 0 005 1H3zM16 16.5a1.5 1.5 0 11-3 0 1.5 1.5 0 013 0zM6.5 18a1.5 1.5 0 100-3 1.5 1.5 0 000 3z"></path>
                                    </svg>
                                </button>
                            </div>
                        </div>
                        <div v-if="product.isNew" class="product-new">
                            <p>{{ $t("product.new") }}</p>
                        </div>
                    </div>
                    <div class="product-right">
                        <h2>{{ product.model }}</h2>
                        <h4>{{ product.comments }}</h4>
                        <p style="word-wrap: break-word">{{ product.description }}</p>
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
import router                 from '@/router'
import { i18n }               from '@/main'


const loading = ref(false)
const product = ref({})

onBeforeMount(async () =>
    api.get(`/products/${ useRoute().params.id }`)
       .then(response => product.value = response.data)
)

const addCart = async (productId) => {
    if (confirm(i18n.global.t('confirm.add-item'))) {
        loading.value = true
        api.get("/users/is-client")
           .then(response => {
               if (!response.data)
                   router.push({name: 'FormView'})
               else
                   api.get(`/shopping-carts/add/${ productId }`)
           })
           .finally(() => loading.value = false)
    }
}

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

.product-detail {
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

.product-right {
    width: 20rem;
    display: flex;
    flex-direction: column;
    row-gap: 0.4rem;
}

.product-new {
    top: 0.5rem;
    left: 0.5rem;
    position: absolute;
    background-color: #FFCB57;
    border-radius: 3rem;
    padding: 0.3rem 1rem !important;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 0.7rem;
    font-weight: bold;
}

.product-left {
    position: relative;
    display: flex;
    flex-direction: column;
    border-radius: 0.5rem;
    width: 20rem;
    row-gap: 0.4rem;
    height: auto;

    .product-image {
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

    .product-price {
        display: flex;
        align-items: center;
        justify-content: space-around;
        column-gap: 0.5rem;

        h2 {
            text-decoration: line-through;
        }

        h3 {
            color: red;
        }

        > div {
            display: flex;
            flex-grow: 1;
            justify-content: flex-end;
        }
    }


    .product-buy {
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

.spinner {
    font-size: 0.78rem;
    border: .4em solid var(--clr-black);
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
