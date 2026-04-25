import java.util.*;

public class StudentManagement {

    // ── Student data stored in parallel arrays ──   
    static int[]    ids     = new int[100];
    static String[] names   = new String[100];
    static int[]    ages    = new int[100];
    static String[] courses = new String[100];
    static double[] gpas    = new double[100];
    static String[] emails  = new String[100];
    static int count = 0;
    static int nextId = 1;

    static Scanner sc = new Scanner(System.in);

    // ── ANSI Colors ──  
    static final String RESET  = "\u001B[0m";
    static final String BOLD   = "\u001B[1m";
    static final String CYAN   = "\u001B[36m";
    static final String GREEN  = "\u001B[32m";
    static final String YELLOW = "\u001B[33m";
    static final String RED    = "\u001B[31m";

    // ─────────────────────────────────────────
    //  MAIN...
    // ─────────────────────────────────────────
    public static void main(String[] args) {
        seedData();
        int choice;
        do {
            printMenu();
            choice = readInt("Enter choice");
            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewAll();
                case 3 -> searchByName();
                case 4 -> searchByCourse();
                case 5 -> updateStudent();
                case 6 -> deleteStudent();
                case 7 -> statistics();
                case 0 -> System.out.println(CYAN + "\nGoodbye! See you soon.\n" + RESET);
                default -> System.out.println(RED + "Invalid choice. Try 0-7." + RESET);
            }
        } while (choice != 0);
    }

    // ─────────────────────────────────────────
    //  SEED SAMPLE DATA///
    // ─────────────────────────────────────────
    static void seedData() {
        addRecord("Aarav Sharma",  20, "Computer Science",  8.7, "aarav@email.com");
        addRecord("Priya Patel",   22, "Electronics Eng.",  7.9, "priya@email.com");
        addRecord("Rahul Verma",   21, "Mechanical Eng.",   6.5, "rahul@email.com");
        addRecord("Sneha Gupta",   19, "Computer Science",  9.1, "sneha@email.com");
        addRecord("Karan Mehta",   23, "Civil Engineering", 7.2, "karan@email.com");
    }

    static void addRecord(String name, int age, String course, double gpa, String email) {
        ids[count]     = nextId++;
        names[count]   = name;
        ages[count]    = age;
        courses[count] = course;
        gpas[count]    = gpa;
        emails[count]  = email;
        count++;
    }

    // ─────────────────────────────────────────
    //  MENU
    // ─────────────────────────────────────────
    static void printMenu() {
        System.out.println(CYAN + BOLD);
        System.out.println("╔══════════════════════════════════╗");
        System.out.println("║   STUDENT MANAGEMENT SYSTEM      ║");
        System.out.println("╠══════════════════════════════════╣");
        System.out.println("║  [1] Add Student                 ║");
        System.out.println("║  [2] View All Students           ║");
        System.out.println("║  [3] Search by Name              ║");
        System.out.println("║  [4] Search by Course            ║");
        System.out.println("║  [5] Update Student              ║");
        System.out.println("║  [6] Delete Student              ║");
        System.out.println("║  [7] Statistics                  ║");
        System.out.println("║  [0] Exit                        ║");
        System.out.println("╚══════════════════════════════════╝");
        System.out.print(RESET);
    }

    // ─────────────────────────────────────────
    //  1. ADD//
    // ─────────────────────────────────────────
    static void addStudent() {
        if (count >= 100) {
            System.out.println(RED + "Storage full!" + RESET);
            return;
        }
        System.out.println(BOLD + "\n--- Add New Student ---" + RESET);
        System.out.print("Name   : "); String name   = sc.nextLine().trim();
        int age    = readInt("Age");
        System.out.print("Course : "); String course = sc.nextLine().trim();
        double gpa = readDouble("GPA (0-10)");
        System.out.print("Email  : "); String email  = sc.nextLine().trim();

        // Basic validation //
        if (name.isEmpty())        { System.out.println(RED + "Name cannot be empty." + RESET); return; }
        if (age < 15 || age > 100) { System.out.println(RED + "Age must be 15-100."   + RESET); return; }
        if (gpa < 0 || gpa > 10)  { System.out.println(RED + "GPA must be 0-10."      + RESET); return; }
        if (!email.contains("@")) { System.out.println(RED + "Invalid email."          + RESET); return; }

        addRecord(name, age, course, gpa, email);
        System.out.println(GREEN + "\nStudent added! ID = " + (nextId - 1) + RESET);
    }

    // ─────────────────────────────────────────
    //  2. VIEW ALL  
    // ─────────────────────────────────────────
    static void viewAll() {
        System.out.println(BOLD + "\n--- All Students ---" + RESET);
        if (count == 0) { System.out.println(YELLOW + "No students found." + RESET); return; }
        printTableHeader();
        for (int i = 0; i < count; i++) printRow(i);
        printTableFooter();
    }

    // ─────────────────────────────────────────
    //  3. SEARCH BY NAME///
    // ─────────────────────────────────────────
    static void searchByName() {
        System.out.println(BOLD + "\n--- Search by Name ---" + RESET);
        System.out.print("Enter name: "); String query = sc.nextLine().trim().toLowerCase();
        boolean found = false;
        printTableHeader();
        for (int i = 0; i < count; i++) {
            if (names[i].toLowerCase().contains(query)) {
                printRow(i);
                found = true;
            }
        }
        printTableFooter();
        if (!found) System.out.println(YELLOW + "No match found for \"" + query + "\"." + RESET);
    }

