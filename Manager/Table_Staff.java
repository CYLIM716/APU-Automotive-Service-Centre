package Manager;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Table_Staff {
    
    static String path = "database.txt";
    
    public Table_Staff(){
        
        JFrame frame = new JFrame("Staff");
        frame.setSize(500,800);
        
        JLabel staff = new JLabel("Staff Data");
        
        String[] columnNames = {"ID", "Password","Role","Name"};
        DefaultTableModel m = new DefaultTableModel(columnNames,0){
            public boolean isCellEditable(int row, int col) {
                return false; // make table read-only
            }
        };
        
        JTable t = new JTable(m);
        boolean found = false;
        
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            
            while((line = br.readLine()) != null){
                if (line.equals("[Users]")){
                    found = true;
                    continue;
                }
                if (found && line.startsWith("[") && !line.equals("[Users]")){
                    break;
                }
                if (found){
                    String[] data = line.split(",");
                    m.addRow(data);
                }
            }
        }catch(Exception e){
            System.out.println("Something wrong");
        }
        
        frame.add(staff,java.awt.BorderLayout.NORTH);
        frame.add(new JScrollPane(t),java.awt.BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
