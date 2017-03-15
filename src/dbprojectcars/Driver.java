
import java.util.*;
import java.text.*;
import java.sql.*;

public class Driver {

    static Connection mycon;
    static Statement dbstate;
    static ResultSet dbrs;

    static final int Companies = 1;
    static final int Dealerships = 2;
    static final int Models = 3;
    static final int Cars = 4;
    static final int Customers = 5;
    static final int DealershipXCar = 6;
    static final int CustomerXCar = 7;
    static final int Employees = 8;
    static final int Sales = 9;

    static final int numcompvars = 1;
    static final int numdealvars = 2;
    static final int nummodvars = 4;
    static final int numcarvars = 9;
    static final int numcustvars = 4;
    static final int numdxcvars = 3;
    static final int numcxcvars = 2;
    static final int numempvars = 5;
    static final int numsalvars = 3;

    public static void main(String[] args) {
        //connectDB();
        printMenu();
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            int selection = 0;
            try {
                selection = scan.nextInt();
            } catch (java.util.InputMismatchException e) {
                scan.next();
            }
            switch (selection) {
                case 1:
                    addManufacturer();
                    break;
                case 2:
                    addDealership();
                    break;
                case 3:
                    addVehicle();
                    break;
                case 4:
                    addEmployee();
                    break;
                case 5:
                    addCustomer();
                    break;
                case 6:
                    //sell();
                    break;
                case 7:
                    //buy();
                    break;
                case 8:
                    addModel();
                    break;
                case 9:
                    getSales();
                    break;
                case 10:
                    //findEmployees();
                    break;
                case 11:
                    System.exit(0);
                    break;
                default:
                    System.out.println();
                    System.out.println("Invalid Entry...Try Again!");
                    break;
            }
            printMenu();
        }
    }

    public static void printMenu() {
        System.out.println();
        System.out.println("MENU: Select Option...");
        System.out.println("====================================================");
        System.out.println("=  Options:                                        =");
        System.out.println("=         1. Add Vehicle Manufacturer to Database  =");
        System.out.println("=         2. Add Dealership Branch                 =");
        System.out.println("=         3. Add Vehicle to Lot                    =");
        System.out.println("=         4. Add Employee to Branch                =");
        System.out.println("=         5. Add Customer                          =");
        System.out.println("=         6. Report Sale                           =");
        System.out.println("=         7. Report Buy Back                       =");
        System.out.println("=         8. Add Vehicle Model to Database         =");
        System.out.println("=         9. Get Sales                             =");
        System.out.println("=        10. Find Employee                         =");
        System.out.println("=        11. Quit                                  =");
        System.out.println("====================================================");
    }

    public static ArrayList<String> getArgs() {
        Scanner scan = new Scanner(System.in);
        ArrayList<String> args = new ArrayList<String>();
        String field = "";
        String input = scan.nextLine();
        scan = new Scanner(input);
        while (scan.hasNext()) {
            String str = scan.next();
            if (str.equals("/")) {
                args.add(field);
                field = "";
            } else {
                if (field.equals("")) {
                    field += str;
                } else {
                    field = field + " " + str;
                }
            }
        }
        args.add(field);
        return args;
    }

    public static int getSalesOpt() {
        Scanner scan = new Scanner(System.in);
        int opt = 0;
        while (opt == 0) {
            //opt = scan.nextInt();
            printSalesMenu();
            try {
                opt = scan.nextInt();
            } catch (java.util.InputMismatchException e) {
                scan.next();
            }
            if (opt > 0 && opt <= 6) {
                return opt;
            } else {
                System.out.println("Invalid option, please enter a valid option");
                opt = 0;
            }
        }
        return 0;
    }

    public static void getByEmpID() {
        Scanner scan = new Scanner(System.in);
        int empid;
        System.out.println("Please enter the Employee ID:");
        empid = scan.nextInt();
        try {
            dbstate = mycon.createStatement();
            dbrs = dbstate.executeQuery("SELECT * FROM Sales WHERE emplid = " + empid);
            //System.out.println("SELECT * FROM Sales WHERE emplid = " + empid);
            while (dbrs.next()) {
                String name = dbrs.getString(1);
                System.out.println(name);
            }
        } catch (Exception e) {
            System.out.println("Error in getByEmpID");
            e.printStackTrace();
        }
    }

    public static void getByVIN() {
        Scanner scan = new Scanner(System.in);
        int vin;
        System.out.println("Please enter the vehicle VIN: ");
        vin = scan.nextInt();
        try {
            dbstate = mycon.createStatement();
            dbrs = dbstate.executeQuery("SELECT * FROM Sales WHERE vin = " + vin);
            while (dbrs.next()) {
                String name = dbrs.getString(1);
                System.out.println(name);
            }
        } catch (Exception e) {
            System.out.println("Error in getByEmpID");
            e.printStackTrace();
        }
    }

    public static void getByDate() {
        Scanner scan = new Scanner(System.in);
        String date;
        System.out.println("Please enter the date in the following format: YYYY-MM-DD");
        date = scan.nextLine();

        try {
            dbstate = mycon.createStatement();
            dbrs = dbstate.executeQuery("SELECT * FROM Sales WHERE date = '" + date + "'");
            //System.out.println("SELECT * FROM Sales WHERE date = '" + date + "'");
            while (dbrs.next()) {
                String name = dbrs.getString(1);
                System.out.println(name);
            }
        } catch (Exception e) {
            System.out.println("Error in getByEmpID");
            e.printStackTrace();
        }
    }
    
    public static void getByDealership() {
        Scanner scan = new Scanner(System.in);
        String company, city;
        System.out.println("Please enter the name of the dealership's company: ");
        company = scan.nextLine();
        System.out.println("Please enter the name of the dealership's city: ");
        city = scan.nextLine();
    }

    public static void getSales() {
        Scanner scan = new Scanner(System.in);
        int opt = 0;

        opt = getSalesOpt();
        switch (opt) {
            case 1:
                getByEmpID();
                return;
            case 2:
                getByVIN();
                return;
            case 3:
                getByDate();
                return;
            case 4:
                //get by dealership
                return;
            case 5:
                //get by manufacturer
                return;
            case 6:
                return;
        }

    }

    public static void printSalesMenu() {
        System.out.println();
        System.out.println("SALES MENU: Select Option...");
        System.out.println("====================================================");
        System.out.println("=  Options:                                        =");
        System.out.println("=         1. Search for sales by Employee ID       =");
        System.out.println("=         2. Search for sales by Vehicle VIN       =");
        System.out.println("=         3. Search for sales by Sale Date         =");
        System.out.println("=         4. Search for sales by Dealership        =");
        System.out.println("=         5. Search for sales by Manufacturerer    =");
        System.out.println("=         6. Back to main menu                     =");
        System.out.println("====================================================");
    }

    public static void addManufacturer() {
        System.out.println("Enter Information exactly in the following format:");
        System.out.println("<Manufacturer Name>");
        ArrayList<String> args = getArgs();
        execInsert(Companies, args);
    }

    public static void addDealership() {
        System.out.println("Enter Information exactly in the following format:");
        System.out.println("<Manufacturer Name> / <Location>");
        ArrayList<String> args = getArgs();
        execInsert(Dealerships, args);
    }

    public static void addVehicle() {
        System.out.println("Enter Information exactly in the following format:");
        System.out.println("<VIN> / <value> / <color> / <Manufacturer> / <model> / <year>");
        ArrayList<String> args = getArgs();
        execInsert(Cars, args);
    }

    public static void addEmployee() {
        System.out.println("Enter Information exactly in the following format:");
        System.out.println("<Name> / <Dealership Branch> / <Position>");
        ArrayList<String> args = getArgs();
        execInsert(Employees, args);
    }

    public static void addCustomer() {
        System.out.println("Enter Information exactly in the following format:");
        System.out.println("<Name> / <Budget> / <Residence>");
        ArrayList<String> args = getArgs();
        execInsert(Customers, args);
    }

    public static void addModel() {
        System.out.println("Enter Information exactly in the following format:");
        System.out.println("<Manufacturer> / <model> / <Minimum Allowed in Stock> / <Maximum Allowed in Stock>");
        ArrayList<String> args = getArgs();
        execInsert(Models, args);
    }

    public static void execQuery() {
        String querystr;
    }

    public static void execInsert(int table, ArrayList<String> values) {
        String insertstr = "";
        int maxarr, i;

        switch (table) {
            case Companies:
                insertstr = "INSERT INTO Companies(name) VALUES (";
                maxarr = numcompvars;
                break;
            case Dealerships:
                insertstr = "INSERT INTO Dealerships VALUES (";
                maxarr = numdealvars;
                break;
            case Models:
                insertstr = "INSERT INTO Models VALUES (";
                maxarr = nummodvars;
                break;
            case Cars:
                insertstr = "INSERT INTO Cars VALUES (";
                maxarr = numcarvars;
                break;
            case Customers:
                insertstr = "INSERT INTO Customers VALUES (";
                maxarr = numcustvars;
                break;
            case DealershipXCar:
                insertstr = "INSERT INTO DealershipXCar VALUES (";
                maxarr = numdxcvars;
                break;
            case CustomerXCar:
                insertstr = "INSERT INTO CustomerXCar VALUES (";
                maxarr = numcxcvars;
                break;
            case Employees:
                insertstr = "INSERT INTO Employees VALUES(";
                maxarr = numempvars;
                break;
            case Sales:
                insertstr = "INSERT INTO Sales VALUES(";
                maxarr = numsalvars;
                break;
            default:
                maxarr = 0;
                break;
        }

        for (i = 0; i < maxarr; i++) {
            boolean isString = false;
            try {
                Double.parseDouble(values.get(i));
            } catch (NumberFormatException e) {
                isString = true;
            }

            if (isString == true) {
                insertstr = insertstr + "'" + values.get(i) + "'";
            } else {
                insertstr += values.get(i);
            }

            if (i != maxarr - 1) {
                insertstr += ", ";
            }
        }

        insertstr += ");";

        System.out.println(insertstr);
        try {
            dbstate.executeUpdate(insertstr);
            dbstate = mycon.createStatement();
        } catch (Exception e) {
            System.out.println("Error in insertion");
            e.printStackTrace();
        }

    }

    public static void execUpdate() {
        String updatestr;
    }

    public static void execDelete(int table, ArrayList<String> values) {
        String deletestr;

    }

    public static void connectDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            mycon = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ProjectDB?user=root&password=iateapoundofchocolate");
            dbstate = mycon.createStatement();
            dbrs = dbstate.executeQuery("SHOW TABLES");
            while (dbrs.next()) {
                String name = dbrs.getString(1);
                System.out.println(name);
            }
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }
}
