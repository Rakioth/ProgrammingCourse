const setCookie = (name, value, days = 7, path = '/') => {
    const expires = new Date(Date.now() + days * 864e5).toUTCString()
    document.cookie = name + '=' + encodeURIComponent(value) + '; expires=' + expires + '; path=' + path
}

const getCookie = (name) => {
    return document.cookie.split('; ').reduce((r, v) => {
        const parts = v.split('=')
        return parts[0] === name ? decodeURIComponent(parts[1]) : r
    }, null)
}

const deleteCookie = (name, path) => {
    setCookie(name, '', -1, path)
}

const locale = ['en', 'es'].includes(getCookie('locale')) ? getCookie('locale') : 'en'
const theme = ['stripes', 'monochrome'].includes(localStorage.getItem('theme')) ? localStorage.getItem('theme') : 'stripes'

document.querySelectorAll('#select-locale ul li')
        .forEach(item => {
            if (item.getAttribute('locale-selection') === locale) {
                let clone = item.cloneNode(true)
                document.querySelector('#select-locale ul').prepend(clone)

                item.style.opacity = '0'
                item.style.display = 'none'
            }
            item.addEventListener('click', () => onSelectLocale(item))
        })

document.querySelectorAll('#select-theme ul li')
        .forEach(item => {
            if (item.getAttribute('theme-selection') === theme) {
                let clone = item.cloneNode(true)
                document.querySelector('#select-theme ul').prepend(clone)

                item.style.opacity = '0'
                item.style.display = 'none'
            }
            item.addEventListener('click', () => onSelectTheme(item))
        })

const onSelectLocale = (item) => {
    let updatedItems = document.querySelectorAll('#select-locale ul li')
    let selectedItem = updatedItems[0]
    let selectedLocale = item.getAttribute('locale-selection')

    setCookie('locale', selectedLocale)
    showUnselectedLocale(selectedItem, updatedItems)

    selectedItem.innerHTML = item.innerHTML
    selectedItem.setAttribute('locale-selection', selectedLocale)
    window.location.reload()
}

const showUnselectedLocale = (selectedItem, updatedItems) => {
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

const onSelectTheme = (item) => {
    let updatedItems = document.querySelectorAll('#select-theme ul li')
    let selectedItem = updatedItems[0]
    let selectedTheme = item.getAttribute('theme-selection')

    localStorage.setItem('theme', selectedTheme)
    document.documentElement.className = selectedTheme
    showUnselectedTheme(selectedItem, updatedItems)

    selectedItem.innerHTML = item.innerHTML
    selectedItem.setAttribute('theme-selection', selectedTheme)
}

const showUnselectedTheme = (selectedItem, updatedItems) => {
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

const setTheme = () =>
    document.documentElement.className = localStorage.getItem('theme')

document.onload = setTheme()

document.querySelectorAll('.go-back')
        .forEach(back => back.addEventListener('click', function () {
            const currentUrl = window.location.href;
            window.location.href = currentUrl.substring(0, currentUrl.lastIndexOf('/'));
        }))