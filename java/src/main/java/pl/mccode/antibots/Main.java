// AntiBots Plugin by pietrek777
// Distributed on MIT License
// This project on Github: https://github.com/pietrek777/AntiBots
package pl.mccode.antibots;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import pl.mccode.antibots.command.CommandAntibots;
import pl.mccode.antibots.event.EventListener;

public class Main extends JavaPlugin{
	public static final String PLUGIN_PREFIX = ChatColor.translateAlternateColorCodes('&', "&9[&3AntiBots&9]&f");

	public static final String BOTS_PROTECTION_KEY = "protection.enabled-default";
	public static final String PLAYER_KICK_KEY = "protection.kick-message";
	public static final String VERIFICATION_URL_KEY = "protection.verification-url";

	public static final String PROTOCOL_KEY = "connection.host";
	public static final String HOST_KEY = "connection.host";
	public static final String PORT_KEY = "connection.port";
	public static final String PATH_KEY = "connection.path";
	public static final String GET_PARAM_KEY = "connection.get-param";

	public static final String WIPE_URL = "wipe.url";
	public static final String WIPE_KEY = "wipe.secret-key";

	public static final String OUTCOME_KEY = "response.outcome";
	public static final String RESULT_KEY = "response.result";

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
        config.addDefault(BOTS_PROTECTION_KEY, false);
        config.addDefault(PLAYER_KICK_KEY, "Please verify that you're not a robot. Visit %s");
        config.addDefault(VERIFICATION_URL_KEY, "http://example.com/verification.php");

        config.addDefault(PROTOCOL_KEY, "http");
        config.addDefault(HOST_KEY, "example.com");
        config.addDefault(PORT_KEY, "80");
        config.addDefault(PATH_KEY, "api.php");
        config.addDefault(GET_PARAM_KEY, "nickname");

        config.addDefault(WIPE_URL, "http://example.com/wipe.php");
        config.addDefault(WIPE_KEY, "secret");

        config.addDefault(OUTCOME_KEY, "outcome");
        config.addDefault(RESULT_KEY, "result");

        config.options().copyDefaults(true);
        saveConfig();

        getServer().getPluginManager().registerEvents(new EventListener(), this);

        EventListener.protection = config.getBoolean(BOTS_PROTECTION_KEY);
        EventListener.kickMessage = config.getString(PLAYER_KICK_KEY);

        this.getCommand("antibots").setExecutor(new CommandAntibots());
    }
}
