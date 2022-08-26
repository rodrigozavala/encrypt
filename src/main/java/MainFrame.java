import javax.swing.*;

public class MainFrame {
    public void startApp() {
        JFrame frame=new JFrame("Password Security");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        ButtonAL aPane=new ButtonAL();
        aPane.setOpaque(true);
        frame.setContentPane(aPane);


        frame.pack();
        frame.setVisible(true);


        //ImageIcon imB= createImageIcon("wewe");

    }

}
