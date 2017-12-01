package design.action.proxy.staticproxy;

import design.action.proxy.UserDaoImpl;

/**
 * Created by marlon on 2017/11/29.
 */
public class UserDaoProxy {
	private UserDaoImpl dao;

	public UserDaoProxy(UserDaoImpl dao) {
		this.dao = dao;

	}

	public void save() {
		System.out.println("begin proxy");
		dao.save();
		System.out.println("end proxy");
	}

	public static void main(String[] args) {
		UserDaoImpl dao = new UserDaoImpl();
		UserDaoProxy proxy = new UserDaoProxy(dao);
		proxy.save();
	}
}
