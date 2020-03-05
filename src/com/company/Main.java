package com.company;

import java.io.File;

public class Main {

    public static void main(String[] args) {
	    try{
	        BankAccountsFileReader reader=new BankAccountsFileReader(new File("/Users/fernando/Downloads/OCR/src/com/company/BankAccounts.txt"));
            reader.getAccounts();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
