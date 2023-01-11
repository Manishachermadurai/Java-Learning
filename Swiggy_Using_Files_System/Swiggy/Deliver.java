package Swiggy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Deliver {
    static int DeliverMainId;
    static Scanner sc=new Scanner(System.in);
    //deliver id,password
    static Map<String,String> deliverPass=new HashMap<>();
    //deliver id,name
    static Map<String,String> deliverRegister=new HashMap<>();
    static Map<String,String> deliverIdMainId=new HashMap<>();
    static Map<String,String> deliverContact=new HashMap<>();
    static ArrayList<String> deliverAvail=new ArrayList<>();
    static ArrayList<String> deliverNot=new ArrayList<>();
    private static void register() throws IOException {
        System.out.println("\nEnter the mail id : ");
        String id=sc.nextLine();
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher m=pattern.matcher(id);
        if(!m.matches()){
            System.out.println("\nPlease provide correct mail id..!\n");
        }
        else {
            if (!deliverPass.containsKey(id)) {
                System.out.println("\n\t\t PASSWORD SHOULD SATISFY ALL THE RULES..!");
                System.out.println("\nYour password should contain one capital alphabet and one small alphabet..!");
                System.out.println("Your password should contain one digit and one special character..!");
                System.out.println("Your password should not contain space character..!");
                System.out.println("Your password should be of length 8 - 15..!");
                System.out.println("\nEnter Password : ");
                boolean c = true;
                while (c) {
                    String password = sc.nextLine();
                    if (PasswordCheck.method(password)) {
                        System.out.println("\nYour Password is valid..!\n");
                        System.out.println("\nName : ");
                        System.out.println("<< Name should be a single word >>\n");
                        String name = sc.nextLine();
                        name = name.replaceAll("\\s", "");
                        System.out.println("\nContact number : ");
                        String mobile = sc.nextLine();
                        System.out.println("\nAvailable\\Not Available");
                        System.out.println("Enter your option : ");
                        System.out.println("1.Available");
                        System.out.println("2.Not\n");
                        boolean g = true;
                        while (g) {
                            int h = sc.nextInt();
                            sc.nextLine();
                            if (h <= 0 || h > 2) {
                                System.out.println("\nPlease enter a valid option..!\n");
                            } else {
                                String mainId="SWIDEL"+DeliverMainId;
                                if (h == 1) {
                                    g = false;
                                    deliverAvail.add(mainId);
                                }
                                if (h == 2) {
                                    g = false;
                                    deliverNot.add(mainId);
                                }
                                DeliverMainId++;
                                deliverIdMainId.put(id,mainId);
                                deliverPass.put(id, password);
                                deliverRegister.put(mainId, name);
                                deliverContact.put(mainId,mobile);
                                Main.CallDeliverPass();
                                Main.CallDeliverMainFileUpdate();
                            }
                        }
                        System.out.println("\nYou registered successfully..Now you can log in...!\n");
                        c = false;
                        break;
                    } else {
                        System.out.println("\nEnter the password that satisfies all the rules..!\n");
                    }
                }
            } else {
                System.out.println("\nThe id is already taken..!");
                System.out.println("\nIf you have forgotten your password give forgot password option, once you try to login..!\n");
                System.out.println("Or else if you are a new user please use another id..!\n");
            }
        }
    }
    private static void login() throws IOException {
        System.out.println("\nEnter the mail id : ");
        String id=sc.nextLine();
        if(deliverPass.containsKey(id)){
            System.out.println("\nEnter the password : ");
            String password=sc.nextLine();
            if(deliverPass.get(id).equals(password)){
                System.out.println("\nLogged in Successfully😊..!\n");
                DeliveryLogin.DeliveryLoginMethod(deliverIdMainId.get(id));
            }
            else{
                System.out.println("\nYou entered wrong Password..!\n");
            }
        }
        else{
            System.out.println("\nThere is no such id present..!Enter correct Id..!\n");
        }
    }
    void forgotPassword() throws IOException {
        System.out.println("Enter your mail id : ");
        String id=sc.nextLine();
        if(deliverPass.containsKey(id)) {
            System.out.println("\nEnter secret key :");
            String sec=sc.nextLine();
            if(sec.equals("SWIDEL")) {
                System.out.println("\nEnter the new password : ");
                String password = sc.nextLine();
                if (PasswordCheck.method(password)) {
                    System.out.println("\nYour Password is valid..!\n");
                    System.out.println("\nPassword changed successfully..!\n");
                    deliverPass.put(id, password);
                    Main.CallDeliverPass();
                }
                else {
                    System.out.println("\nEnter the password that satisfies all the rules..!\n");
                }
            }
            else{
                System.out.println("Sorry...You entered wrong Secret key..!\n");
            }
        }
        else{
            System.out.println("\nThere is no such id present..Please enter valid id..!\n");
        }
    }
    void DeliverMethod() throws IOException {
        System.out.println("\nWELCOME TO THE DELIVERY PERSONS PAGE🙋‍..!");
        System.out.println("\n1.Register");
        System.out.println("2.Login");
        System.out.println("3.Forgot Password");
        System.out.println("Any other number for log out..");
        System.out.println("\nEnter your option :");
        int s=sc.nextInt();
        sc.nextLine();
        switch(s){
            case 1:
                System.out.println();
                Deliver.register();
                break;
            case 2:
                System.out.println();
                Deliver.login();
                break;
            case 3:
                forgotPassword();
                break;
        }
    }

}
