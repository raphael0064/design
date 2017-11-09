package singleton;

/**
 * static执行顺序
 */
public class Outer {

	private Outer(){
		System.out.println("constructor mathod...");
	}

	static {
		System.out.println("load outer class...");
	}

	public static void getIns(){
		System.out.println("static outer class...");
	}

	//静态内部类
	static class StaticInner {
		static {
			System.out.println("load static inner class...");
		}

		static void staticInnerMethod() {
			System.out.println("static inner method...");
		}
	}

	/**
	 * 静态代码块>构造方法>内部类静态代码块>内部类构造方法
	 */
	public static void main(String[] args) {
		Outer outer = new Outer();      //此刻其内部类是否也会被加载？
		System.out.println("===========分割线===========");
		Outer.StaticInner.staticInnerMethod();      //调用内部类的静态方法
	}

}
