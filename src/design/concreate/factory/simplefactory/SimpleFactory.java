package design.concreate.factory.simplefactory;

import design.concreate.factory.Benz;
import design.concreate.factory.Bmw;
import design.concreate.factory.Car;

/**
 * 简单工厂
 */
public class SimpleFactory {

	public Car drive(String name){
		if(name.equalsIgnoreCase("bmw")){
			return new Bmw();
		}else if(name.equalsIgnoreCase("benz")){
			return new Benz();
		}else {
			throw new RuntimeException("error type");
		}
	}


	public static void main(String[] args) {
		SimpleFactory simpleFactory = new SimpleFactory();
		Car bmw = simpleFactory.drive("bmw");
		bmw.drive();

		Car benz = simpleFactory.drive("benz");
		benz.drive();
	}
}
