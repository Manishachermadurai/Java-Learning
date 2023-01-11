package SuperMarket;
import java.util.Scanner;
class Admin {
    //add items and price
    //remove items
    //update price
    //see all the items
    Scanner sc=new Scanner(System.in);
    void adminMethod() {
        System.out.println("Welcome Admin...!\n");
        boolean c=true;
        while(c){
            System.out.println("Enter the choice ");
            System.out.println("1.Add items..");
            System.out.println("2.Remove items..");
            System.out.println("3.Update price..");
            System.out.println("4.Update Stock..");
            System.out.println("5.Display items..");
            System.out.println("6.Exit..\n");
            int g=sc.nextInt();
            switch(g){
                case 1:
                    Items.entryItems();
                    break;
                case 2:
                    sc.nextLine();
                    System.out.println("\nThe item name you want to remove..");
                    String itemName=sc.nextLine();
                    System.out.println("\nAre you sure you want to remove this item..?");
                    String e=sc.nextLine();
                    if(e.toLowerCase().equals("yes")) {
                        if (Items.items.containsKey(itemName)) {
                            Items.items.remove(itemName);
                            System.out.println("\nRemoved successfully..!\n");
                        } else {
                            System.out.println("\nYou entered wrong name...The item is not present in the list..!\n");
                        }
                    }
                    else{
                        System.out.println("\nOkay:) ...!\n");
                        }
                    break;
                case 3:
                    sc.nextLine();
                    System.out.println("\nThe price of item you want to update..");
                    String itemNameUpdatePrice=sc.nextLine();
                    System.out.println("Enter the new price :");
                    double price=sc.nextDouble();
                    sc.nextLine();
                    if (Items.items.containsKey(itemNameUpdatePrice)){
                        Items.items.put(itemNameUpdatePrice,price);
                        System.out.println("Updated Successfully..!\n");
                    }
                    else{
                        System.out.println("\nThere is no product named in this shop..!");
                        System.out.println("Do you want to add this product new..?");
                        String h=sc.nextLine();
                        if(h.equals("yes")){
                            System.out.println("Enter the stock available for this product : ");
                            int p=sc.nextInt();
                            sc.nextLine();
                            Items.items.put(itemNameUpdatePrice,price);
                            Items.nameQuantity.put(itemNameUpdatePrice,p);
                            System.out.println("\nUpdated Successfully..!\n");
                        }
                        else{
                            System.out.println("\nYou chose no..You have been redirected to the main menu..!\n");
                        }
                    }
                    break;
                case 4:
                    sc.nextLine();
                    System.out.println("\nThe quantity of item you want to update..");
                    String itemNameUpdateQuantity=sc.nextLine();
                    System.out.println("Enter the new stocks arrived :");
                    int p = sc.nextInt();
                    sc.nextLine();
                    if (Items.items.containsKey(itemNameUpdateQuantity)) {
                        p += Items.nameQuantity.get(itemNameUpdateQuantity);
                        Items.nameQuantity.put(itemNameUpdateQuantity, p);
                    }
                    else{
                            System.out.println("\nThere is no product named in this shop..!");
                            System.out.println("Do you want to add this product new..?");
                            String h=sc.nextLine();
                            if(h.equals("yes")){
                                System.out.println("Enter the price for this product : ");
                                double priceUp=sc.nextDouble();
                                sc.nextLine();
                                Items.items.put(itemNameUpdateQuantity,priceUp);
                                Items.nameQuantity.put(itemNameUpdateQuantity,p);
                                System.out.println("\nUpdated Successfully..!\n");
                            }
                            else{
                                System.out.println("\nYou chose no..You have been redirected to the main menu..!\n");
                            }
                        }
                    break;
                case 5:
                    Items.display();
                    break;
                case 6:
                    c=false;
                    break;
                default:
                    System.out.println("You entered wrong option...Please make sure you enter the right option next time..!\n");
            }
        }
    }
}
