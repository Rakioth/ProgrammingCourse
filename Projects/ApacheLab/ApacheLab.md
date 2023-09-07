# 🛸 Web Server Management

## 📜 Table of Contents

- [🌍 Web Service](#-web-service)
- [💼 Web Hosting](#-web-hosting)
    - [🧑🏻‍💼 Customer 1](#-customer-1)
    - [🧑🏼‍💼 Customer 2](#-customer-2)
    - [🧑🏽‍💼 Customer 3](#-customer-3)
- [👔 Company Intranet](#-company-intranet)
- [♻️ Linux Services](#-linux-services)
  - [🪶 Apache](#-apache)
  - [⚡ ProFTPD](#-proftpd)

![](/img/cover.png)

<div class="page"></div>

## 🌍 Web Service

📄 **CONFIG FILE** - `/etc/apache2/sites-available/sitesbox.es.conf`

```xml
<VirtualHost 10.0.34.1:*>
    ServerName sitesbox.es
    ServerAlias www.sitesbox.es
    ServerAdmin www-admin@sitesbox.es

    DocumentRoot /var/www/sitesbox.es/html

    ErrorLog /var/www/sitesbox.es/log/error.log
    CustomLog /var/www/sitesbox.es/log/access.log combined

    <Directory /var/www/sitesbox.es>
        Options -Indexes
    </Directory>
</VirtualHost>
```

🗃️ **DIRECTORY STRUCTURE** - `/var/www/sitesbox.es`

```bash
├── 📁 conf
├── 📁 html
│  ├── 📁 css
│  │  └── styles.css
│  └── index.html
└── 📁 log
   ├── access.log
   └── error.log
```

<div class="page"></div>

📢 **_DEFAULT_ SITE** - `Capture any request to non-existent virtual hosts`

```xml
<!-- /etc/apache2/sites-available/default.conf -->
<VirtualHost _default_:*>
    DocumentRoot /var/www/default/html
</VirtualHost>
```

![](/img/default-page.png)

<div class="page"></div>

📤 **STORAGE DIRECTORY** - `Outside of /var/www and accessible from anywhere`

```xml
<!-- /etc/apache2/apache2.conf -->
Alias /images /opt/images

<Directory /opt/images>
    Options +Indexes
    Require all granted
</Directory>
```

![](/img/storage-page.png)

🛠️ **ENABLING PHP SCRIPTS**

```bash
apt install libapache2-mod-phpX.X
a2enmod phpx.x
systemctl reload apache2
```

<div class="page"></div>

## 💼 Web Hosting

### 🧑🏻‍💼 Customer 1

📄 **CONFIG FILE** - `/etc/apache2/sites-available/webmaster.org.conf`

```xml
<VirtualHost 10.0.34.2:80 10.0.34.2:12345>
    ServerName webmaster.org
    ServerAlias www.webmaster.org web.webmaster.org
    ServerAdmin admin@webmaster.org

    DocumentRoot /var/www/webmaster.org/html

    ErrorLog /var/www/webmaster.org/log/error.log
    CustomLog /var/www/webmaster.org/log/access.log combined

    ErrorDocument 404 /error/404.html
    ErrorDocument 403 /error/403.html

    Redirect permanent /docu-apache https://httpd.apache.org/docs/

    <Directory /var/www/webmaster.org>
        Options -Indexes
        AllowOverride All
        <RequireAll>
            Require all granted 
            Require not ip 192.168.34
        </RequireAll>
    </Directory>

    <Directory /var/www/webmaster.org/html/protected>
        AuthType Digest
        AuthDigestProvider file
        AuthUserFile /var/www/webmaster.org/conf/.digest_pwd
        AuthName "secure"
        AuthDigestDomain /protected
        Require user alpha
    </Directory>
</VirtualHost>
```

<div class="page"></div>

🗃️ **DIRECTORY STRUCTURE** - `/var/www/webmaster.org`

```bash
├── 📁 conf
│  └── .digest_pwd
├── 📁 html
│  ├── 📁 css
│  │  └── styles.css
│  ├── 📁 error
│  │  ├── 403.html
│  │  ├── 404.html
│  │  ├── 📁 css
│  │  │  └── error.css
│  │  ├── 📁 img
│  │  │  ├── astronaut.svg
│  │  │  └── planet.svg
│  │  └── 📁 js
│  │     └── error.js
│  ├── index.html
│  └── 📁 protected
│     └── index.html
└── 📁 log
   ├── access.log
   └── error.log
```

🔗 **ENABLING FTP ACCESS** - `Website publishing directory`

1. Install ProFTPD `apt install proftpd`
2. Add this lines inside `/etc/proftpd/proftpd.conf`
    ```
    DefaultRoot ~
    RequireValidShell off
    AuthUserFile /etc/proftpd/ftpd.passwd
    ```
3. Create a user for each customer with this command
    ```
    ftpasswd --passwd --name=webmaster.org --uid=1001 --home=/var/www/webmaster.org/html --shell=/bin/false
    ftpasswd --passwd --name=sitesbox.biz --uid=1002 --home=/var/www/sitesbox.biz/html --shell=/bin/false
    ftpasswd --passwd --name=sitesbox.com --uid=1003 --home=/var/www/sitesbox.com/html --shell=/bin/false
    ```
4. Restart ProFTPD `systemctl restart proftpd`

![](/img/filezilla-sites.png)
![](/img/filezilla-connection.png)

<div class="page"></div>

🛡️ **DIGEST AUTHENTICATION**

Enable `auth_digest` module

Create user with this command `htdigest -c .digest_pwd "secure" "alpha"`

```bash
# /var/www/webmaster.org/conf/.digest_pwd
alpha:secure:ea56feb7476d59e97082270a10774814
```

![](/img/digest-1.png)
![](/img/digest-2.png)

🔒 **BLOCK ACCESS** - `Internal LAN access not allowed`

![](/img/block-1.png)

<div class="page"></div>

❌ **ERROR HANDLING**

![](/img/404-page.png)
![](/img/403-page.png)

<div class="page"></div>

### 🧑🏼‍💼 Customer 2

📄 **CONFIG FILE** - `/etc/apache2/sites-available/sitesbox.biz.conf`

```xml
<VirtualHost 10.0.34.3:80>
    ServerName sitesbox.biz
    ServerAlias www.sitesbox.biz
    ServerAdmin www-admin@sitesbox.biz

    DocumentRoot /var/www/sitesbox.biz/html

    ErrorLog /var/www/sitesbox.biz/log/error.log
    CustomLog /var/www/sitesbox.biz/log/access.log combined

    DirectoryIndex main.html home.html

    <Directory /var/www/sitesbox.biz>
        Options +Indexes
        AllowOverride AuthConfig
        <FilesMatch "^_">
            Require all denied
        </FilesMatch>
    </Directory>
</VirtualHost>
```

🗃️ **DIRECTORY STRUCTURE** - `/var/www/sitesbox.biz`

```bash
├── 📁 conf
├── 📁 html
│  ├── 📁 css
│  │  ├── styles.css
│  │  └── _test-file
│  └── main.html
└── 📁 log
   ├── access.log
   └── error.log
```

<div class="page"></div>

🌀 **FILTER FILES** - `Files with leading caracter “_” will not be shown`

![](/img/filter-page.png)

### 🧑🏽‍💼 Customer 3

📄 **CONFIG FILE** - `/etc/apache2/sites-available/sitesbox.com.conf`

```xml
<VirtualHost 10.0.34.3:80>
    ServerName sitesbox.com
    ServerAlias www.sitesbox.com
    ServerAdmin www-admin@sitesbox.com

    DocumentRoot /var/www/sitesbox.com/html

    ErrorLog /var/www/sitesbox.com/log/error.log
    CustomLog /var/www/sitesbox.com/log/access.log combined

    <Directory /var/www/sitesbox.com>
        Options +Indexes
        AllowOverride None
    </Directory>
</VirtualHost>
```

<div class="page"></div>

🗃️ **DIRECTORY STRUCTURE** - `/var/www/sitesbox.com`

```bash
├── 📁 conf
├── 📁 html
│  ├── 📁 css
│  │  └── styles.css
│  └── index.html
└── 📁 log
   ├── access.log
   └── error.log
```

<div class="page"></div>

## 👔 Company Intranet

📄 **CONFIG FILE** - `/etc/apache2/sites-available/intranet.sitesbox.com.es.conf`

```xml
<VirtualHost 10.0.34.1:8080 10.0.34.1:443>
    ServerName intranet.sitesbox.com.es
    ServerAlias intranet.sitesbox.es intranet.es
    ServerAdmin www-admin@sitesbox.es

    DocumentRoot /var/www/intranet.sitesbox.com.es/html

    ErrorLog /var/www/intranet.sitesbox.com.es/log/error.log
    CustomLog /var/www/intranet.sitesbox.com.es/log/access.log combined

    RewriteEngine on
    RewriteCond %{HTTPS} off
    RewriteRule ^(.*)$ https://%{HTTP_HOST}%{REQUEST_URI} [L,R=301]

    SSLEngine on
    SSLOptions +StrictRequire
    SSLCertificateFile /var/www/intranet.sitesbox.com.es/conf/apache.crt
    SSLCertificateKeyFile /var/www/intranet.sitesbox.com.es/conf/apache.key

    <Directory /var/www/intranet.sitesbox.com.es>
        AuthType Basic
        AuthName "secure"
        AuthUserFile /var/www/intranet.sitesbox.com.es/conf/.dbmpasswd
        Require valid-user
        <RequireAll>
            Require all granted
            Require ip 192.168.34
        </RequireAll>
    </Directory>
</VirtualHost>
```

<div class="page"></div>

🗃️ **DIRECTORY STRUCTURE** - `/var/www/intranet.sitesbox.com.es`

```bash
├── 📁 conf
│  ├── apache.crt
│  ├── apache.key
│  └── .dbmpasswd
├── 📁 html
│  ├── 📁 css
│  │  └── styles.css
│  ├── index.html
│  └── 📁 users
└── 📁 log
   ├── access.log
   └── error.log
```

🔍 **HTTPS REDIRECTION** - `Using the HTTP protocol with a SSL certificate`

Enable `rewrite` & `ssl` modules

![](/img/https-1.png)
![](/img/https-2.png)

🛡️ **BASIC AUTHENTICATION**

Enable `auth_basic` module

Create user with this command `htpasswd -c .dbmpasswd "alpha"`

```bash
# /var/www/intranet.sitesbox.com.es/conf/.dbmpasswd
alpha:$apr1$sExyHv7i$McZX6brpqO.UI8CC2Wo9l.
```

![](/img/basic-1.png)
![](/img/basic-2.png)

🔒 **BLOCK ACCESS** - `Internal LAN only access allowed`

![](/img/block-2.png)

👤 **USER DIRECTORY** - `Personal website hosted on the server`

Enable `userdir` module

Give permissions to personal folder `chmod 755 /home/user/public_html`

![](/img/userdir.png)

## ♻️ Linux Services

### 🪶 Apache

```bash
$ whereis apache2
apache2: /usr/sbin/apache2 /usr/lib/apache2 /etc/apache2 /usr/share/apache2 /usr/share/man/man8/apache2.8.gz
$ which apache2
/usr/sbin/apache2
$ ps -ef | grep apache2
root         965       1  0 15:49 ?        00:00:00 /usr/sbin/apache2 -k start
www-data   71617     965  0 16:56 ?        00:00:00 /usr/sbin/apache2 -k start
www-data   71618     965  0 16:56 ?        00:00:00 /usr/sbin/apache2 -k start
www-data   71619     965  0 16:56 ?        00:00:00 /usr/sbin/apache2 -k start
www-data   71620     965  0 16:56 ?        00:00:00 /usr/sbin/apache2 -k start
www-data   71621     965  0 16:56 ?        00:00:00 /usr/sbin/apache2 -k start
www-data   71749     965  0 16:56 ?        00:00:00 /usr/sbin/apache2 -k start
```

### ⚡ ProFTPD

```bash
$ whereis proftpd
proftpd: /usr/sbin/proftpd /usr/lib/proftpd /etc/proftpd /usr/share/proftpd /usr/share/man/man8/proftpd.8.gz
$ which apache2
/usr/sbin/proftpd
$ ps -ef | grep proftpd
proftpd      994       1  0 15:49 ?        00:00:00 proftpd: (accepting connections)
```
