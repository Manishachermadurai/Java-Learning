package SuperMarket;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
class User {
    //buying items
    //see items list
    //cart
    //checkout
    //remove from cart
    //exit

    //if you try to exit without checkout it asks whether not to checkout..
    // and says yes then the cart is emptied as you are not checked out the items are restored in the items stock

    //without saying no we can't exit
    //if we say no then you have been again redirected to user menu
    static Scanner sc=new Scanner(System.in);
    static Map<String,Integer> cart=new HashMap<>();
    static Map<String,Double> priceProduct=new HashMap<>();
    static Map<String,Double> cartPrice=new HashMap<>();
    static Map<String,String> cartHotelId=new HashMap<>();
    static double total=0;
    static boolean bill=true;
    void userMethod() throws IOException {
        System.out.println("Welcome User..!\n");
        boolean c=true;
        while(c) {
            System.out.println("\nEnter your option..");
            System.out.println("1.See the items present..");
            System.out.println("2.buy items..");
            System.out.println("3.your cart..");
            System.out.println("4.checkout..");
            System.out.println("5.Remove product from your cart..");
            System.out.println("6.Exit..\n");
            int option=sc.nextInt();
            sc.nextLine();
            switch(option){
                case 1:
                    Items.display();
                    break;
                case 2:
                    //buying items
                        System.out.println("\nItem name please : ");
                        String it=sc.nextLine();
                        System.out.println("How many you want..?");
                        int qu=sc.nextInt();
                        sc.nextLine();
                        if(Items.items.containsKey(it)) {
                            priceProduct.put(it,Items.items.get(it));
                            if(!cart.containsKey(it)){
                            int d = Items.nameQuantity.get(it);
                            if (d >= qu) {
                                cart.put(it, qu);
                                double temp = qu * (Items.items.get(it));
                                cartPrice.put(it, temp);
                                Items.nameQuantity.put(it, d - qu);
                                if (d - qu == 0) {
                                    Items.items.remove(it);
                                    Items.nameQuantity.remove(it);
                                }
                                System.out.println("Added to cart successfully..!\n");
                            } else {
                                System.out.println("There is no sufficient quantity available..!");
                                System.out.println("\nOnly " + Items.nameQuantity.get(it) + " present in this store..!");
                                System.out.println("You want to purchase this..?");
                                String f = sc.nextLine();
                                if (f.equals("yes")) {
                                    cart.put(it, Items.nameQuantity.get(it));
                                    double temp = Items.nameQuantity.get(it) * (Items.items.get(it));
                                    cartPrice.put(it, temp);
                                    Items.items.remove(it);
                                    Items.nameQuantity.remove(it);
                                    System.out.println("Added to cart successfully..!\n");
                                } else {
                                    System.out.println("\nThank you..!We assure you that next time sufficient amount of item available..!");
                                }
                            }
                        }
                            else{
                                int d1=cart.get(it);
                                int d = Items.nameQuantity.get(it);
                                if (d >= qu) {
                                    d1+=qu;
                                    cart.put(it, d1);
                                    double temp1=cartPrice.get(it);
                                    double temp = qu * (Items.items.get(it));
                                    temp+=temp1;
                                    cartPrice.put(it, temp);
                                    Items.nameQuantity.put(it, d - qu);
                                    if (d - qu == 0) {
                                        Items.items.remove(it);
                                        Items.nameQuantity.remove(it);
                                    }
                                    System.out.println("Added to cart successfully..!\n");
                                }
                                else {
                                    System.out.println("There is no sufficient quantity available..!");
                                    System.out.println("\nOnly " + Items.nameQuantity.get(it) + " present in this store..!");
                                    System.out.println("You want to purchase this..?");
                                    String f = sc.nextLine();
                                    if (f.equals("yes")) {
                                        int g=cart.get(it);
                                        cart.put(it, g+Items.nameQuantity.get(it));
                                        double r=cartPrice.get(it);
                                        double temp = Items.nameQuantity.get(it) * (Items.items.get(it));
                                        cartPrice.put(it,r+temp);
                                        Items.items.remove(it);
                                        Items.nameQuantity.remove(it);
                                        System.out.println("Added to cart successfully..!\n");
                                    } else {
                                        System.out.println("\nThank you..!We assure you that next time sufficient amount of item available..!");
                                    }
                                }
                            }
                        }
                        else{
                            System.out.println("\nYou entered wrong name...Please give the correct product name next time..!\n");
                        }
                    break;
                case 3:
                    //display cart
                    if(cart.size()==0){
                        System.out.println("Sorry..! Your cart is empty..!\n");
                    }
                    else{
                        System.out.println("******************* The items present in the cart...! ***************");
                        System.out.println();
                        System.out.printf("|----------------------|----------------------|---------------------|\n");
                        System.out.printf("| %-20s | %-19s | %-20s|\n", "Name of the item ", "Quantity of the item", "Price");
                        System.out.printf("|----------------------|----------------------|---------------------|\n");
                        for (Map.Entry<String,Integer> entry : cart.entrySet())
                            System.out.printf("| %-20s |  %-19s | %-20s|\n" ,entry.getKey(),cartPrice.get(entry.getKey()) ,entry.getValue());
                        System.out.printf("|----------------------|----------------------|---------------------|\n");
                        System.out.println();
                        System.out.println("*********************************************************************\n");
                    }
                    break;
                case 4:
                    //bill
                    if(bill) {
                        if(cart.size()>0) {
                            FileWriter fw=new FileWriter("D:\\bills.txt",true);
                            PrintWriter ps=new PrintWriter(fw);
                            System.out.println("Wait for few minutes...We are generating bill..!\n");
                            System.out.println("Please enter your name : ");
                            String r = sc.nextLine();
                            System.out.println();
                            System.out.println();
                            ps.println("*******************************************************************************************\n\n");
                            System.out.println("*******************************************************************************************\n\n");
                            System.out.println();
                            ps.println();
                            System.out.printf("%-15s : %-25s\n", "User Name ", r);
                            ps.printf("%-15s : %-25s\n", "User Name ", r);
                            Date dNow = new Date();
                            SimpleDateFormat ft = new SimpleDateFormat("E yyyy.MM.dd  hh:mm:ss");
                            System.out.printf("%-15s : %-25s\n", "Date ", ft.format(dNow));
                            ps.printf("%-15s : %-25s\n", "Date ", ft.format(dNow));
                            System.out.println();
                            ps.println();
                            System.out.println("|------------------------------------------------------------------------------------------|");
                            ps.println("|------------------------------------------------------------------------------------------|");
                            System.out.printf("|%-20s | %-20s | %-20s | %-20s |\n", "Name of the item", "Quantity of the item", "MRP of Item", "Price of the item");
                            ps.printf("|%-20s | %-20s | %-20s | %-20s |\n", "Name of the item", "Quantity of the item", "MRP of Item", "Price of the item");
                            System.out.println("|---------------------|----------------------|----------------------|----------------------|");
                            ps.println("|---------------------|----------------------|----------------------|----------------------|");
                            for (Map.Entry<String, Double> entry : cartPrice.entrySet()) {
                                System.out.printf("|%-20s | %-20s | %-20s | %-20s |\n", entry.getKey(), priceProduct.get(entry.getKey()), cart.get(entry.getKey()), entry.getValue());
                                ps.printf("|%-20s | %-20s | %-20s | %-20s |\n", entry.getKey(), priceProduct.get(entry.getKey()), cart.get(entry.getKey()), entry.getValue());
                                total = total + entry.getValue();
                            }
                            System.out.println("|------------------------------------------------------------------------------------------|\n");
                            ps.println("|------------------------------------------------------------------------------------------|\n");
                            ps.println();
                            System.out.println();
                            System.out.printf("%-20s  %-21s  %-21s  %-21s\n", "Total Price", "", "", total);
                            ps.printf("%-20s  %-21s  %-21s  %-21s\n", "Total Price", "", "", total);
                            System.out.println();
                            ps.println();
                            System.out.println("\t \t \t \t \t \t \t \tThank You ...!\n");
                            ps.println("\t \t \t \t \t \t \t \tThank You ...!\n");
                            System.out.println("\t \t \t \t \t \t \t Welcome...Come Again...!\n\n");
                            ps.println("\t \t \t \t \t \t \t Welcome...Come Again...!\n\n");
                            System.out.println("*******************************************************************************************\n\n\n");
                            ps.println("*******************************************************************************************\n\n\n");
                            ps.println();
                            ps.close();
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
                case 5:
                    if(cart.size()!=0) {
                        System.out.println("\nThe name of the product you want to remove..!");
                        String remPro = sc.nextLine();
                        if (cart.containsKey(remPro)) {
                            int g = cart.get(remPro);
                            System.out.println("How much quantity you want to remove..?");
                            int h = sc.nextInt();
                            sc.nextLine();
                            if (h == g) {
                                if (!Items.items.containsKey(remPro)) {
                                    double tempamount = cartPrice.get(remPro);
                                    tempamount = tempamount / h;
                                    Items.items.put(remPro, tempamount);
                                    Items.nameQuantity.put(remPro, h);
                                } else {
                                    Items.nameQuantity.put(remPro, (h + Items.nameQuantity.get(remPro)));
                                }
                                cart.remove(remPro);
                                cartPrice.remove(remPro);
                            } else if (h < g) {
                                if (!Items.items.containsKey(remPro)) {
                                    double tempamount = cartPrice.get(remPro);
                                    tempamount = tempamount / h;
                                    Items.items.put(remPro, tempamount);
                                    Items.nameQuantity.put(remPro, h);
                                    cart.put(remPro, (g - h));
                                    cartPrice.put(remPro, (h * tempamount));
                                } else {
                                    Items.nameQuantity.put(remPro, (Items.nameQuantity.get(remPro) + h));
                                    cart.put(remPro, (g - h));
                                    cartPrice.put(remPro, (h * Items.items.get(remPro)));
                                }
                            } else {
                                System.out.println("\nYou have only " + cart.get(remPro) + " in your cart..!\n");
                                System.out.println("Do you want to remove the product from your cart?");
                                String d = sc.nextLine();
                                if (d.equals("yes")) {
                                    if (!Items.items.containsKey(remPro)) {
                                        double tempamount = cartPrice.get(remPro);
                                        tempamount = tempamount / h;
                                        Items.items.put(remPro, tempamount);
                                        Items.nameQuantity.put(remPro, h);
                                    } else {
                                        Items.nameQuantity.put(remPro, (h + Items.nameQuantity.get(remPro)));
                                    }
                                    cart.remove(remPro);
                                    cartPrice.remove(remPro);
                                } else {
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
                case 6:
                    if(bill) {
                        if (cart.size() != 0) {
                            System.out.println("\nAre you sure you don't want to checkout..?");
                            String d = sc.nextLine();
                            if (d.toLowerCase().equals("yes")) {
                                for (Map.Entry<String, Integer> entry : cart.entrySet()) {
                                    if (Items.items.containsKey(entry.getKey())) {
                                        int temp = Items.nameQuantity.get(entry.getKey());
                                        temp += entry.getValue();
                                        Items.nameQuantity.put(entry.getKey(), temp);
                                    } else {
                                        double amount = cartPrice.get(entry.getKey());
                                        int o = cart.get(entry.getKey());
                                        amount = amount / o;
                                        Items.items.put(entry.getKey(), amount);
                                        Items.nameQuantity.put(entry.getKey(), o);
                                    }
                                }
                                cart.clear();
                                cartPrice.clear();
                                System.out.println("\nYou didn't checkout..So your cart is emptied..!\n");
                            }
                            else{
                                System.out.println("\nIf you want to exit..You have to checkout....Or else enter no while asking about checkout..!\n\n");
                                System.out.println("\nYou chose no option...So you nave been redirected to the main menu..!And your cart items are still there...!\n");
                                c = true;
                                break;
                            }
                        }
                    }
                    c=false;
                    break;
                default:
                    System.out.println("You entered wrong option...Please make sure you enter the right option next time..!\n");
            }
        }
    }
}
