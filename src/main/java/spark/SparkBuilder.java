package spark;

public class SparkBuilder {

	private static boolean useDefault = true;

	public static void initDefault() {
		useDefault = true;
	}

	public static void initMock() {
		useDefault = false;
	}

	public static void defaultResponseTransformer(ResponseTransformer responseTransformer) {
		if (useDefault) {
			Spark.defaultResponseTransformer(responseTransformer);
		} else {
			MockSpark.defaultResponseTransformer(responseTransformer);
		}
	}

	/**
	 * Add a path-prefix to the routes declared in the routeGroup
	 * The path() method adds a path-fragment to a path-stack, adds
	 * routes from the routeGroup, then pops the path-fragment again.
	 * It's used for separating routes into groups, for example:
	 * path("/api/email", () -> {
	 * ....post("/add",       EmailApi::addEmail);
	 * ....put("/change",     EmailApi::changeEmail);
	 * ....etc
	 * });
	 * Multiple path() calls can be nested.
	 *
	 * @param path       the path to prefix routes with
	 * @param routeGroup group of routes (can also contain path() calls)
	 */
	public static void path(String path, RouteGroup routeGroup) {
		if (useDefault) {
			Spark.path(path, routeGroup);
		} else {
			MockSpark.path(path, routeGroup);
		}
	}

	/**
	 * Maps one or many filters to be executed before any matching routes
	 *
	 * @param filters The filters
	 */
	public static void before(Filter... filters) {
		if (useDefault) {
			Spark.before(filters);
		} else {
			MockSpark.before(filters);
		}
	}

	/**
	 * Maps a filter to be executed before any matching routes
	 *
	 * @param path   the path
	 * @param filter The filter
	 */
	public static void before(String path, Filter filter) {
		if (useDefault) {
			Spark.before(path, filter);
		} else {
			MockSpark.before(path, filter);
		}
	}

	/**
	 * Maps an array of filters to be executed before any matching routes
	 *
	 * @param path    the path
	 * @param filters the filters
	 */

	public static void before(String path, Filter... filters) {
		if (useDefault) {
			Spark.before(path, filters);
		} else {
			MockSpark.before(path, filters);
		}
	}

	/**
	 * Maps one or many filters to be executed after any matching routes
	 *
	 * @param filters The filters
	 */
	public static void after(Filter... filters) {
		if (useDefault) {
			Spark.after(filters);
		} else {
			MockSpark.after(filters);
		}
	}

	/**
	 * Maps one or many filters to be executed before any matching routes
	 *
	 * @param path       the path
	 * @param acceptType the accept type
	 * @param filters    The filters
	 */
	public static void before(String path, String acceptType, Filter... filters) {
		if (useDefault) {
			Spark.before(path, acceptType, filters);
		} else {
			MockSpark.before(path, acceptType, filters);
		}
	}

	/**
	 * Maps one or many filters to be executed after any matching routes
	 *
	 * @param path       the path
	 * @param acceptType the accept type
	 * @param filters    The filters
	 */
	public static void after(String path, String acceptType, Filter... filters) {
		if (useDefault) {
			Spark.after(path, acceptType, filters);
		} else {
			MockSpark.after(path, acceptType, filters);
		}
	}

	/**
	 * Execute after route even if the route throws exception
	 *
	 * @param path   the path
	 * @param filter the filter
	 */
	public static void afterAfter(String path, Filter filter) {
		if (useDefault) {
			Spark.afterAfter(path, filter);
		} else {
			MockSpark.afterAfter(path, filter);
		}
	}

	/**
	 * Execute after any matching route even if the route throws exception
	 *
	 * @param filter the filter
	 */
	public static void afterAfter(Filter filter) {
		if (useDefault) {
			Spark.afterAfter(filter);
		} else {
			MockSpark.afterAfter(filter);
		}
	}

	//////////////////////////////////////////////////
	// END route/filter mapping with accept type
	//////////////////////////////////////////////////

	/**
	 * Map the route for HTTP GET requests
	 *
	 * @param path  the path
	 * @param route The route
	 */
	public static void get(final String path, final Route route) {
		if (useDefault) {
			Spark.get(path, route);
		} else {
			MockSpark.get(path, route);
		}
	}

	/**
	 * Map the route for HTTP POST requests
	 *
	 * @param path  the path
	 * @param route The route
	 */
	public static void post(String path, Route route) {
		if (useDefault) {
			Spark.post(path, route);
		} else {
			MockSpark.post(path, route);
		}
	}

