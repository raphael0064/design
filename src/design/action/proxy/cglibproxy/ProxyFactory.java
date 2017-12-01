package design.action.proxy.cglibproxy;

import design.action.proxy.UserDao;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib动态代理
 */
public class ProxyFactory implements MethodInterceptor {

	private Object target;

	public ProxyFactory(Object target) {
		this.target = target;
	}

	public Object getProxyInstance(){
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(this.target.getClass());
		// 回调方法
		enhancer.setCallback(this);
		// 创建代理对象
		return enhancer.create();
	}

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		System.out.println("开始事务...");

		//执行目标对象的方法
		Object returnValue = method.invoke(target, args);

		System.out.println("提交事务...");

		return returnValue;
	}

	public Object getInstance(){
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(this.target.getClass());
		enhancer.setCallback(this);
		return enhancer.create();
	}

	public static void main(String[] args) {
		UserDao userDao = new UserDao();
		System.out.println(userDao.getClass());

		ProxyFactory proxyFactory = new ProxyFactory(userDao);
		UserDao dao = (UserDao) proxyFactory.getProxyInstance();
		System.out.println(dao.getClass());
		dao.save();
	}
}
