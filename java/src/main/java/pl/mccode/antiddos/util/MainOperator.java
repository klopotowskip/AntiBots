// AntiDDoS Plugin by pietrek777
// Distributed on MIT License
// This project on Github: https://github.com/pietrek777/AntiDDoS


package pl.mccode.antiddos.util;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import pl.mccode.antiddos.Main;
import pl.mccode.antiddos.event.EventListener;

import java.util.ArrayList;
import java.util.List;

public class MainOperator {
	public static void wipe() throws Exception{
	    ConfigDataProvider cdp = ConfigDataProvider.getInstance();
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost request = new HttpPost(cdp.getWipeUrl());

        List<NameValuePair> params = new ArrayList<>(1);
        params.add(new BasicNameValuePair("key", cdp.getWipeKey()));
        request.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

        HttpResponse response = httpClient.execute(request);
        if(response==null) throw new Exception("Response is null");
        int code = response.getStatusLine().getStatusCode();
        if(code!=200) throw new Exception("Http response code: " + code);
	}
	public static void enableProtection(){
		EventListener.ddosProtection = true;
	}
	public static void disableProtection(){
		EventListener.ddosProtection = false;
	}
	public static void kickAll(){
		Main.instance()
				.getServer()
				.getOnlinePlayers()
				.stream()
				.filter(x -> !x.hasPermission("antiddos.nokick"))
				.forEach(x -> x.kickPlayer(kickMessage()));
	}
	public static void forceKickAll(){
		Main.instance()
				.getServer()
				.getOnlinePlayers()
				.forEach(x ->  x.kickPlayer(kickMessage()));
	}
	private static String kickMessage(){
		return ConfigDataProvider.getInstance().getFormattedMessage();
	}
}
