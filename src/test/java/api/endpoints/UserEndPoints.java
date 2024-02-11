package api.endpoints;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
public class UserEndPoints {

	public static Response createUser(User user) {
		
		return given().contentType("application/json")
		.accept(ContentType.JSON)
		.body(user)
		.when().post(Routes.user_post_url);
		

	}

	public static Response getUser(String userName) {
		
		return given().contentType(ContentType.JSON)
				.pathParam("username", userName)
				.when().get(Routes.user_get_url);

	}

	public static Response updateUser(String userName,User user) {
		
		
		return given().contentType(ContentType.JSON)
				.body(user).pathParam("username", userName)
				.when().put(Routes.user_update_url);

	}

	public static Response deleteUser(String userName) {
		return given().contentType(ContentType.JSON)
				.pathParam("username", userName)
				.when().delete(Routes.user_delete_url);

	}

}
