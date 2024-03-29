const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
    transpileDependencies: true,
    css                  : {
        loaderOptions: {
            scss: {
                additionalData: `
                  @import '@/assets/scss/core.scss';
                  @import '@/assets/scss/mixins.scss';
                `
            }
        }
    }
})