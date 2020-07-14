package resource;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

	public AddPlace addPlacePayload(String name, String language, String address) {
		AddPlace obj = new AddPlace();

		obj.setAccuracy(50);
		obj.setName(name);
		obj.setPhone_number("(+91) 983 893 3937");
		obj.setAddress(address);
		obj.setWebsite("http://google.com");
		obj.setLanguage(language);

		List<String> l = new ArrayList<String>();
		l.add("shoe park");
		l.add("shop");
		obj.setTypes(l);

		Location loc = new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		obj.setLocation(loc);
		return obj;
	}
	
	public String deletePlace_Payload(String place_id) {
		return "{\r\n\t\"place_id\" :\""+place_id+"\"\r\n}";
	}

}
