<template>
    <div id="theme-container">
        <ul>
            <li v-for="theme in themes" :key="theme.selection" :theme-selection="theme.selection">
                <img :src="theme.src" :alt="$t(theme.alt)">
            </li>
        </ul>
    </div>
</template>

<script setup>
import { defineProps, onMounted } from 'vue'
import useSettings                from '@/store/settings'

const settings = useSettings()

defineProps({
    themes: {
        type    : Array,
        required: true
    }
})

onMounted(() => {
    document.querySelectorAll('#theme-container ul li')
            .forEach(item => {
                if (item.getAttribute('theme-selection') === settings.validTheme) {
                    let clone = item.cloneNode(true)
                    document.querySelector('#theme-container ul').prepend(clone)

                    item.style.opacity = '0'
                    item.style.display = 'none'
                }
                item.addEventListener('click', () => onSelect(item))
            })

    const onSelect = (item) => {
        let updatedItems = document.querySelectorAll('#theme-container ul li')
        let selectedItem = updatedItems[0]
        let selectedTheme = item.getAttribute('theme-selection')

        settings.setTheme(selectedTheme)
        document.documentElement.className = selectedTheme
        showUnselected(selectedItem, updatedItems)

        selectedItem.innerHTML = item.innerHTML
        selectedItem.setAttribute('theme-selection', selectedTheme)
    }

    const showUnselected = (selectedItem, updatedItems) => {
        let selectedTheme = selectedItem.getAttribute('theme-selection')

        updatedItems.forEach(item => {
            if (item.getAttribute('theme-selection') === selectedTheme) {
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

#theme-container {
    @include select-container(
        $inset: auto auto 0 0,
        $border-radius: 0 0.625rem 0 0,
        $direction: 'bottom'
    );

    &:hover {
        height: 2.5rem * 2;
        border-radius: 0 1.25rem 0 0;
    }
}

</style>