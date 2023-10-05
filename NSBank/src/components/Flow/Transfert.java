package components.Flow;

import java.time.LocalDate;

public class Transfert extends Flow {
	private int currentAccountNumber;

	public Transfert(String comment, int identifier, double amount, int targetAccountNumber, boolean effect,
			LocalDate dateOfFlow,int currentAccountNum) {
		super(comment, identifier, amount, targetAccountNumber, effect, dateOfFlow);
		this.currentAccountNumber=currentAccountNum;
	}
	
	@Override
	public String toString() {
		return "Transfert de  compte nº "+super.getTargetAccountNumber()+" vers compte nº "+this.currentAccountNumber+" d'un montant de : "+super.getAmount()+"€ effectuer le "+super.getDateOfFlow();
	}

	public int getCurrentAccountNumber() {
		return currentAccountNumber;
	}

	public void setCurrentAccountNumber(int currentAccountNumber) {
		this.currentAccountNumber = currentAccountNumber;
	}
	
	
}
