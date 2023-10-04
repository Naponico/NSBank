package testing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Stream;

import components.*;
import components.Account.*;

public class Main {
	//1.1.2
	private static Client[] clients;
	private static Account[] comptes;
	private static Hashtable<Integer,Account> hashtable;
	
	
	//1.1.2
	public static void main(String[] args) {
		clients=fillClients(5);
		//showClients(clients);
		
		comptes=fillAccounts(clients);
		//showAccount(comptes);
		
		comptes[8].setBalance(150);
		comptes[5].setBalance(3500);
		hashtable=fillHashtable(comptes);
		showHashTable(hashtable);
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
	
	//1.2.3
	public static Account[] fillAccounts(Client[] clients){
		Account[] returnAccount= new Account[clients.length*2];
		int currentClient=0;
		for(int i=0;i<returnAccount.length;i+=2){
			returnAccount[i]=new CurrentAccount("Current account of ",clients[currentClient]);
			returnAccount[i].setBalance(0);
			returnAccount[i+1]=new SavingsAccount("Saving account of ",clients[currentClient]);
			returnAccount[i+1].setBalance(0);
			currentClient++;
		}
		return returnAccount;
	}
	
	
	//1.2.3
	public static void showAccount(Account[] comptes){
		Stream<Account> stream=Arrays.stream(comptes);
		stream.forEach(System.out::println);
	}

	
	public static Hashtable<Integer,Account> fillHashtable(Account[] comptes){
		Hashtable<Integer,Account> returnHashTable=new Hashtable<Integer,Account>();
		for(int i =0;i<comptes.length;i++){
			returnHashTable.put(comptes[i].getAccountNumber(),comptes[i]);
		}
		return returnHashTable;
	}
	
	public static void showHashTable(Hashtable<Integer,Account> hashtable) {
		Set<Map.Entry<Integer,Account>> entries=hashtable.entrySet();
		
		Stream<Map.Entry<Integer,Account>> stream=entries.stream();
		
		stream.sorted(new Comparator<Map.Entry<Integer,Account>>(){
			@Override
			public int compare(Map.Entry<Integer,Account> o1, Map.Entry<Integer,Account> o2) {
	            return Double.compare(o1.getValue().getBalance(),(o2.getValue().getBalance()));
	        }}).forEach(System.out::println);
	}
	
}

