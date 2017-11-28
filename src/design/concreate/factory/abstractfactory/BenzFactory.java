package design.concreate.factory.abstractfactory;

/**
 * Created by marlon on 2017/10/31.
 */
public class BenzFactory implements AbstractFactory{


	@Override
	public Engine createEngine() {
		return new EngineB();
	}

	@Override
	public AirCondition createAirCondition() {
		return new AirConditionB();
	}
}
