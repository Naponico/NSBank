package testing;

import java.util.Arrays;
import java.util.stream.Stream;

import components.*;

public class Main {
	//1.1.2
	private static Client[] clients;
	
	//1.1.2
	public static void main(String[] args) {
		clients=fillClients(5);
		showClients(clients);
	}

	//1.1.2
	public static Client[] fillClients(int numberOfClient) {
		Client[] returnClient = new Client[numberOfClient];
		for(int i =0;i<numberOfClient;i++) {
			returnClient[i]= new Client("name"+(i+1),"firstname"+(i+1));
		}
		return returnClient;
	}
	
	//1.1.2
	public static void showClients(Client[] clients){
		Stream<Client> stream=Arrays.stream(clients);
		stream.forEach(System.out::println);
	}
	
	//1.1.2
	public Client[] getClients() {
		return clients;
	}

}
