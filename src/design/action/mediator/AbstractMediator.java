package design.action.mediator;

/**
 * 中介者
 */
public abstract class AbstractMediator {
	protected AbstractColleague A;
	protected AbstractColleague B;

	public AbstractMediator(AbstractColleague a, AbstractColleague b) {
		A = a;
		B = b;
	}

	protected AbstractMediator() {
	}

	public abstract void AaffectB();

	public abstract void BaffectA();

}
