// AntiBots Plugin by pietrek777
// Distributed on MIT License
// This project on GitHub: https://github.com/pietrek777/AntiBots
// This project on SpigotMC: https://www.spigotmc.org/resources/antibots.45137/

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
	private String getResponseString(String nickname) throws Exception{
		DataProvider dp = DataProvider.getInstance();
		URL url = new URL(dp.getVerificationRoot() + "api.php?nickname=" + nickname);
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
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(getResponseString(nickname));
		if((Boolean) jsonObject.get("outcome")){
			return (Boolean) jsonObject.get("result");
		} else throw new Exception();

	}
}
