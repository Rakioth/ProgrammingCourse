import { defineStore } from 'pinia'
import api             from '@/api'
import router          from '@/router'

const useAuth = defineStore('auth', {
    state  : () => {
        return {
            errors      : {},
            registerForm: {},
            loginForm   : {},
            clientForm  : {},
            loading     : false,
            forgoter    : false,
            baseURL     : '/auth'
        }
    },
    actions: {
        async register(email) {
            this.loading = true
            const uri = `${ this.baseURL }/register`

            api.post(uri, {
                'email': email
            })
               .then(() => {
                   this.errors = {}
                   router.push({name: 'VerifyView'})
               })
               .catch(error => this.errors = error.response.data)
               .finally(() => this.loading = false)
        },
        async verify(code) {
            this.loading = true
            const uri = `${ this.baseURL }/register/verify`

            api.post(uri, {
                'email': this.registerForm.data.email,
                'code' : code
            })
               .then(() => {
                   this.errors = {}
                   router.push({name: 'AuthView'})
               })
               .catch(error => this.errors = error.response.data)
               .finally(() => this.loading = false)
        },
        async auth(username, password, passwordConfirm) {
            this.loading = true
            const uri = `${ this.baseURL }/register/auth`

            api.post(uri, {
                'username'        : username,
                'email'           : this.registerForm.data.email,
                'password'        : password,
                'password_confirm': passwordConfirm
            })
               .then(() => {
                   this.errors = {}
                   router.push({name: 'FormView'})
               })
               .catch(error => this.errors = error.response.data)
               .finally(() => this.loading = false)
        },
        async login(username) {
            this.loading = true
            const uri = `${ this.baseURL }/login`

            api.post(uri, {
                'username': username
            })
               .then(() => {
                   this.errors = {}
                   router.push({name: 'LogauthView'})
               })
               .catch(error => this.errors = error.response.data)
               .finally(() => this.loading = false)
        },
        async logauth(password) {
            this.loading = true
            const uri = `${ this.baseURL }/login/auth`

            api.post(uri, {
                'username': this.loginForm.data.username,
                'password': password,
            })
               .then(() => {
                   this.errors = {}
                   router.push({name: 'StoreView'})
               })
               .catch(error => this.errors = error.response.data)
               .finally(() => this.loading = false)
        },
        async forgot(email) {
            this.loading = true
            const uri = `${ this.baseURL }/login/forgot`

            api.post(uri, {
                'email': email,
            }).then(() => {
                this.forgoter = true
                this.errors = {}
            })
               .catch(error => {
                   this.forgoter = false
                   this.errors = error.response.data
               })
               .finally(() => this.loading = false)

        },
        async reset(password, passwordConfirm, token) {
            this.loading = true
            const uri = `${ this.baseURL }/reset/${ token }`

            api.post(uri, {
                'password'        : password,
                'password_confirm': passwordConfirm
            }).then(() => {
                this.errors = {}
                router.push({name: 'LoginView'})
            })
               .catch(error => this.errors = error.response.data)
               .finally(() => this.loading = false)
        },
        async clientInfo(name, surnames, gender, birthdate) {
            this.loading = true
            const uri = `${ this.baseURL }/client/info`

            api.post(uri, {
                'name'       : name,
                'surnames'   : surnames,
                'gender_code': gender,
                'birthdate'  : birthdate
            })
               .then(() => {
                   this.errors = {}
                   router.push({name: 'FormDetailsView'})
               })
               .catch(error => this.errors = error.response.data)
               .finally(() => this.loading = false)
        },
        async clientDetails(documentType, document, countryCode, phoneNumber, addrVia, addrName, addrNumber, addrPortal, addrFloor, addrLocality, addrRegion, addrPostalCode) {
            this.loading = true
            const uri = `${ this.baseURL }/client/details`

            api.post(uri, {
                'document_type'   : documentType,
                'document'        : document,
                'country_type'    : countryCode,
                'phone_number'    : phoneNumber,
                'addr_via'        : addrVia,
                'addr_name'       : addrName,
                'addr_number'     : addrNumber,
                'addr_portal'     : addrPortal,
                'addr_floor'      : addrFloor,
                'addr_locality'   : addrLocality,
                'addr_region'     : addrRegion,
                'addr_postal_code': addrPostalCode
            })
               .then(() => {
                   this.errors = {}
                   router.push({name: 'FormOtherView'})
               })
               .catch(error => this.errors = error.response.data)
               .finally(() => this.loading = false)
        },
        async clientOther(cardType, cardNumber, cardCcv, cardExpiredDate, comments, license) {
            this.loading = true
            const uri = `${ this.baseURL }/client/other`

            api.post(uri, {
                'card_type'        : cardType,
                'card_number'      : cardNumber,
                'card_ccv'         : cardCcv,
                'card_expired_date': cardExpiredDate,
                'comments'         : comments,
                'license'          : license
            })
               .then(() => {
                   this.errors = {}
                   router.push({name: 'StoreView'})
               })
               .catch(error => this.errors = error.response.data)
               .finally(() => this.loading = false)
        },
        async resendRecovery() {
            const uri = `${ this.baseURL }/email/recovery`

            await api.get(uri)
        },

        async resendConfirmation() {
            const uri = `${ this.baseURL }/email/confirmation`

            await api.get(uri)
        },

        async formRegister(view) {
            const uri = `${ this.baseURL }/form/register`

            api.get(uri)
               .then(response => {
                   this.registerForm = response.data
                   if (this.registerForm.step !== view)
                       router.push({name: this.registerForm.step})
               })
        },
        async formLogin() {
            const uri = `${ this.baseURL }/form/login`

            api.get(uri)
               .then(response => {
                   this.loginForm = response.data
                   if (this.loginForm.data === null)
                       router.push({name: 'LoginView'})
               })
        },
        async formClient(view, stepNumber) {
            const uri = `${ this.baseURL }/form/client`

            api.get(uri)
               .then(response => {
                   this.clientForm = response.data
                   if (this.clientForm.step !== view)
                       if (stepNumber > this.clientForm.step_number)
                           router.push({name: this.clientForm.step})
                       else
                           router.push({name: view})
               })
        }
    }
})

export default useAuth