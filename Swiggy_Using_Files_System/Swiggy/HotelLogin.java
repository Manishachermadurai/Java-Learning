package Swiggy;
import java.io.IOException;
import java.util.*;
public class HotelLogin {
    static Scanner sc=new Scanner(System.in);
    static void HotelLoginMethod(String id) throws IOException {
        System.out.println("Welcome ✨😎...!\n");
        String mainId=id;
        for(Map.Entry<String,String> entry:Hotel.hotelIdMainId.entrySet()){
            if(entry.getValue().equals(mainId)){
                id=entry.getKey();
                break;
            }
        }
        boolean c=true;
        while(c) {
            System.out.println("\nEnter the choice ");
            System.out.println("0.Shop Details..");
            System.out.println("1.Add items..");
            System.out.println("2.Remove items..");
            System.out.println("3.Update price..");
            System.out.println("4.Update Offer percentage..");
            System.out.println("5.Display items..");
            System.out.println("6.Update availability..");
            System.out.println("7.Log out..\n");
            int g = sc.nextInt();
            sc.nextLine();
            switch(g){
                case 0:
                    System.out.println("********************************************************************");
                    System.out.println("The shop Details Page");
                    System.out.println("********************************************************************");
                    System.out.println();
                    System.out.println("ID : "+id);
                    System.out.println("Main ID : "+mainId);
                    System.out.println("Name : "+Hotel.hotelRegister.get(mainId));
                    System.out.println("Address : "+Hotel.hotelDetails.get(mainId).get(0));
                    System.out.println("Contact Number : "+Hotel.hotelDetails.get(mainId).get(1));
                    System.out.println("Availability : "+(Hotel.hotelOpen.contains(mainId)?"Open":"Close"));
                    System.out.println();
                    System.out.println("********************************************************************\n");
                    break;
                case 1:
                    //add items
                    System.out.println("\nAdding item");
                    System.out.println("\nEnter item name : ");
                    String name=sc.nextLine();
                    int s = 0;
                    if(Hotel.itemsRate.size()>0){
                    if(Hotel.itemsRate.containsKey(mainId)) {
                        if(Hotel.itemsRate.get(mainId).size()>0) {
                            ArrayList<Map<String, ArrayList<Double>>> temp = Hotel.itemsRate.get(mainId);
                            for (int i = 0; i < temp.size(); i++) {
                                Map<String, ArrayList<Double>> k = temp.get(i);
                                for (Map.Entry<String, ArrayList<Double>> e : k.entrySet()) {
                                    if (e.getKey().equalsIgnoreCase(name + " ")) {
                                        s = 1;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    }
                        if (s == 1) {
                            System.out.println("\nAlready the item is present...If you want to update please go for 3rd and 4th Options..!\n");
                        } else {
                            System.out.println("\nEnter price : ");
                            double price = sc.nextDouble();
                            System.out.println("Enter offer percentage (without percentage symbol) : ");
                            double offer = sc.nextDouble();
                            sc.nextLine();
                            ArrayList<Double> h = new ArrayList<>();
                            h.add(price);
                            h.add(offer);
                            Map<String, ArrayList<Double>> d = new HashMap<>();
                            d.put(name, h);
                            ArrayList<Map<String, ArrayList<Double>>> tempn = new ArrayList<>();
                            tempn.add(d);
                            if (!Hotel.itemsRate.containsKey(mainId)) {
                                Hotel.itemsRate.put(mainId, tempn);
                            } else {
                                ArrayList<Map<String, ArrayList<Double>>> p;
                                p = Hotel.itemsRate.get(mainId);
                                p.add(d);
                                Hotel.itemsRate.put(mainId, p);
                            }
                        }
                    Main.CallHotelItemFileUpdate();
                    break;
                case 2:
                    //remove items
                    if(Hotel.itemsRate.containsKey(mainId)) {
                        ArrayList<Map<String, ArrayList<Double>>> a1 = Hotel.itemsRate.get(mainId);
                        System.out.println("************* Items ***************");
                        System.out.println();
                        System.out.println("|----------------|-------|--------|");
                        System.out.printf("|%-15s | %-5s | %-5s  |\n", "Name", "Price", "Offer");
                        System.out.println("|----------------|-------|--------|");
                        for (Map<String, ArrayList<Double>> i : a1) {
                            Map<String, ArrayList<Double>> k = i;
                            for (Map.Entry<String, ArrayList<Double>> e : k.entrySet()) {
                                System.out.printf("|%-15s | ", e.getKey());
                                System.out.printf("%-5s | ", e.getValue().get(0));
                                System.out.printf("%-5s  |", e.getValue().get(1) + "%");
                                System.out.println();
                            }
                        }
                        System.out.println("|----------------|-------|--------|");
                        System.out.println();
                        System.out.println("***********************************");
                    }
                    else{
                        System.out.println("\nSorry......Currently no items have been added..!\n");
                        break;
                    }
                    System.out.println("\nRemoving Item");
                    System.out.println("\nEnter item name : ");
                    int c1=1;
                    String name1=sc.nextLine();
                    ArrayList<Map<String,ArrayList<Double>>> temp1=Hotel.itemsRate.get(mainId);
                    for(int i=0;i<temp1.size();i++){
                        Map<String,ArrayList<Double>> k=temp1.get(i);
                        for(Map.Entry<String,ArrayList<Double>> e:k.entrySet()){
                            if(e.getKey().trim().equalsIgnoreCase(name1)){
                                c1=0;
                                temp1.remove(i);
                                Hotel.itemsRate.put(mainId,temp1);
                            }
                        }
                    }
                    Main.CallHotelItemFileUpdate();
                    if(c1==0){
                        System.out.println("\nRemoved Successfully..!");
                    }
                    else{
                        System.out.println("\nThere is no such item present in the list..!\n");
                    }
                    break;
                case 3:
                    //update items
                    if(Hotel.itemsRate.containsKey(mainId)) {
                        ArrayList<Map<String, ArrayList<Double>>> a1 = Hotel.itemsRate.get(mainId);
                        System.out.println("************* Items ***************");
                        System.out.println();
                        System.out.println("|----------------|-------|--------|");
                        System.out.printf("|%-15s | %-5s | %-5s  |\n", "Name", "Price", "Offer");
                        System.out.println("|----------------|-------|--------|");
                        for (Map<String, ArrayList<Double>> i : a1) {
                            Map<String, ArrayList<Double>> k = i;
                            for (Map.Entry<String, ArrayList<Double>> e : k.entrySet()) {
                                System.out.printf("|%-15s | ", e.getKey());
                                System.out.printf("%-5s | ", e.getValue().get(0));
                                System.out.printf("%-5s  |", e.getValue().get(1) + "%");
                                System.out.println();
                            }
                        }
                        System.out.println("|----------------|-------|--------|");
                        System.out.println();
                        System.out.println("***********************************");
                    }
                    else{
                        System.out.println("\nSorry......Currently no items have been added..!\n");
                        break;
                    }
                    System.out.println("\nUpdating Price of Item");
                    System.out.println("\nEnter item name : ");
                    int c2=1;
                    String name2=sc.nextLine().trim();
                    System.out.println("Enter the new price : ");
                    double d1=sc.nextDouble();
                    ArrayList<Map<String,ArrayList<Double>>> temp2=Hotel.itemsRate.get(mainId);
                    for(int i=0;i<temp2.size();i++){
                        Map<String,ArrayList<Double>> k=temp2.get(i);
                        for(Map.Entry<String,ArrayList<Double>> e:k.entrySet()){
                            if(e.getKey().trim().equalsIgnoreCase(name2)){
                                c2=0;
                                ArrayList<Double> t=e.getValue();
                                t.set(0,d1);
                                k.put(e.getKey(),t);
                                temp2.set(i,k);
                                Hotel.itemsRate.put(mainId,temp2);
                            }
                        }
                    }
                    Main.CallHotelItemFileUpdate();
                    if(c2==0){
                        System.out.println("\nPrice Updated Successfully..!");
                    }
                    else{
                        System.out.println("\nThere is no such item present in the list..!\n");
                    }
                    break;
                case 4:
                    //update offer percentage
                    if(Hotel.itemsRate.containsKey(mainId)) {
                        ArrayList<Map<String, ArrayList<Double>>> a1 = Hotel.itemsRate.get(mainId);
                        System.out.println("************* Items ***************");
                        System.out.println();
                        System.out.println("|----------------|-------|--------|");
                        System.out.printf("|%-15s | %-5s | %-5s  |\n", "Name", "Price", "Offer");
                        System.out.println("|----------------|-------|--------|");
                        for (Map<String, ArrayList<Double>> i : a1) {
                            Map<String, ArrayList<Double>> k = i;
                            for (Map.Entry<String, ArrayList<Double>> e : k.entrySet()) {
                                System.out.printf("|%-15s | ", e.getKey());
                                System.out.printf("%-5s | ", e.getValue().get(0));
                                System.out.printf("%-5s  |", e.getValue().get(1) + "%");
                                System.out.println();
                            }
                        }
                        System.out.println("|----------------|-------|--------|");
                        System.out.println();
                        System.out.println("***********************************");
                    }
                    else{
                        System.out.println("\nSorry......Currently no items have been added..!\n");
                        break;
                    }
                    System.out.println("\nUpdating Offer Percentage of Item");
                    System.out.println("\nEnter item name : ");
                    int c3=1;
                    String name3=sc.nextLine().trim();
                    System.out.println("Enter the new offer : ");
                    double d2=sc.nextDouble();
                    sc.nextLine();
                    ArrayList<Map<String,ArrayList<Double>>> temp3=Hotel.itemsRate.get(mainId);
                    for(int i=0;i<temp3.size();i++){
                        Map<String,ArrayList<Double>> k=temp3.get(i);
                        for(Map.Entry<String,ArrayList<Double>> e:k.entrySet()){
                            if(e.getKey().trim().equalsIgnoreCase(name3)){
                                c3=0;
                                ArrayList<Double> t=e.getValue();
                                t.set(1,d2);
                                k.put(e.getKey(),t);
                                temp3.set(i,k);
                                Hotel.itemsRate.put(mainId,temp3);
                            }
                        }
                    }
                    Main.CallHotelItemFileUpdate();
                    if(c3==0){
                        System.out.println("\nOffer Percentage Updated Successfully..!");
                    }
                    else{
                        System.out.println("\nThere is no such item present in the list..!\n");
                    }
                    break;
                case 5:
                    //display items
                    if(Hotel.itemsRate.containsKey(mainId)) {
                        ArrayList<Map<String, ArrayList<Double>>> a1 = Hotel.itemsRate.get(mainId);
                        System.out.println("************* Items ***************");
                        System.out.println();
                        System.out.println("|----------------|-------|--------|");
                        System.out.printf("|%-15s | %-5s | %-5s  |\n", "Name", "Price", "Offer");
                        System.out.println("|----------------|-------|--------|");
                        for (Map<String, ArrayList<Double>> i : a1) {
                            Map<String, ArrayList<Double>> k = i;
                            for (Map.Entry<String, ArrayList<Double>> e : k.entrySet()) {
                                System.out.printf("|%-15s | ", e.getKey());
                                System.out.printf("%-5s | ", e.getValue().get(0));
                                System.out.printf("%-5s  |", e.getValue().get(1) + "%");
                                System.out.println();
                            }
                        }
                        System.out.println("|----------------|-------|--------|");
                        System.out.println();
                        System.out.println("***********************************");
                    }
                    else{
                        System.out.println("\nSorry......Currently no items have been added by the hotel..!\n");
                        System.out.println("Please kindly visit another hotel..!\n");
                    }
                    break;
                case 6:
                    //update open/close for shop
                    System.out.println("\nEnter your shop Main Id : ");
                    String t=sc.nextLine().toUpperCase();
                    System.out.println("Enter your Option :");
                    System.out.println("1.Open");
                    System.out.println("2.Close\n");
                    int option=sc.nextInt();
                    sc.nextLine();
                    switch(option){
                        case 1:
                            if(Hotel.hotelClose.contains(t)){
                                Hotel.hotelClose.remove(t);
                                Hotel.hotelOpen.add(t);
                                System.out.println("\nUpdated Successfully..!\n");
                            }
                            else{
                                if(Hotel.hotelOpen.contains(t)){
                                    System.out.println("\nAlready in open state..!");
                                }
                                else{
                                    System.out.println("\nThere is no such shop...Please enter correct Main ID..!\n");
                                }
                            }
                            break;
                        case 2:
                            if(Hotel.hotelOpen.contains(t)){
                                Hotel.hotelOpen.remove(t);
                                Hotel.hotelClose.add(t);
                                System.out.println("\nUpdated Successfully..!\n");
                            }
                            else{
                                if(Hotel.hotelClose.contains(t)){
                                    System.out.println("\nAlready in open state..!");
                                }
                                else{
                                    System.out.println("\nThere is no such shop...Please enter correct Main ID..!\n");
                                }
                            }
                            break;
                        default:
                            System.out.println("\nEntered wrong Option...!\n");
                            break;
                    }
                    break;
                case 7:
                    c=false;
                    break;
            }
        }
    }
}
