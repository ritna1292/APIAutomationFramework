package stepDefinition;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resource.APIResource;
import resource.TestDataBuild;
import resource.Utils;

public class PlaceValidations extends Utils {

	RequestSpecification req;
	RequestSpecification response;
	ResponseSpecification res;
	Response rescp;
	TestDataBuild data = new TestDataBuild();
    static String place_id;

	@Given("Add Place Payload with {string} {string} {string}")
	public void add_Place_Payload_with(String name, String language, String address) throws IOException {

		response = given().relaxedHTTPSValidation().urlEncodingEnabled(false).spec(requestSpecification())
				.body(data.addPlacePayload(name, language, address));

	}

	@When("User call {string} with {string} http Request")
	public void user_call_with_Post_http_Request(String apiType, String method) {
		
		
		APIResource resourceAPI = APIResource.valueOf(apiType);
		System.out.println(resourceAPI.getResource());
		
		res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(method.equalsIgnoreCase("POST"))
		rescp = response.when().post(resourceAPI.getResource());
		else if(method.equalsIgnoreCase("GET"))
			rescp = response.when().get(resourceAPI.getResource());
			

	}

	@Then("The API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(Integer int1) {

		assertEquals(rescp.getStatusCode(), 200);
	}

	@Then("{string} in Response body is {string}")
	public void in_Response_body_is(String Key, String Value) {
		assertEquals(getJsonPath(rescp,Key), Value);
	}
	
	@Then("Verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
		 place_id = getJsonPath(rescp, "place_id");
		 System.out.println(place_id);
		response = given().relaxedHTTPSValidation().urlEncodingEnabled(false).spec(requestSpecification()).
				queryParam("place_id", getJsonPath(rescp, "place_id"));
		user_call_with_Post_http_Request(resource, "GET");
		assertEquals(getJsonPath(rescp, "name"), expectedName);
			
	}
	
	@Given("deletePlace payload")
	public void deleteplace_payload() throws IOException {
		response = given().relaxedHTTPSValidation().urlEncodingEnabled(false).spec(requestSpecification()).body(data.deletePlace_Payload(place_id));
	}

}
