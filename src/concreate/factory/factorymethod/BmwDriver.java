package concreate.factory.factorymethod;


import concreate.factory.Bmw;
import concreate.factory.Car;

/**
 * Created by marlon on 2017/10/31.
 */
public class BmwDriver implements Driver{
	@Override
	public Car driveCar() {
		return new Bmw();
	}
}
