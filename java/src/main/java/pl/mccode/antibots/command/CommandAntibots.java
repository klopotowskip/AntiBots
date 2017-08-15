// AntiBots Plugin by pietrek777
// Distributed on MIT License
// This project on GitHub: https://github.com/pietrek777/AntiBots
// This project on SpigotMC: https://www.spigotmc.org/resources/antibots.45137/

package pl.mccode.antibots.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import pl.mccode.antibots.Main;
import pl.mccode.antibots.util.MainOperator;

public class CommandAntibots implements CommandExecutor{
	private static final String PROTECT_COMMAND = "protect";
    private static final String ENABLE_ALIAS_COMMAND = "enable";
	private static final String DISABLE_COMMAND = "disable";
	private static final String WIPE_COMMAND = "wipe";
	private static final String DEFAULT_COMMAND = "default";
	private static final String VERSION_COMMAND = "version";
	private static final String HELP_COMMAND = "help";

	private static final String NO_KICK_PARAM = "no-kick";
	private static final String KICK_ALL_PARAM = "kick-all";
	private static final String FORCE_KICK_ALL_PARAM = "force-kick-all";

	private static final String WIPE_PARAM = "wipe";

	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
		if(strings==null||strings.length==0){
			printHelp(sender);
			return true;
		}
        for (int i = 0; i < strings.length; i++) {
            strings[i] = strings[i].toLowerCase();
        }
        switch(strings[0]){
			case PROTECT_COMMAND:
				protect(sender, strings);
				break;
            case ENABLE_ALIAS_COMMAND:
                protect(sender, strings);
                break;
			case DISABLE_COMMAND:
				disable(sender);
				break;
			case WIPE_COMMAND:
				wipe(sender);
				break;
			case DEFAULT_COMMAND:
				setDefault(sender, strings[1]);
				break;
			case VERSION_COMMAND:
				printVersion(sender);
				break;
			case HELP_COMMAND:
				printHelp(sender);
				break;
			default:
				sender.sendMessage(Main.PLUGIN_PREFIX + "Unknown sub-command");
				printHelp(sender);
				break;
		}
		return true;
	}
	private void printHelp(CommandSender sender){
		if(!sender.hasPermission("antibots.help")){
			sendNoPermissionsMessage(sender);
			return;
		}
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&9-------------"));
		sender.sendMessage(Main.PLUGIN_PREFIX + " Commands:");
		printCommand(sender, "/antibots protect [no-kick/kick-all/force-kick-all] [wipe]", "Enables AntiBots protection");
		printCommand(sender, "/antibots disable", "Disables AntiBots protection");
		printCommand(sender, "/antibots wipe", "Wipes the AntiBots database");
		printCommand(sender, "/antibots default <true/false>", "Sets if AntiBots protection is enabled on start");
		printCommand(sender, "/antibots version", "Prints this plugin version");
		printCommand(sender, "/antibots help", "Prints this message");


	}
	private void printCommand(CommandSender sender, String command, String desc){
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&9" + command + "&f - " + desc));
	}
	private void printVersion(CommandSender sender){
		if(!sender.hasPermission("antibots.version")){
			sendNoPermissionsMessage(sender);
			return;
		}
		sender.sendMessage(Main.PLUGIN_PREFIX + " by pietrek777");
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&9Version v1.1"));
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&9This plugin on SpigotMC: &fhttps://www.spigotmc.org/resources/antibots.45137/"));
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&9Sourcecode and full documentation on Github: &fhttps://github.com/pietrek777/AntiBots"));
	}
	private void disable(CommandSender sender){
		if(!sender.hasPermission("antibots.disable")){
			sendNoPermissionsMessage(sender);
			return;
		}
		sender.sendMessage(Main.PLUGIN_PREFIX + "AntiBots Protection disabled");
		MainOperator.disableProtection();
	}
	private void protect(CommandSender sender, String[] strings){
		if(!sender.hasPermission("antibots.protect")){
            sendNoPermissionsMessage(sender);
            return;
        }
		String kicking = null;
		Boolean wipe = false;
		for(String param : strings){
			if(param.equals(NO_KICK_PARAM) || param.equals(KICK_ALL_PARAM) || param.equals(FORCE_KICK_ALL_PARAM)){
				kicking = param;
			} else if(param.equals(WIPE_PARAM)){
			    if(!sender.hasPermission("antibots.wipe")) {
                    sendNoPermissionsMessage(sender);
                    return;
                }
				wipe = true;
			}
		}
		kicking = kicking == null ? NO_KICK_PARAM : kicking;
		sender.sendMessage(Main.PLUGIN_PREFIX + "AntiBots protection enabled successfully (kicking: " + kicking.toUpperCase() + ", wipe: " + wipe.toString().toUpperCase() + ")");
		switch(kicking){
            case NO_KICK_PARAM:
                MainOperator.enableProtection();
                break;
            case KICK_ALL_PARAM:
                MainOperator.enableProtection();
                MainOperator.kickAll();
                break;
            case FORCE_KICK_ALL_PARAM:
                MainOperator.enableProtection();
                MainOperator.forceKickAll();
                break;
            default:
                sender.sendMessage(Main.PLUGIN_PREFIX + "Usage: /antibots protect [no-kick/kick-all/force-kick-all] [wipe]");
                return;
        }
		if(wipe){
		    wipe(sender);
        }

	}
	private void wipe(CommandSender sender){
		if(!sender.hasPermission("antibots.wipe")){
			sendNoPermissionsMessage(sender);
			return;
		}
        try{
            MainOperator.wipe();
            sender.sendMessage(Main.PLUGIN_PREFIX + "Verified players database truncated successfully");
        } catch(Exception e){
            sender.sendMessage(Main.PLUGIN_PREFIX + "An exception occurred while truncating database! Check console logs for more informations");
            e.printStackTrace();
        }
	}
	private void setDefault(CommandSender sender, String bool){
		if(!sender.hasPermission("antibots.default")){
			sendNoPermissionsMessage(sender);
			return;
		}
		if(bool==null ||  bool.length()==0){
			sender.sendMessage(Main.PLUGIN_PREFIX + "Usage: /antibots default <true/false>");
			return;
		}
		boolean b = Boolean.parseBoolean(bool);
		Main.config().set(Main.PROTECTION_KEY, b);
		Main.instance().saveConfig();
		sender.sendMessage(Main.PLUGIN_PREFIX + "You changed default protection to " + b + " ");
	}
	private void sendNoPermissionsMessage(CommandSender sender){
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4You don't have permission to do that!"));
	}
}
