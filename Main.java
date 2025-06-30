import java.util.ArrayList;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;

//Database of all accounts.
public class Main {
    static ArrayList<CreateAccount> accountDatabase = new ArrayList<>();
    static String bankDatabase = "Bank_Database.xlsx";
    static Scanner sc2 = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to National Bank of UP");
        initializeExcelWorkbook(accountDatabase, CreateAccount.accNumList);
        loadDataFromExcel(accountDatabase, CreateAccount.accNumList);
        boolean t = true;
        while (t) {
            System.out.println("1-Create Account\n2-LoginAccount\n3-Exit\nEnter your choice");
            switch (sc2.nextInt()) {
                case 1:
                    CreateAccount ob = new CreateAccount();
                    ob.createAccount();
                    accountDatabase.add(ob);
                    break;
                case 2:
                    LoginAccount ob1 = new LoginAccount();
                    ob1.loginAccount(accountDatabase);
                    break;
                case 3:
                    t = false;//Why we are writing here? Because we want to save data as our arraylist will be empty now after program closes
                    writeDataToExcel(accountDatabase,CreateAccount.accNumList);
                    return;//We dont need to write anywhere else in code since all task will be handled by arraylist only during runtime
                //all we want is to initalize and update excel sheet everytime
                default:
                    System.out.println("Invalid choice");

            }
        }
    }

    static void initializeExcelWorkbook(ArrayList<CreateAccount> ob1, ArrayList<Long> ob2) {
        ob1.clear();
        ob2.clear();
        File file = new File(bankDatabase);
        if (file.exists()) {
            loadDataFromExcel(ob1, ob2);
        } else {
            writeDataToExcel(ob1, ob2);
        }
    }

    static void loadDataFromExcel(ArrayList<CreateAccount> ob1, ArrayList<Long> ob2) {
        try (FileInputStream file = new FileInputStream(bankDatabase); XSSFWorkbook workbook = new XSSFWorkbook(file)) {
            XSSFSheet sheet1 = workbook.getSheet("Public Account Database");
            XSSFSheet sheet2 = workbook.getSheet("Account number list");
            for (int i = 1; i < sheet1.getLastRowNum(); i++) {//row traversal loop
                Row row = sheet1.getRow(i);
                if (row != null) {
                    CreateAccount ob = new CreateAccount();
                    ob.username=row.getCell(0).getStringCellValue();//here index i is columns number
                    ob.password=row.getCell(1).getStringCellValue();
                    ob.acc_no=(long) row.getCell(2).getNumericCellValue();
                    ob.name=row.getCell(3).getStringCellValue();
                    ob.accType=(int) row.getCell(4).getNumericCellValue();
                    ob.presentBalance=(double) row.getCell(5).getNumericCellValue();
                    ob1.add(ob);
                }

            }
            for (int i = 1; i < sheet2.getLastRowNum(); i++) {//row traversal loop
                Row row = sheet2.getRow(i);
                if (row != null) {
                    long num =(long) row.getCell(1).getNumericCellValue();
                    ob2.add(num);
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void writeDataToExcel(ArrayList<CreateAccount> ob1, ArrayList<Long> ob2) {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet1 = workbook.createSheet("Public Account Database");
            XSSFSheet sheet2 = workbook.createSheet("Account number list");
            int rowCount = 0;
            Row header1 = sheet1.createRow(rowCount++);
            for (int i=0;i<5;i++)
            {
                sheet1.setColumnWidth(i,20*256);}

            header1.createCell(0).setCellValue("Username");
            header1.createCell(1).setCellValue("Password");
            header1.createCell(2).setCellValue("Account Number");//encryption
            header1.createCell(3).setCellValue("Name");
            header1.createCell(4).setCellValue("Account Type");
            header1.createCell(5).setCellValue("Balance");//encryption
            for (CreateAccount ob : ob1) {
                Row row = sheet1.createRow(rowCount++);
                row.createCell(0).setCellValue(ob.username);
                row.createCell(1).setCellValue(ob.password);
                row.createCell(2).setCellValue(ob.acc_no);
                row.createCell(3).setCellValue(ob.name);
                row.createCell(4).setCellValue(ob.accType);
                row.createCell(5).setCellValue(ob.presentBalance);
            }
            rowCount = 0;
            sheet2.setColumnWidth(0,20*256);
            Row header2 = sheet2.createRow(rowCount);
            header2.createCell(0).setCellValue("Acc_num list");
            for (Long ob : ob2) {
                Row row = sheet2.createRow(rowCount++);
                row.createCell(1).setCellValue(ob);
            }
            try (FileOutputStream file = new FileOutputStream(bankDatabase)) {
                workbook.write(file);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
