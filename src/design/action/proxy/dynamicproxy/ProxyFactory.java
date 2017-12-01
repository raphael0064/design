package design.action.proxy.dynamicproxy;

import design.action.proxy.IUserDao;
import design.action.proxy.UserDaoImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理类
 * 动态代理不需要实现接口,但是需要指定接口类型
 */
public class ProxyFactory {

	//维护一个目标对象
	private Object target;

	public ProxyFactory(Object target) {
		this.target = target;
	}

	//给目标对象生成代理对象
	public Object getProxyInstance() {
		return Proxy.newProxyInstance(
				target.getClass().getClassLoader(),
				target.getClass().getInterfaces(),
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						System.out.println("开始事务2");
						//执行目标对象方法
						Object returnValue = method.invoke(target, args);
						System.out.println("提交事务2");
						return returnValue;
					}
				}
		);
	}

	public static void main(String[] args) {
		// 目标对象
		UserDaoImpl target = new UserDaoImpl();
		// 原始的类型
		System.out.println(target.getClass());

		// 给目标对象，创建代理对象
		IUserDao proxy = (IUserDao) new ProxyFactory(target).getProxyInstance();
		// class $Proxy0   内存中动态生成的代理对象
		System.out.println(proxy.getClass());

		// 执行方法 代理对象
		proxy.save();
	}

	public Object getInstance() {
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				return method.invoke(proxy, args);
			}
		});
	}

}