	/**
	 * Map the route for HTTP PUT requests
	 *
	 * @param path  the path
	 * @param route The route
	 */
	public static void put(String path, Route route) {
		if (useDefault) {
			Spark.put(path, route);
		} else {
			MockSpark.put(path, route);
		}
	}

	/**
	 * Map the route for HTTP PATCH requests
	 *
	 * @param path  the path
	 * @param route The route
	 */
	public static void patch(String path, Route route) {
		if (useDefault) {
			Spark.patch(path, route);
		} else {
			MockSpark.patch(path, route);
		}
	}

	/**
	 * Map the route for HTTP DELETE requests
	 *
	 * @param path  the path
	 * @param route The route
	 */
	public static void delete(String path, Route route) {
		if (useDefault) {
			Spark.delete(path, route);
		} else {
			MockSpark.delete(path, route);
		}
	}

	/**
	 * Map the route for HTTP HEAD requests
	 *
	 * @param path  the path
	 * @param route The route
	 */
	public static void head(String path, Route route) {
		if (useDefault) {
			Spark.head(path, route);
		} else {
			MockSpark.head(path, route);
		}
	}
	//////////////////////////////////////////////////
	// BEGIN Template View Routes
	//////////////////////////////////////////////////

	/**
	 * Map the route for HTTP GET requests
	 *
	 * @param path   the path
	 * @param route  The route
	 * @param engine the template engine
	 */
	public static void get(String path, TemplateViewRoute route, TemplateEngine engine) {
		if (useDefault) {
			Spark.get(path, route, engine);
		} else {
			MockSpark.get(path, route, engine);
		}
	}

	/**
	 * Map the route for HTTP GET requests
	 *
	 * @param path       the path
	 * @param acceptType the accept type
	 * @param route      The route
	 * @param engine     the template engine
	 */
	public static void get(String path,
		String acceptType,
		TemplateViewRoute route,
		TemplateEngine engine) {
		if (useDefault) {
			Spark.get(path, acceptType, route, engine);
		} else {
			MockSpark.get(path, acceptType, route, engine);
		}
	}

	/**
	 * Map the route for HTTP POST requests
	 *
	 * @param path   the path
	 * @param route  The route
	 * @param engine the template engine
	 */
	public static void post(String path, TemplateViewRoute route, TemplateEngine engine) {
		if (useDefault) {
			Spark.post(path, route, engine);
		} else {
			MockSpark.post(path, route, engine);
		}
	}

	/**
	 * Map the route for HTTP POST requests
	 *
	 * @param path       the path
	 * @param acceptType the accept type
	 * @param route      The route
	 * @param engine     the template engine
	 */
	public static void post(String path,
		String acceptType,
		TemplateViewRoute route,
		TemplateEngine engine) {
		if (useDefault) {
			Spark.post(path, acceptType, route, engine);
		} else {
			MockSpark.post(path, acceptType, route, engine);
		}
	}

	/**
	 * Map the route for HTTP PUT requests
	 *
	 * @param path   the path
	 * @param route  The route
	 * @param engine the template engine
	 */
	public static void put(String path, TemplateViewRoute route, TemplateEngine engine) {
		if (useDefault) {
			Spark.put(path, route, engine);
		} else {
			MockSpark.put(path, route, engine);
		}
	}

	/**
	 * Map the route for HTTP PUT requests
	 *
	 * @param path       the path
	 * @param acceptType the accept type
	 * @param route      The route
	 * @param engine     the template engine
	 */
	public static void put(String path,
		String acceptType,
		TemplateViewRoute route,
		TemplateEngine engine) {
		if (useDefault) {
			Spark.put(path, acceptType, route, engine);
		} else {
			MockSpark.put(path, acceptType, route, engine);
		}
	}

	/**
	 * Map the route for HTTP DELETE requests
	 *
	 * @param path   the path
	 * @param route  The route
	 * @param engine the template engine
	 */
	public static void delete(String path, TemplateViewRoute route, TemplateEngine engine) {
		if (useDefault) {
			Spark.delete(path, route, engine);
		} else {
			MockSpark.delete(path, route, engine);
		}
	}

	/**
	 * Map the route for HTTP DELETE requests
	 *
	 * @param path       the path
	 * @param acceptType the accept type
	 * @param route      The route
	 * @param engine     the template engine
	 */
	public static void delete(String path,
		String acceptType,
		TemplateViewRoute route,
		TemplateEngine engine) {
		if (useDefault) {
			Spark.delete(path, acceptType, route, engine);

		} else {
			MockSpark.delete(path, acceptType, route, engine);

		}
	}
}
