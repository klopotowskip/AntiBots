package pl.mccode.antiddos.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import pl.mccode.antiddos.util.ApiDataProvider;
import pl.mccode.antiddos.util.ConfigDataProvider;

public class EventListener implements Listener{

	public static boolean ddosProtection;
	public static String kickMessage;
	@EventHandler
	public void onPlayerJoin(AsyncPlayerPreLoginEvent event){
		if(ddosProtection){
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
