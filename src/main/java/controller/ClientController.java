package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import repository.DataManager;
import model.*;

public class ClientController {
	private DataManager _dataManager;
    
    public ClientController(){
        _dataManager = new DataManager();
    }
    
    private String ValidateClient(String name, String address, String id){
        if (id == "" || !id.matches("^[0-9]*$")) {
            return "Invalid id format.";
        }
        if  (name.length() >= 256) {
            return "Name can not be longer than 256";
        }

        if(!name.equals("") && !address.equals("") && !name.equals(" ")){
            for(int i=0;i<name.length();i++){
                if((!Character.isUpperCase(name.charAt(i))) && (!Character.isLowerCase(name.charAt(i))) && (!Character.isSpaceChar(name.charAt(i)))){
                    return "Invalid character: " + name.charAt(i);
                }
            }
            return null;
        }else{
            return "Name or address cannot be empty!";
        }
    }
    
    public String AddClient(String name, String address, String id){
        //validation
        String valid;
        if((valid = ValidateClient(name, address,id)) != null){
            return valid;
        }
        Client c = new Client(name, address,id);
        //uniqueness
        for(int j=0; j<_dataManager.Clients.size(); j++){
            if(_dataManager.Clients.get(j).equals(c)){
                return "Client already exists!";
            }
        }
        try{
            _dataManager.Clients.add(c);
            _dataManager.SaveChanges();
            return null;
        }catch(Exception ex){
            return ex.getMessage();
        }
    }
    
    public String AddClientIndex(Client c, int year, int month, float toPay) {
        if (year < 2015 || year > 2018) {
            return "Invalid year";
        }

        if (month < 0 || month > 12) {
            return "Invalid month format";
        }

        if (toPay < 0) {
            return "Money to pay can't be less than 0";
        }

        String valid = ValidateClient(c.Name, c.Address, c.idClient);

        if (valid != null) {
            return valid;
        }

        Optional<Client> clientOptional = _dataManager.getClientsList().stream()
                            .filter(client1 -> client1.equals(c))
                            .findFirst();

        if (!clientOptional.isPresent()) {
            return "Client does not exists!";
        }

        Client client = clientOptional.get();
        Issue issue = new Issue(client, year, month, toPay, 0);

        _dataManager.getInvoicesList().add(issue);
        _dataManager.SaveChanges();

        return null;
    }
    
    public String ListIssue(Client client){
//        String s = "";
//        String pen = "";
//        Double total = 0.0;
//        Issue last = null, beforeLast;
//        for(int i=0; i<_dataManager.Issues.size(); i++){
//        	if(_dataManager.Issues.get(i).Client.equals(c)){
//            	 pen += String.format("Year: %d, Month: %d, Penalty: %2.0f\n", i);
//            	 s += pen;
//        	}
//        }
//        return s;

        String output = "";
        List<Issue> issues = _dataManager.getInvoicesList().stream()
                                .filter(issue -> issue.Client.equals(client))
                                .collect(Collectors.toList());

        for (Issue issue : issues) {
            output += String.format("Year: %d, Month: %d, ToPay: %2.0f, Paid: %2.0f", issue.Year, issue.Month, issue.ToPay, issue.Paid) + "\n";
        }

        return output.length() > 0 ? output : "No issues available";
    }
    
}
