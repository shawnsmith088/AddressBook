import java.util.Scanner;
import java.sql.*;

public class DatabaseStuff {
    private Connection myConn = null;
    private Statement myStmt = null;
    private ResultSet myRs = null;

    private String dbUrl = "jdbc:mysql://localhost:3306/AddressBook?autoReconnect=true&useSSL=false";
    private String user = "shawn";
    private String pass = "smitty";
    public DatabaseStuff(){
    }

    public void addPerson() throws SQLException {
        System.out.println("Enter name of friend:");
        Scanner input=new Scanner(System.in);
        String name = input.nextLine();

        System.out.println("Enter Address: ");
        String address=input.nextLine();
        System.out.println("Enter City");
        String city=input.nextLine();
        System.out.println("Enter State: ");
        String state=input.nextLine();
        System.out.println("Enter Phone Number: ");
        long number=input.nextLong();
        try{
            myConn=DriverManager.getConnection(dbUrl, user, pass);
            myStmt=myConn.createStatement();
            System.out.println("Inserting friend into book.");
            myStmt.executeUpdate("INSERT INTO addresses " + "(friendnames, phonenumber, address, city, state)"
                    + "values " + "('" + name + "', " + number + ", '" + address + "', '" + city + "', '"+ state + "')");
            myRs = myStmt.executeQuery("select * from addresses order by friendnames");



        }catch (Exception exc) {
            exc.printStackTrace();
        }
        finally {
            if (myRs != null) {
                myRs.close();
            }

            if (myStmt != null) {
                myStmt.close();
            }

            if (myConn != null) {
                myConn.close();
            }
        }
    }
}
