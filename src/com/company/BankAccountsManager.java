package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class BankAccountsManager {

    private BankAccountsFileReader bankAccountsFileReader;

    public BankAccountsManager(File accountsFile) throws Exception{
        bankAccountsFileReader=new BankAccountsFileReader(accountsFile);
    }

    public void startValidation() throws Exception {
        List<String> accounts=bankAccountsFileReader.getAccounts();
        validateAccounts(accounts);
    }

    private void validateAccounts(List<String> accounts) throws IOException {
    	List<Account> accountsResults = new ArrayList<Account>();
        
        for (String i : accounts) {
        	String status = "ERR";
        	int valid = isChecksumValid(i);
        	if (valid == 0) {
        		status = "OK";
        	}else if(valid == 2) {
        		status = "ILL";
        	}
        	Account res = new Account(i, status);
        	accountsResults.add(res);
        }
        writeAccounts(accountsResults);
    }

    private int isChecksumValid(String account){
    	String longNumber = "\\d{9}";
		int result = 0;
		
		if (account != null && account.matches(longNumber)) {

			int total = 0;
			int i = 1;
			for (char j : new StringBuilder(account).reverse().toString().toCharArray()) {
				total += (j - '0') * i++;
			}
			total = total % 11;
			if (total != 0) {
				result = 1;
			}

		}else {
			result = 2;
		}
		return result;
    }

    private void writeAccounts(List<Account> accounts) throws IOException{
	    try{
	        File file = new File("./src/com/company/BankAccountsResults.txt");
	        PrintWriter out = new PrintWriter(new FileWriter(file));

	        for (Account status : accounts) {
	            out.println(status);
		        System.out.println(status); 
	        }
	        out.close();
        }catch (Exception ex){
            ex.printStackTrace();
            
        }
    }

}
