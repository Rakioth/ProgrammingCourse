<template>
    <div id="settings-container">
        <div>
            <SettingsTheme :themes="themes"/>
            <SettingsLocale :locales="locales"/>
        </div>
    </div>
</template>

<script setup>
import SettingsTheme  from '@/components/helper/SettingsTheme.vue'
import SettingsLocale from '@/components/helper/SettingsLocale.vue'

const themes = [
    {
        selection: 'stripes',
        src      : 'https://i.imgur.com/avKeZYs.png',
        alt      : 'theme.stripes'
    },
    {
        selection: 'monochrome',
        src      : 'https://i.imgur.com/ij6HwGx.png',
        alt      : 'theme.monochrome'
    }
]

const locales = [
    {
        selection: 'en',
        src      : 'https://cdn.countryflags.com/thumbs/united-states-of-america/flag-800.png',
        alt      : 'locale.usa'
    },
    {
        selection: 'es',
        src      : 'https://cdn.countryflags.com/thumbs/spain/flag-800.png',
        alt      : 'locale.spain'
    }
]

</script>

<style scoped lang="scss">

#settings-container {
    position: fixed;
    top: 50%;
    left: 0;
    height: auto;
    width: 3.225rem;

    > div {
        position: relative;
    }
}

</style>