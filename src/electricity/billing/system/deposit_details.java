package electricity.billing.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.ResultSet;

public class deposit_details extends JFrame implements ActionListener {

    JButton search, print, close;
    Choice searchmeterCho,searchNameCho, searchMonthCho;
    JTable table;
    deposit_details(){
        super("Deposit Details ");
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
            ResultSet resultSet = c.statement.executeQuery("select * from bill");
            while(resultSet.next()){
                searchmeterCho.add(resultSet.getString("meter_num"));
            }

        }catch (Exception E){
            E.printStackTrace();
        }

        JLabel searchMonth  = new JLabel("Search by Month");
        searchMonth.setBounds(420,20,100,20);
        add(searchMonth);

        searchMonthCho = new Choice();
        searchMonthCho.add("January");
        searchMonthCho.add("February");
        searchMonthCho.add("March");
        searchMonthCho.add("April");
        searchMonthCho.add("May");
        searchMonthCho.add("June");
        searchMonthCho.add("July");
        searchMonthCho.add("August");
        searchMonthCho.add("September");
        searchMonthCho.add("October");
        searchMonthCho.add("November");
        searchMonthCho.add("December");
        searchMonthCho.setBounds(525,20,150,20);
        add(searchMonthCho);



        table = new JTable();


        try {
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from bill");
            JTable table = new JTable(DbUtils.resultSetToTableModel(resultSet));

            /*JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBounds(0, 100, 700, 500);
            scrollPane.setBackground(Color.WHITE);

            add(scrollPane);*/

        } catch (Exception e) {
            e.printStackTrace();
        }


        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 100, 700, 500);
        scrollPane.setBackground(Color.WHITE);

        add(scrollPane);


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
            String query_search = "select * from bill where meter_num = '" +searchmeterCho.getSelectedItem()+ "' and month = '"+searchMonthCho.getSelectedItem()+ "'";
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
;

    public static void main(String[] args) {
        new deposit_details();

    }
}
