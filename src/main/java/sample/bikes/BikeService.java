package sample.bikes;

import java.util.ArrayList;
import java.util.List;

public class BikeService {

	static List<Bike> dataStore = new ArrayList<>();

	public List<Bike> findAll() {
		return new ArrayList<>(dataStore);
	}

	public BikeView create(BikeView view) {
		Bike bike = view.toBike();
		dataStore.add(bike);
		BikeView result = BikeView.of(bike);
		return result;
	}
}
