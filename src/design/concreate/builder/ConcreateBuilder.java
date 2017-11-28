package design.concreate.builder;

/**
 * Created by marlon on 2017/11/7.
 */
public class ConcreateBuilder extends  Builder{
	private Product product = new Product();
	@Override
	public void setPart(String arg1, String arg2) {
		product.setName(arg1);
		product.setType(arg2);
	}

	@Override
	public Product getProduct() {
		return product;
	}
}
