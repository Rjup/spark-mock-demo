package spark;

import java.util.function.Consumer;

public class MockSpark {

	// Hide constructor
	protected MockSpark() {
	}

	public static SparkClient getClient() {
		return new SparkClient(SingletonHolder.INSTANCE);
	}

	/**
	 * Initializes singleton.
	 */
	private static class SingletonHolder {

		private static final MockService INSTANCE = ignite();
	}

	private static MockService getInstance() {
		return MockSpark.SingletonHolder.INSTANCE;
	}

	/**
	 * Creates a new Service (a Spark instance). This should be used instead of the static API if the user wants
	 * multiple services in one process.
	 *
	 * @return the newly created object
	 */
	public static MockService ignite() {
		return new MockService();
	}

	/**
	 * Statically import this for redirect utility functionality, see {@link spark.Redirect}
	 */
	public static final Redirect redirect = getInstance().redirect;

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
		getInstance().path(path, routeGroup);
	}

	/**
	 * Map the route for HTTP GET requests
	 *
	 * @param path  the path
	 * @param route The route
	 */
	public static void get(final String path, final Route route) {
		getInstance().get(path, route);
	}

	/**
	 * Map the route for HTTP POST requests
	 *
	 * @param path  the path
	 * @param route The route
	 */
	public static void post(String path, Route route) {
		getInstance().post(path, route);
	}

	/**
	 * Map the route for HTTP PUT requests
	 *
	 * @param path  the path
	 * @param route The route
	 */
	public static void put(String path, Route route) {
		getInstance().put(path, route);
	}

	/**
	 * Map the route for HTTP PATCH requests
	 *
	 * @param path  the path
	 * @param route The route
	 */
	public static void patch(String path, Route route) {
		getInstance().patch(path, route);
	}

	/**
	 * Map the route for HTTP DELETE requests
	 *
	 * @param path  the path
	 * @param route The route
	 */
	public static void delete(String path, Route route) {
		getInstance().delete(path, route);
	}

	/**
	 * Map the route for HTTP HEAD requests
	 *
	 * @param path  the path
	 * @param route The route
	 */
	public static void head(String path, Route route) {
		getInstance().head(path, route);
	}

	/**
	 * Map the route for HTTP TRACE requests
	 *
	 * @param path  the path
	 * @param route The route
	 */
	public static void trace(String path, Route route) {
		getInstance().trace(path, route);
	}

	/**
	 * Map the route for HTTP CONNECT requests
	 *
	 * @param path  the path
	 * @param route The route
	 */
	public static void connect(String path, Route route) {
		getInstance().connect(path, route);
	}

	/**
	 * Map the route for HTTP OPTIONS requests
	 *
	 * @param path  the path
	 * @param route The route
	 */
	public static void options(String path, Route route) {
		getInstance().options(path, route);
	}

	/**
	 * Maps a filter to be executed before any matching routes
	 *
	 * @param path   the path
	 * @param filter The filter
	 */
	public static void before(String path, Filter filter) {
		getInstance().before(path, filter);
	}

	/**
	 * Maps an array of filters to be executed before any matching routes
	 *
	 * @param path    the path
	 * @param filters the filters
	 */

	public static void before(String path, Filter... filters) {
		for (Filter filter : filters) {
			getInstance().before(path, filter);
		}
	}

	/**
	 * Maps a filter to be executed after any matching routes
	 *
	 * @param path   the path
	 * @param filter The filter
	 */
	public static void after(String path, Filter filter) {
		getInstance().after(path, filter);
	}

	/**
	 * Maps an array of filters to be executed after any matching routes
	 *
	 * @param path    the path
	 * @param filters The filters
	 */

	public static void after(String path, Filter... filters) {
		for (Filter filter : filters) {
			getInstance().after(path, filter);
		}
	}

	//////////////////////////////////////////////////
	// BEGIN route/filter mapping with accept type
	//////////////////////////////////////////////////

	/**
	 * Map the route for HTTP GET requests
	 *
	 * @param path       the path
	 * @param acceptType the accept type
	 * @param route      The route
	 */
	public static void get(String path, String acceptType, Route route) {
		getInstance().get(path, acceptType, route);
	}

	/**
	 * Map the route for HTTP POST requests
	 *
	 * @param path       the path
	 * @param acceptType the accept type
	 * @param route      The route
	 */
	public static void post(String path, String acceptType, Route route) {
		getInstance().post(path, acceptType, route);
	}

	/**
	 * Map the route for HTTP PUT requests
	 *
	 * @param path       the path
	 * @param acceptType the accept type
	 * @param route      The route
	 */
	public static void put(String path, String acceptType, Route route) {
		getInstance().put(path, acceptType, route);
	}

	/**
	 * Map the route for HTTP PATCH requests
	 *
	 * @param path       the path
	 * @param acceptType the accept type
	 * @param route      The route
	 */
	public static void patch(String path, String acceptType, Route route) {
		getInstance().patch(path, acceptType, route);
	}

	/**
	 * Map the route for HTTP DELETE requests
	 *
	 * @param path       the path
	 * @param acceptType the accept type
	 * @param route      The route
	 */
	public static void delete(String path, String acceptType, Route route) {
		getInstance().delete(path, acceptType, route);
	}

	/**
	 * Map the route for HTTP HEAD requests
	 *
	 * @param path       the path
	 * @param acceptType the accept type
	 * @param route      The route
	 */
	public static void head(String path, String acceptType, Route route) {
		getInstance().head(path, acceptType, route);
	}

	/**
	 * Map the route for HTTP TRACE requests
	 *
	 * @param path       the path
	 * @param acceptType the accept type
	 * @param route      The route
	 */
	public static void trace(String path, String acceptType, Route route) {
		getInstance().trace(path, acceptType, route);
	}

	/**
	 * Map the route for HTTP CONNECT requests
	 *
	 * @param path       the path
	 * @param acceptType the accept type
	 * @param route      The route
	 */
	public static void connect(String path, String acceptType, Route route) {
		getInstance().connect(path, acceptType, route);
	}

	/**
	 * Map the route for HTTP OPTIONS requests
	 *
	 * @param path       the path
	 * @param acceptType the accept type
	 * @param route      The route
	 */
	public static void options(String path, String acceptType, Route route) {
		getInstance().options(path, acceptType, route);
	}


	/**
	 * Maps one or many filters to be executed before any matching routes
	 *
	 * @param filters The filters
	 */
	public static void before(Filter... filters) {
		for (Filter filter : filters) {
			getInstance().before(filter);
		}
	}

	/**
	 * Maps one or many filters to be executed after any matching routes
	 *
	 * @param filters The filters
	 */
	public static void after(Filter... filters) {
		for (Filter filter : filters) {
			getInstance().after(filter);
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
		for (Filter filter : filters) {
			getInstance().before(path, acceptType, filter);
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
		for (Filter filter : filters) {
			getInstance().after(path, acceptType, filter);
		}
	}

	/**
	 * Execute after route even if the route throws exception
	 *
	 * @param path   the path
	 * @param filter the filter
	 */
	public static void afterAfter(String path, Filter filter) {
		getInstance().afterAfter(path, filter);
	}

	/**
	 * Execute after any matching route even if the route throws exception
	 *
	 * @param filter the filter
	 */
	public static void afterAfter(Filter filter) {
		getInstance().afterAfter(filter);
	}

	//////////////////////////////////////////////////
	// END route/filter mapping with accept type
	//////////////////////////////////////////////////

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
		getInstance().get(path, route, engine);
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
		getInstance().get(path, acceptType, route, engine);
	}

	/**
	 * Map the route for HTTP POST requests
	 *
	 * @param path   the path
	 * @param route  The route
	 * @param engine the template engine
	 */
	public static void post(String path, TemplateViewRoute route, TemplateEngine engine) {
		getInstance().post(path, route, engine);
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
		getInstance().post(path, acceptType, route, engine);
	}

	/**
	 * Map the route for HTTP PUT requests
	 *
	 * @param path   the path
	 * @param route  The route
	 * @param engine the template engine
	 */
	public static void put(String path, TemplateViewRoute route, TemplateEngine engine) {
		getInstance().put(path, route, engine);
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
		getInstance().put(path, acceptType, route, engine);
	}

	/**
	 * Map the route for HTTP DELETE requests
	 *
	 * @param path   the path
	 * @param route  The route
	 * @param engine the template engine
	 */
	public static void delete(String path, TemplateViewRoute route, TemplateEngine engine) {
		getInstance().delete(path, route, engine);
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
		getInstance().delete(path, acceptType, route, engine);
	}

	/**
	 * Map the route for HTTP PATCH requests
	 *
	 * @param path   the path
	 * @param route  The route
	 * @param engine the template engine
	 */
	public static void patch(String path, TemplateViewRoute route, TemplateEngine engine) {
		getInstance().patch(path, route, engine);
	}

	/**
	 * Map the route for HTTP PATCH requests
	 *
	 * @param path       the path
	 * @param acceptType the accept type
	 * @param route      The route
	 * @param engine     the template engine
	 */
	public static void patch(String path,
		String acceptType,
		TemplateViewRoute route,
		TemplateEngine engine) {
		getInstance().patch(path, acceptType, route, engine);
	}

	/**
	 * Map the route for HTTP HEAD requests
	 *
	 * @param path   the path
	 * @param route  The route
	 * @param engine the template engine
	 */
	public static void head(String path, TemplateViewRoute route, TemplateEngine engine) {
		getInstance().head(path, route, engine);
	}

	/**
	 * Map the route for HTTP HEAD requests
	 *
	 * @param path       the path
	 * @param acceptType the accept type
	 * @param route      The route
	 * @param engine     the template engine
	 */
	public static void head(String path,
		String acceptType,
		TemplateViewRoute route,
		TemplateEngine engine) {
		getInstance().head(path, acceptType, route, engine);
	}

	/**
	 * Map the route for HTTP TRACE requests
	 *
	 * @param path   the path
	 * @param route  The route
	 * @param engine the template engine
	 */
	public static void trace(String path, TemplateViewRoute route, TemplateEngine engine) {
		getInstance().trace(path, route, engine);
	}

	/**
	 * Map the route for HTTP TRACE requests
	 *
	 * @param path       the path
	 * @param acceptType the accept type
	 * @param route      The route
	 * @param engine     the template engine
	 */
	public static void trace(String path,
		String acceptType,
		TemplateViewRoute route,
		TemplateEngine engine) {
		getInstance().trace(path, acceptType, route, engine);
	}

	/**
	 * Map the route for HTTP CONNECT requests
	 *
	 * @param path   the path
	 * @param route  The route
	 * @param engine the template engine
	 */
	public static void connect(String path, TemplateViewRoute route, TemplateEngine engine) {
		getInstance().connect(path, route, engine);
	}

	/**
	 * Map the route for HTTP CONNECT requests
	 *
	 * @param path       the path
	 * @param acceptType the accept type
	 * @param route      The route
	 * @param engine     the template engine
	 */
	public static void connect(String path,
		String acceptType,
		TemplateViewRoute route,
		TemplateEngine engine) {
		getInstance().connect(path, acceptType, route, engine);
	}

	/**
	 * Map the route for HTTP OPTIONS requests
	 *
	 * @param path   the path
	 * @param route  The route
	 * @param engine the template engine
	 */
	public static void options(String path, TemplateViewRoute route, TemplateEngine engine) {
		getInstance().options(path, route, engine);
	}

	/**
	 * Map the route for HTTP OPTIONS requests
	 *
	 * @param path       the path
	 * @param acceptType the accept type
	 * @param route      The route
	 * @param engine     the template engine
	 */
	public static void options(String path,
		String acceptType,
		TemplateViewRoute route,
		TemplateEngine engine) {
		getInstance().options(path, acceptType, route, engine);
	}

	//////////////////////////////////////////////////
	// END Template View Routes
	//////////////////////////////////////////////////

	//////////////////////////////////////////////////
	// BEGIN Response Transforming Routes
	//////////////////////////////////////////////////

	/**
	 * Map the route for HTTP GET requests
	 *
	 * @param path        the path
	 * @param route       The route
	 * @param transformer the response transformer
	 */
	public static void get(String path, Route route, ResponseTransformer transformer) {
		getInstance().get(path, route, transformer);
	}

	/**
	 * Map the route for HTTP GET requests
	 *
	 * @param path        the path
	 * @param acceptType  the accept type
	 * @param route       The route
	 * @param transformer the response transformer
	 */
	public static void get(String path, String acceptType, Route route, ResponseTransformer transformer) {
		getInstance().get(path, acceptType, route, transformer);
	}

	/**
	 * Map the route for HTTP POST requests
	 *
	 * @param path        the path
	 * @param route       The route
	 * @param transformer the response transformer
	 */
	public static void post(String path, Route route, ResponseTransformer transformer) {
		getInstance().post(path, route, transformer);
	}

	/**
	 * Map the route for HTTP POST requests
	 *
	 * @param path        the path
	 * @param acceptType  the accept type
	 * @param route       The route
	 * @param transformer the response transformer
	 */
	public static void post(String path, String acceptType, Route route, ResponseTransformer transformer) {
		getInstance().post(path, acceptType, route, transformer);
	}

	/**
	 * Map the route for HTTP PUT requests
	 *
	 * @param path        the path
	 * @param route       The route
	 * @param transformer the response transformer
	 */
	public static void put(String path, Route route, ResponseTransformer transformer) {
		getInstance().put(path, route, transformer);
	}

	/**
	 * Map the route for HTTP PUT requests
	 *
	 * @param path        the path
	 * @param acceptType  the accept type
	 * @param route       The route
	 * @param transformer the response transformer
	 */
	public static void put(String path, String acceptType, Route route, ResponseTransformer transformer) {
		getInstance().put(path, acceptType, route, transformer);
	}

	/**
	 * Map the route for HTTP DELETE requests
	 *
	 * @param path        the path
	 * @param route       The route
	 * @param transformer the response transformer
	 */
	public static void delete(String path, Route route, ResponseTransformer transformer) {
		getInstance().delete(path, route, transformer);
	}

	/**
	 * Map the route for HTTP DELETE requests
	 *
	 * @param path        the path
	 * @param acceptType  the accept type
	 * @param route       The route
	 * @param transformer the response transformer
	 */
	public static void delete(String path,
		String acceptType,
		Route route,
		ResponseTransformer transformer) {
		getInstance().delete(path, acceptType, route, transformer);
	}

	/**
	 * Map the route for HTTP HEAD requests
	 *
	 * @param path        the path
	 * @param route       The route
	 * @param transformer the response transformer
	 */
	public static void head(String path, Route route, ResponseTransformer transformer) {
		getInstance().head(path, route, transformer);
	}

	/**
	 * Map the route for HTTP HEAD requests
	 *
	 * @param path        the path
	 * @param acceptType  the accept type
	 * @param route       The route
	 * @param transformer the response transformer
	 */
	public static void head(String path, String acceptType, Route route, ResponseTransformer transformer) {
		getInstance().head(path, acceptType, route, transformer);
	}

	/**
	 * Map the route for HTTP CONNECT requests
	 *
	 * @param path        the path
	 * @param route       The route
	 * @param transformer the response transformer
	 */
	public static void connect(String path, Route route, ResponseTransformer transformer) {
		getInstance().connect(path, route, transformer);
	}

	/**
	 * Map the route for HTTP CONNECT requests
	 *
	 * @param path        the path
	 * @param acceptType  the accept type
	 * @param route       The route
	 * @param transformer the response transformer
	 */
	public static void connect(String path,
		String acceptType,
		Route route,
		ResponseTransformer transformer) {
		getInstance().connect(path, acceptType, route, transformer);
	}

	/**
	 * Map the route for HTTP TRACE requests
	 *
	 * @param path        the path
	 * @param route       The route
	 * @param transformer the response transformer
	 */
	public static void trace(String path, Route route, ResponseTransformer transformer) {
		getInstance().trace(path, route, transformer);
	}

	/**
	 * Map the route for HTTP TRACE requests
	 *
	 * @param path        the path
	 * @param acceptType  the accept type
	 * @param route       The route
	 * @param transformer the response transformer
	 */
	public static void trace(String path,
		String acceptType,
		Route route,
		ResponseTransformer transformer) {
		getInstance().trace(path, acceptType, route, transformer);
	}

	/**
	 * Map the route for HTTP OPTIONS requests
	 *
	 * @param path        the path
	 * @param route       The route
	 * @param transformer the response transformer
	 */
	public static void options(String path, Route route, ResponseTransformer transformer) {
		getInstance().options(path, route, transformer);
	}

	/**
	 * Map the route for HTTP OPTIONS requests
	 *
	 * @param path        the path
	 * @param acceptType  the accept type
	 * @param route       The route
	 * @param transformer the response transformer
	 */
	public static void options(String path,
		String acceptType,
		Route route,
		ResponseTransformer transformer) {
		getInstance().options(path, acceptType, route, transformer);
	}

	/**
	 * Map the route for HTTP PATCH requests
	 *
	 * @param path        the path
	 * @param route       The route
	 * @param transformer the response transformer
	 */
	public static void patch(String path, Route route, ResponseTransformer transformer) {
		getInstance().patch(path, route, transformer);
	}

	/**
	 * Map the route for HTTP PATCH requests
	 *
	 * @param path        the path
	 * @param acceptType  the accept type
	 * @param route       The route
	 * @param transformer the response transformer
	 */
	public static void patch(String path,
		String acceptType,
		Route route,
		ResponseTransformer transformer) {
		getInstance().patch(path, acceptType, route, transformer);
	}

	//////////////////////////////////////////////////
	// END Response Transforming Routes
	//////////////////////////////////////////////////

	//////////////////////////////////////////////////
	// EXCEPTION mapper
	//////////////////////////////////////////////////

	/**
	 * Maps an exception handler to be executed when an exception occurs during routing
	 *
	 * @param exceptionClass the exception class
	 * @param handler        The handler
	 */
	public static <T extends Exception> void exception(Class<T> exceptionClass, ExceptionHandler<? super T> handler) {
		getInstance().exception(exceptionClass, handler);
	}

	//////////////////////////////////////////////////
	// HALT methods
	//////////////////////////////////////////////////

	/**
	 * Immediately stops a request within a filter or route
	 * NOTE: When using this don't catch exceptions of type HaltException, or if catched, re-throw otherwise
	 * halt will not work
	 */
	public static HaltException halt() {
		throw getInstance().halt();
	}

	/**
	 * Immediately stops a request within a filter or route with specified status code
	 * NOTE: When using this don't catch exceptions of type HaltException, or if catched, re-throw otherwise
	 * halt will not work
	 *
	 * @param status the status code
	 */
	public static HaltException halt(int status) {
		throw getInstance().halt(status);
	}

	/**
	 * Immediately stops a request within a filter or route with specified body content
	 * NOTE: When using this don't catch exceptions of type HaltException, or if catched, re-throw otherwise
	 * halt will not work
	 *
	 * @param body The body content
	 */
	public static HaltException halt(String body) {
		throw getInstance().halt(body);
	}

	/**
	 * Immediately stops a request within a filter or route with specified status code and body content
	 * NOTE: When using this don't catch exceptions of type HaltException, or if catched, re-throw otherwise
	 * halt will not work
	 *
	 * @param status The status code
	 * @param body   The body content
	 */
	public static HaltException halt(int status, String body) {
		throw getInstance().halt(status, body);
	}


	/**
	 * Set the default response transformer. All requests not using a custom transformer will use this one
	 *
	 * @param transformer
	 */
	public static void defaultResponseTransformer(ResponseTransformer transformer) {
		getInstance().defaultResponseTransformer(transformer);
	}


	/**
	 * Overrides default exception handler during initialization phase
	 *
	 * @param initExceptionHandler The custom init exception handler
	 */
	public static void initExceptionHandler(Consumer<Exception> initExceptionHandler) {
		getInstance().initExceptionHandler(initExceptionHandler);
	}

}
