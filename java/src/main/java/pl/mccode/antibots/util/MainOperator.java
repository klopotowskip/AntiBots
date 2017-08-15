// AntiBots Plugin by pietrek777
// Distributed on MIT License
// This project on GitHub: https://github.com/pietrek777/AntiBots
// This project on SpigotMC: https://www.spigotmc.org/resources/antibots.45137/


package pl.mccode.antibots.util;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import pl.mccode.antibots.Main;
import pl.mccode.antibots.event.EventListener;

import java.util.ArrayList;
import java.util.List;

public class MainOperator {
	public static void wipe() throws Exception{
		DataProvider dp = DataProvider.getInstance();
		HttpClient httpClient = HttpClients.createDefault();
		String root = dp.getVerificationRoot();
		HttpPost request = new HttpPost(root + "wipe.php");

		List<NameValuePair> params = new ArrayList<>(1);
		params.add(new BasicNameValuePair("key", dp.getWipeKey()));
		request.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

		HttpResponse response = httpClient.execute(request);
		if(response==null) throw new Exception("Response is null");
		int code = response.getStatusLine().getStatusCode();
		if(code!=200) throw new Exception("Http response code: " + code);
	}
	public static void enableProtection(){
		EventListener.protection = true;
	}
	public static void disableProtection(){
		EventListener.protection = false;
	}
	public static void kickAll(){
		Main.instance()
				.getServer()
				.getOnlinePlayers()
				.stream()
				.filter(x -> !x.hasPermission("antibots.nokick"))
				.forEach(x -> x.kickPlayer(kickMessage()));
	}
	public static void forceKickAll(){
		Main.instance()
				.getServer()
				.getOnlinePlayers()
				.forEach(x ->  x.kickPlayer(kickMessage()));
	}
	private static String kickMessage(){
		return DataProvider.getInstance().getFormattedMessage();
	}
}