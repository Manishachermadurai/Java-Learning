package Swiggy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
class User {
    public static Map<String,String> orderStatus=new HashMap<>();
    static int UserMainId;
    static int orderId;
    static Map<String,String> orderIDUser=new HashMap<>();
    static Map<String,String> orderIDDelivery=new HashMap<>();
    static Map<String,String> UserIdMainId=new HashMap<>();
    static Scanner sc=new Scanner(System.in);
    //user id,password
    static Map<String,String> UserPass =new HashMap<>();
    //user id,name
    static Map<String,String> UserRegister =new HashMap<>();
    //user id-address,phone number
    static Map<String, ArrayList<String>> UserDetails =new HashMap<>();
    private static void register() throws IOException {
        System.out.println("\nEnter the mail id : ");
        String id=sc.nextLine();
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher m=pattern.matcher(id);
        if(!m.matches()){
            System.out.println("\nPlease provide correct mail id..!\n");
        }else {
            if (!UserPass.containsKey(id)) {
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
                        System.out.println("\nYour User Name : ");
                        System.out.println("<<User Name should be a single word >>\n");
                        String name = sc.nextLine();
                        name = name.replaceAll("\\s", "");
                        System.out.println("\nAddress : ");
                        String address = sc.nextLine();
                        System.out.println("\nContact number : ");
                        String mobile = sc.nextLine();
                        String mainId="SWIUSE"+UserMainId;
                        ArrayList<String> temp = new ArrayList<>();
                        temp.add(address);
                        temp.add(mobile);
                        UserMainId++;
                        UserPass.put(id, password);
                        UserRegister.put(mainId, name);
                        UserDetails.put(mainId, temp);
                        UserIdMainId.put(id,mainId);
                        Main.UserPass();
                        Main.CallUserMainFileUpdate();
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
        if(UserPass.containsKey(id)){
            System.out.println("\nEnter the password : ");
            String password=sc.nextLine();
            if(UserPass.get(id).equals(password)){
                System.out.println("\nLogged in Successfully😊..!\n");
                UserLogin.UserLoginMethod(UserIdMainId.get(id));
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
        if(UserPass.containsKey(id)) {
            System.out.println("\nEnter secret key :");
            String sec=sc.nextLine();
            if(sec.equals("SWIUSE")) {
                System.out.println("\nEnter the new password : ");
                String password = sc.nextLine();
                if (PasswordCheck.method(password)) {
                    System.out.println("\nYour Password is valid..!\n");
                    System.out.println("\nPassword changed successfully..!\n");
                    UserPass.put(id, password);
                    Main.UserPass();
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
    void UserMethod() throws IOException {
        System.out.println("\nWELCOME TO THE USERS PAGE☺😃..!\n");
        System.out.println("\n1.Register");
        System.out.println("2.Login");
        System.out.println("3.Forgot Password");
        System.out.println("Any other number for log out..");
        System.out.println("Enter your option :\n");
        int s=sc.nextInt();
        sc.nextLine();
        switch(s){
            case 1:
                System.out.println();
                User.register();
                break;
            case 2:
                System.out.println();
                User.login();
                break;
            case 3:
                forgotPassword();
                break;
            default:
                System.out.println("\nYou entered wrong option...!\n");
        }
    }

}
