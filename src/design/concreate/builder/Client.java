package design.concreate.builder;

/**
 * Created by marlon on 2017/11/7.
 */
public class Client {

	public static void main(String[] args) {
		Director director = new Director();
		director.getAudiProduct();
		director.getAudiProduct();
	}
}
