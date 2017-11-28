package design.action.template;

/**
 * Created by marlon on 2017/11/9.
 */
public class Client {
	public static int[] a = {10, 32, 1, 9, 5, 7, 12, 0, 4, 3}; // 预设数据数组

	public static void main(String[] args) {
		AbstractSort s = new ConcreatSort();
		s.show(a);
	}
}
