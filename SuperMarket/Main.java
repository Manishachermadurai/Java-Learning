package SuperMarket;
import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
public class Main {
    static String path="D:\\DayEnd.txt";
    private static void stockUpdate() throws FileNotFoundException {
        FileReader fr=new FileReader(path);
        BufferedReader br=new BufferedReader(fr);
        try {
             br.readLine();
             br.readLine();
            String line=br.readLine();
            while (line != null) {
                if(!line.isEmpty()) {
                    String[] a = line.split("\\s");
                    ArrayList<String> arr = new ArrayList<>();
                    for (String i : a) {
                        if (!(i.length() == 0)) {
                            arr.add(i);
                        }
                    }
                    Items.items.put(arr.get(0), Double.valueOf(arr.get(1)));
                    Items.nameQuantity.put(arr.get(0), Integer.valueOf(arr.get(2)));
                }
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void FileUpdate() throws IOException {
        FileWriter fw=new FileWriter(path);
        PrintWriter pw=new PrintWriter(fw);
        pw.printf("%-15s %-16s %-15s\n","Name","Price","Quantity");
        pw.println();
        for(Map.Entry<String,Double> entry:Items.items.entrySet()){
            pw.printf("%-15s %-16s %-15s\n",entry.getKey(),entry.getValue(),Items.nameQuantity.get(entry.getKey()));
        }
        pw.close();
    }
    static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) throws IOException {
            File n1=new File("D:\\DayEnd.txt");
            if(!n1.exists()){
                n1.createNewFile();
            }
            File n2=new File("D:\\DayStart.txt");
            if(!n2.exists()){
                n2.createNewFile();
            }
            File n3=new File("D:\\bills.txt");
            if(!n3.exists()){
                n3.createNewFile();
            }
        File f1=new File("D:\\DayEnd.txt");
        File f2=new File("D:\\DayStart.txt");
        try (FileInputStream in = new FileInputStream(f1); FileOutputStream out = new FileOutputStream(f2)) {
            int n;
            while ((n = in.read()) != -1) {
                out.write(n);
            }
        }
        System.out.println();
        stockUpdate();
        System.out.println("Enter the option whether you are the user or admin : ");
        boolean c=true;
        while(c) {
            System.out.println("1.Admin");
            System.out.println("2.User");
            System.out.println("3.Exit\n");
            int n=sc.nextInt();
            sc.nextLine();
            switch(n){
                case 1:
                    Admin a=new Admin();
                    a.adminMethod();
                    FileUpdate();
                    break;
                case 2:
                    User u=new User();
                    u.userMethod();
                    break;
                case 3:
                    FileUpdate();
                    c=false;
                    break;
                default:
                    System.out.println("You entered wrong option...Please make sure you enter the right option next time..!");
            }
        }

    }
}
