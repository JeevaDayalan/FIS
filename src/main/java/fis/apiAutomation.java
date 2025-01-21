package fis;

import java.io.IOException;
import java.util.Iterator;

import org.json.JSONObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class apiAutomation {

	public static String baseuri = "https://api.coindesk.com/";
	public static String path = "v1/bpi";

	public static void main(String[] args) throws IOException
	{

		Response response = given()
				.contentType(ContentType.JSON)
				.baseUri(baseuri+path)
				.when()
				.get("/currentprice.json")
				.then()
				.statusCode(200)
				.extract()
				.response();

		JSONObject jObj = new JSONObject(response.asPrettyString().toString());	
		JSONObject jsonObject = (JSONObject)jObj.get("bpi");
		 Iterator<String> keys = jsonObject.keys();
	        while(keys.hasNext()) {
	        	String check = keys.next();
	        	if(check.equals("USD") || check.equals("GBP") || check.equals("EUR")) {
	        		System.out.println("Key available : "+ check);
	        	}
	        }
		JSONObject gbp = (JSONObject)jsonObject.get("GBP");
		if(gbp.get("description").toString().equals("British Pound Sterling"))
		{
			System.out.println("The GBP description is displayed as : "+gbp.get("description").toString());
		}
	}

}
