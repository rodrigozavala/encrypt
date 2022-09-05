import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.FileSystemException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;


public class EnterData {
    private JPanel panel1;
    private JTextField txtOriginPath;
    private JTextField txtDestinyPath;
    private JButton btnEncrypt;
    private JTextField txtEncriptionKey;
    private JButton btnDecrypt;
    private JButton btnSearchOrigin;
    private JButton btnSearchDestiny;
    private JButton btnSaveInDestiny;
    private JButton btnSelectKey;
    private JTextField txtIV;
    private JButton btnSelectIV;
    private JTextArea txtAOriginal;
    private JTextArea txtAEncrypted;
    private JTextArea txtADecrypted;
    private JButton btnShow;
    private JFileChooser JFC ;

    private ArrayList<String> originList;
    private byte[] iv;

    public EnterData(){
        JFC=new JFileChooser();

        btnEncrypt.addActionListener(aLEncrypt);
        btnDecrypt.addActionListener(aLDecrypt);

        btnSearchOrigin.addActionListener(searchOrigin);
        btnSearchDestiny.addActionListener(searchDestiny);

        btnSelectIV.addActionListener(selectIV);
        btnSelectKey.addActionListener(selectKey);

        btnSaveInDestiny.addActionListener(saveInDestiny);

        btnShow.addActionListener(showOriginFile);


    }


    public JPanel getInstance(){
        return panel1;
    }



    public ActionListener aLEncrypt=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Hola");
            if(originList==null){
                JOptionPane.showMessageDialog(null, "You must select AND show an origin file");
            }else{
                originList.stream();

            }
        }
    };

    public ActionListener aLDecrypt=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            System.out.println("Hola");
            if(originList==null){
                JOptionPane.showMessageDialog(null, "You must select AND show an origin file");
            }else{

            }
        }
    };

    public ActionListener searchOrigin=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {searchFile(txtOriginPath);}};

    public ActionListener searchDestiny=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //JFileChooser JFC =new JFileChooser();
            //searchFile(txtDestinyPath);
            System.out.println("Dialog= "+JFC.getDialogTitle());
            int status=JFC.showSaveDialog(panel1);
            if(status==JFileChooser.APPROVE_OPTION){
                System.out.println("Aprobado");
                String filePath=JFC.getSelectedFile().getPath();
                System.out.println(filePath);
                txtDestinyPath.setText(filePath);
            }else if(status==JFileChooser.CANCEL_OPTION){
                System.out.println("Cancelado");
            }
        }
    };


    ActionListener selectKey=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {searchFile(txtEncriptionKey);}};
    ActionListener selectIV= new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            searchFile(txtIV);
            FileInputStream iS= null;
            BufferedReader dis=null;

            try{
                iS = new FileInputStream(txtIV.getText());
                dis=new BufferedReader(new InputStreamReader(iS));
                //originList=(ArrayList<String>) dis.lines().collect(Collectors.toList());
                String[] all= dis.lines().reduce("",(a,b)->a+b).split(",");
                iv=new byte[all.length];
                for(int i=0;i< all.length;i++){
                    iv[i]=Byte.valueOf(all[i]);
                }
                /*int count=0;
                for(byte b:iv){
                    System.out.println(" "+String.valueOf(count++)+b);
                }*/


            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }finally {
                if(iS!=null){
                    try {
                        iS.close();
                        dis.close();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }

        }};

    ActionListener saveInDestiny=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    };

    ActionListener showOriginFile=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            FileInputStream iS= null;
            BufferedReader dis=null;
            try {
                iS = new FileInputStream(txtOriginPath.getText());
                dis=new BufferedReader(new InputStreamReader(iS));
                originList=(ArrayList<String>) dis.lines().collect(Collectors.toList());
                String all=originList.stream().reduce("",(a,b)->a+=b+"\n");
                //sb.append(dis.readLine());
                txtAOriginal.setText(all);
                //DataInputStream dis=new DataInputStream(iS);

            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            } finally {
                try {
                    if(dis!=null){
                        dis.close();
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        }
    };

    private void searchFile(JTextField txt){
        System.out.println("Dialog= "+JFC.getDialogTitle());
        int status=JFC.showOpenDialog(panel1);
        if(status==JFileChooser.APPROVE_OPTION){
            System.out.println("Aprobado");
            String filePath=JFC.getSelectedFile().getPath();
            System.out.println(filePath);
            txt.setText(filePath);
        }else if(status==JFileChooser.CANCEL_OPTION){
            System.out.println("Cancelado");
        }
    }
}
