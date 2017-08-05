// AntiBots Plugin by pietrek777
// Distributed on MIT License
// This project on Github: https://github.com/pietrek777/AntiBots

package pl.mccode.antibots.util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiDataProvider {
	private static ApiDataProvider instance = null;

	public static ApiDataProvider getInstance() {
		if(instance==null) instance = new ApiDataProvider();
		return instance;
	}
	private ApiDataProvider(){}
	private ConfigDataProvider cdp;
	private String getResponseString(String nickname) throws Exception{
		URL url = new URL("http://"+ cdp.getHost() + ":" + cdp.getPort() + "/" + cdp.getPath() + "?" + cdp.getGetParam() + "=" + nickname);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Accept", "application/json");
		if (connection.getResponseCode() != 200) throw new Exception();

		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

		String line;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}

		connection.disconnect();

		return sb.toString();
	}

	public boolean testFor(String nickname) throws Exception{
		nickname = nickname.toLowerCase();
		cdp = ConfigDataProvider.getInstance();
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(getResponseString(nickname));
		if((Boolean) jsonObject.get(cdp.getOutcome())){
			return (Boolean) jsonObject.get(cdp.getResult());
		} else throw new Exception();

	}
}
