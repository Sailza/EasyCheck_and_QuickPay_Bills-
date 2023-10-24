package electricity.billing.system;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class database {
    Connection connection;
    Statement statement;
    database(){

        try {

            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Bill_Calc","root","Saika@28");
            statement = connection.createStatement();


        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
