// AntiBots Plugin by pietrek777
// Distributed on MIT License
// This project on GitHub: https://github.com/pietrek777/AntiBots
// This project on SpigotMC: https://www.spigotmc.org/resources/antibots.45137/

package pl.mccode.antibots.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import pl.mccode.antibots.Main;
import pl.mccode.antibots.util.ApiDataProvider;
import pl.mccode.antibots.util.DataProvider;

import java.util.logging.Level;

public class EventListener implements Listener{

	public static boolean protection;
	public static String kickMessage;
	@EventHandler
	public void onPlayerJoin(AsyncPlayerPreLoginEvent event){
		if(protection){
			DataProvider dp = DataProvider.getInstance();
			ApiDataProvider adp = ApiDataProvider.getInstance();
			try{
				if(dp.getWhitelist().contains(event.getName())) return;
				if(!adp.testFor(event.getName())){
					event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, DataProvider.getInstance().getFormattedMessage());
				}
			} catch(Exception e) {
				Main.instance().getLogger().log(Level.SEVERE, "An exception occured while verifying " + event.getName() + ". Usually it's caused by bad configuration. Check your configuration, or start a new issue on GitHub!");
				event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, DataProvider.getInstance().getFormattedMessage());
			}
		}
	}
}
