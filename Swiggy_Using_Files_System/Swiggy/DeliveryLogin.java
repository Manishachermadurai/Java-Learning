package Swiggy;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

class DeliveryLogin {
    static LinkedList<String> orderOfDeliver =new LinkedList<>();
    static LinkedList<String> orderUser=new LinkedList<>();
    static Scanner sc=new Scanner(System.in);
    private static void orderIdsOfDelivery(String mainId) throws FileNotFoundException {
        FileReader fr=new FileReader(Main.orderIdPath);
        BufferedReader br=new BufferedReader(fr);
        try {
            br.readLine();
            br.readLine();
            br.readLine();
            String line = br.readLine();
            while (line != null) {
                if(!line.isEmpty()) {
                    String[] a = line.split("\\s");
                    ArrayList<String> arr = new ArrayList<>();
                    for (String i : a) {
                        if (!(i.length() == 0)) {
                            arr.add(i);
                        }
                    }
                    if(arr.get(2).trim().equals(mainId)){
                        if(arr.get(3).trim().equals("Ordered")){
                            orderOfDeliver.add(arr.get(0).trim());
                            orderUser.add(arr.get(1).trim());
                        }
                    }
                }
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void DeliveryLoginMethod(String id) throws IOException {
        //profile
        //orders took
        //order details
        //update available/no
        //update delivered
        //exit
        System.out.println("\nWELCOME DELIVER..😊😇");
        String mainId = id;
        for (Map.Entry<String, String> entry : Deliver.deliverIdMainId.entrySet()) {
            if (entry.getValue().equals(mainId)) {
                id = entry.getKey();
                break;
            }
        }
        orderIdsOfDelivery(mainId);
        boolean c = true;
        while (c) {
            System.out.println("\nEnter your option :");
            System.out.println("1.Profile..");
            System.out.println("2.Order Allocated..");
            System.out.println("3.Order Details..");
            System.out.println("4.Update Availability..");
            System.out.println("5.Update the delivery status..");
            System.out.println("6.Log out..\n");
            int option=sc.nextInt();
            sc.nextLine();
            switch (option){
                case 1:
                    //Profile
                    System.out.println("********************************************************************");
                    System.out.println("The Delivery Person Details Page");
                    System.out.println("********************************************************************");
                    System.out.println();
                    System.out.println("ID : "+id);
                    System.out.println("Main ID : "+mainId);
                    System.out.println("Name : "+Deliver.deliverRegister.get(mainId));
                    System.out.println("Contact Number : "+Deliver.deliverContact.get(mainId));
                    System.out.println("Availability : "+(Deliver.deliverAvail.contains(mainId)?"Available":"No"));
                    System.out.println();
                    System.out.println("********************************************************************\n");
                    break;
                case 2:
                    //Orders allocated if any
                    if(orderOfDeliver.size()>0){
                        System.out.println("*************************");
                        System.out.println("Order ID");
                        System.out.println("*************************");
                        for(String i: orderOfDeliver){
                            System.out.println(i);
                        }
                        System.out.println("*************************");
                        System.out.println();
                        System.out.println("\nCurrently you have "+ orderOfDeliver.size()+" orders..!\n");
                    }
                    else{
                        System.out.println("There are no orders at present..!");
                    }
                    break;
                case 3:
                    //Orders details - user details for location
                    if(orderOfDeliver.size()>0){
                        System.out.println("*************************");
                        System.out.println("Order ID");
                        System.out.println("*************************");
                        for(String i: orderOfDeliver){
                            System.out.println(i);
                        }
                        System.out.println("*************************");
                        System.out.println();
                        System.out.println("\nCurrently you have "+ orderOfDeliver.size()+" orders..!\n");
                        System.out.println();
                        System.out.println("Enter the orderID for details : ");
                        String order=sc.nextLine();
                        if(orderOfDeliver.contains(order)){
                            int ind=orderOfDeliver.indexOf(order);
                            String mainId1=orderUser.get(ind);
                            String userId="";
                            for(Map.Entry<String,String> entry:User.UserIdMainId.entrySet()){
                                if(entry.getValue().trim().equals(mainId1)){
                                    userId=entry.getKey();
                                    break;
                                }
                            }
                            System.out.println("********************************************************************");
                            System.out.println("The user's Details");
                            System.out.println("********************************************************************");
                            System.out.println();
                            System.out.println("ID : "+userId);
                            System.out.println("Main ID : "+mainId1);
                            System.out.println("Name : "+User.UserRegister.get(mainId1));
                            System.out.println("Address : "+User.UserDetails.get(mainId1).get(0));
                            System.out.println("Contact Number : "+User.UserDetails.get(mainId1).get(1));
                            System.out.println();
                            System.out.println("********************************************************************\n");
                        }
                        else{
                            System.out.println("\nThere is no such ID..!\n");
                        }
                    }
                    else{
                        System.out.println("\nThere are no orders at present..!");
                    }
                    break;
                case 4:
                    //update availability
                    System.out.println("\nEnter your choice:");
                    System.out.println("1.Available..");
                    System.out.println("2.No..\n");
                    int g=sc.nextInt();
                    sc.nextLine();
                    switch(g){
                        case 1:
                            if(Deliver.deliverNot.contains(mainId)){
                                Deliver.deliverNot.remove(mainId);
                                Deliver.deliverAvail.add(mainId);
                                System.out.println("\nSuccessfully updated..!\n");
                            }
                            else{
                                System.out.println("You are already in available state..!\n");
                            }
                            break;
                        case 2:
                            if(Deliver.deliverAvail.contains(mainId)){
                                Deliver.deliverNot.add(mainId);
                                Deliver.deliverAvail.remove(mainId);
                                System.out.println("\nSuccessfully updated..!\n");
                            }
                            else{
                                System.out.println("You are already in not available state..!\n");
                            }
                            break;
                    }
                    Main.CallDeliverDetailsFileUpdate();
                    break;
                case 5:
                    //update delivery status
                    if(orderOfDeliver.size()>0) {
                        System.out.println("*************************");
                        System.out.println("Order ID");
                        System.out.println("*************************");
                        for (String i : orderOfDeliver) {
                            System.out.println(i);
                        }
                        System.out.println("*************************");
                        System.out.println();
                        System.out.println("\nCurrently you have " + orderOfDeliver.size() + " orders..!\n");
                        System.out.println();
                        System.out.println("Enter the orderID for details : ");
                        String order = sc.nextLine();
                        if(orderOfDeliver.contains(order)) {
                            System.out.println("Are you sure you have to update the status delivered..?\n");
                            System.out.println("Enter the option");
                            System.out.println("1.yes");
                            System.out.println("2.no\n");
                            int h = sc.nextInt();
                            sc.nextLine();
                            switch (h) {
                                case 1:
                                    System.out.println("Enter the otp code from user :");
                                    String otp = sc.nextLine();
                                    if (otp.trim().equals("OKAY2022")) {
                                        if (orderOfDeliver.contains(order)) {
                                            int ind = orderOfDeliver.indexOf(order);
                                            String userId = orderUser.get(ind);
                                            User.orderStatus.put(order, "Delivered");
                                            Main.CallOrderIDFileUpdate();
                                            orderOfDeliver.remove(order);
                                            orderUser.remove(userId);
                                            System.out.println("Updated successfully..!\n");
                                        } else {
                                            System.out.println("\nThe order has been already in delivered state..!\n");
                                        }
                                    } else {
                                        System.out.println("\nSorry the otp doesn't match..!\n");
                                    }
                                    break;
                                case 2:
                                    System.out.println("\nYou chose no option..!\n");
                                    break;
                                default:
                                    System.out.println("\nYou entered wrong option..!\n");
                                    break;
                            }
                        }
                        else{
                            System.out.println("\nThere is no such id..!\n");
                        }
                    }
                    else{
                        System.out.println("\nThere are no orders at present..!");
                    }
                    break;
                case 6:
                    //Exit
                    c=false;
                    break;
            }
        }
    }
}
