package com.company;

import java.io.File;
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

    private void validateAccounts(List<String> accounts) {

    }

    private boolean isChecksumValid(String account){
        return true;
    }

    private void writeAccounts(List<Account> accounts){

    }
}
