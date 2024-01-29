import { defineStore } from 'pinia'

const useSettings = defineStore('settings', {
    state  : () => {
        return {
            locale: localStorage.getItem('locale'),
            theme : localStorage.getItem('theme')
        }
    },
    getters: {
        validLocale() {
            const locales = ['en', 'es']
            return locales.includes(this.locale) ? this.locale : locales[0]
        },
        validTheme() {
            const themes = ['stripes', 'monochrome']
            return themes.includes(this.theme) ? this.theme : themes[0]
        }
    },
    actions: {
        setLocale(value) {
            this.locale = value;
            localStorage.setItem('locale', value);
        },
        setTheme(value) {
            this.theme = value;
            localStorage.setItem('theme', value);
        }
    }
})

export default useSettings