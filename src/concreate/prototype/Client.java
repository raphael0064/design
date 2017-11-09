package concreate.prototype;

/**
 * Created by marlon on 2017/11/9.
 */
public class Client {

	public static void main(String[] args) throws CloneNotSupportedException {
		ConcretePrototype prototype = new ConcretePrototype();

		for(int i=0;i<10;i++){
			ConcretePrototype clonePrototype = (ConcretePrototype) prototype.clone();
			clonePrototype.show();
		}
	}
}
