package design.action.proxy.dynamicproxy;

import design.action.proxy.IUserDao;
import design.action.proxy.UserDao;
import design.action.proxy.UserDaoImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk代理-实现
 */
public class ProxyFactory2 implements InvocationHandler{
	private Object target;

	public ProxyFactory2(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("jdk代理-实现InvocationHandler");
		return method.invoke(target,args);
	}

	public static void main(String[] args) {
		UserDaoImpl dao = new UserDaoImpl();
		System.out.println(dao.getClass());
		dao.save();

		ProxyFactory2 proxy = new ProxyFactory2(dao);
		IUserDao proxyDao  = (IUserDao) Proxy.newProxyInstance(dao.getClass().getClassLoader(),dao.getClass().getInterfaces(),proxy);
		System.out.println(proxyDao.getClass());
		proxyDao.save();
	}
}