// develope by piyushSoni
    // ─────────────────────────────────────────
    //  4. SEARCH BY COURSE.
    // ─────────────────────────────────────────
    static void searchByCourse() {
        System.out.println(BOLD + "\n--- Search by Course ---" + RESET);
        System.out.print("Enter course: "); String query = sc.nextLine().trim().toLowerCase();
        boolean found = false;
        printTableHeader();
        for (int i = 0; i < count; i++) {
            if (courses[i].toLowerCase().contains(query)) {
                printRow(i);
                found = true;
            }
        }
        printTableFooter();
        if (!found) System.out.println(YELLOW + "No students in \"" + query + "\"." + RESET);
    }

    // ─────────────────────────────────────────
    //  5. UPDATE
    // ─────────────────────────────────────────
    static void updateStudent() {
        System.out.println(BOLD + "\n--- Update Student ---" + RESET);
        int id = readInt("Enter Student ID");
        int idx = findIndex(id);
        if (idx == -1) { System.out.println(RED + "Student ID " + id + " not found." + RESET); return; }

        System.out.println(YELLOW + "(Press Enter to keep current value)" + RESET);

        System.out.print("Name   [" + names[idx]   + "]: "); String name = sc.nextLine().trim();
        System.out.print("Age    [" + ages[idx]    + "]: "); String ageS = sc.nextLine().trim();
        System.out.print("Course [" + courses[idx] + "]: "); String course = sc.nextLine().trim();
        System.out.print("GPA    [" + gpas[idx]    + "]: "); String gpaS = sc.nextLine().trim();
        System.out.print("Email  [" + emails[idx]  + "]: "); String email = sc.nextLine().trim();

        if (!name.isEmpty())   names[idx]   = name;
        if (!ageS.isEmpty())   ages[idx]    = Integer.parseInt(ageS);
        if (!course.isEmpty()) courses[idx] = course;
        if (!gpaS.isEmpty())   gpas[idx]    = Double.parseDouble(gpaS);
        if (!email.isEmpty())  emails[idx]  = email;

        System.out.println(GREEN + "\nStudent ID " + id + " updated successfully." + RESET);
    }
