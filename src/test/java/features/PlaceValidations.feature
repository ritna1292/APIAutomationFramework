Feature: Validating Place API's

@AddPlace @Regression
Scenario Outline: Verify if place is being succesfully added using AddPlaceAPI
Given Add Place Payload with "<name>" "<language>" "<address>"
When User call "addPlaceAPI" with "POST" http Request
Then The API call got success with status code 200
And "status" in Response body is "OK"
And Verify place_id created maps to "<name>" using "getPlaceAPI"

 Examples: 
           |name  |language |address           |
           |vvs   |English  |world trade centre|
     
@DeletePlace @Regression   
Scenario: Verify if deletePlace functionality is working
Given deletePlace payload
When User call "deletePlaceAPI" with "POST" http Request
Then The API call got success with status code 200
And "status" in Response body is "OK"
          