package concreate.factory.factorymethod;


import concreate.factory.Benz;
import concreate.factory.Car;

/**
 * Created by marlon on 2017/10/31.
 */
public class BenzDriver implements Driver {
	@Override
	public Car driveCar() {
		return new Benz();
	}
}
