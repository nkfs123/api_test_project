import io.restassured.RestAssured;
import org.junit.Test;
import java.io.File;
import io.restassured.module.jsv.JsonSchemaValidator;

public class PeopleResponseSchemaValidationTests {

	@Test
	public void peopleResponseValidation_validTest() {
		File file = new File("src/test/resources/GetAllPeopleResponse.json");
		GetAllPeopleResponse resp = new GetAllPeopleResponse();
		RestAssured.get("https://swapi.dev/api/people").then().assertThat()
				.body(JsonSchemaValidator.matchesJsonSchema(file));
	}
	}

