package resource;

public enum APIResource {
	
	addPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");
	
	private String resource;
	
	APIResource(String resource){
		this.resource = resource;
	}
	
	public String getResource() {
		return resource;
	}
	

}
