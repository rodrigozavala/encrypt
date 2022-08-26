import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

public class ButtonAL extends JPanel {

    private JButton myButton;

    public ButtonAL(){
        //String cad="C:\\Users\\Rodrigo\\Documents\\PLANES_ESTERILES\\Algo de codigo\\JavaProjects\\encrypting cookies\\src\\someTest\\btnImg.jpg";
        /*ImageIcon imB=createImageIcon("someTest/btnImg.jpg");*/
        ImageIcon imB=createImageIcon("btnImg.jpg");
        //ImageIcon imB1=createImageIcon("../btnImg.jpg");//
        //ImageIcon imB2=createImageIcon("src/someTest/btnImg.jpg");//
        //ImageIcon imB3=createImageIcon(cad);
        myButton=new JButton("Let's see what happen");
        myButton.setVerticalTextPosition(AbstractButton.CENTER);
        myButton.setMnemonic(KeyEvent.VK_D);
        myButton.setHorizontalTextPosition(AbstractButton.LEADING);
        myButton.setActionCommand("disable");
        myButton.addActionListener(actionListener);
        myButton.setIcon(imB);


        myButton.setToolTipText("Please press me :D");
        add(myButton);
    }





    ActionListener actionListener=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("disable")) {
                myButton.setEnabled(false);

            }else {
                myButton.setEnabled(true);
            }
        }
    };


    /** Returns an ImageIcon, or null if the path was invalid. */
    protected ImageIcon createImageIcon(String path) {
        ClassLoader classloader = this.getClass().getClassLoader();//Thread.currentThread().getContextClassLoader();
        try {
            Enumeration<URL> s=classloader.getResources("");
            while (s.hasMoreElements()==true) {
                System.out.println(s.nextElement());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        java.net.URL imgURL = classloader.getResource(path); //this.getClass().getResource(path);//getResource(path,button.class);
        System.out.println(classloader.toString());
        if (imgURL != null) {
            System.out.println("we did it");
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
