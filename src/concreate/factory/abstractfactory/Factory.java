package concreate.factory.abstractfactory;

/**
 * Created by marlon on 2017/10/31.
 */
public class Factory {
	public static void main(String[] args) {
		BenzFactory benzFactory = new BenzFactory();
		AirCondition benzAirCondition = benzFactory.createAirCondition();
		Engine benzEngine = benzFactory.createEngine();
		benzAirCondition.work();
		benzEngine.work();

		BmwFactory bmwFactory = new BmwFactory();
		AirCondition bmwAirCondition = bmwFactory.createAirCondition();
		Engine bmwEngine = bmwFactory.createEngine();
		bmwAirCondition.work();
		bmwEngine.work();



	}
}
