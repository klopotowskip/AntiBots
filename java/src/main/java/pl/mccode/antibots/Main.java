// AntiBots Plugin by pietrek777
// Distributed on MIT License
// This project on GitHub: https://github.com/pietrek777/AntiBots
// This project on SpigotMC: https://www.spigotmc.org/resources/antibots.45137/

package pl.mccode.antibots;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import pl.mccode.antibots.command.CommandAntibots;
import pl.mccode.antibots.event.EventListener;

import java.util.ArrayList;
import java.util.logging.Level;

public class Main extends JavaPlugin{
    public static final String PLUGIN_PREFIX = ChatColor.translateAlternateColorCodes('&', "&9[&3AntiBots&9]&f");

    public static final String PROTECTION_KEY = "protection.enabled-default";
    public static final String PLAYER_KICK_KEY = "protection.kick-message";
    public static final String WHITELIST_KEY = "protection.whitelist";
    public static final String VERIFICATION_ROOT_KEY = "connection.verification-url";
    public static final String WIPE_KEY = "connection.wipe-key";

    private static FileConfiguration defaultConfig;
    private static JavaPlugin instance;

    public static FileConfiguration config(){
        return defaultConfig;
    }
    public static JavaPlugin instance() { return instance; }

	@Override
	public void onEnable() {
        defaultConfig = getConfig();
        instance = this;

        FileConfiguration config = getConfig();
        config.addDefault(PROTECTION_KEY, false);
        config.addDefault(PLAYER_KICK_KEY, "Please verify that you're not a robot. Visit %s");
        config.addDefault(WHITELIST_KEY, new ArrayList<String>());

        config.addDefault(VERIFICATION_ROOT_KEY, "http://yourdomain.com/");
        config.addDefault(WIPE_KEY, "secret");

        config.options().copyDefaults(true);
        saveConfig();

        getServer().getPluginManager().registerEvents(new EventListener(), this);

        EventListener.protection = config.getBoolean(PROTECTION_KEY);
        EventListener.kickMessage = config.getString(PLAYER_KICK_KEY);

        this.getCommand("antibots").setExecutor(new CommandAntibots());

        getLogger().log(Level.INFO, "Plugin successfully enabled. Default protection is " + config.getBoolean(PROTECTION_KEY));
    }
}
