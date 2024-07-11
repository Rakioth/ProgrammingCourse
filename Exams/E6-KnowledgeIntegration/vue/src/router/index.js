import { createRouter, createWebHistory } from 'vue-router'
import StoreView                          from '@/views/StoreView.vue'
import RegisterView                       from '@/views/register/RegisterView.vue'
import VerifyView                         from '@/views/register/VerifyView.vue'
import AuthView                           from '@/views/register/AuthView.vue'
import LoginView                          from '@/views/login/LoginView.vue'
import LogauthView                        from '@/views/login/LogauthView.vue'
import ForgotView                         from '@/views/login/ForgotView.vue'
import ResetView                          from '@/views/ResetView.vue'
import ProductsView                       from '@/views/ProductsView.vue'
import ProductView                        from '@/views/ProductView.vue'
import CartView                           from '@/views/CartView.vue'
import OrderView                          from '@/views/OrderView.vue'
import ProfileView                        from '@/views/ProfileView.vue'
import FormView                           from '@/views/client/FormView.vue'
import FormDetailsView                    from '@/views/client/FormDetailsView.vue'
import FormInfoView                       from '@/views/client/FormInfoView.vue'
import FormOtherView                      from '@/views/client/FormOtherView.vue'
import ErrorView                          from '@/views/ErrorView.vue'
import StoView                            from '@/views/StoView.vue'
import app                                from '@/main'
import api                                from '@/api'

const routes = [
    {
        path     : '/',
        name     : 'StoreView',
        component: StoreView,
        meta     : {
            requireAuth  : false,
            alreadyAuth  : false,
            requireClient: false
        }
    },
    {
        path     : '/stores/:id',
        name     : 'StoView',
        component: StoView,
        meta     : {
            requireAuth  : false,
            alreadyAuth  : false,
            requireClient: false
        }
    },
    {
        path     : '/register',
        name     : 'RegisterView',
        component: RegisterView,
        meta     : {
            requireAuth  : false,
            alreadyAuth  : true,
            requireClient: false
        }
    },
    {
        path     : '/register/verify',
        name     : 'VerifyView',
        component: VerifyView,
        meta     : {
            requireAuth  : false,
            alreadyAuth  : true,
            requireClient: false
        }
    },
    {
        path     : '/register/auth',
        name     : 'AuthView',
        component: AuthView,
        meta     : {
            requireAuth  : false,
            alreadyAuth  : true,
            requireClient: false
        }
    },
    {
        path     : '/form',
        name     : 'FormView',
        component: FormView,
        meta     : {
            requireAuth  : true,
            alreadyAuth  : false,
            requireClient: false
        }
    },
    {
        path     : '/form/info',
        name     : 'FormInfoView',
        component: FormInfoView,
        meta     : {
            requireAuth  : true,
            alreadyAuth  : false,
            requireClient: false
        }
    },
    {
        path     : '/form/details',
        name     : 'FormDetailsView',
        component: FormDetailsView,
        meta     : {
            requireAuth  : true,
            alreadyAuth  : false,
            requireClient: false
        }
    },
    {
        path     : '/form/other',
        name     : 'FormOtherView',
        component: FormOtherView,
        meta     : {
            requireAuth  : true,
            alreadyAuth  : false,
            requireClient: false
        }
    },
    {
        path     : '/login',
        name     : 'LoginView',
        component: LoginView,
        meta     : {
            requireAuth  : false,
            alreadyAuth  : true,
            requireClient: false
        }
    },
    {
        path     : '/login/auth',
        name     : 'LogauthView',
        component: LogauthView,
        meta     : {
            requireAuth  : false,
            alreadyAuth  : true,
            requireClient: false
        }
    },
    {
        path     : '/login/forgot',
        name     : 'ForgotView',
        component: ForgotView,
        meta     : {
            requireAuth  : false,
            alreadyAuth  : true,
            requireClient: false
        }
    },
    {
        path     : '/reset/:token',
        name     : 'ResetView',
        component: ResetView,
        meta     : {
            requireAuth  : false,
            alreadyAuth  : true,
            requireClient: false
        }
    },
    {
        path     : '/products',
        name     : 'ProductsView',
        component: ProductsView,
        meta     : {
            requireAuth  : false,
            alreadyAuth  : false,
            requireClient: false
        }
    },
    {
        path     : '/products/:id',
        name     : 'ProductView',
        component: ProductView,
        meta     : {
            requireAuth  : false,
            alreadyAuth  : false,
            requireClient: false
        }
    },
    {
        path     : '/cart',
        name     : 'CartView',
        component: CartView,
        meta     : {
            requireAuth  : true,
            alreadyAuth  : false,
            requireClient: true
        }
    },
    {
        path     : '/order',
        name     : 'OrdersView',
        component: OrderView,
        meta     : {
            requireAuth  : true,
            alreadyAuth  : false,
            requireClient: true
        }
    },
    {
        path     : '/profile',
        name     : 'ProfileView',
        component: ProfileView,
        meta     : {
            requireAuth  : true,
            alreadyAuth  : false,
            requireClient: true
        }
    },
    {
        path     : '/:pathMatch(.*)*',
        name     : 'ErrorView',
        component: ErrorView,
        meta     : {
            requireAuth  : false,
            alreadyAuth  : false,
            requireClient: false
        }
    }
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

router.beforeEach((to, from, next) => {
    const auth = app.$cookies.get('access_token') != null
    const needAuth = to.meta.requireAuth
    const alreadyAuth = to.meta.alreadyAuth
    const needClient = to.meta.requireClient


    if (needAuth && !auth)
        next({name: 'LoginView'})
    else if (needClient) {
        api.get("/users/is-client")
           .then(response => {
               if (!response.data)
                   next({name: 'FormView'})
               else
                   next()
           })
    } else if (alreadyAuth && auth)
        next({name: 'StoreView'})
    else
        next()

})

export default router