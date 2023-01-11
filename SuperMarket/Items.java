package SuperMarket;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
//creating items list
//adding items
//displaying items
public class Items {
    static Scanner sc=new Scanner(System.in);
    static Map<String,Double> items=new HashMap<>();
    static Map<String,Integer> nameQuantity=new HashMap<>();
    public static void entryItems() {
        System.out.println();
        System.out.println("Enter how many items you have to add in list..");
        int n=sc.nextInt();
        sc.nextLine();
        System.out.println("\nEnter name and price and quantity of each item...!\n");
        for(int i=0;i<n;i++){
            System.out.println("Name : ");
            String name=sc.nextLine();
            System.out.println("Price : ");
            double price=sc.nextDouble();
            System.out.println("Quantity available : ");
            int quantityAvailable=sc.nextInt();
            sc.nextLine();
            if(Items.items.containsKey(name)){
                int temp=nameQuantity.get(name);
                nameQuantity.put(name,temp+quantityAvailable);
                System.out.println("\nActually the product is already present...so the price is updated and the quantity available is increased..!\n");
            }
            else{
            nameQuantity.put(name,quantityAvailable);
            }
            items.put(name,price);
            System.out.println();
        }
    }
    public static void display(){
        if(items.size()>0) {
            System.out.println("******************* The items present in the store..! ***************");
            System.out.println();
            System.out.printf("|----------------------|----------------------|---------------------|\n");
            System.out.printf("| %-20s |  %-19s | %-20s|\n", "Name of the item ", "Price of the item ", "Quantity Available ");
            System.out.printf("|----------------------|----------------------|---------------------|\n");
            for (Map.Entry<String, Double> entry : items.entrySet())
                System.out.printf("| %-20s |  %-19s | %-20s|\n", entry.getKey(), entry.getValue(), nameQuantity.get(entry.getKey()));
            System.out.printf("|----------------------|----------------------|---------------------|\n");
            System.out.println();
            System.out.println("*********************************************************************\n");
        }
        else{
            System.out.println("Still now for today no products have been added in the store..!Sorry...!\n");
        }
    }
}
