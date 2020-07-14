package stepDefinition;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeDeletePlace() throws IOException {
		// This method only execute when place_id is null
		// write the code to get the place id
		PlaceValidations stepDef = new PlaceValidations();

		if (PlaceValidations.place_id == null) {
			stepDef.add_Place_Payload_with("Rituraj", "French", "VVS Residency");
			stepDef.user_call_with_Post_http_Request("addPlaceAPI", "POST");
			stepDef.verify_place_id_created_maps_to_using("Rituraj", "getPlaceAPI");

		}
	}

}
