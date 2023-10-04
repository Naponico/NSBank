package components.Flow;

import java.util.Date;

public class Debit extends Flow{

	protected Debit(String comment, int identifier, double amount, int targetAccountNumber, boolean effect,
			Date dateOfFlow) {
		super(comment, identifier, amount, targetAccountNumber, effect, dateOfFlow);
	}

}