// piyushSoni
    // ─────────────────────────────────────────
    //  6. DELETE 
    // ─────────────────────────────────────────
    static void deleteStudent() {
        System.out.println(BOLD + "\n--- Delete Student ---" + RESET);
        int id = readInt("Enter Student ID");
        int idx = findIndex(id);
        if (idx == -1) { System.out.println(RED + "Student ID " + id + " not found." + RESET); return; }

        System.out.print(YELLOW + "Delete \"" + names[idx] + "\"? (y/n): " + RESET);
        String confirm = sc.nextLine().trim();
        if (!confirm.equalsIgnoreCase("y")) { System.out.println("Cancelled."); return; }

        // Shift array left
        for (int i = idx; i < count - 1; i++) {
            ids[i]     = ids[i + 1];
            names[i]   = names[i + 1];
            ages[i]    = ages[i + 1];
            courses[i] = courses[i + 1];
            gpas[i]    = gpas[i + 1];
            emails[i]  = emails[i + 1];
        }
        count--;
        System.out.println(GREEN + "\nStudent deleted." + RESET);
    }

    // ─────────────────────────────────────────
    //  7. STATISTICS
    // ─────────────────────────────────────────
    static void statistics() {
        System.out.println(BOLD + "\n--- Statistics ---" + RESET);
        if (count == 0) { System.out.println(YELLOW + "No data." + RESET); return; }

        double total = 0, maxGpa = -1, minGpa = 11;
        String topName = "", lowName = "";
        for (int i = 0; i < count; i++) {
            total += gpas[i];
            if (gpas[i] > maxGpa) { maxGpa = gpas[i]; topName = names[i]; }
            if (gpas[i] < minGpa) { minGpa = gpas[i]; lowName = names[i]; }
        }
        double avg = total / count;

        System.out.printf("  Total Students : %d%n", count);
        System.out.printf("  Average GPA    : %.2f%n", avg);
        System.out.printf("  Top Student    : %s (%.1f)%n", topName, maxGpa);
        System.out.printf("  Lowest GPA     : %s (%.1f)%n%n", lowName, minGpa);

        // Course breakdown
        System.out.println(BOLD + "  Students per Course:" + RESET);
        ArrayList<String> seen = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            if (!seen.contains(courses[i])) {
                seen.add(courses[i]);
                int c = 0;
                for (int j = 0; j < count; j++)
                    if (courses[j].equals(courses[i])) c++;
                String bar = "█".repeat(c * 3);
                System.out.printf("  %-22s %s%s%s %d%n",
                    courses[i], CYAN, bar, RESET, c);
            }
        }
        System.out.println();
    }
// develope by piyushsoni
    // ─────────────────────────────────────────
    //  HELPERS
    // ─────────────────────────────────────────
    static void printTableHeader() {
        String line = "+------+----------------------+-----+----------------------+------+---------------------------+";
        System.out.println(CYAN + line);
        System.out.println("| ID   | Name                 | Age | Course               | GPA  | Email                     |");
        System.out.println(line + RESET);
    }

    static void printRow(int i) {
        String gpaColor = gpas[i] >= 8.5 ? GREEN : gpas[i] >= 6.0 ? YELLOW : RED;
        System.out.printf("| %-4d | %-20s | %-3d | %-20s | %s%-4.1f%s | %-25s |%n",
            ids[i], names[i], ages[i], courses[i], gpaColor, gpas[i], RESET, emails[i]);
    }

    static void printTableFooter() {
        System.out.println(CYAN + "+------+----------------------+-----+----------------------+------+---------------------------+" + RESET);
    }

    static int findIndex(int id) {
        for (int i = 0; i < count; i++)
            if (ids[i] == id) return i;
        return -1;
    }

    static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt + ": ");
            try { int v = Integer.parseInt(sc.nextLine().trim()); return v; }
            catch (NumberFormatException e) { System.out.println(RED + "Enter a valid number." + RESET); }
        }
    }

    static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt + ": ");
            try { double v = Double.parseDouble(sc.nextLine().trim()); return v; }
            catch (NumberFormatException e) { System.out.println(RED + "Enter a valid number." + RESET); }
        }
    }
}
