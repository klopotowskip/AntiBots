

----------

# AntiBots
#### AntiBots plugin for Spigot

----------

## Features
* Switchable AntiBots protection
* Commands for enabling and disabling protection and for changing default settings
* Verified users database can be easily truncated from game using command
* Permission for every command
* Not verified players have to visit your page, and fill the <a href="https://www.google.com/recaptcha/intro/" target="_blank">reCAPTCHA</a>


## Requirements
* Spigot/Bukkit server for Minecraft 1.8/1.12
* Hosting, that supports PHP >=5.3 (there are a lot of free hostings, and almost all of it supports PHP)
* MySQL database (also available on almost all hostings)

## Building
If you want to build this app from sourcecode, go to __java__ folder, open your console and execute this command:

```sh
mvn clean package
```

After that go to target folder. File __antibots-x.x-jar-with-dependencies.jar__ 
You can also download builded packages, ready to deploy on your server <a href="https://github.com/pietrek777/AntiBots/releases" target="_blank">here</a>

## Video tutorials
[![AntiBots – Installation Tutorial](https://img.youtube.com/vi/Zzk7koKq0Kk/0.jpg)](https://www.youtube.com/watch?v=Zzk7koKq0Kk)
## Instalation
### PHP side configuration
 * Download AntiBots package (or build it from sourcecode if you really want)
 * Go to <a href="https://www.google.com/recaptcha/intro/" target="_blank">reCAPTCHA</a>, click *Get reCAPTCHA* button, and register your domain (any subdomains can be registered as well). Now you have your *Site key* and *Secret key* (save them somewhere, it will be required for futher configuration)
 * Open your FTP client (for example <a href="https://www.google.com/recaptcha/intro/" target="_blank">FileZilla</a>, it's free), log into your server and upload **content** of **php** folder to your domain root (or to other subfolder)
 *  Go to **./resources** and open **verification.ini**. Here you have to set some some settings:
  - **host** – your database host (e.g. http://mysql.example.com)
  -  **username** – your database username
  -  **password** – your database account password
  -  **database** – database name
  -  **site-key** – your *reCAPTCHA* site key
  -  **secret-key** – your *reCAPTCHA* secret key
  - **allowed_api_ips** (optional) – Ip's allowed to use the verified-users API, separated by space (e.g. if your Spigot server will be requesting to API from IP 1.2.3.4, add this IP to this setting, then API will be accessed only from 1.2.3.4 IP. Clients with every other IP will receive <a href="https://en.wikipedia.org/wiki/HTTP_403" target="_blank">403 Response Code</a>. If your server IP for outer connections is not persistent, or you don't know this IP, leave this setting empty)
 * Now open your domain (or where you placed the files). If you see reCAPTCHA with checkbox and "I'm not a robot" label, you configured reCAPTCHA well (<a href="http://i.imgur.com/24xFf1Q" target="_blank">image with properly configured verification</a>)
 
   > **verification.ini**  template is also available <a href="https://pastebin.com/4rudZNiA" target="_blank">here</a>


### Bukkit/Spigot side configuration
 * Place plugin JAR in your server **./plugins** folder, and run your server (or type */reload* command)
 * Go to **./plugins/AntiBots/** and open **config.yml**. There are also some things to configure here
  - **protection ➔ enabled-default** – is AntiBots protection will be launched on start
  - **protection ➔ kick-message** – message, that will be displayed to not-verified players on kick
  - **protection ➔ kick-message** – message, that will be displayed to not-verified players on kick
  - **protection ➔ whitelist** – list of nicknames, that will avoid verification (use it carefully), [see YAML list](http://docs.ansible.com/ansible/latest/YAMLSyntax.html#yaml-basics)
  - **connection ➔ wipe-key** (not to be mistaken with reCAPTCHA keys!) – paste here **wipe-password** from verification.ini (this password is generated on first visit to verification site, if you don't have the password, enter the verification site)

   > **config.yml**  template is also available <a href="https://pastebin.com/tHwqNiSG" target="_blank">here</a>

And finally plugin is ready. Now you can set up [permissions](#permissions). I know, that verification may be hard, so if something is not working, see [FAQ](#FAQ) section. If there's no answer to your problem there, [contact me](#contact), or <a href="https://github.com/pietrek777/AntiBots/issues" target="_blank">start a new issue</a>.

## Commands
> **Note**
> Params in **&lt;angle braces&gt;** are required, those in **[square braces]** are optional

 * **/antibots protect** (alias: **/antibots enable**) – enables AntiBots protection
    - Params
      -  [no-kick/kick-all/force-kick-all] – kicking mode
         - no-kick – without kicking any players
          - kick-all – kick all players without *antibots.nokick* permission (see [permissions](#permissions))
          - force-kick-all – kick all players, without any exceptions
      - [wipe] – enables database wiping
  - Examples
     - **/antibots protect no-kick** – enables protection, kicks nobody, doesn't truncate verified users database
     - **/antibots enable kick-all wipe** – enables protection, kicks all players without *antibots.nokick* permission, truncates verified users database
 * **/antibots disable** – disables protection
 * **/antibots wipe** – truncates verified users database
  *  **/antibots default &lt;on/off&gt;** – enables/disables protection on launch (see **protection ➔ enabled-default** in **config.yml**)
  *  **/antibots help** – lists all plugin commands
  *  **/antibots version** – displays plugin version
 
## Permissions
  * **antibots.nokick** – makes player non-kickalbe (see [**/antibots protect** command](#Commands))
  * **antibots.protect** – allows using **/antibots protect** command
  * **antibots.disable** – allows using **/antibots disable** command
  * **antibots.wipe** – allows truncating database using **/antibots protect** or **/antibots wipe** command
  * **antibots.default** – allows using **/antibots default** command
  * **antibots.help** – allows using **/antibots help** command
  * **antibots.version** – allows using **/antibots version** command
  * **antibots.&ast;** – Grant all permissions for this plugin
  
## FAQ
**Q: It's not working!**

A:  Firstly check if data, that you've entered to **config.yml** and **verification.ini** are valid. If plugin still doesn't work, visit <a href="http://www.unserialize.me/" target="_blank">Online YAML and ini parser</a>, to make sure, that both files are parseable. If this didn't fixed the problem, [contact me](#contact), or [start a new issue](https://github.com/pietrek777/AntiBots/issues).

**Q: I found a bug. What can I do with it?**

A: You can report it <a href="https://github.com/pietrek777/AntiBots/issues" target="_blank">here</a>. I will fix it as soon as possible. Thanks in advance for any bugs reports!

**Q: I have an idea, that can make this plugin better!**

A:  Tell me more about it! [Contact me](#contact), or [create a new pull request](https://github.com/pietrek777/AntiBots/pulls)!

**Q: Do you know any good free hosting?**

A:  To be honest, ~~all~~ most of free hostings sucks. Annoying ads, limited FTP access etc. are standard there. If you want your verification site to be solid, always available and without any ads, you should consider paid hosting.

**Q: I want to remake this software by myself. Can I?**

A:  Surely, you can. AntiBots is on <a href="https://github.com/pietrek777/AntiBots/blob/master/LICENSE" target="_blank">MIT license</a>, so you can fork it, make some changes and publish it. You can even sell your remake, but you should give a link to the orginal.

**Q: There's no answer for my question here/I want to ask you something**

A:  [Contact me](#contact), I will answer you as soon as possible :)

## Contact
Email address: <a href="mailto:pietrek777@gmail.com" target="_blank">pietrek777@gmail.com</a>


-------
**AntiBots plugin by <a href="https://github.com/pietrek777" target="_blank">pietrek777</a> – Distributed on <a href="https://opensource.org/licenses/MIT" target="_blank">MIT License</a>**
