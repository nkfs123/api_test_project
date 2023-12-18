import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class SingleFilmTests {

	public static GetFilmsResponse filmsResponse;

	@Before
	public void getFilms() throws JsonProcessingException {
		sendGetFilmsRequest();
	}
	@Test
	@DisplayName("Use cases 1 - 2 : Find the film with latest release date AND Using previous response (1) find the tallest person among the characters that\n" +
			"were part of that film.")
	public void findTallestPersonPlayedInFilmWithLatestReleaseDate() throws JsonProcessingException {
		GetFilmsResponse.Films film = findFilmWithLatestReleaseDate();
		List<GetPeopleResponse> listOfPeopleInTheFilm = new ArrayList<>();
		int i = 0;
		int j = 0;

		int biggestHeight = 0;
		int height = 0;
		for (String character : film.getCharacters()) {
			GetPeopleResponse response = sendGetSpecificPeopleRequest(character);
			height = Integer.parseInt(response.getHeight());

			if (height > biggestHeight) {
				biggestHeight = height;
				j = i;
			}
			listOfPeopleInTheFilm.add(i, response);
			i++;
		}
		System.out.println("The tallest person of the film "+film.getTitle()+" is "+listOfPeopleInTheFilm.get(j).getName()+" with height "+listOfPeopleInTheFilm.get(j).getHeight());
	}




	private GetFilmsResponse.Films findFilmWithLatestReleaseDate() throws JsonProcessingException {
		Date latestReleaseDate;
		List<Date> filmsDateList = filmsResponse.
				getFilmsList().stream()
				.map(GetFilmsResponse.Films::getRelease_date).collect(Collectors.toList());

		//Could also be found with stream().filter
		int i = 0;
		int j = 0;
		latestReleaseDate = filmsDateList.get(i);
		for (Date date : filmsDateList){
			if (date.after(latestReleaseDate)) {
				date = latestReleaseDate;
				j = i;
			}
			i++;
		}
		System.out.println("The film with the latest release date is: "+ filmsResponse.getFilmsList().get(j).getTitle());
		return filmsResponse.getFilmsList().get(j);
	}

	private void sendGetFilmsRequest() throws JsonProcessingException {
		RestAssured.baseURI = "https://swapi.dev/api/";
		String resp = RestAssured.get("films").getBody().asString();
		ObjectMapper objectMapper = new ObjectMapper();
		filmsResponse = objectMapper.readValue(resp, GetFilmsResponse.class);

	}

	private GetPeopleResponse sendGetSpecificPeopleRequest(String characterUrl) throws JsonProcessingException {
		String resp = RestAssured.get(characterUrl).getBody().asString();
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(resp, GetPeopleResponse.class);
	}


}
