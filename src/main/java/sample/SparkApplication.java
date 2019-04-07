package sample;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sample.bikes.BikeModule;
import sample.bikes.BikeRoutes;
import spark.SparkBuilder;

public class SparkApplication implements spark.servlet.SparkApplication {

	private static final Logger LOG = LoggerFactory.getLogger(SparkApplication.class);

	public static void main(String[] args) {
		new SparkApplication().init();
		SparkBuilder.initDefault();
	}

	@Override
	public void init() {
		Injector injector = startGuice();
		configureRoutes(injector);
	}

	private void configureRoutes(Injector injector) {
		SparkBuilder.defaultResponseTransformer(new Json.JsonTransformer());

		SparkBuilder.before("/*", (q, a) -> LOG.info("{} {}", q.requestMethod(), q.pathInfo()));
		SparkBuilder.path("/hello", injector.getInstance(BikeRoutes.class).routes());
	}

	private Injector startGuice() {
		Injector injector = Guice.createInjector(new BikeModule());
		return injector;
	}

}
