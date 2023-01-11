package Swiggy;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;
class UserLogin{
    static Map<String,Integer> cart=new HashMap<>();
    static Map<String,Double> priceProduct=new HashMap<>();
    static Map<String,Double> cartPrice=new HashMap<>();
    static Map<String,String> cartPlusIds=new HashMap<>();
    static ArrayList<String> remainingOrder=new ArrayList<>();
    // Hotel list display
    private static void HotelsDisplay() {
        //for user display only
        if (Hotel.hotelOpen.size()>0) {
            System.out.println("********************************************************* HOTELS LIST **********************************************************");
            System.out.println();
            System.out.println("|-----------|--------------------------------|------------------------------------------|----------------------|----------------|");
            System.out.printf("|%-10s | %-30s | %-40s | %-20s | %-15s|\n", "ID", "Name", "Address", "Contact Number", "Availability");
            System.out.println("|-----------|--------------------------------|------------------------------------------|----------------------|----------------|");
            for (String i:Hotel.hotelOpen) {
                System.out.printf("|%-10s | %-30s | %-40s | %-20s | %-15s|\n", i, Hotel.hotelRegister.get(i), Hotel.hotelDetails.get(i).get(0), Hotel.hotelDetails.get(i).get(1), Hotel.hotelDetails.get(i).get(2));
            }
            System.out.println("|-----------|--------------------------------|------------------------------------------|----------------------|----------------|");
            System.out.println();
            System.out.println("*********************************************************************************************************************************");
        }
        else{
            System.out.println("\nCurrently no hotels have been registered..!\n");
        }
    }

