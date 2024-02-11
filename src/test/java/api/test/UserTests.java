package api.test;

import org.testng.annotations.Test;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import com.github.javafaker.Faker;
import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.ExtentTestManager;
import io.restassured.response.Response;

public class UserTests {
	public Faker faker = null;
	public User user = null;

	@BeforeClass
	public void setUp() {
		faker = new Faker();
		user = new User();
		user.setUsername(faker.name().username());
		user.setFirstName(faker.name().firstName());
		user.setLastName(faker.name().lastName());
		user.setEmail(faker.internet().safeEmailAddress());
		user.setPassword(faker.internet().password(5, 10));
		user.setPhone(faker.phoneNumber().cellPhone());
		user.setUserStatus(0);

		
	}

	@Test(priority = 1)
	public void userCreateTest() {
		Response res = UserEndPoints.createUser(user);
		System.out.println("User Name:-" + user.getUsername());
		res.then().log().all();
		res.then().assertThat().statusCode(200);
		ExtentTestManager.addJsonResponseToReport(res.asPrettyString());
	}

	@Test(priority = 2)
	public void userGetTest() {
		System.out.println("User Name:-" + user.getUsername());
		Response res = UserEndPoints.getUser(user.getUsername());
		res.then().log().all();
		res.then().assertThat().statusCode(200);
		ExtentTestManager.addJsonResponseToReport(res.asPrettyString());

	}

	@Test(priority = 3,enabled = true)
	public void userUpdateTest() {
		String userName = user.getUsername();
		user.setFirstName("Update First Name");
		user.setLastName("Update Last Name");
		Response res = UserEndPoints.updateUser(userName, user);
		res.then().log().all();
		res.then().assertThat().statusCode(200);
		ExtentTestManager.addJsonResponseToReport(res.asPrettyString());
	}

	@Test(priority = 4,enabled = true)
	public void userGetUpdatedTest() {
		System.out.println("User Name:-" + user.getUsername());
		Response res = UserEndPoints.getUser(user.getUsername());
		res.then().log().all();
		res.then().assertThat().statusCode(200);

		ExtentTestManager.addJsonResponseToReport(res.asPrettyString());
	}

	@Test(priority = 5,enabled = true)
	public void userDeleteTest() {
		System.out.println("User Name:-" + user.getUsername());
		Response res = UserEndPoints.deleteUser(user.getUsername());
		res.then().log().all();
		res.then().assertThat().statusCode(200);
		ExtentTestManager.addJsonResponseToReport(res.asPrettyString());
	}

}
