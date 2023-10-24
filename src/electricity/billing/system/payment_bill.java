package electricity.billing.system;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class payment_bill extends JFrame implements ActionListener {
    JButton back;
    String meter;
    payment_bill(String meter){
        this.meter = meter;
        JEditorPane j = new JEditorPane();
        j.setEditable(false);

        try{
            j.setPage("https://paytm.com/online-payments");;
           // j.setPage("https://www.doxo.com/info/toms-electric-mt?campaign_code=bingads&msclkid=4e3420365f321b4ac48745009ffc1d70&utm_source=bing&utm_medium=cpc&utm_campaign=Geo%20Target%20State%20-%20MT&utm_term=toms%20electric%20payment&utm_content=toms-electric-mt");
            j.setBounds(400,150,800,600);
        }catch (Exception E){
            E.printStackTrace();
            j.setContentType("text/html");
            j.setText("<html>Errorr! Errorr!  Errorr!  Errorr!  Errorr!  Errorr! </html>");
        }

        JScrollPane pane = new JScrollPane(j);
        add(pane);

        back = new JButton("Back");
        back.setBounds(640,20,80,30);
        back.addActionListener(this);
        j.add(back);


        setSize(800,600);
        setLocation(400,150);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new pay_bill(meter);
    }

    public static void main(String[] args) {
        new payment_bill("");
    }
}

