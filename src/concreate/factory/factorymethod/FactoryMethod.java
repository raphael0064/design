package concreate.factory.factorymethod;


import concreate.factory.Car;

/**
 * 工厂方法
 */
public class FactoryMethod {

	public static void main(String[] args) {
		BenzDriver benzDriver = new BenzDriver();
		Car car = benzDriver.driveCar();
		car.drive();

		BmwDriver bmwDriver = new BmwDriver();
		car = bmwDriver.driveCar();
		car.drive();
	}
}
