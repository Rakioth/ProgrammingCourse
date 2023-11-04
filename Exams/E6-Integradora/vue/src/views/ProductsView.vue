<template>
    <StoreLayout>
        <form @submit.prevent="filterProducts" class="consult">
            <div class="opa">
                <div class="glass-input">
                    <label for="model">{{ $t("label.model") }}</label>
                    <input type="text" id="model" v-model="model">
                </div>
                <div class="glass-input">
                    <label for="minPrice">{{ $t("label.min-price") }}</label>
                    <input type="number" step="any" id="minPrice" v-model="minPrice">
                </div>
                <div class="glass-input">
                    <label for="maxPrice">{{ $t("label.max-price") }}</label>
                    <input type="number" step="any" id="maxPrice" v-model="maxPrice">
                </div>
                <div class="glass-input">
                    <label for="categories">{{ $t("label.categories") }}</label>
                    <select style="width: auto" class="glass-select" v-model="categories">
                        <option value="">{{ $t("label.option-category") }}</option>
                        <option value="ELECTRONICS">{{ $t("electronics") }}</option>
                        <option value="CLOTHES">{{ $t("clothes") }}</option>
                    </select>
                </div>
                <div class="wrapper-check">
                    <div class="glass-checkbox dashboard-checkbox">
                        <input type="checkbox" id="onSale" v-model="onSale"/>
                        <label for="onSale">{{ $t("label.on-sale") }}</label>
                    </div>
                    <div class="glass-checkbox dashboard-checkbox">
                        <input type="checkbox" id="isNew" v-model="isNew"/>
                        <label for="isNew">{{ $t("label.is-new") }}</label>
                    </div>
                </div>
            </div>
            <SpinnerButton class="button-search" :loading="loading" :label="$t('label.filter')"/>

        </form>
        <div class="main-content">
            <router-link :to="{name: 'ProductView', params: {id: product.id}}" class="product-card"
                         v-for="(product, index) in products" :key="index">
                <div class="product-image">
                    <img :src="product.productImages[0].path"/>
                </div>
                <div class="product-body">
                    <h3>{{ product.model }}</h3>
                    <h5>{{ product.comments }}</h5>
                </div>
                <div class="product-price">
                    <h2 :style="{ 'text-decoration': product.onSale ? 'line-through' : 'none' }" class="price-discount">
                        {{ $t("product.price", {price: product.price}) }}</h2>
                    <h3 v-if="product.onSale" class="price-discount">
                        {{ $t("product.price", {price: (product.price * 0.85).toFixed(2)}) }}</h3>
                    <div>
                    </div>
                </div>
                <div v-if="product.isNew" class="product-new">
                    <p>{{ $t("product.new") }}</p>
                </div>
            </router-link>
        </div>
    </StoreLayout>
</template>

<script setup>
import StoreLayout            from '@/layouts/StoreLayout.vue'
import SpinnerButton          from '@/components/core/SpinnerButton.vue'
import { onBeforeMount, ref } from 'vue'
import api                    from '@/api'

const model = ref('')
const minPrice = ref('')
const maxPrice = ref('')
const onSale = ref(false)
const isNew = ref(false)
const categories = ref('')

const loading = ref(false)
const products = ref([])

onBeforeMount(async () =>
    api.get('/products')
       .then(response => products.value = response.data)
)

const filterProducts = async () => {
    loading.value = true
    api.post('/products/filter', {
        'model'    : model.value,
        'min_price': minPrice.value,
        'max_price': maxPrice.value,
        'on_sale'  : !onSale.value ? null : onSale.value,
        'is_new'   : !isNew.value ? null : isNew.value,
        'cat'      : categories.value
    }).then(response => products.value = response.data)
       .finally(() => loading.value = false)
}

</script>

<style scoped lang="scss">

.button-search {
    align-self: center;
    width: auto;
}

.consult {
    padding: 1.6rem;
    margin-bottom: 1rem;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
}

.opa {
    display: flex;
    flex-direction: row;
    column-gap: 1rem;
}

.wrapper-check {
    display: flex;
    flex-direction: column;
    justify-content: end;
    align-items: flex-start;
}

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
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    gap: 2rem;

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

.product-card {
    @extend %glass-effect;
    position: relative;
    color: var(--font-inverse-color);
    display: flex;
    flex-direction: column;
    border-radius: 0.5rem;
    height: auto;

    &:hover {
        background-color: rgba(255, 255, 255, 0.45);
    }

    > div {
        padding: 1.5rem;
    }

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

    .product-body {
        display: flex;
        flex-direction: column;
        border: 1px solid rgba(255, 255, 255, 0.125);
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
</style>
