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
    private String key;

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
            System.out.println("Encrypt");
            if(originList==null){
                JOptionPane.showMessageDialog(null, "You must select AND show an origin file");
            }else{
                String[] chain=txtAOriginal.getText().split("\n");
                String result="";
                for(String s: chain){
                    if(chain.equals("")){
                        result+="\n";
                    }else{
                        result+=Crypt.encryptText(s,key,"104729",iv)+"\n";
                    }
                }
                txtAEncrypted.setText(result);

                //System.out.println("'"+txtAOriginal.getText()+"' is converted");
                //System.out.println("'"+key+"'");


                //txtAEncrypted.setText(Crypt.encryptText(txtAOriginal.getText(),key,"104729",iv));
            }
        }
    };

    public ActionListener aLDecrypt=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            System.out.println("Decrypt");
            if(originList==null){
                JOptionPane.showMessageDialog(null, "You must select AND show an origin file");
            }else{
                String[] chain=txtAOriginal.getText().split("\n");
                String result="";
                for(String s: chain){
                    if(chain.equals("")){
                        result+="\n";
                    }else{
                        result+=Crypt.decryptText(s,key,"104729",iv)+"\n";
                    }
                }
                txtADecrypted.setText(result);

                //System.out.println("'"+txtAOriginal.getText()+"' is converted");
                //System.out.println("'"+key+"'");

                //txtADecrypted.setText(Crypt.decryptText(txtAOriginal.getText(),key,"104729",iv));
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
        public void actionPerformed(ActionEvent e) {
            if(searchFile(txtEncriptionKey)){
                FileInputStream iS= null;
                BufferedReader dis=null;
                try{
                    iS = new FileInputStream(txtEncriptionKey.getText());
                    dis=new BufferedReader(new InputStreamReader(iS));
                    String[] all= dis.lines().reduce("",(a,b)->a+b).split(",");
                    key=dis.lines().reduce("",(a,b)->a+b);

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
            }
        }};
    ActionListener selectIV= new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            if(searchFile(txtIV)){
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
                txtAOriginal.setText(all);

            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            } finally {
                try {
                    if(iS!=null){
                        iS.close();
                        dis.close();
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        }
    };

    private boolean searchFile(JTextField txt){
        System.out.println("Dialog= "+JFC.getDialogTitle());
        int status=JFC.showOpenDialog(panel1);
        if(status==JFileChooser.APPROVE_OPTION){
            System.out.println("Aprobado");
            String filePath=JFC.getSelectedFile().getPath();
            System.out.println(filePath);
            txt.setText(filePath);
            return true;
        }else if(status==JFileChooser.CANCEL_OPTION){
            System.out.println("Cancelado");
            return false;
        }
        return false;
    }
}
