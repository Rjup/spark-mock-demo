package sample.bikes;

import com.google.inject.Inject;
import sample.Json;
import spark.RouteGroup;

import static spark.SparkBuilder.after;
import static spark.SparkBuilder.get;
import static spark.SparkBuilder.post;

public class BikeRoutes {

	@Inject
	private BikeService bikeService;

	public RouteGroup routes() {
		return () -> {
			after(Json.addJsonHeader());
			get("", (request, response) -> bikeService.findAll());
			post("",
				(request, response) -> bikeService.create(Json.parse(request, BikeView.class)));
		};
	}
}
