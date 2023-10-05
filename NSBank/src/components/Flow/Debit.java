package components.Flow;

import java.time.LocalDate;

public class Debit extends Flow{

	public Debit(String comment, int identifier, double amount, int targetAccountNumber, boolean effect,
			LocalDate dateOfFlow) {
		super(comment, identifier, amount, targetAccountNumber, effect, dateOfFlow);
	}
	
	@Override
	public String toString() {
		return "Debit de  compte nº "+super.getTargetAccountNumber()+" de : "+super.getAmount()+"€ effectuer le "+super.getDateOfFlow() ;
	}
}
