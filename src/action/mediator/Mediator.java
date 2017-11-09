package action.mediator;

/**
 * Created by marlon on 2017/11/9.
 */
public class Mediator extends AbstractMediator{

	public Mediator(AbstractColleague a, AbstractColleague b) {
		this.A = a;
		this.B = b;
	}

	@Override
	public void AaffectB() {
		int number = A.getNumber();
		B.setNumber(number*100);
	}

	@Override
	public void BaffectA() {
		int number = B.getNumber();
		A.setNumber(number/100);
	}
}
