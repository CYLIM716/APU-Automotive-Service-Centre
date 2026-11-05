package Manager;

import java.awt.Color;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewFeedback {
    
    static String path = "database.txt";
    
    public ViewFeedback(){
        JFrame frame = new JFrame("View Comment");
        frame.setSize(600, 500);
        frame.getContentPane().setBackground(Color.gray);
        
        JLabel comment = new JLabel("Customer Feedback");
        String[] column = {"User ID", "Feedback","Date"};
        DefaultTableModel m = new DefaultTableModel(column,0){
            public boolean isCellEditable(int row, int col) {
                return false; // make table read-only
            }
        };
        
        JTable t = new JTable(m);
        
        t.setRowHeight(30);
        t.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        t.getColumnModel().getColumn(0).setPreferredWidth(80);   
        t.getColumnModel().getColumn(1).setPreferredWidth(400);  
        t.getColumnModel().getColumn(2).setPreferredWidth(100);
        
        boolean found = false;
        
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            
            while((line = br.readLine()) != null){
                if (line.equals("[Feedbacks]")){
                    found = true;
                    continue;
                }
                if (found && line.startsWith("[") && !line.equals("[Feedbacks]")){
                    break;
                }
                if (found){
                    String[] data = line.split(",");
                    m.addRow(new Object[]{
                        data[1],data[2],data[3]
                    });
                }
            }
            br.close();

        }catch(Exception e){
            System.out.println("Something wrong");
        }
        
        frame.add(comment, java.awt.BorderLayout.NORTH);
        frame.add(new JScrollPane(t),java.awt.BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
