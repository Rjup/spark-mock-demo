package sample.bikes;

import org.junit.Before;
import org.junit.Test;
import sample.SparkApplication;
import spark.MockSpark;
import spark.SparkBuilder;
import spark.SparkClient;
import spark.http.Response;

import static org.junit.Assert.assertEquals;

public class BikeRoutesTest {

	@Before
	public void setup() {
		SparkBuilder.initMock();
		SparkApplication app = new SparkApplication();
		app.init();
	}

	@Test
	public void getReturnsEmptyArray() {
		SparkClient client = MockSpark.getClient();

		Response response = client.get("/hello");
		assertEquals(200, response.status());
		assertEquals("[]", response.body());
	}

	@Test
	public void wrongUrlReturns404() {

		SparkClient client = MockSpark.getClient();

		Response response = client.get("/bad-url");
		assertEquals(404, response.status());
	}

	@Test
	public void canCreateABike() {

		SparkClient client = MockSpark.getClient();

		Response postResponse = client.post("/hello", "{\"name\": \"foo\"}");
		assertEquals(200, postResponse.status());
		Response response = client.get("/hello");
		assertEquals(200, response.status());
		assertEquals("[{\"id\":null,\"name\":\"foo\"}]", response.body());
	}
}
