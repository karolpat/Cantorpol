package karolpat.kantor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
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

	public static List<Rate> receiveData() throws IOException {

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
		return parseJSON(jArray);
	}

	public static List<Rate> parseJSON(JSONArray jArray) {

		JSONObject myJson = (JSONObject) jArray.get(0);

		List<Rate> list = new ArrayList<Rate>();
		JSONArray rates = myJson.getJSONArray("rates");
		for (int i = 0; i < rates.length(); i++) {
			JSONObject temp = rates.getJSONObject(i);
			list.add(new Rate(temp.getString("currency"), temp.getString("code"), temp.getDouble("mid")));
			System.out.println(list.get(i).toString());
		}

		NBPObject nbpObject = new NBPObject(myJson.getString("table"), myJson.getString("effectiveDate"),
				myJson.getString("no"));
		System.out.println(nbpObject);

		return list;
	}

	public static String[] codeList(List<Rate> list) {

		String[] codeArray = new String[list.size()];

		for (int i = 0; i < codeArray.length; i++) {
			codeArray[i] = list.get(i).getCode();
		}

		return codeArray;

	}

	public static BigDecimal getResult(String first, String second, double money) {

		BigDecimal result;

		double firstMid = getValue(first);
		double secondMid = getValue(second);

		result = new BigDecimal((firstMid / secondMid) * money);

		return result;

	}

	public static double getValue(String code) {

		double result = 0;
		List<Rate> list = new ArrayList<Rate>();
		try {
			list = receiveData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Rate r : list) {
			if (code.equals(r.getCode())) {
				result = r.getMid();
			}
		}

		try {
			validateValue(result);
		} catch (Exception ex) {
			ex.getMessage();
		}
		return result;
	}

	private static double validateValue(double value) {

		Exception ex;

		if (value == 0) {
			ex = new Exception("Sth went wrong with currency exchange rate");
		}
		return value;
	}
}
