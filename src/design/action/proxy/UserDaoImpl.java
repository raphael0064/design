package design.action.proxy;

/**
 * 实现类
 */
public class UserDaoImpl implements IUserDao{

	@Override
	public void save() {
		System.out.println("hello user");
	}
}
