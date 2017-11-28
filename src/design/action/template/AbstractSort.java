package design.action.template;

/**
 * Created by marlon on 2017/11/9.
 */
public abstract class AbstractSort {

	protected abstract void sort(int[] array);

	protected final void show(int[] array) {
		this.sort(array);
		for (int i : array) {
			System.out.println(i);
		}
	}
}
