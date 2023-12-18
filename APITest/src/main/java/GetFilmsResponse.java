import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.List;
import java.util.Date;

@Getter
@Setter
public class GetFilmsResponse {

    @JsonProperty("results")
    private List<Films> filmsList;

    @JsonProperty("count")
    private int count;

    @JsonProperty("next")
    private String next;

    @JsonProperty("previous")
    private String previous;



    @Getter
    @Setter
    public static class Films {

        @JsonProperty("title")
        private String title;

        @JsonProperty("episode_id")
        private int episode_id;

        @JsonProperty("opening_crawl")
        private String opening_crawl;

        @JsonProperty("director")
        private String director;

        @JsonProperty("producer")
        private String producer;

        @JsonProperty("release_date")
        private Date release_date;

        @JsonProperty("species")
        private String[] species;

        @JsonProperty("starships")
        private String[] starships;

        @JsonProperty("vehicles")
        private String[] vehicles;

        @JsonProperty("characters")
        private String[] characters;

        @JsonProperty("planets")
        private String[] planets;

        @JsonProperty("url")
        private URL url;

        @JsonProperty("created")
        private String created;

        @JsonProperty("edited")
        private String edited;

    }

}


