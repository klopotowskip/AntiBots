package pl.mccode.antiddos.util;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import pl.mccode.antiddos.Main;

public class ConfigDataProvider {
	private static ConfigDataProvider instance = null;
	public static ConfigDataProvider getInstance() {
		if(instance==null){
			instance = new ConfigDataProvider();
		}
		return instance;
	}

	private boolean protection;
	private String kickMessage;
	private String verificationUrl;
	private String formattedMessage;

	private String protocol;
	private String host;
	private String port;
	private String path;
	private String getParam;

    private String wipeUrl;
	private String wipeKey;

	private String outcome;
	private String result;

	public boolean isProtection() {
		return protection;
	}

	public String getKickMessage() {
		return kickMessage;
	}
	public String getVerificationUrl() {
		return verificationUrl;
	}

    public String getFormattedMessage() {
        return formattedMessage;
    }

    public String getProtocol(){
		return protocol;
	}
	public String getHost() {
		return host;
	}
	public String getPort() {
		return port;
	}
	public String getPath() {
		return path;
	}
	public String getGetParam() {
		return getParam;
	}

    public String getWipeUrl(){ return wipeUrl; }
	public String getWipeKey() {
		return wipeKey;
	}

	public String getOutcome() {
		return outcome;
	}
	public String getResult() {
		return result;
	}

	private ConfigDataProvider(){
		FileConfiguration config = Main.config();

		this.protection = config.getBoolean(Main.DDOS_PROTECTION_KEY);
		this.kickMessage = config.getString(Main.PLAYER_KICK_KEY);
		this.verificationUrl = config.getString(Main.VERIFICATION_URL_KEY);
		this.formattedMessage = String.format(ChatColor.translateAlternateColorCodes('&', getKickMessage()), getVerificationUrl());

		this.protocol = config.getString(Main.PROTOCOL_KEY);
		this.host = config.getString(Main.HOST_KEY);
		this.port = config.getString(Main.PORT_KEY);
		this.path = config.getString(Main.PATH_KEY);
		this.getParam = config.getString(Main.GET_PARAM_KEY);

		this.wipeUrl = config.getString(Main.WIPE_URL);
		this.wipeKey = config.getString(Main.WIPE_KEY);

		this.outcome = config.getString(Main.OUTCOME_KEY);
		this.result = config.getString(Main.RESULT_KEY);
	}
}
