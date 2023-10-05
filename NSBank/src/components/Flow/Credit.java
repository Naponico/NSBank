package components.Flow;

import java.time.LocalDate;

public class Credit extends Flow {

	public Credit(String comment, int identifier, double amount, int targetAccountNumber, boolean effect,
			LocalDate dateOfFlow) {
		super(comment, identifier, amount, targetAccountNumber, effect, dateOfFlow);
	}

	@Override
	public String toString() {
		return "Credit a compte nº "+super.getTargetAccountNumber()+" de : "+super.getAmount()+"€ effectuer le "+super.getDateOfFlow();
	}
}
