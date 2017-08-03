# AntiDDoS
AntiDDoS plugin for Spigot

## Features
* Switchable AntiDDoS protection
* Commands (with permissions) for enabling and disabling protection, truncating the database or for changing default settings
* Not verified players have to visit your page, and fill the [reCAPTCHA](https://www.google.com/recaptcha/intro/)

## Requirements
* Spigot/Bukkit server for Minecraft 1.8/1.12
* Hosting, that supports PHP >=5.3 (there are a lot of free hostings, and almost all of it supports PHP)
* MySQL database (also available on almost all hostings)

## Building
If you want to build this app from sourcecode, go to __java__ folder, open your console and execute this command:

```sh
mvn clean package
```

After that go to target folder. File __antiddos-x.x-jar-with-dependencies.jar__ 
You can also download builded packages, ready to go from [here](https://github.com/pietrek777/AntiDDoS/releases)

### Video tutorials
(Coming soon)
### Instalation
* Download AntiDDoS package (or build it from sourcecode if you really want)
* Go to [Google reCAPTCHA site](https://www.google.com/recaptcha/intro/), click *Get reCAPTCHA* button, and register your domain (any subdomains can be registered as well)
* Now when you have your *Site key* and *Secret key*, 
