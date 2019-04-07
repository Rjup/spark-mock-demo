package spark;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.http.matching.MatcherFilter;
import spark.route.HttpMethod;
import spark.route.Routes;
import spark.staticfiles.StaticFilesConfiguration;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class MockService extends Routable {

	private static final Logger LOG = LoggerFactory.getLogger(MockService.class);

	private Routes routes;

	private Deque<String> pathDeque = new ArrayDeque<>();

	public final Redirect redirect;

	private final StaticFilesConfiguration staticFilesConfiguration;

	private final ExceptionMapper exceptionMapper = new ExceptionMapper();

	private boolean initialized;

	// default exception handler during initialization phase
	private Consumer<Exception> initExceptionHandler = (e) -> {
		LOG.error("ignite failed", e);
		System.exit(100);
	};

	private MatcherFilter matcherFilter;

	public MockService() {
		redirect = Redirect.create(this);
//		staticFiles = new Service.StaticFiles();

		staticFilesConfiguration = StaticFilesConfiguration.create();

	}

	@Override
	public void addRoute(HttpMethod httpMethod, RouteImpl route) {
		init();
		routes.add(httpMethod, route.withPrefix(getPaths()));
	}

	@Override
	public void addFilter(HttpMethod httpMethod, FilterImpl filter) {
		init();
		routes.add(httpMethod, filter.withPrefix(getPaths()));
	}

	@Override
	@Deprecated
	public void addRoute(String httpMethod, RouteImpl route) {
		init();
		routes.add(httpMethod + " '" + getPaths() + route.getPath() + "'", route.getAcceptType(), route);
	}

	@Override
	@Deprecated
	public void addFilter(String httpMethod, FilterImpl filter) {
		init();
		routes.add(httpMethod + " '" + getPaths() + filter.getPath() + "'", filter.getAcceptType(), filter);
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
	public void path(String path, RouteGroup routeGroup) {
		pathDeque.addLast(path);
		routeGroup.addRoutes();
		pathDeque.removeLast();
	}

	public String getPaths() {
		return pathDeque.stream().collect(Collectors.joining(""));
	}

	/**
	 * Maps an exception handler to be executed when an exception occurs during routing
	 *
	 * @param exceptionClass the exception class
	 * @param handler        The handler
	 */
	public synchronized <T extends Exception> void exception(Class<T> exceptionClass, ExceptionHandler<? super T> handler) {
		// wrap
		ExceptionHandlerImpl wrapper = new ExceptionHandlerImpl<T>(exceptionClass) {

			@Override
			public void handle(T exception, Request request, Response response) {
				handler.handle(exception, request, response);
			}
		};

		exceptionMapper.map(exceptionClass, wrapper);
	}

	//////////////////////////////////////////////////
	// HALT methods
	//////////////////////////////////////////////////

	/**
	 * Immediately stops a request within a filter or route
	 * NOTE: When using this don't catch exceptions of type HaltException, or if catched, re-throw otherwise
	 * halt will not work
	 *
	 * @return HaltException object
	 */
	public HaltException halt() {
		throw new HaltException();
	}

	/**
	 * Immediately stops a request within a filter or route with specified status code
	 * NOTE: When using this don't catch exceptions of type HaltException, or if catched, re-throw otherwise
	 * halt will not work
	 *
	 * @param status the status code
	 * @return HaltException object with status code set
	 */
	public HaltException halt(int status) {
		throw new HaltException(status);
	}

	/**
	 * Immediately stops a request within a filter or route with specified body content
	 * NOTE: When using this don't catch exceptions of type HaltException, or if catched, re-throw otherwise
	 * halt will not work
	 *
	 * @param body The body content
	 * @return HaltException object with body set
	 */
	public HaltException halt(String body) {
		throw new HaltException(body);
	}

	/**
	 * Immediately stops a request within a filter or route with specified status code and body content
	 * NOTE: When using this don't catch exceptions of type HaltException, or if catched, re-throw otherwise
	 * halt will not work
	 *
	 * @param status The status code
	 * @param body   The body content
	 * @return HaltException object with status and body set
	 */
	public HaltException halt(int status, String body) {
		throw new HaltException(status, body);
	}

	/**
	 * Overrides default exception handler during initialization phase
	 *
	 * @param initExceptionHandler The custom init exception handler
	 */
	public void initExceptionHandler(Consumer<Exception> initExceptionHandler) {
		if (initialized) {
			throwBeforeRouteMappingException();
		}
		this.initExceptionHandler = initExceptionHandler;
	}

	private void throwBeforeRouteMappingException() {
		throw new IllegalStateException(
			"This must be done before route mapping has begun");
	}

	private void init() {
		if (!initialized) {
			routes = Routes.create();
			matcherFilter = new MatcherFilter(routes, staticFilesConfiguration, exceptionMapper, false, false);
			matcherFilter.init(null);
			initialized = true;
		}
	}

	public MatcherFilter getMatcherFilter() {
		return matcherFilter;
	}
}
