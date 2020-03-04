package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class BankAccountsFileReader {

    private static final int DIGIT_OCR_LENGHT = 3;
    private static final int DIGITS_COUNT_IN_LINE = 9;
    private File fileToRead;
    private Hashtable<String, String> OCRDigitsMapping;

    private static final String ILLEGIBLE_OCR_DIGIT = "?";

    private final String ZERO= " _ "+
                              "| |"+
                              "|_|";

    private final String ONE= "   "+
                              "  |"+
                              "  |";

    private final String TWO= " _ "+
                              " _|"+
                              "|_ ";

    private final String THREE=" _ "+
                               " _|"+
                               " _|";

    private final String FOUR="   " +
                              "|_|" +
                              "  |";

    private final String FIVE=" _ " +
                              "|_ " +
                              " _|";

    private final String SIX=" _ "+
                             "|_ "+
                             "|_|";
    private final String SEVEN=" _ "+
                               "  |"+
                               "  |";
    private final String EIGHT=" _ "+
                               "|_|"+
                               "|_|";
    private final String NINE=" _ "+
                              "|_|"+
                              " _|";

    public BankAccountsFileReader(File fileToRead) throws FileNotFoundException {
        this.fileToRead=fileToRead;
        prepareMappingTable();
    }

    private void prepareMappingTable(){
        this.OCRDigitsMapping=new Hashtable<>();
        this.OCRDigitsMapping.put(ZERO,"0");
        this.OCRDigitsMapping.put(ONE,"1");
        this.OCRDigitsMapping.put(TWO,"2");
        this.OCRDigitsMapping.put(THREE,"3");
        this.OCRDigitsMapping.put(FOUR,"4");
        this.OCRDigitsMapping.put(FIVE,"5");
        this.OCRDigitsMapping.put(SIX,"6");
        this.OCRDigitsMapping.put(SEVEN,"7");
        this.OCRDigitsMapping.put(EIGHT,"8");
        this.OCRDigitsMapping.put(NINE,"9");
    }

    public List<String> getAccounts() throws Exception{
        List<String> fileLines=Files.readAllLines(fileToRead.toPath(), StandardCharsets.UTF_8);
        List<String> digitLines=new ArrayList<>();
        List<String> accounts=new ArrayList<>();
        for(int lineIndex=0;lineIndex<=fileLines.size();lineIndex++){
            if(lineIndex !=0 && lineIndex%4==0){
                List<String>digitsFromLine=getAccountsDigitsFromLines(digitLines);
                String bankAccountNumber=getAccountNumberFromOCR(digitsFromLine);
                System.out.println(bankAccountNumber);
                digitLines.clear();
                if(lineIndex<fileLines.size())
                digitLines.add(fileLines.get(lineIndex));
            }else{
                digitLines.add(fileLines.get(lineIndex));
            }
        }

        return accounts;
    }

    private String getAccountNumberFromOCR(List<String> digitsFromLine) {
        StringBuilder accountBuilder=new StringBuilder();
        for(String digit:digitsFromLine){
            accountBuilder.append(parseDigit(digit));
        }
        return accountBuilder.toString();
    }

    private String parseDigit(String digitOCR){
        return OCRDigitsMapping.getOrDefault(digitOCR,ILLEGIBLE_OCR_DIGIT);
    }

    public List<String> getAccountsDigitsFromLines(List<String> digitLines) throws Exception {
        List<String> digits=new ArrayList<>();
        for(int digitIndex=0;digitIndex<DIGITS_COUNT_IN_LINE;digitIndex++){
            StringBuilder stringBuilder=new StringBuilder();
            for(int i=0;i<DIGIT_OCR_LENGHT;i++){
                String line=digitLines.get(i);
                stringBuilder.append(line, digitIndex*DIGIT_OCR_LENGHT, digitIndex*DIGIT_OCR_LENGHT+DIGIT_OCR_LENGHT);
            }
            digits.add(stringBuilder.toString());
        }
        return digits;
    }

}
