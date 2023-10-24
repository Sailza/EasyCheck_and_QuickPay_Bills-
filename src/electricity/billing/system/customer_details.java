package electricity.billing.system;
import net.proteanit.sql.*;
import net.proteanit.sql.DbUtils;
import net.proteanit.sql.JTableUpdateDb;
import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;

import static net.proteanit.sql.DbUtils.*;

public class customer_details  extends JFrame implements ActionListener {
    JButton search, print, close;
    Choice searchmeterCho,searchNameCho;
    JTable table;
    customer_details(){
        super("Customer Details ");
        getContentPane().setBackground(new Color(192,186,254));
        setSize(700,500);
        setLocation(400,200);
        setLayout(null);
        setVisible(true);

        JLabel searchMeter = new JLabel("Search by Meter Number");
        searchMeter.setBounds(20,20,150,20);
        add(searchMeter);

        searchmeterCho = new Choice();
        searchmeterCho.setBounds(180,20,150,20);
        add(searchmeterCho);

        try{
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from New_Customer");
            while(resultSet.next()){
                searchmeterCho.add(resultSet.getString("meterno"));
            }

        }catch (Exception E){
            E.printStackTrace();
        }

        JLabel searchName  = new JLabel("Search by Name");
        searchName.setBounds(420,20,100,20);
        add(searchName);

        searchNameCho = new Choice();
        searchNameCho.setBounds(525,20,150,20);
        add(searchNameCho);

        try{
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from New_Customer");
            while(resultSet.next()){
                searchNameCho.add(resultSet.getString("name"));
            }

        }catch (Exception E){
            E.printStackTrace();
        }


        table = new JTable();


        try {
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from New_Customer");
            JTable table = new JTable(DbUtils.resultSetToTableModel(resultSet));

            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBounds(0, 100, 700, 500);
            scrollPane.setBackground(Color.WHITE);

            add(scrollPane);



        } catch (Exception e) {
            e.printStackTrace();
        }


           /*JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBounds(0, 100, 700, 500);
            scrollPane.setBackground(Color.WHITE);

            add(scrollPane);*/


           search = new JButton("Search");
            search.setBackground(Color.white);
            search.setBounds(20,70,80,20);
            search.addActionListener(this);
            add(search);

            print = new JButton("Print");
            print.setBackground(Color.white);
            print.setBounds(120,70,80,20);
            print.addActionListener(this);
            add(print);

            close = new JButton("Close");
            close.setBackground(Color.white);
            close.setBounds(600,70,80,20);
            close.addActionListener(this);
            add(close);

            setVisible(true);


        /*} catch (Exception e) {
            e.printStackTrace();
        }*/




    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource() == search){
            String query_search = "select * from New_Customer where meterno = '" +searchmeterCho.getSelectedItem()+ "' and name = '"+searchNameCho.getSelectedItem()+ "'";
            try{
                 database c = new database();
                 ResultSet resultSet = c.statement.executeQuery(query_search);
                 table.setModel(DbUtils.resultSetToTableModel(resultSet));
                 //table.setModel((TableModel) resultSetToNestedList(resultSet));

            }catch(Exception E){
                E.printStackTrace();
            }
        }



        else if (e.getSource()== print)
        {
            try {
                table.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }


        }else{
            setVisible(false);

        }

    }

    public static void main(String[] args) {
        new customer_details();

    }
}
