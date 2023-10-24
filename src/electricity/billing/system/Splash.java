package electricity.billing.system;
import java.awt.*;
import javax.swing.*;

public class Splash extends JFrame {
    Splash(){
        ImageIcon imageicon = new ImageIcon(ClassLoader.getSystemResource("icon/main.jpg"));
        Image imageOne = imageicon.getImage().getScaledInstance(600,400,Image.SCALE_DEFAULT);
        ImageIcon imageIcon2 = new ImageIcon(imageOne);
        JLabel imageLabel = new JLabel(imageIcon2);
        add(imageLabel);
        setSize(600,400);
        setLocation(500,200);
        setVisible(true);

        try{
            Thread.sleep(3000);
            setVisible(false);

            new Login();

        }
        catch(Exception e ){
            e.printStackTrace();
            //System.out.println(e);

        }

    }
    public static void main(String[] args) {
        new Splash();
    }

}
