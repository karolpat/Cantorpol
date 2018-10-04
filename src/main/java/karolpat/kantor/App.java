package karolpat.kantor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		new MVCApp().setVisible(true);
		
//		try {
//			receiveData();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	public static void receiveData() throws IOException {

		String sURL = "http://api.nbp.pl/api/exchangerates/tables/A?format=json";

		URL obj = new URL(sURL);

		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + sURL);
		System.out.println("Response Code : " + responseCode);

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(obj.openStream()));

		StringBuilder report = new StringBuilder();

		String line;
		while ((line = bufferedReader.readLine()) != null) {
			report.append(line + "\n");
		}

		bufferedReader.close();

		System.out.println(report.toString());
		
		JSONArray jArray = new JSONArray(report.toString());
		parseJSON(jArray);
		}
	
	public static void parseJSON(JSONArray jArray) {
		
		JSONObject myJson = (JSONObject) jArray.get(0);

		List<Rate> list = new ArrayList<Rate>();
		JSONArray rates = myJson.getJSONArray("rates");
		for (int i = 0; i < rates.length(); i++) {
			JSONObject temp = rates.getJSONObject(i);
			list.add(new Rate(temp.getString("currency"), temp.getString("code"), temp.getDouble("mid")));
			System.out.println(list.get(i).toString());
		}
		
		NBPObject nbpObject = new NBPObject(myJson.getString("table"), myJson.getString("effectiveDate"),myJson.getString("no"));
		System.out.println(nbpObject);
	
	}
}
