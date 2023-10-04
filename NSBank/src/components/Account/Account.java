package components.Account;

import components.Client;

public abstract class Account {
	//1.2.1
	protected String label;
	protected  double balance;
	protected static int  numberOfAccount;
	protected int accountNumber;
	protected Client client;
	
	//1.2.1
	protected Account(String label,Client client){
		this.label=label;
		this.client=client;
		this.accountNumber=numberOfAccount;
		numberOfAccount++;
	}
	
	//1.2.1
	public String getLabel() {
		return label;
	}
	//1.2.1
	public void setLabel(String label) {
		this.label = label;
	}
	//1.2.1
	public double getBalance() {
		return balance;
	}
	//1.2.1
	public void setBalance(double amount) {
		this.balance = amount;
	}
	//1.2.1
	public int getAccountNumber() {
		return accountNumber;
	}
	//1.2.1
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	//1.2.1
	public Client getClient() {
		return client;
	}
	//1.2.1
	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public String toString(){
		return "{label : "+this.label+", balance : "+this.balance+", account Number : "+this.accountNumber+", Client : "+this.client+" }";
	}
	
}
