package testing;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.time.format.DateTimeFormatter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import components.*;
import components.Account.*;
import components.Flow.*;

public class Main {
	//1.1.2
	private static Client[] clients;
	private static Account[] comptes;
	private static Hashtable<Integer,Account> hashtable;
	private static Flow[] flows;

	public static void main(String[] args) {
		//1.1
//		clients=fillClients(5);
//		showClients(clients);
//		
		//1.2.
//		comptes=fillAccounts(clients);
//		showAccount(comptes);
//		
		//1.3.1
//		hashtable=fillHashtable(comptes);
//		showHashTable(hashtable);
//		
		//1.3.4
//		flows=fillFlowTable();
//		showFlowTable(flows);
//		updateAccountFromFlow(flows,hashtable);
//		
		//2.1
//		flows=createFlowsArrayFromJson();
//		showFlowTable(flows);
		
		//2.2
//		comptes=loadAndReadXMLToAccountArray();
//		hashtable=fillHashtable(comptes);
//		showHashTable(hashtable);
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
	
	//1.2.3
	public static Account[] fillAccounts(Client[] clients){
		Account[] returnAccount= new Account[clients.length*2];
		int currentClient=0;
		for(int i=0;i<returnAccount.length;i+=2){
			returnAccount[i]=new CurrentAccount("Current account of ",clients[currentClient]);
			returnAccount[i+1]=new SavingsAccount("Saving account of ",clients[currentClient]);
			currentClient++;
		}
		return returnAccount;
	}
	
	
	//1.2.3
	public static void showAccount(Account[] comptes){
		Stream<Account> stream=Arrays.stream(comptes);
		stream.forEach(System.out::println);
	}

	//1.3.1
	public static Hashtable<Integer,Account> fillHashtable(Account[] comptes){
		Hashtable<Integer,Account> returnHashTable=new Hashtable<Integer,Account>();
		for(int i =0;i<comptes.length;i++){
			returnHashTable.put(comptes[i].getAccountNumber(),comptes[i]);
		}
		return returnHashTable;
	}
	//1.3.1
	public static void showHashTable(Hashtable<Integer,Account> hashtable) {
		Set<Map.Entry<Integer,Account>> entries=hashtable.entrySet();
		
		Stream<Map.Entry<Integer,Account>> stream=entries.stream();
		
		stream.sorted(new Comparator<Map.Entry<Integer,Account>>(){
			@Override
			public int compare(Map.Entry<Integer,Account> o1, Map.Entry<Integer,Account> o2) {
	            return Double.compare(o1.getValue().getBalance(),(o2.getValue().getBalance()));
	        }}).forEach(System.out::println);
	}
	
	//1.3.4
	public static Flow[] fillFlowTable(){
		LocalDate today= LocalDate.now();
		Flow[] returnFlow=new Flow[2+comptes.length];
		returnFlow[0]=new Debit("Debit du premier comptes", 0, 50, comptes[0].getAccountNumber(), false, today.plusDays(2));
		int currentFlow=1;
		for(int i=0;i<comptes.length;i++) {
			if(comptes[i] instanceof CurrentAccount) {
				returnFlow[currentFlow]=new Credit("Credit au compte courant numero "+i,currentFlow,100.50,comptes[i].getAccountNumber(),false,today.plusDays(2));
			}
			else if( comptes[i] instanceof SavingsAccount) {
				returnFlow[currentFlow]=new Credit("Credit au compte Epargne numero "+i,currentFlow,1500,comptes[i].getAccountNumber(),false,today.plusDays(2));
			}
			else {
				returnFlow[currentFlow]=null;
			}
			currentFlow++;
		}
		returnFlow[currentFlow]=new Transfert("Transfert du compte n1 au compte n2", currentFlow, 50, comptes[0].getAccountNumber(), false, today.plusDays(2), comptes[1].getAccountNumber());
		return returnFlow;
	}
	//1.3.4
	public static void showFlowTable(Flow[] flows){
		Stream<Flow> stream=Arrays.stream(flows);
		stream.forEach(System.out::println);
	}
	
	//1.3.5
	public static void updateAccountFromFlow(Flow[] flows,Hashtable hashtable){
		for(int i=0;i<flows.length;i++){
			Account compte =(Account) hashtable.get(flows[i].getTargetAccountNumber());
			compte.setBalance(flows[i]);
				
		}	
		for(int i=0;i<hashtable.size();i++){
			Account compte =(Account) hashtable.get(i);
			Predicate<Account> inferiorofzero= u->(u.getBalance()<0);
			Optional<Boolean> opt=Optional.of(inferiorofzero.test(compte));	
			if(opt.get()) {
				Stream stream = opt.stream();
				stream.forEach(u->System.out.println(" un compte est <0 = "+compte));
			}
		}
		showHashTable(hashtable);
	}
	
	//2.1
	public static Flow[] createFlowsArrayFromJson() {
			List<String> listOfJsonObject=loadAndReturnListOfJsonArray();
			Flow[] flows=new Flow[listOfJsonObject.size()];
			for(int u=0;u<listOfJsonObject.size();u++) {
				// on parcours le String et on en tire des clefs
				Pattern keyPattern=Pattern.compile("([A-Za-z]+)\":");
				Matcher keyMatcher=keyPattern.matcher(listOfJsonObject.get(u));			
				List<String> keyList=new ArrayList();
				while(keyMatcher.find()){
					keyList.add(keyMatcher.group(1));
				}
				
				// on parcours le String et on en tire des valeurs
				Pattern valuePattern=Pattern.compile(":\\\"([a-zA-Z0-9 /]+)");
				Matcher valueMatcher = valuePattern.matcher(listOfJsonObject.get(u));
				List<String> valueList=new ArrayList();
				while(valueMatcher.find()) {
					valueList.add(valueMatcher.group(1));
				}
				
				//on cree une HashTable que l'on remplie avec les données des deux Liste
				Hashtable<String,String> hash=new Hashtable<String,String>();
				for(int i =0;i<keyList.size();i++){
					hash.put(keyList.get(i), valueList.get(i));
				}
				flows[u]=flowFromJson(hash);
			}
			return flows;
	}
	
	//2.1
	public static Flow flowFromJson(Hashtable<String,String> hash){
		Flow returnFlow=null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		switch(hash.get("type")){
			case "Debit":{
				LocalDate localDate = LocalDate.parse(hash.get("date"),formatter);
				returnFlow= new Debit(hash.get("comment"), Integer.parseInt(hash.get("identifier")), Double.parseDouble(hash.get("amount")), Integer.parseInt(hash.get("target")), false,localDate.plusDays(2));
				break;
			}
			
			case "Credit":{
				LocalDate localDate = LocalDate.parse(hash.get("date"),formatter);
				returnFlow= new Credit(hash.get("comment"), Integer.parseInt(hash.get("identifier")), Double.parseDouble(hash.get("amount")), Integer.parseInt(hash.get("target")), false,localDate.plusDays(2));
				break;
			}
			
			case "Transfert":{
				LocalDate localDate = LocalDate.parse(hash.get("date"),formatter);
				returnFlow= new Transfert(hash.get("comment"), Integer.parseInt(hash.get("identifier")), Double.parseDouble(hash.get("amount")), Integer.parseInt(hash.get("target")), false,localDate.plusDays(2),Integer.parseInt(hash.get("from")));
			}
		}
		return returnFlow;
	}
	
	//2.1
	public static List<String> loadAndReturnListOfJsonArray(){
		Path path=FileSystems.getDefault().getPath("src","testing","ressources","flow.json");
		try {
			String JSONContent = Files.readString(path);
			Pattern patt=Pattern.compile("\\{([\\s\\\"a-zA-Z:,0-9/]+)}");
			Matcher mat=patt.matcher(JSONContent);
			
			List<String> list=new ArrayList<String>();
			while(mat.find()) {
				list.add(mat.group());
			}
			
			return list;
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		return new ArrayList<String>();
	}
	//2.2
	public static Account[] loadAndReadXMLToAccountArray(){
		Path path=FileSystems.getDefault().getPath("src","testing","ressources","account.xml");
		Account[] accounts=new Account[0];
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			try {
				Document doc = builder.parse(path.toString());
				doc.getDocumentElement().normalize();
				NodeList nodes=doc.getElementsByTagName("account");
				
				Predicate<Node> predi = u->(u.getNodeName().contains("#"));
				accounts=new Account[nodes.getLength()];
				Hashtable<String,String> hash = new Hashtable<String,String>();
				
				int current=0;
				for(int u=0;u<nodes.getLength();u++) {
					for(int i=0;i<nodes.item(u).getChildNodes().getLength();i++) {
						Node CurrentNode = nodes.item(u).getChildNodes().item(i);
						if(!predi.test(CurrentNode)) {
							hash.put(CurrentNode.getNodeName()+current,CurrentNode.getTextContent());
						}
						
					}
					current++;
				}
				for(int i=0;i<current;i++){
					switch(hash.get("type"+i)) {
					case "Saving":
						accounts[i] = new SavingsAccount(hash.get("label"+i),new Client("name"+i,"firstname"+i));
						break;
						
					case "Current":
						accounts[i] = new CurrentAccount(hash.get("label"+i),new Client("name"+i,"firstname"+i));
						break;
					}
				}
				return accounts;
				
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return accounts;
	}
}




