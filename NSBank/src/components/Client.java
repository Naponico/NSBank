package components;

public class Client {
	//1.1.1
	private String name;
	private String firstName;
	private int clientNumber;
	private static int numberOfClient=1;
	
	public Client(String name,String firstname){
		this.clientNumber=numberOfClient;
		numberOfClient++;
		this.setName(name);
		this.setFirstName(firstname);
	}
	
	
	@Override
	public String toString(){
		return "{Name : "+this.name +",first name : "+this.firstName +",client number : "+getClientNumber()+"}";
	}
	
	//Getters and Mutator
	
	//1.1.1
	public String getFirstName() {
		return firstName;
	}
	//1.1.1
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	//1.1.1
	public String getName() {
		return name;
	}
	//1.1.1
	public void setName(String name) {
		this.name = name;
	}
	
	//1.1.1
	public  int getClientNumber() {
		return this.clientNumber;
	}
	//1.1.1
	public  void setClientNumber(int clientNumber) {
		this.clientNumber = clientNumber;
	}
	
}
