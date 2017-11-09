package concreate.builder;

/**
 * Created by marlon on 2017/11/7.
 */
public class Director {
	Builder builder = new ConcreateBuilder();

	public Product getBmwProduct(){
		builder.setPart("宝马汽车","X7");
		return builder.getProduct();
	}
	public Product getAudiProduct(){
		builder.setPart("奥迪汽车","Q5");
		return builder.getProduct();
	}
}
