package components.Flow;

import java.time.LocalDate;

public abstract class Flow {
	private String comment;
	private int identifier;
	private double amount;
	private int targetAccountNumber;
	private boolean effect;
	private LocalDate dateOfFlow;
	

	protected Flow(String comment, int identifier, double amount, int targetAccountNumber, boolean effect,
			LocalDate dateOfFlow) {
		this.comment = comment;
		this.identifier = identifier;
		this.amount = amount;
		this.targetAccountNumber = targetAccountNumber;
		this.effect = effect;
		this.dateOfFlow = dateOfFlow;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getIdentifier() {
		return identifier;
	}


	public void setIdentifier(int identifier) {
		this.identifier = identifier;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getTargetAccountNumber() {
		return targetAccountNumber;
	}

	public void setTargetAccountNumber(int targetAccountNumber) {
		this.targetAccountNumber = targetAccountNumber;
	}

	public boolean isEffect() {
		return effect;
	}

	public void setEffect(boolean effect) {
		this.effect = effect;
	}

	public LocalDate getDateOfFlow() {
		return dateOfFlow;
	}

	public void setDateOfFlow(LocalDate dateOfFlow) {
		this.dateOfFlow = dateOfFlow;
	}
	
}
