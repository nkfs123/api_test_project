import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AllFilmsTests {

	public static GetAllPeopleResponse allPeople;

	@Before
	public void getAllPeople() throws JsonProcessingException {
		allPeople = sendGetAllPeopleRequest("https://swapi.dev/api/people");
	}

	@Test
	@DisplayName("Use Case 3: Find the tallest person ever played in any Star Wars film.")
	public void findTallestPersonOfAllFilms() throws JsonProcessingException {
		int j = 0;
		int largestHeight = 0;
		List<GetPeopleResponse> tallestPerPage = new ArrayList<>();

		GetAllPeopleResponse peopleResponsePerPage = allPeople;
		for (int i = 0; i < allPeople.getCount(); i++) {
			tallestPerPage.add(i, findTallestPersonInSpecificPage(peopleResponsePerPage));
			int tmpHeight = Integer.parseInt(tallestPerPage.get(i).getHeight());
			if (tmpHeight > largestHeight) {
				largestHeight = tmpHeight;
				j = i;
			}
			if (peopleResponsePerPage.getNext() != null)
				peopleResponsePerPage = sendGetAllPeopleRequest(peopleResponsePerPage.getNext());
			else
				break;
		}
		System.out.println("The tallest person that has played in films is "
				+tallestPerPage.get(j).getName()+
				" with height "
				+tallestPerPage.get(j).getHeight());
	}

	private GetAllPeopleResponse sendGetAllPeopleRequest(String url) throws JsonProcessingException {
		String resp = RestAssured.get(url).getBody().asString();
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(resp, GetAllPeopleResponse.class);
	}

	public GetPeopleResponse findTallestPersonInSpecificPage(GetAllPeopleResponse allPeopleOfCurrentPage) {
		List<GetPeopleResponse> listOfPeopleThatPlayedInFilmsInCurrentPage = allPeopleOfCurrentPage.getAllPeopleList()
				.stream().filter(person -> person.getFilms().length > 0).collect(Collectors.toList());
		int i = 0;
		int j = 0;
		int largestHeight = 0;
		for (GetPeopleResponse person : listOfPeopleThatPlayedInFilmsInCurrentPage) {
			try {
				if (Integer.parseInt(person.getHeight()) > largestHeight) {
					largestHeight = Integer.parseInt(person.getHeight());
					j = i;
				}
				i++;
			} catch (NumberFormatException ex)
			{
				i++;
				continue;
			}
		}
		return listOfPeopleThatPlayedInFilmsInCurrentPage.get(j);
	}







}
