package spark;

import spark.http.MockRequest;
import spark.http.MockResponse;
import spark.http.Response;

import javax.servlet.ServletException;
import java.io.IOException;

public class SparkClient {

	private final MockService service;

	public SparkClient(MockService service) {
		this.service = service;
	}

	public Response get(String path) {
		return doHttpCall(new MockRequest("GET", path).withHeader("Accept", "*/*"));
	}

	private Response doHttpCall(MockRequest request) {
		MockResponse response = new MockResponse();
		try {
			service.getMatcherFilter().doFilter(request, response, null);
		} catch (IOException | ServletException e) {
			throw new RuntimeException(e);
		}
		return response;
	}

	public Response post(String path, String body) {
		return doHttpCall(new MockRequest("POST", path).body(body));
	}
}
