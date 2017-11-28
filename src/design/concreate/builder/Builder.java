package design.concreate.builder;

/**
 * Created by marlon on 2017/11/7.
 */
public abstract class Builder {
	public abstract void setPart(String arg1, String arg2);
	public abstract Product getProduct();
}
