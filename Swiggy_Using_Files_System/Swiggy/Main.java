package Swiggy;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static Scanner sc=new Scanner(System.in);
    static File f=new File("D:\\Swiggy");
    static String hotelItemsPath="D:\\Swiggy\\HotelItemsData.txt";
    static String hotelRegPath ="D:\\Swiggy\\HotelRegisterData.txt";
    static String hotelPassPath="D:\\Swiggy\\HotelPassword.txt";
    static String hotelMainIdPath="D:\\Swiggy\\HotelMainId.txt";
    static String userMainIdPath="D:\\Swiggy\\UserMainId.txt";
    static String userDetailsPath="D:\\Swiggy\\UserDetails.txt";
    static String userPassPath="D:\\Swiggy\\UserPassword.txt";
    static String userBuyPath="D:\\Swiggy\\UserBuyData.txt";
    static String deliverPassPath="D:\\Swiggy\\DeliverPass.txt";
    static String deliverMainPath="D:\\Swiggy\\DeliverMainId.txt";
    static String deliverDetailsPath="D:\\Swiggy\\DeliverDetails.txt";
    static String billPath="D:\\Swiggy\\bills.txt";
    static String orderIdPath="D:\\Swiggy\\OrderID.txt";
    static String remOrdPath="D:\\Swiggy\\RemainingOrder.txt";

    //Static block for the creation of files in the user system even if the file is not found in the particular folder
    static {
        //All the file paths..!
        File f1 = new File(hotelMainIdPath);
        File f2 = new File(hotelRegPath);
        File f3 = new File(hotelItemsPath);
        File f4 = new File(hotelPassPath);
        File f5 = new File(userDetailsPath);
        File f6 = new File(userPassPath);
        File f7 = new File(userMainIdPath);
        File f8 = new File(userBuyPath);
        File f9 = new File(deliverDetailsPath);
        File f10 = new File(deliverPassPath);
        File f11 = new File(deliverMainPath);
        File f12 = new File(billPath);
        File f13 = new File(orderIdPath);
        File f14 = new File(remOrdPath);
        f.mkdir();
        //if the file not there...it will be created automatically..!
        try {
            f1.createNewFile();
            f2.createNewFile();
            f3.createNewFile();
            f4.createNewFile();
            f5.createNewFile();
            f6.createNewFile();
            f7.createNewFile();
            f8.createNewFile();
            f9.createNewFile();
            f10.createNewFile();
            f11.createNewFile();
            f12.createNewFile();
            f13.createNewFile();
            f14.createNewFile();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        //Files created using this static block
    }

    //Updating HotelPassword.txt file
    private static void HotelPasswordFileUpdate() throws IOException {
        FileWriter fw=new FileWriter(hotelPassPath);
        PrintWriter pw=new PrintWriter(fw);
        pw.printf("%-25s  %-15s\n","ID","Password");
        pw.println();
        for(Map.Entry<String, String> entry:Hotel.hotelPass.entrySet()){
            pw.printf("%-25s  %-15s\n",entry.getKey(),entry.getValue());
        }
        pw.close();
    }
    public static void HotelPass() throws IOException {
        Main.HotelPasswordFileUpdate();
    }
    //Reading all the details from the hotelRegisterData.txt file and updating hashmap values
    private static void HotelPasswordHashUpdate() throws FileNotFoundException {
        FileReader fr=new FileReader(hotelPassPath);
        BufferedReader br=new BufferedReader(fr);
        try {
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
                    Hotel.hotelPass.put(arr.get(0),arr.get(1));
                }
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Updating HotelRegisterData.txt file
    private static void HotelRegisterFileUpdate() throws IOException {
        FileWriter fw=new FileWriter(hotelRegPath);
        PrintWriter pw=new PrintWriter(fw);
        pw.println(Hotel.HotelMainId);
        pw.printf("%-25s  %-30s  %-40s  %-20s  %-15s\n","ID","Name","Address","Contact Number","Availability");
        pw.println();
        for(Map.Entry<String, ArrayList<String>> entry:Hotel.hotelDetails.entrySet()){
           pw.printf("%-25s  %-30s  %-40s  %-20s  %-15s\n",entry.getKey(),Hotel.hotelRegister.get(entry.getKey()),entry.getValue().get(0),entry.getValue().get(1),entry.getValue().get(2));
        }
        pw.close();
    }
    //Reading all the details from the hotelRegisterData.txt file and updating hashmap values
    private static void HotelRegHashUpdate() throws FileNotFoundException {
        FileReader fr=new FileReader(hotelRegPath);
        BufferedReader br=new BufferedReader(fr);
        try {
            try {
                String line1 = br.readLine();
                if (line1.isEmpty()) {
                    Hotel.HotelMainId = 8907;
                } else {
                    Hotel.HotelMainId = Integer.parseInt(line1);
                }
            }
            catch(NullPointerException n){
                Hotel.HotelMainId=8907;
            }
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
                    String avail=arr.get(arr.size()-1);
                    String mobile=arr.get(arr.size()-2);
                    ArrayList<String> temp=new ArrayList<>();
                    StringBuilder address= new StringBuilder();
                    for(int y=2;y<arr.size()-2;y++){
                        address.append(arr.get(y));
                        address.append(" ");
                    }
                    temp.add(address.toString());
                    temp.add(mobile);
                    temp.add(avail);
                    Hotel.hotelRegister.put(arr.get(0),arr.get(1));
                    Hotel.hotelDetails.put(arr.get(0),temp);
                    if(avail.equals("Available")){
                        Hotel.hotelOpen.add(arr.get(0));
                    }
                    else{
                        Hotel.hotelClose.add(arr.get(0));
                    }
                }
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //updating HotelMainId.txt file
    private static void HotelMainFileUpdate() throws IOException {
        FileWriter fw=new FileWriter(hotelMainIdPath);
        PrintWriter pw=new PrintWriter(fw);
        pw.printf("%-25s  %-15s\n","ID","MAIN ID");
        pw.println();
        for(Map.Entry<String,String> entry:Hotel.hotelIdMainId.entrySet()){
            pw.printf("%-25s  %-15s\n",entry.getKey(),entry.getValue());
        }
        pw.close();
    }

    public static void CallHotelMainFileUpdate() throws IOException {
        Main.HotelMainFileUpdate();
    }
    //Reading data from hotelMainId.txt and updating hashmap
    private static void HotelMainHashUpdate() throws IOException {
        FileReader fr = new FileReader(hotelMainIdPath);
        BufferedReader br = new BufferedReader(fr);
        try {
            br.readLine();
            br.readLine();
            String line = br.readLine();
            while (line != null) {
                if (!line.isEmpty()) {
                    String[] a = line.split("\\s");
                    ArrayList<String> arr = new ArrayList<>();
                    for (String i : a) {
                        if (!(i.length() == 0)) {
                            arr.add(i);
                        }
                    }
                    Hotel.hotelIdMainId.put(arr.get(0),arr.get(1));
                }
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //Updating HotelItemsData.txt
    private static void HotelItemsFileUpdate() throws IOException {
        FileWriter fw=new FileWriter(hotelItemsPath);
        PrintWriter pw=new PrintWriter(fw);
        pw.printf("%-20s ITEMS\n","Main ID");
        pw.println();
        for(Map.Entry<String,ArrayList<Map<String,ArrayList<Double>>>> entry:Hotel.itemsRate.entrySet()){
            ArrayList<Map<String,ArrayList<Double>>>  a=entry.getValue();
            for(Map<String,ArrayList<Double>> i :a){
                Map<String,ArrayList<Double>> k=i;
                for(Map.Entry<String,ArrayList<Double>> e:k.entrySet()){
                    pw.printf("%-20s ",entry.getKey());
                    pw.printf("%-15s  ",e.getKey());
                    pw.printf("%-5s  ", e.getValue().get(0));
                    pw.printf("%-5s  ", e.getValue().get(1));
                    pw.println();
                }
            }
        }
        pw.close();
    }
    public static void CallHotelItemFileUpdate() throws IOException {
        Main.HotelItemsFileUpdate();
    }
    //HotelItemsData file reading and updating itemsRate Hashmap
    public static void HotelItemHashUpdate() throws FileNotFoundException {
        FileReader fr=new FileReader(hotelItemsPath);
        BufferedReader br=new BufferedReader(fr);
        try {
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
                    String id=arr.get(0);
                    StringBuilder name1=new StringBuilder();
                    for(int i=1;i<arr.size()-2;i++){
                        name1.append(arr.get(i));
                        name1.append(" ");
                    }
                    String name=name1.toString();
                    double price=Double.parseDouble(arr.get(arr.size()-2));
                    double offer= Double.parseDouble(arr.get(arr.size()-1));
                    ArrayList<Double> h=new ArrayList<>();
                    h.add(price);
                    h.add(offer);
                    Map<String, ArrayList<Double>> d=new HashMap<>();
                    d.put(name,h);
                    ArrayList<Map<String,ArrayList<Double>>> temp=new ArrayList<>();
                    temp.add(d);
                    if(!Hotel.itemsRate.containsKey(arr.get(0))){
                        Hotel.itemsRate.put(id,temp);
                    }
                    else{
                        ArrayList<Map<String, ArrayList<Double>>> p;
                        p=Hotel.itemsRate.get(id);
                        p.add(d);
                        Hotel.itemsRate.put(id,p);
                    }
                }
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Updating Userdata.txt file
    private static void UserPasswordFileUpdate() throws IOException {
        FileWriter fw=new FileWriter(userPassPath);
        PrintWriter pw=new PrintWriter(fw);
        pw.printf("%-25s  %-15s\n","ID","Password");
        pw.println();
        for(Map.Entry<String, String> entry:User.UserPass.entrySet()){
            pw.printf("%-25s  %-15s\n",entry.getKey(),entry.getValue());
        }
        pw.close();
    }
    public static void UserPass() throws IOException {
        Main.UserPasswordFileUpdate();
    }
    //Reading all the details from the Userdata.txt file and updating hashmap values
    private static void UserPasswordHashUpdate() throws FileNotFoundException {
        FileReader fr=new FileReader(userPassPath);
        BufferedReader br=new BufferedReader(fr);
        try {
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
                    User.UserPass.put(arr.get(0),arr.get(1));
                }
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Updating UserDetails.txt file
    private static void UserDetailsFileUpdate() throws IOException {
        FileWriter fw=new FileWriter(userDetailsPath);
        PrintWriter pw=new PrintWriter(fw);
        pw.println(User.UserMainId);
        pw.printf("%-10s  %-30s  %-40s  %-20s\n","ID","Name","Address","Contact Number");
        pw.println();
        for(Map.Entry<String, ArrayList<String>> entry:User.UserDetails.entrySet()){
            pw.printf("%-10s  %-30s  %-40s  %-20s\n",entry.getKey(),User.UserRegister.get(entry.getKey()),entry.getValue().get(0),entry.getValue().get(1));
        }
        pw.close();
    }

    public static void CallUserDetailsFileUpdate() throws IOException {
        UserDetailsFileUpdate();
    }
    //Reading all the details from the UserDetails.txt file and updating hashmap values
    private static void UserDetailsHashUpdate() throws FileNotFoundException {
        FileReader fr=new FileReader(userDetailsPath);
        BufferedReader br=new BufferedReader(fr);
        try {
            try{
            String line1=br.readLine();
            if(line1.isEmpty()){
                User.UserMainId=704;
            }
            else {
                User.UserMainId= Integer.parseInt(line1);
            }}
            catch (NullPointerException n){
                User.UserMainId=704;
            }
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
                    String mobile=arr.get(arr.size()-1);
                    ArrayList<String> temp=new ArrayList<>();
                    StringBuilder address= new StringBuilder();
                    for(int y=2;y<arr.size()-1;y++){
                        address.append(arr.get(y));
                        address.append(" ");
                    }
                    temp.add(address.toString());
                    temp.add(mobile);
                    User.UserRegister.put(arr.get(0),arr.get(1));
                    User.UserDetails.put(arr.get(0),temp);
                }
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //updating userMainId.txt file
    private static void UserMainFileUpdate() throws IOException {
        FileWriter fw=new FileWriter(userMainIdPath);
        PrintWriter pw=new PrintWriter(fw);
        pw.printf("%-25s  %-15s\n","ID","MAIN ID");
        pw.println();
        for(Map.Entry<String,String> entry:User.UserIdMainId.entrySet()){
            pw.printf("%-25s  %-15s\n",entry.getKey(),entry.getValue());
        }
        pw.close();
    }

    public static void CallUserMainFileUpdate() throws IOException {
        Main.UserMainFileUpdate();
    }
    //reading from userMainId.txt file and updating hashmap
    private static void UserMainHashUpdate() throws IOException {
        FileReader fr = new FileReader(userMainIdPath);
        BufferedReader br = new BufferedReader(fr);
        try {
            br.readLine();
            br.readLine();
            String line = br.readLine();
            while (line != null) {
                if (!line.isEmpty()) {
                    String[] a = line.split("\\s");
                    ArrayList<String> arr = new ArrayList<>();
                    for (String i : a) {
                        if (!(i.length() == 0)) {
                            arr.add(i);
                        }
                    }
                    User.UserIdMainId.put(arr.get(0),arr.get(1));
                }
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //updating orderId.txt file
    private static void OrderIdFileUpdate() throws IOException {
        FileWriter fw=new FileWriter(orderIdPath);
        PrintWriter pw=new PrintWriter(fw);
        pw.println(User.orderId);
        pw.printf("%-20s  %-20s  %-20s  %-20s\n","ORDER ID","USER ID","DELIVERY ID","STATUS");
        pw.println();
        for(Map.Entry<String,String> entry:User.orderIDUser.entrySet()){
            pw.printf("%-20s  %-20s  %-20s  %-20s\n",entry.getKey(),entry.getValue(),User.orderIDDelivery.get(entry.getKey()),User.orderStatus.get(entry.getKey()));
        }
        pw.close();
    }
    public static  void CallOrderIDFileUpdate() throws IOException {
        Main.OrderIdFileUpdate();
    }
    //reading orderId.txt file and updating hashmap
    private static void OrderIDHashUpdate() throws IOException {
        FileReader fr = new FileReader(orderIdPath);
        BufferedReader br = new BufferedReader(fr);
        try {
            try {
                String line1 = br.readLine();
                if (line1.isEmpty()) {
                    User.orderId = 563;
                } else {
                    User.orderId = Integer.parseInt(line1);
                }
            }
            catch (NullPointerException n){
                User.orderId = 563;
            }
            br.readLine();
            br.readLine();
            String line = br.readLine();
            while (line != null) {
                if (!line.isEmpty()) {
                    String[] a = line.split("\\s");
                    ArrayList<String> arr = new ArrayList<>();
                    for (String i : a) {
                        if (!(i.length() == 0)) {
                            arr.add(i);
                        }
                    }
                    User.orderIDUser.put(arr.get(0),arr.get(1));
                    User.orderIDDelivery.put(arr.get(0),arr.get(2));
                    User.orderStatus.put(arr.get(0),arr.get(3));
                }
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //for deliveryPass.txt file updation
    private static void DeliverPasswordFileUpdate() throws IOException {
        FileWriter fw=new FileWriter(deliverPassPath);
        PrintWriter pw=new PrintWriter(fw);
        pw.println(Deliver.DeliverMainId);
        pw.printf("%-25s  %-15s\n","ID","Password");
        pw.println();
        for(Map.Entry<String, String> entry:Deliver.deliverPass.entrySet()){
            pw.printf("%-25s  %-15s\n",entry.getKey(),entry.getValue());
        }
        pw.close();
    }
    public static void CallDeliverPass() throws IOException {
        Main.DeliverPasswordFileUpdate();
    }
    //Reading all the details from the deliverPass.txt file and updating hashmap values
    private static void DeliverPasswordHashUpdate() throws FileNotFoundException {
        FileReader fr=new FileReader(deliverPassPath);
        BufferedReader br=new BufferedReader(fr);
        try {
            try {
                String line1 = br.readLine();
                if (line1.isEmpty()) {
                    Deliver.DeliverMainId = 232;
                } else {
                    Deliver.DeliverMainId = Integer.parseInt(line1);
                }
            }
            catch (NullPointerException n){
                Deliver.DeliverMainId=232;
            }
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
                    Deliver.deliverPass.put(arr.get(0),arr.get(1));
                }
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //for deliveryMainFile.txt file update and reading
    private static void DeliverMainFileUpdate() throws IOException {
        FileWriter fw=new FileWriter(deliverMainPath);
        PrintWriter pw=new PrintWriter(fw);
        pw.printf("%-25s  %-15s\n","ID","MAIN ID");
        pw.println();
        for(Map.Entry<String,String> entry:Deliver.deliverIdMainId.entrySet()){
            pw.printf("%-25s  %-15s\n",entry.getKey(),entry.getValue());
        }
        pw.close();
    }

    public static void CallDeliverMainFileUpdate() throws IOException {
        Main.DeliverMainFileUpdate();
    }

    private static void DeliverMainHashUpdate() throws IOException {
        FileReader fr = new FileReader(deliverMainPath);
        BufferedReader br = new BufferedReader(fr);
        try {
            br.readLine();
            br.readLine();
            String line = br.readLine();
            while (line != null) {
                if (!line.isEmpty()) {
                    String[] a = line.split("\\s");
                    ArrayList<String> arr = new ArrayList<>();
                    for (String i : a) {
                        if (!(i.length() == 0)) {
                            arr.add(i);
                        }
                    }
                    Deliver.deliverIdMainId.put(arr.get(0),arr.get(1));
                }
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //updating deliver details file and hashmap
    private static void DeliverDetailsFileUpdate() throws IOException {
        FileWriter fw=new FileWriter(deliverDetailsPath);
        PrintWriter pw=new PrintWriter(fw);
        pw.printf("%-25s  %-15s  %-20s  %-18s  %-15s\n","ID","MAIN ID","NAME","CONTACT NUMBER","AVAILABILITY");
        pw.println();
        for(Map.Entry<String,String> entry:Deliver.deliverIdMainId.entrySet()){
            pw.printf("%-25s  %-15s  %-20s  %-18s  %-15s\n",entry.getKey(),entry.getValue(),Deliver.deliverRegister.get(entry.getValue()),Deliver.deliverContact.get(entry.getValue()),Deliver.deliverAvail.contains(entry.getValue())?"Available":"No");
        }
        pw.close();
    }
    public static void CallDeliverDetailsFileUpdate() throws IOException {
        DeliverDetailsFileUpdate();
    }

    private static void DeliverDetailsHashUpdate() throws IOException {
        FileReader fr = new FileReader(deliverDetailsPath);
        BufferedReader br = new BufferedReader(fr);
        try {
            br.readLine();
            br.readLine();
            String line = br.readLine();
            while (line != null) {
                if (!line.isEmpty()) {
                    String[] a = line.split("\\s");
                    ArrayList<String> arr = new ArrayList<>();
                    for (String i : a) {
                        if (!(i.length() == 0)) {
                            arr.add(i);
                        }
                    }
                    Deliver.deliverIdMainId.put(arr.get(0),arr.get(1));
                    Deliver.deliverContact.put(arr.get(1),arr.get(3));
                    Deliver.deliverRegister.put(arr.get(1),arr.get(2));
                    if(arr.get(arr.size()-1).equals("Available")){
                        Deliver.deliverAvail.add(arr.get(1));
                    }
                    else{
                        Deliver.deliverNot.add(arr.get(1));
                    }
                }
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //updating remainingOrder.txt file update if any order is remaining
    private static void remFileUpdate() throws IOException {
        FileWriter fw=new FileWriter(remOrdPath);
        PrintWriter pw=new PrintWriter(fw);
        pw.printf("%-25s\n","Order id");
        pw.println();
        for(String i:UserLogin.remainingOrder){
            pw.printf("%-25s\n",i);
        }
        pw.close();
    }
    //main function
    public static void main(String[] args) throws IOException {
        //For necessary hashmap updates from the particular files
        HotelRegHashUpdate();
        HotelPasswordHashUpdate();
        HotelItemHashUpdate();
        UserPasswordHashUpdate();
        UserDetailsHashUpdate();
        UserMainHashUpdate();
        HotelMainHashUpdate();
        UserMainHashUpdate();
        OrderIDHashUpdate();
        DeliverMainHashUpdate();
        DeliverPasswordHashUpdate();
        DeliverDetailsHashUpdate();
        // all the data from files has been read for the further process
        System.out.println();
        System.out.println("WELCOME TO SWIGGY🥂....TASTY FOOD AT YOUR DOORSTEP🍽...!");
        boolean c=true;
        while(c) {
            System.out.println("\nEnter your option..");
            System.out.println("\n1.Hotel");
            System.out.println("2.User");
            System.out.println("3.Delivery Person");
            System.out.println("4.Admin");
            System.out.println("5.Exit\n");
            int n = sc.nextInt();
            sc.nextLine();
            switch (n) {
                case 1:
                    //hotel
                    //register
                    //login
                    //add or remove items
                    //update price
                    //update offer
                    Hotel f=new Hotel();
                    f.hotelMethod();
                    HotelRegisterFileUpdate();
                    HotelItemsFileUpdate();
                    break;
                case 2:
                    //user
                    //register
                    //see all the hotels and items in hotels
                    //buy - add to cart
                    //remove from cart
                    //checkout
                    User r=new User();
                    r.UserMethod();
                    UserDetailsFileUpdate();
                    remFileUpdate();
                    break;
                case 3:
                    //delivery person
                    //register
                    //login
                    //delivery person available or not
                    //how many deliveries a person took
                    //deliveries he took and the details
                    Deliver d=new Deliver();
                    d.DeliverMethod();
                    DeliverDetailsFileUpdate();
                    break;
                case 4:
                    //user details
                    //hotel details
                    //delivery person details
                    //order details
                    //remaining orders
                    //allocate order
                    //exit
                    Admin.adminMethod();
                    break;
                case 5:
                    //exit
                    System.out.println("\nTHANK YOU🥰🥰....COME AGAIN FOR MORE TASTY FOODS...😋😉!\n\n");
                    c = false;
                    break;
                default:
                    System.out.println("\nSorry You entered wrong option☹...Please Check and enter correct option...!\n");
                    break;
            }
        }
    }
}
