package singleton;

/**
 * Created by marlon on 2017/11/6.
 */
public class Singleton {

	private Singleton single = null;

	private Singleton(){}

	/**
	 * 基础懒汉
	 * @return
	 */
	public Singleton getInstance(){
		if(null == single){
			single = new Singleton();
		}

		return single;
	}

	/**
	 * 同步
	 * @return
	 */
	public synchronized Singleton getInstance2(){
		if(null == single){
			single = new Singleton();
		}

		return single;
	}

	/**
	 * 双检锁
	 * @return
	 */
	public Singleton getInstance3() {
		if (null == single) {
			synchronized (single) {
				if (null == single) {
					single = new Singleton();
				}
			}
		}
		return single;
	}


	/**
	 * 静态内部类
	 */
	private static class LazyHolder {
		private static final Singleton INSTANCE = new Singleton();
	}
	public static final Singleton getInstance4() {
		return LazyHolder.INSTANCE;
	}

	/**
	 * 饿汉
	 */
//	private static final Singleton single = new Singleton();
//	//静态工厂方法
//	public static Singleton getInstance5() {
//		return single;
//	}


}
