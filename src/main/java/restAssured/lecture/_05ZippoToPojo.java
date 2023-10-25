package restAssured.lecture;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.junit.Test;

import static io.restassured.RestAssured.get;

import java.util.ArrayList;

/*
{"post code": "34110",
"country": "Turkey",
"country abbreviation": "TR",
"places": [{"place name": "Alemdar Mah.", "longitude": "34.1812",
"state": "\u0130stanbul", "state abbreviation": "34", "latitude": "39.9844"},
{"place name": "Hocapa\u015fa Mah.", "longitude": "34.1812",
"state": "\u0130stanbul", "state abbreviation": "34", "latitude": "39.9844"}]}
 */
public class _05ZippoToPojo {
    @Test
    public void test1() {
        Root root = get("https://api.zippopotam.us/tr/34110").as(Root.class);
        System.out.println(root.getPostcode());
        System.out.println(root.getPlaces().get(1).getPlacename());


    }


    @Data
    static class Place {
        public String placename;
        public String longitude;
        public String state;
        public String stateabbreviation;
        public String latitude;

        @JsonProperty("place name")
        public void setPlacename(String placename) {
            this.placename = placename;
        }

        @JsonProperty("state abbreviation")
        public void setStateabbreviation(String stateabbreviation) {
            this.stateabbreviation = stateabbreviation;
        }


    }

    @Data
    static class Root {
        public String postcode;
        public String country;
        public String countryabbreviation;
        public ArrayList<Place> places;

        @JsonProperty("post code")
        public void setPostcode(String postcode) {
            this.postcode = postcode;

        }
            @JsonProperty("country abbreviation")
            public void setCountryabbreviation (String countryabbreviation){
                this.countryabbreviation = countryabbreviation;
            }

        }
    }


