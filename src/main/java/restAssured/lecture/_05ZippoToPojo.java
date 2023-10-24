package restAssured.lecture;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.junit.Test;
import static io.restassured.RestAssured.get;

import java.util.ArrayList;



public class _05ZippoToPojo {
    @Test
    public void test(){

    }



    static public class Place{
        public String placename;

        @JsonProperty("place name")
        public void setPlacename(String placename) {
            this.placename = placename;
        }

        @JsonProperty("State abbreviation")
           public void setStateabbreviation(String stateabbreviation) {
            this.stateabbreviation = stateabbreviation;
        }

        public String longitude;
        public String state;
        public String stateabbreviation;
        public String latitude;
    }

    public class Root{
        public String postcode;
        public String country;

        @JsonProperty("post code")
        public void setPostcode(String postcode) {
            this.postcode = postcode;
        }

        @JsonProperty("Country abbreviation")
        public void setCountryabbreviation(String countryabbreviation) {
            this.countryabbreviation = countryabbreviation;
        }

        public String countryabbreviation;
        public ArrayList<Place> places;
    }

}
