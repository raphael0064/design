package action.mediator;

/**
 * Created by marlon on 2017/11/9.
 */
public abstract class AbstractColleague {
	protected int number;

	public int getNumber() {
		return number;
	}

	public void setNumber(int num) {
		this.number = num;
	}

	public abstract void setNumber(int number, AbstractMediator am);
}
