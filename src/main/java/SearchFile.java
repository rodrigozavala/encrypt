import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class SearchFile {
    private JPanel panel1;
    private JFileChooser JFC;


    public SearchFile(){
        JFC.addActionListener(ac);
        Arrays.stream(JFC.getComponents()).forEach(s->System.out.println(s));

        int len=0;
        for(int j=0;j<JFC.getComponents().length;j++){
            System.out.println((j+1)+")   :\n");
            len=JFC.getComponent(j).getAccessibleContext().getAccessibleChildrenCount();
            System.out.println("With components:");
            for(int i=0;i<len;i++){
                System.out.println(JFC.getComponent(j).getAccessibleContext().getAccessibleChild(i));
            }
            System.out.println("\n\n");
        }
    }

    public JPanel getInstance(){
        return panel1;
    }
    private JComponent [] searchButton(JPanel jp){
        for (int i=0;i<jp.getComponentCount();i++){
            System.out.print("");
        }
        return null;
    }

    ActionListener ac=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //System.out.println("Hola jajajaja");
            int status=JFC.showOpenDialog(null);
            if(status==JFileChooser.APPROVE_OPTION){
                System.out.println("Aprobado");
            }else if(status==JFileChooser.CANCEL_OPTION){
                System.out.println("Cancelado");
            }

        }
    };

}
