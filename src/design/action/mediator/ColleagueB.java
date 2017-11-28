package design.action.mediator;

/**
 * Created by marlon on 2017/11/9.
 */
public class ColleagueB extends AbstractColleague{

	public void setNumber(int number, AbstractMediator am) {
		this.number = number;
		am.BaffectA();
	}
}
