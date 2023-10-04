package components.Flow;

import java.util.Date;

public class Transfert extends Flow {
	private int currentAccountNumber;

	public Transfert(String comment, int identifier, double amount, int targetAccountNumber, boolean effect,
			Date dateOfFlow,int currentAccountNum) {
		super(comment, identifier, amount, targetAccountNumber, effect, dateOfFlow);
		this.currentAccountNumber=currentAccountNum;
	}
	

	
	
}