    static Scanner sc=new Scanner(System.in);
    static void UserLoginMethod(String id) throws IOException {
        //profile showing
        //see hotels list
        //see hotel's food list
        //buy
        //remove from cart
        //checkout
        //exit
        System.out.println("\nWELCOME USER..😊😇");
        boolean bill=true;
        String mainId=id;
        for(Map.Entry<String,String> entry:User.UserIdMainId.entrySet()){
            if(entry.getValue().equals(mainId)){
                id=entry.getKey();
                break;
            }
        }
        boolean c=true;
        while(c){
        System.out.println("\nEnter your option : ");
        System.out.println("1.Profile Page..");
        System.out.println("2.Hotels List..");
        System.out.println("3.Cart..");
        System.out.println("4.Remove from cart..");
        System.out.println("5.Checkout..");
        System.out.println("6.Log out..\n");
        int g=sc.nextInt();
        sc.nextLine();
        switch(g) {
            case 1:
                //display profile of the user
                System.out.println("********************************************************************");
                System.out.println("The user Details Page");
                System.out.println("********************************************************************");
                System.out.println();
                System.out.println("ID : "+id);
                System.out.println("Main ID : "+mainId);
                System.out.println("Name : "+User.UserRegister.get(mainId));
                System.out.println("Address : "+User.UserDetails.get(mainId).get(0));
                System.out.println("Contact Number : "+User.UserDetails.get(mainId).get(1));
                System.out.println();
                System.out.println("********************************************************************\n");
                break;
            case 2:
                //hotels list
                //food list
                //buy
                HotelsDisplay();
                System.out.println("\n To see the food list of a hotel..Please enter the main id of the hotel..!\n");
                String id1=sc.nextLine();
                if(!Hotel.hotelRegister.containsKey(id1)){
                    System.out.println("\nYou have entered wrong id..Please enter correct id next time..!\n");
                }
                else{
                    //display items
                    if(Hotel.itemsRate.containsKey(id1)) {
                        ArrayList<Map<String, ArrayList<Double>>> a1 = Hotel.itemsRate.get(id1);
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
                        System.out.println();
                        System.out.println("Enter if you want to buy food : ");
                        System.out.println("1.Yes..");
                        System.out.println("2.No..\n");
                        int h=sc.nextInt();
                        sc.nextLine();
                        switch(h){
                            case 1:
                                System.out.println("\nThanks for coming..!\n");
                                System.out.println("Item name : ");
                                String name=sc.nextLine().trim();
                                String name1="";
                                int c2=1;
                                int j=0;
                                ArrayList<Map<String,ArrayList<Double>>> temp2=Hotel.itemsRate.get(id1);
                                for(int i=0;i<temp2.size();i++){
                                    Map<String,ArrayList<Double>> k=temp2.get(i);
                                    for(Map.Entry<String,ArrayList<Double>> e:k.entrySet()){
                                        if(e.getKey().trim().equalsIgnoreCase(name)){//
                                            name=e.getKey().trim();
                                            name1=id1+"-"+name;
                                            c2=0;
                                            j=i;
                                            break;
                                        }
                                    }
                                }
                                if(c2==0){
                                    //item present
                                    System.out.println("\nHow much quantity you want ?\n");
                                    int n=sc.nextInt();
                                    sc.nextLine();
                                    Map<String,ArrayList<Double>> k=temp2.get(j);
                                    for(Map.Entry<String,ArrayList<Double>> e:k.entrySet()){
                                        if(e.getKey().trim().equalsIgnoreCase(name)){//
                                            double amount=e.getValue().get(0);
                                            double offer=e.getValue().get(1);
                                            if(offer>0){
                                               amount=amount-(offer/100)*amount;
                                            }
                                            priceProduct.put(name1,amount);
                                            double net=n*amount;
                                            if(!cart.containsKey(name1)){
                                                cartPlusIds.put(name1,id1);
                                                cart.put(name1,n);
                                                cartPrice.put(name1,net);
                                            }
                                            else{
                                                int h1=cart.get(name1);
                                                double h2=cartPrice.get(name1);
                                                cart.put(name1,cart.get(name1)+n);
                                                cartPrice.put(name1,cartPrice.get(name1)+net);
                                            }
                                            System.out.println("\nAdded to cart successfully...!");
                                        }
                                    }
                                }
                                else{
                                    System.out.println("\nThere is no such item available...!Kindly re-check..\n");
                                }
                                break;
                            case 2:
                                System.out.println("\nThanks for visited..!");
                                break;
                            default:
                                break;
                        }
                    }
                    else{
                        System.out.println("\nSorry......Currently no items have been added by the hotel..!\n");
                        System.out.println("Please kindly visit another hotel..!\n");
                    }
                }
                break;
            case 3:
                //cart
                if(cart.size()==0){
                    System.out.println("Sorry..! Your cart is empty..!\n");
                }
                else{
                    System.out.println("**************************** The items present in the cart...! *********************");
                    System.out.println();
                    System.out.println("|--------------------------------|---------------------------|---------------------|");
                    System.out.printf("| %-30s | %-25s | %-20s|\n", "Name of the item ", "Quantity of the item", "Price");
                    System.out.println("|--------------------------------|---------------------------|---------------------|");
                    for (Map.Entry<String,Integer> entry : cart.entrySet())
                        System.out.printf("| %-30s | %-25s | %-20s|\n" ,entry.getKey() ,entry.getValue(),cartPrice.get(entry.getKey()));
                    System.out.println("|--------------------------------|---------------------------|---------------------|");
                    System.out.println();
                    System.out.println("************************************************************************************\n");
                }
                break;
            case 4:
                //remove cart
                if(cart.size()!=0) {
                    System.out.println("**************************** The items present in the cart...! *********************");
                    System.out.println();
                    System.out.println("|--------------------------------|---------------------------|---------------------|");
                    System.out.printf("| %-30s | %-25s | %-20s|\n", "Name of the item ", "Quantity of the item", "Price");
                    System.out.println("|--------------------------------|---------------------------|---------------------|");
                    for (Map.Entry<String,Integer> entry : cart.entrySet())
                        System.out.printf("| %-30s | %-25s | %-20s|\n" ,entry.getKey() ,entry.getValue(),cartPrice.get(entry.getKey()));
                    System.out.println("|--------------------------------|---------------------------|---------------------|");
                    System.out.println();
                    System.out.println("************************************************************************************\n");
                    System.out.println("The name of the product you want to remove..!\n");
                    String remPro = sc.nextLine();
                    if (cart.containsKey(remPro)) {
                        int gh = cart.get(remPro);
                        System.out.println("How much quantity you want to remove..?");
                        int h = sc.nextInt();
                        sc.nextLine();
                        if (h == gh) {
                            cart.remove(remPro);
                            cartPrice.remove(remPro);
                            System.out.println("Removed Successfully..!\n");
                        }
                        else if (h < gh) {
                            cart.put(remPro, (gh - h));
                            cartPrice.put(remPro, (h * priceProduct.get(remPro)));
                            System.out.println("Removed Successfully..!\n");
                        }
                        else {
                        System.out.println("\nYou have only " + cart.get(remPro) + " in your cart..!\n");
                        System.out.println("Do you want to remove the product from your cart?");
                        String d = sc.nextLine();
                        if (d.equals("yes")) {
                            cart.remove(remPro);
                            cartPrice.remove(remPro);
                            System.out.println("Removed Successfully..!\n");
                        }
                        else {
                            System.out.println("\nYou have chosen no option...\n");
                        }
                    }
                } else {
                    System.out.println("\nThere is no such product in your cart..!\n");
                }
                }
                else{
                    System.out.println("\nCurrently..there are no products in your cart..!\n");
                }
                break;
            case 5:
                //checkout
                if(bill) {
                    if(cart.size()>0) {
                        double total=0;
                        FileWriter fw=new FileWriter("D:\\Swiggy\\bills.txt",true);
                        PrintWriter ps=new PrintWriter(fw);
                        String ordId="SWIORD"+User.orderId;
                        User.orderId++;
                        System.out.println("Wait for few minutes...We are generating bill..!\n");
                        String r = User.UserRegister.get(mainId);
                        System.out.println();
                        System.out.println();
                        ps.println("************************************************************************************************\n\n");
                        System.out.println("************************************************************************************************\n\n");
                        System.out.println();
                        ps.println();
                        System.out.printf("%-15s : %-25s\n", "Order Id", ordId);
                        ps.printf("%-15s : %-25s\n", "Order Id", ordId);
                        User.orderIDUser.put(ordId,mainId);
                        String deliverPersonId="";
                        if(Deliver.deliverAvail.size()>0){
                            Random random=new Random();
                            int rand=random.nextInt(Deliver.deliverAvail.size());
                            if(rand<Deliver.deliverAvail.size() && rand>=0){
                                deliverPersonId=Deliver.deliverAvail.get(rand);
                            }
                            else {
                                deliverPersonId = Deliver.deliverAvail.get(0);
                            }
                        }
                        else{
                            deliverPersonId="RemindLater";
                            remainingOrder.add(ordId);
                        }
                        User.orderIDDelivery.put(ordId,deliverPersonId);
                        User.orderStatus.put(ordId,"Ordered");
                        Main.CallOrderIDFileUpdate();
                        System.out.printf("%-15s : %-25s\n", "User Name ", r);
                        ps.printf("%-15s : %-25s\n", "User Name ", r);
                        Date dNow = new Date();
                        SimpleDateFormat ft = new SimpleDateFormat("E yyyy.MM.dd  hh:mm:ss");
                        System.out.printf("%-15s : %-25s\n", "Date ", ft.format(dNow));
                        ps.printf("%-15s : %-25s\n", "Date ", ft.format(dNow));
                        System.out.println();
                        ps.println();
                        System.out.println("|----------------------------------------------------------------------------------------------------|");
                        ps.println("|----------------------------------------------------------------------------------------------------|");
                        System.out.printf("|%-30s | %-20s | %-20s | %-20s |\n", "Name of the item", "Quantity of the item", "MRP of Item", "Price of the item");
                        ps.printf("|%-30s | %-20s | %-20s | %-20s |\n", "Name of the item", "Quantity of the item", "MRP of Item", "Price of the item");
                        System.out.println("|-------------------------------|----------------------|----------------------|----------------------|");
                        ps.println("|-------------------------------|----------------------|----------------------|----------------------|");
                        for (Map.Entry<String, Double> entry : cartPrice.entrySet()) {
                            System.out.printf("|%-30s | %-20s | %-20s | %-20s |\n", entry.getKey(), priceProduct.get(entry.getKey()), cart.get(entry.getKey()), entry.getValue());
                            ps.printf("|%-30s | %-20s | %-20s | %-20s |\n", entry.getKey(), priceProduct.get(entry.getKey()), cart.get(entry.getKey()), entry.getValue());
                            total = total + entry.getValue();
                        }
                        System.out.println("|----------------------------------------------------------------------------------------------------|\n");
                        ps.println("|----------------------------------------------------------------------------------------------------|\n");
                        ps.println();
                        System.out.println();
                        ps.println("|----------------------------------------------------------------------------------------------------|");
                        System.out.println("|----------------------------------------------------------------------------------------------------|");
                        ps.printf("|%-30s  %-21s  %-21s  %-22s|\n", "Total Price", "", "", total);
                        System.out.printf("|%-30s  %-21s  %-21s  %-22s|\n", "Total Price", "", "", total);
                        ps.println("|----------------------------------------------------------------------------------------------------|");
                        System.out.println("|----------------------------------------------------------------------------------------------------|");
                        System.out.println();
                        ps.println();
                        System.out.println("\t \t \t \t \t \t \t \tThank You ...!\n");
                        ps.println("\t \t \t \t \t \t \t \tThank You ...!\n");
                        System.out.println("\t \t \t \t \t \t \t Welcome...Come Again...!\n\n");
                        if(deliverPersonId.equals("RemindLater")){
                            System.out.println("\nDelivery person details will be shared in few minutes..!\n");
                        }
                        else{
                            System.out.println("\nYour delivery will be delivered by "+deliverPersonId+"\n");
                            ps.println("\nYour delivery will be delivered by "+deliverPersonId+"\n");
                        }
                        ps.println("\t \t \t \t \t \t \t Welcome...Come Again...!\n\n");
                        System.out.println("************************************************************************************************\n\n\n");
                        ps.println("************************************************************************************************\n\n\n");
                        ps.println();
                        ps.close();
                        FileWriter fr=new FileWriter("D:\\Swiggy\\UserBuyData.txt",true);
                        PrintWriter pr=new PrintWriter(fr);
                        pr.println("*******************************************************************************************************************\n");
                        pr.println();
                        pr.println("User Name : "+User.UserRegister.get(mainId));
                        pr.println();
                        for (Map.Entry<String, Double> entry : cartPrice.entrySet()) {
                            pr.printf("%-20s  %-20s  %-20s  %-20s  %-30s\n",cartPlusIds.get(entry.getKey()), entry.getKey(), cart.get(entry.getKey()), entry.getValue(),ft.format(dNow));
                        }
                        pr.println();
                        pr.println("*******************************************************************************************************************\n");
                        pr.close();
                        cart.clear();
                        cartPrice.clear();
                        bill = false;
                    }
                    else{
                        System.out.println("\nYour cart is empty...Please purchase something before checkout..!\n");
                    }
                }
                else{
                    System.out.println("\nYou have already checked out...!..So please buy something to check out..!\n");
                }
                break;
            case 6:
                //exit
                if(bill) {
                    if(cart.size()!=0) {
                        System.out.println("**************************** The items present in the cart...! *********************");
                        System.out.println();
                        System.out.println("|--------------------------------|---------------------------|---------------------|");
                        System.out.printf("| %-30s | %-25s | %-20s|\n", "Name of the item ", "Quantity of the item", "Price");
                        System.out.println("|--------------------------------|---------------------------|---------------------|");
                        for (Map.Entry<String,Integer> entry : cart.entrySet())
                            System.out.printf("| %-30s | %-25s | %-20s|\n" ,entry.getKey() ,entry.getValue(),cartPrice.get(entry.getKey()));
                        System.out.println("|--------------------------------|---------------------------|---------------------|");
                        System.out.println();
                        System.out.println("************************************************************************************\n");
                        System.out.println("\nYour cart is not empty....If you really want to exit without checkout...then enter yes option please..!");
                            System.out.println("\nAre you sure you don't want to checkout..?");
                            System.out.println("1.yes");
                            System.out.println("2.No\n");
                            int y = sc.nextInt();
                            sc.nextLine();
                            switch (y) {
                                case 1:
                                    cart.clear();
                                    cartPrice.clear();
                                    cartPlusIds.clear();
                                    c = false;
                                    break;
                                case 2:
                                    System.out.println("\nYou chose no option..!\n");
                                    break;
                        }
                    }
                }
                c=false;
                break;
            default:
                System.out.println("\nYou entered wrong option...Please make sure you enter the right option next time..!\n");
        }
        }
    }
}
