package com.company;

import java.io.File;

public class Main {
	
    public static void main(String[] args) {
	    try{
            BankAccountsManager bankAccountsManager = new BankAccountsManager(new File("./src/com/company/BankAccounts.txt"));
            bankAccountsManager.startValidation();
            
        }catch (Exception ex){
            ex.printStackTrace();
            
        }
	    //
    }
}
