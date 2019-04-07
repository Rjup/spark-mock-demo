package sample.bikes;

import com.google.common.eventbus.AsyncEventBus;
import com.google.inject.AbstractModule;

import java.util.concurrent.Executors;

public class BikeModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(BikeService.class);
		AsyncEventBus eventBus = new AsyncEventBus(Executors.newFixedThreadPool(5));
		bind(AsyncEventBus.class).toInstance(eventBus);
	}
}
