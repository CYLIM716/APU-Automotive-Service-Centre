package Manager;

import static Manager.Manage.path;
import java.awt.Color;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;


public class SetPrice {
    private double normalPrice;
    private double majorPrice;
    private double currentNorPrice;
    private double currentMajPrice;
    static String path = "database.txt";
    
        
    public SetPrice(double nPrice,double mPrice){
        normalPrice = nPrice;
        majorPrice = mPrice;
    }

    public SetPrice(){
        JFrame f = new JFrame("Set Price");
        f.setSize(600,500);
        f.setLayout(null);
        f.getContentPane().setBackground(Color.gray);
        
        JLabel label = new JLabel("Set Price");
        label.setBounds(150,20,200,50);
        
        JButton normal = new JButton("Set Normal Service Price");
        JButton major = new JButton("Set Major Service Price");
        JButton checkprice = new JButton("Check price");

        normal.setBounds(150,100,300,50);
        major.setBounds(150,180,300,50);
        checkprice.setBounds(150,260,300,50);
        
        normal.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                norService();
            }       
        });
        
        major.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                majService();
            }       
        });
        
        checkprice.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new CheckPrice();
            }
        });
        
        f.add(label);
        f.add(normal);
        f.add(major);
        f.add(checkprice);
        f.setVisible(true);
        f.setDefaultCloseOperation(f.DISPOSE_ON_CLOSE);
    }
    
    public void norService(){
        JFrame f = new JFrame("Normal Service");
        f.setSize(600,500);
        f.setLayout(null);
        f.getContentPane().setBackground(Color.gray);
        
        currentNorPrice = getCurrentNorPrice();
        JLabel curPrice = new JLabel("Current Normal Service Price: RM " + currentNorPrice);
        
        JLabel newPrice = new JLabel("New Price(RM)");
        JTextField txtPrice = new JTextField();
        JButton save = new JButton("Save");
        JButton cancel = new JButton("Cancel");
        
        curPrice.setBounds(50,70,250,30);
        newPrice.setBounds(50,100,120,30);
        txtPrice.setBounds(180,100,120,30);
        save.setBounds(70,150,120,30);
        cancel.setBounds(200,150,120,30);
        
        save.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String input = txtPrice.getText();
                if (input.isEmpty()){
                    JOptionPane.showMessageDialog(f,"Please enter a price","Warning",JOptionPane.WARNING_MESSAGE);
                }
                try{
                    double price = Double.parseDouble(input);
                    if(price <= 0){
                        JOptionPane.showMessageDialog(f,"Price cannot less than 0","Warning",JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    
                    boolean success = updPrice("NormalService",price);
                    
                    if (success){
                        JOptionPane.showMessageDialog(f, "Normal Service price updated to RM " + price);
                        
                        curPrice.setText("Current Normal Price Service: RM " + price);
                    }
                    else{
                        JOptionPane.showMessageDialog(f,"Failed to update");
                    }
                }
                catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(f,"Please enter a valid number");
                }
            }
        
        });
        
        cancel.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f.dispose();
            }
        });
        
        
        f.add(curPrice);
        f.add(newPrice);
        f.add(txtPrice);
        f.add(save);
        f.add(cancel);
        f.setVisible(true);
        f.setDefaultCloseOperation(f.DISPOSE_ON_CLOSE);
    }
    
    public void majService(){
    JFrame f = new JFrame("Major Service");
        f.setSize(600,500);
        f.setLayout(null);
        f.getContentPane().setBackground(Color.gray);
        
        currentMajPrice = getCurrentMajPrice();
        JLabel curPrice = new JLabel("Current Major Service Price: RM " + currentMajPrice);
        
        JLabel newPrice = new JLabel("New Price(RM)");
        JTextField txtPrice = new JTextField();
        JButton save = new JButton("Save");
        JButton cancel = new JButton("Cancel");
        
        curPrice.setBounds(50,70,250,30);
        newPrice.setBounds(50,100,120,30);
        txtPrice.setBounds(180,100,120,30);
        save.setBounds(70,150,120,30);
        cancel.setBounds(200,150,120,30);
        
        save.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String input = txtPrice.getText();
                if (input.isEmpty()){
                    JOptionPane.showMessageDialog(f,"Please enter a price","Warning",JOptionPane.WARNING_MESSAGE);
                }

                try{
                    double price = Double.parseDouble(input);
                    if(price <= 0){
                        JOptionPane.showMessageDialog(f,"Price cannot less than 0","Warning",JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    
                    boolean success = updPrice("MajorService",price);
                    
                    if (success){
                        JOptionPane.showMessageDialog(f, "Major Service price updated to RM " + price);
                        
                        curPrice.setText("Current Major Price Service: RM " + price);
                    }
                    else{
                        JOptionPane.showMessageDialog(f,"Failed to update");
                    }
                }
                catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(f,"Please enter a valid number");
                }
            }
        
        });
        
        cancel.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f.dispose();
            }
        });
        
        
        f.add(curPrice);
        f.add(newPrice);
        f.add(txtPrice);
        f.add(save);
        f.add(cancel);
        f.setVisible(true);
        f.setDefaultCloseOperation(f.DISPOSE_ON_CLOSE);
    }
    
    public static double getPrice(String service){
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            
            boolean found = false;
            
            while((line = br.readLine()) != null){
                if (line.equals("[Price]")){
                    found = true;
                    continue;
                }
                
                if (found && line.startsWith("[") && !line.equals("[Price]")){
                    break;
                }
                if (found && line.contains(",")){
                    String[] data = line.split(",");
                    if (data[0].equals(service)){
                        return Double.parseDouble(data[1]);
                    }
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return 200.00;
    }
    
    public static double getCurrentNorPrice(){
        return SetPrice.getPrice("NormalService");
    }
    
    public static double getCurrentMajPrice(){
        return SetPrice.getPrice("MajorService");
    }
    
    
    public boolean updPrice(String service,double price){
        try{
            File file = new File(path);
            
            BufferedReader br = new BufferedReader(new FileReader(file));
            java.util.ArrayList<String> lines = new java.util.ArrayList<>();
            String line;
            
            boolean inPrice = false;
            boolean found = false;
            
            while ((line = br.readLine()) != null){
                lines.add(line);
            }
            br.close();
            
            for(int i = 0; i < lines.size(); i++){
                String currentLine = lines.get(i);
            
                if (currentLine.equals("[Price]")) {
                inPrice = true;
                continue;         
                }
                
                if (inPrice && currentLine.startsWith("[") && !line.equals("[Price]")){
                    break;
                }
                
                if (inPrice && currentLine.contains(",")){
                    String[] data = currentLine.split(",");
                    if (data.length >= 2 && data[0].equals(service)){
                        lines.set(i,service + "," + price);
                        found = true;
                        break;
                    }
                }
            }
                           
            if (!found){
                JOptionPane.showMessageDialog(null,"Service Not Found","Warning",JOptionPane.WARNING_MESSAGE);
                return false;
            }
            
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (String l : lines) {
               bw.write(l);
               bw.newLine();
            }
            bw.close();
            
            return true;
        }catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }
}
