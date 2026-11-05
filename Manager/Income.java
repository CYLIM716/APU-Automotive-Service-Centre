package Manager;

import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Income {
    
    static String path = "database.txt";
    
    public Income(){
        JFrame frame = new JFrame("Table of Service"); 
        frame.setSize(500,400);
        
        JLabel price = new JLabel("Total Revenue");
        JLabel l = new JLabel("");
        
        String[] columnNames = {"Service Types", "Price","Payment Method"};
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
                if (line.equals("[CounterStaffPayments]")){
                    found = true;
                    continue;
                }
                if (found && line.startsWith("[") && !line.equals("[CounterStaffPayments]")){
                    break;
                }
                if (found){
                    String[] data = line.split(",");
                    m.addRow(new Object[]{
                        data[5],data[2],data[3]
                    });
                }
            } 
            l.setText(calcTotalRevenue());
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        frame.add(price,java.awt.BorderLayout.NORTH);
        frame.add(new JScrollPane(t),java.awt.BorderLayout.CENTER);
        frame.add(l,java.awt.BorderLayout.SOUTH);
        frame.setVisible(true);
    
    }
    
    public static String calcTotalRevenue(){
        double total = 0;
        double normal = 0;
        double major = 0;
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            
            boolean found = false;
            while((line = br.readLine()) != null){
                if (line.trim().equals("[CounterStaffPayments]")){
                    found = true;
                    continue;
                }
                if (found && line.trim().startsWith("[") && !line.trim().equals("[CounterStaffPayments]")){
                    break;
                }
                if (found){
                    String[] data = line.split(",");
                    
                    if (data[5].equals("Normal Service")){
                        double nor_amount = Double.parseDouble(data[2]);
                        normal += nor_amount;                       
                    }
                    else if (data[5].equals("Major Service")){
                        double maj_amount = Double.parseDouble(data[2]);
                        major += maj_amount; 
                    }
                    
                    double amount = Double.parseDouble(data[2]);
                    total += amount;
                }
            }
            br.close();
        }catch (Exception e){
            System.out.println("Something wrong");
        }
        
        return "<html>Normal Service: RM " + normal +
           "<br>Major Service: RM " + major +
           "<br>--------------------------------------" +
           "<br>Total Revenue: RM " + total + "</html>";
    }
}
    



