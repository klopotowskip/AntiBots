// AntiBots Plugin by pietrek777
// Distributed on MIT License
// This project on GitHub: https://github.com/pietrek777/AntiBots
// This project on SpigotMC: https://www.spigotmc.org/resources/antibots.45137/

package pl.mccode.antibots.util;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import pl.mccode.antibots.Main;

import java.util.List;

public class DataProvider {
    private static DataProvider instance = null;

    public static DataProvider getInstance() {
        if (instance == null) {
            instance = new DataProvider();
        }
        return instance;
    }

    private boolean protection;
    private String kickMessage;
    private String verificationRoot;

    private List<String> whitelist;
    private String wipeKey;
    private String formattedMessage;


    public boolean isProtection() {
        return protection;
    }

    public String getKickMessage() {
        return kickMessage;
    }

    public String getVerificationRoot() {
        return verificationRoot;
    }

    public String getWipeKey() {
        return wipeKey;
    }

    public List<String> getWhitelist() {
        return whitelist;
    }

    public String getFormattedMessage() {
        return formattedMessage;
    }

    public static void update() {
        instance = new DataProvider();
    }

    private DataProvider() {
        FileConfiguration config = Main.config();

        this.protection = config.getBoolean(Main.PROTECTION_KEY);
        this.kickMessage = config.getString(Main.PLAYER_KICK_KEY);
        String root = config.getString(Main.VERIFICATION_ROOT_KEY);
        if (!root.endsWith("/")) root += '/';
        root = root.replace('\\', '/');
        this.verificationRoot = root;
        this.wipeKey = config.getString(Main.WIPE_KEY);
        this.whitelist = config.getStringList(Main.WHITELIST_KEY);

        this.formattedMessage = String.format(ChatColor.translateAlternateColorCodes('&', getKickMessage()), root);

    }
}