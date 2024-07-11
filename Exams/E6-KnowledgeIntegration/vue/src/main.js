import { createApp }   from 'vue'
import { createI18n }  from 'vue-i18n'
import { createPinia } from 'pinia'
import VueCookies      from 'vue-cookies'
import useSettings     from '@/store/settings'
import App             from '@/App.vue'
import router          from '@/router'
import en              from '@/locale/dictionary/en.json'
import es              from '@/locale/dictionary/es.json'

const app = createApp(App)
app.use(createPinia())

const settings = useSettings()

const i18n = createI18n({
    locale        : settings.locale,
    fallbackLocale: 'en',
    messages      : { en, es },
})

app.use(i18n)
   .use(router)
   .use(VueCookies)
   .mount('#app')

const setTheme = () =>
    document.documentElement.className = useSettings().theme

document.onload = setTheme()

export { i18n }
export default app