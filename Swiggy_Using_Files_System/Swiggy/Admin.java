package Swiggy;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

class Admin {
    static class myThread implements Runnable{

        @Override
        public void run() {
            try {
                remFileReader();
            }
            catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
    static Scanner sc=new Scanner(System.in);
    static ArrayList<String> rem=new ArrayList<>();
    private static void remFileReader() throws FileNotFoundException {
        FileReader fr=new FileReader(Main.remOrdPath);
        BufferedReader br=new BufferedReader(fr);
        try {
            br.readLine();
            br.readLine();
            String line = br.readLine();
            while (line != null) {
                if (!line.isEmpty()) {
                    if(!rem.contains(line.trim())){
                        rem.add(line.trim());
                    }
                }
                line = br.readLine();
            }
            br.close();
            //System.out.println(rem);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static void remFileUpdate() throws IOException {
        FileWriter fw=new FileWriter(Main.remOrdPath);
        PrintWriter pw=new PrintWriter(fw);
        pw.printf("%-25s\n","Order id");
        pw.println();
        for(String i:rem){
            pw.printf("%-25s\n",i);
        }
        pw.close();
    }
    private static void adminMainMethod() throws IOException {
        //user details
        //hotel details
        //delivery person details
        //order details
        //remaining orders
        //allocate order
        //exit
        System.out.println("\nWELCOME ADMIN.....😇😎😇");
        boolean c=true;
        while(c) {
            System.out.println("\nEnter your option :");
            System.out.println("1.Hotel Details");
            System.out.println("2.User Details");
            System.out.println("3.Delivery person details");
            System.out.println("4.Items sold details");
            System.out.println("5.Order details");
            System.out.println("6.Remaining orders");
            System.out.println("7.Allocate orders");
            System.out.println("8.Exit\n");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    //hotel details
                    if(Hotel.hotelIdMainId.size()>0) {
                        System.out.println("************************************************************************* Hotels List ********************************************************************************************");
                        System.out.println();
                        System.out.println("|-------------------------------|------------------|------------|--------------------------------|------------------------------------------|-------------------|----------------|");
                        System.out.printf("|%-30s | %-16s | %-10s | %-30s | %-40s | %-17s | %-15s|\n", "ID", "Password", "Main Id", "Name", "Address", "Contact Number", "Availability");
                        System.out.println("|-------------------------------|------------------|------------|--------------------------------|------------------------------------------|-------------------|----------------|");
                        for (Map.Entry<String, String> entry : Hotel.hotelIdMainId.entrySet()) {
                            System.out.printf("|%-30s | %-16s | %-10s | %-30s | %-40s | %-17s | %-15s|\n", entry.getKey(), Hotel.hotelPass.get(entry.getKey()), entry.getValue(), Hotel.hotelRegister.get(entry.getValue()), Hotel.hotelDetails.get(entry.getValue()).get(0), Hotel.hotelDetails.get(entry.getValue()).get(1), Hotel.hotelDetails.get(entry.getValue()).get(2));
                        }
                        System.out.println("|-------------------------------|------------------|------------|--------------------------------|------------------------------------------|-------------------|----------------|");
                        System.out.println();
                        System.out.println("**********************************************************************************************************************************************************************************");
                    }
                    else{
                        System.out.println("\nThere are no hotels have been registered yet..!\n");
                    }
                    break;
                case 2:
                    //user details
                    if(User.UserIdMainId.size()>0) {
                        System.out.println("********************************************************************  Users List ********************************************************************************");
                        System.out.println();
                        System.out.println("|-------------------------------|------------------|------------|--------------------------------|------------------------------------------|-------------------|");
                        System.out.printf("|%-30s | %-16s | %-10s | %-30s | %-40s | %-17s |\n", "ID", "Password", "Main Id", "Name", "Address", "Contact Number");
                        System.out.println("|-------------------------------|------------------|------------|--------------------------------|------------------------------------------|-------------------|");
                        for (Map.Entry<String, String> entry : User.UserIdMainId.entrySet()) {
                            System.out.printf("|%-30s | %-16s | %-10s | %-30s | %-40s | %-17s |\n", entry.getKey(), User.UserPass.get(entry.getKey()), entry.getValue(), User.UserRegister.get(entry.getValue()), User.UserDetails.get(entry.getValue()).get(0), User.UserDetails.get(entry.getValue()).get(1));
                        }
                        System.out.println("|-------------------------------|------------------|------------|--------------------------------|------------------------------------------|-------------------|");
                        System.out.println();
                        System.out.println("*****************************************************************************************************************************************************************");
                    }
                    else{
                        System.out.println("\nThere are no users have been registered..!\n");
                    }
                    break;
                case 3:
                    //delivery person details
                    if(Deliver.deliverIdMainId.size()>0) {
                        System.out.println("******************************************************** Delivery Persons List *********************************************************");
                        System.out.println();
                        System.out.println("|-------------------------------|------------------|------------|--------------------------------|-------------------|-----------------|");
                        System.out.printf("|%-30s | %-16s | %-10s | %-30s | %-17s | %-15s |\n", "ID", "Password", "Main Id", "Name", "Contact Number", "Ävailability");
                        System.out.println("|-------------------------------|------------------|------------|--------------------------------|-------------------|-----------------|");
                        for (Map.Entry<String, String> entry : Deliver.deliverIdMainId.entrySet()) {
                            System.out.printf("|%-30s | %-16s | %-10s | %-30s | %-17s | %-15s |\n", entry.getKey(), Deliver.deliverPass.get(entry.getKey()), entry.getValue(), Deliver.deliverRegister.get(entry.getValue()), Deliver.deliverContact.get(entry.getValue()), (Deliver.deliverAvail.contains(entry.getValue()) ? "Available" : "Not Available"));
                        }
                        System.out.println("|-------------------------------|------------------|------------|--------------------------------|-------------------|-----------------|");
                        System.out.println();
                        System.out.println("****************************************************************************************************************************************");
                    }
                    else{
                        System.out.println("\nThere are no deliver persons have registered..!\n");
                    }
                    break;
                case 4:
                    //items sold details
                    if(User.orderIDUser.size()>0) {
                        FileReader fr = new FileReader(Main.userBuyPath);
                        BufferedReader br = new BufferedReader(fr);
                        try {
                            String line = br.readLine();
                            while (line != null) {
                                if (!line.isEmpty()) {
                                    if (line.startsWith("***********")) {
                                        System.out.println();
                                    }
                                    System.out.println(line);
                                }
                                line = br.readLine();
                            }
                            br.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    else{
                        System.out.println("\nThere are no items sold yet..!\n");
                    }
                    break;
                case 5:
                    //order details
                    if(User.orderIDUser.size()>0) {
                        System.out.println("********************************** Order Details List **************************************");
                        System.out.println();
                        System.out.println("|----------------|----------------------|----------------------|---------------------------|");
                        System.out.printf("|%-15s | %-20s | %-20s | %-25s |\n", "Order Id", "Ordered Person ID", "Delivered by ID", "Delivery Status");
                        System.out.println("|----------------|----------------------|----------------------|---------------------------|");
                        FileReader frr = new FileReader(Main.orderIdPath);
                        BufferedReader brr = new BufferedReader(frr);
                        try {
                            brr.readLine();
                            brr.readLine();
                            String line = brr.readLine();
                            while (line != null) {
                                if (!line.isEmpty()) {
                                    String[] a = line.split("\\s");
                                    ArrayList<String> arr = new ArrayList<>();
                                    for (String i : a) {
                                        if (!(i.length() == 0)) {
                                            arr.add(i);
                                        }
                                    }
                                    System.out.printf("|%-15s | %-20s | %-20s | %-25s |\n", arr.get(0), arr.get(1), arr.get(2), arr.get(3));
                                }
                                line = brr.readLine();
                            }
                            brr.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("|----------------|----------------------|----------------------|---------------------------|");
                        System.out.println();
                        System.out.println("********************************************************************************************");
                    }
                    else{
                        System.out.println("\nThere are no orders placed yet..!\n");
                    }
                    break;
                case 6:
                    //remaining orders
                    if(rem.size()>0) {
                        System.out.println("*********** Remaining Orders *************");
                        for(String i:rem){
                            System.out.println(i);
                        }
                        System.out.println("*******************************************");
                    }
                    else{
                        System.out.println("\nThere are no other remaining orders..!\n");
                    }
                    break;
                case 7:
                    //allocate orders to delivery persons
                    if(rem.size()>0) {
                        System.out.println("*********** Remaining Orders *************");
                        for(String i:rem){
                            System.out.println(i);
                        }
                        System.out.println("*******************************************");
                        System.out.println("\nEnter the order id for which you want to allocate delivery person : ");
                        String orderId=sc.nextLine().trim();
                        if(rem.contains(orderId)){
                            if(Deliver.deliverAvail.size()>0){
                                String y=Deliver.deliverAvail.get(0);
                                User.orderIDDelivery.put(orderId,y);
                                rem.remove(orderId);
                                System.out.println("\nAllocated successfully..!\n");
                                Main.CallOrderIDFileUpdate();
                            }
                            else{
                                System.out.println("\nNo delivery persons available..please come back late..!\n");
                            }
                        }
                        else{
                            System.out.println("\nThere is no such order id present in remaining order list..Please enter valid order id...!\n");
                        }

                    }
                    else{
                        System.out.println("\nThere are no other remaining orders..!\n");
                    }
                    break;
                case 8:
                    c=false;
                    break;
            }
        }
    }
    public static void adminMethod() throws IOException {
        myThread b=new myThread();
        b.run();
        //remFileReader();
        System.out.println("Enter Admin access name :");
        String adminName=sc.nextLine().trim();
        if(adminName.equals("admin")){
            System.out.println("Password please :");
            String pass=sc.nextLine().trim();
            if(pass.equals("password")){
                System.out.println("Enter secret key😉");
                String sec=sc.nextLine();
                if(sec.equals("ADMIN")){
                    adminMainMethod();
                }
                else{
                    System.out.println("\nWrong secret key..!\n");
                }
            }
            else{
                System.out.println("\nWrong password..!\n");
            }
        }
        else{
            System.out.println("\nWrong access name..!\n");
        }
    }
}
