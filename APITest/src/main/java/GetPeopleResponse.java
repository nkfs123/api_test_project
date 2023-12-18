import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetPeopleResponse {

    @JsonProperty("name")
    private String name;

    @JsonProperty("birth_year")
    private String birth_year;

    @JsonProperty("eye_color")
    private String eye_color;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("hair_color")
    private String hair_color;

    @JsonProperty("height")
    private String height;

    @JsonProperty("mass")
    private String mass;

    @JsonProperty("skin_color")
    private String skin_color;

    @JsonProperty("homeworld")
    private String homeworld;

    @JsonProperty("films")
    private String[] films;

    @JsonProperty("species")
    private String[] species;

    @JsonProperty("starships")
    private String[] starships;

    @JsonProperty("vehicles")
    private String[] vehicles;

    @JsonProperty("url")
    private String url;

    @JsonProperty("created")
    private String created;

    @JsonProperty("edited")
    private String edited;




}


