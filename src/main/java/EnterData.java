import jdk.internal.dynalink.support.ClassLoaderGetterContextProvider;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EnterData {
    private JPanel panel1;
    private JTextField txtOriginPath;
    private JTextField txtDestinyPath;
    private JButton btnEncrypt;
    private JTextField txtEncriptionKey;
    private JTextField textField1;
    private JButton btnDecrypt;

    public EnterData(){

        btnEncrypt.addActionListener(aLEncrypt);
        btnDecrypt.addActionListener(aLDecrypt);
    }


    public JPanel getInstance(){
        return panel1;
    }



    public ActionListener aLEncrypt=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Hola");
        }
    };

    public ActionListener aLDecrypt=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Hola");

        }
    };




}
