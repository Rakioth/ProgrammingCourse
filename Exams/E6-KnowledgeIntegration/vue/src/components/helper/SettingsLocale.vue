<template>
    <div id="locale-container">
        <ul>
            <li v-for="locale in locales" :key="locale.selection" :locale-selection="locale.selection"
                @click="$i18n.locale = locale.selection">
                <img :src="locale.src" :alt="$t(locale.alt)">
            </li>
        </ul>
    </div>
</template>

<script setup>
import { defineProps, onMounted } from 'vue'
import useSettings                from '@/store/settings'

const settings = useSettings()

defineProps({
    locales: {
        type    : Array,
        required: true
    }
})

onMounted(() => {
    document.querySelectorAll('#locale-container ul li')
            .forEach(item => {
                if (item.getAttribute('locale-selection') === settings.validLocale) {
                    let clone = item.cloneNode(true)
                    document.querySelector('#locale-container ul').prepend(clone)

                    item.style.opacity = '0'
                    item.style.display = 'none'
                }
                item.addEventListener('click', () => onSelect(item))
            })

    const onSelect = (item) => {
        let updatedItems = document.querySelectorAll('#locale-container ul li')
        let selectedItem = updatedItems[0]
        let selectedLocale = item.getAttribute('locale-selection')

        settings.setLocale(selectedLocale)
        showUnselected(selectedItem, updatedItems)

        selectedItem.innerHTML = item.innerHTML
        selectedItem.setAttribute('locale-selection', selectedLocale)
    }

    const showUnselected = (selectedItem, updatedItems) => {
        let selectedLocale = selectedItem.getAttribute('locale-selection')

        updatedItems.forEach(item => {
            if (item.getAttribute('locale-selection') === selectedLocale) {
                item.style.opacity = '1'
                item.style.display = ''
            } else {
                item.style.opacity = '0'
                item.style.display = 'none'
            }
        })
    }
})

</script>

<style scoped lang="scss">

#locale-container {
    @include select-container(
        $inset: 0 auto auto 0,
        $border-radius: 0 0 0.625rem 0,
        $direction: 'top'
    );

    &:hover {
        height: 2.4rem * 2;
        border-radius: 0 0 1.25rem 0;
    }
}

</style>