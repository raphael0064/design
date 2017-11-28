package design.concreate.factory.abstractfactory;

/**
 * Created by marlon on 2017/10/31.
 */
public class BmwFactory implements AbstractFactory{
	@Override
	public Engine createEngine() {
		return new EngineA();
	}

	@Override
	public AirCondition createAirCondition() {
		return new AirConditionA();
	}
}
