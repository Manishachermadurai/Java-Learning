package Swiggy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
class Hotel{
    static int HotelMainId;
    static Scanner sc=new Scanner(System.in);
    //hotel id,password
    static Map<String,String> hotelPass=new HashMap<>();
    //hotel id,name
    static Map<String,String> hotelRegister=new HashMap<>();
    static Map<String,String> hotelIdMainId=new HashMap<>();
    //hotel id-address,phone number,available or not
    static Map<String, ArrayList<String>> hotelDetails=new HashMap<>();
    //hotel id-all the items available and the rate and offer available
    static Map<String,ArrayList<Map<String,ArrayList<Double>>>>  itemsRate=new HashMap<>();
    //Open or close hotels
    static ArrayList<String> hotelOpen=new ArrayList<>();
    static ArrayList<String> hotelClose=new ArrayList<>();
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
            if (!hotelPass.containsKey(id)) {
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
                        System.out.println("\nShop Name : ");
                        System.out.println("<< Shop name should be a single word >>\n");
                        String name = sc.nextLine();
                        name = name.replaceAll("\\s", "");
                        System.out.println("\nShop address : ");
                        String address = sc.nextLine();
                        System.out.println("\nShop Contact number : ");
                        String mobile = sc.nextLine();
                        ArrayList<String> temp = new ArrayList<>();
                        temp.add(address);
                        temp.add(mobile);
                        System.out.println("\nOpen\\Close");
                        System.out.println("Enter your option : ");
                        System.out.println("1.Open");
                        System.out.println("2.Close\n");
                        boolean g = true;
                        while (g) {
                            int h = sc.nextInt();
                            sc.nextLine();
                            if (h <= 0 || h > 2) {
                                System.out.println("\nPlease enter a valid option..!\n");
                            } else {
                                String mainId="SWIHOT"+HotelMainId;
                                if (h == 1) {
                                    g = false;
                                    hotelOpen.add(mainId);
                                    temp.add("Available");
                                }
                                if (h == 2) {
                                    g = false;
                                    hotelClose.add(mainId);
                                    temp.add("Close");
                                }
                                HotelMainId++;
                                hotelIdMainId.put(id,mainId);
                                hotelPass.put(id, password);
                                hotelRegister.put(mainId, name);
                                hotelDetails.put(mainId, temp);
                                Main.HotelPass();
                                Main.CallHotelMainFileUpdate();
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
        if(hotelPass.containsKey(id)){
            System.out.println("\nEnter the password : ");
            String password=sc.nextLine();
            if(hotelPass.get(id).equals(password)){
                System.out.println("\nLogged in Successfully😊..!\n");
                HotelLogin.HotelLoginMethod(hotelIdMainId.get(id));
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
        if(hotelPass.containsKey(id)) {
            System.out.println("\nEnter secret key :");
            String sec=sc.nextLine();
            if(sec.equals("SWIHOT")) {
                System.out.println("\nEnter the new password : ");
                String password = sc.nextLine();
                if (PasswordCheck.method(password)) {
                    System.out.println("\nYour Password is valid..!\n");
                    System.out.println("\nPassword changed successfully..!\n");
                    hotelPass.put(id, password);
                    Main.HotelPass();
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

    void hotelMethod() throws IOException {
        System.out.println("\nWELCOME TO THE HOTELS PAGE🏨..!");
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
                Hotel.register();
                break;
            case 2:
                System.out.println();
                Hotel.login();
                break;
            case 3:
                forgotPassword();
                break;
        }
    }

}
