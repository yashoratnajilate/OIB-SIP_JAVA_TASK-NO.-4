import java.io.*;
import java.util.*;

class OnlineExam {

    static Scanner sc = new Scanner(System.in);
    static long examTime = 60 * 1000; // 1 minute

    // LOGIN
    static boolean login() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("users.txt"));
        System.out.print("Username: ");
        String user = sc.nextLine();
        System.out.print("Password: ");
        String pass = sc.nextLine();

        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            if (data[0].equals(user) && data[1].equals(pass)) {
                br.close();
                return true;
            }
        }
        br.close();
        return false;
    }

    // EXAM
    static void startExam() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("questions.txt"));
        FileWriter fw = new FileWriter("answers.txt");
        String line;

        long startTime = System.currentTimeMillis();

        while ((line = br.readLine()) != null) {
            if (System.currentTimeMillis() - startTime > examTime) {
                System.out.println("\nTime Over! Auto Submitting...");
                break;
            }

            String[] q = line.split("\\|");
            System.out.println("\nQ" + q[0] + ": " + q[1]);
            System.out.println("1. " + q[2]);
            System.out.println("2. " + q[3]);
            System.out.println("3. " + q[4]);
            System.out.println("4. " + q[5]);

            System.out.print("Your Answer: ");
            int ans = sc.nextInt();
            sc.nextLine();

            fw.write(q[0] + "," + ans + "\n");
        }

        fw.close();
        br.close();
        System.out.println("\nExam Submitted Successfully!");
    }

    // MAIN
    public static void main(String[] args) {
        try {
            System.out.println("=== Online Examination System ===");

            if (!login()) {
                System.out.println("Invalid Login!");
                return;
            }

            System.out.println("Login Successful!");
            System.out.println("Exam Started...");
            startExam();

            System.out.println("Logged Out Successfully");

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}