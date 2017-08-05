// AntiBots Plugin by pietrek777
// Distributed on MIT License
// This project on Github: https://github.com/pietrek777/AntiBots

package pl.mccode.antibots.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import pl.mccode.antibots.util.ApiDataProvider;
import pl.mccode.antibots.util.ConfigDataProvider;

public class EventListener implements Listener{

	public static boolean protection;
	public static String kickMessage;
	@EventHandler
	public void onPlayerJoin(AsyncPlayerPreLoginEvent event){
		if(protection){
			ApiDataProvider adp = ApiDataProvider.getInstance();
			try{
				if(!adp.testFor(event.getName())){
                    event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, ConfigDataProvider.getInstance().getFormattedMessage());
				}
			} catch(Exception e) {
				System.out.println("An exception occured while verifying " + event.getName() + ". Check your config file!");
                event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, ConfigDataProvider.getInstance().getFormattedMessage());
			}
		}
	}
}
