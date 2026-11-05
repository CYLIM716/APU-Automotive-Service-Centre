import Manager.AddUser;
import java.awt.Color;
import java.awt.event.*;
import java.io.*;
import javax.swing.JFrame;
import javax.swing.*;

public class ResetPsw {
    static String path = "database.txt";
    public ResetPsw(){
        JFrame f = new JFrame("Reset Password");
        f.setLayout(null);
        f.setSize(550,500);
        f.getContentPane().setBackground(Color.gray);
        
        JLabel title = new JLabel("Reset Password Dashboard");
        JLabel id = new JLabel("User ID");
        JLabel psw = new JLabel("Enter New Password");
        JLabel reenter = new JLabel("Confirm Password");
        title.setBounds(100,20,200,50);
        id.setBounds(50,80,100,30);
        psw.setBounds(50,120,150,30);
        reenter.setBounds(50,160,150,30);
        
        JTextField txtid = new JTextField();
        JTextField txtpsw = new JTextField();
        JTextField txtreenter = new JTextField();
        txtid.setBounds(200,80,120,30);
        txtpsw.setBounds(200,120,120,30);
        txtreenter.setBounds(200,160,120,30);
        
        JButton btnchange = new JButton("Change Password");
        btnchange.setBounds(170,200,150,30);
        
        
        btnchange.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String getid = txtid.getText();
                String getpsw = txtpsw.getText();
                String getconpsw = txtreenter.getText();
                
                if (!getpsw.equals(getconpsw)){
                   JOptionPane.showMessageDialog(f,"Passwords are inconsistent! Try Again!",
                           "Warning",JOptionPane.WARNING_MESSAGE);
                   return;
                }
                
                if (getid.isEmpty() || getpsw.isEmpty() || getconpsw.isEmpty()){
                    AddUser.empty();
                    return;
                }
                
                StringBuilder sb = new StringBuilder();
                
                try{
                    BufferedReader br = new BufferedReader(new FileReader(path));
                    
                    String line;
                    String currentSection = "";
                    boolean userfound = false;
                    
                    while((line = br.readLine()) != null){
                        if (line.startsWith("[")){
                            currentSection = line;
                            sb.append(line).append("\n");
                            continue;
                        }
                        
                        String[] data = line.split(",");
                        
                        switch (currentSection){
                            case "[Users]":
                                if (data.length >= 4 && data[0].equals(getid)){
                                    line = data[0] + "," + getconpsw + "," + data[2] + "," + data[3];
                                    userfound = true;
                                }
                                break;
                                
                            case "[Customers]":
                                if (data.length >= 5 && data[0].equals(getid)){
                                    line = data[0] + "," + data[1] + "," + data[2] + "," + data[3] + "," + getconpsw;
                                    userfound = true;
                                }
                                break;
                                
                            case "[CustomersDetails]":
                                if (data.length >= 7 && data[0].equals(getid)){
                                    line = data[0] + "," + 
                                           data[1] + "," + 
                                           data[2] + "," + 
                                           data[3] + "," +
                                           data[4] + "," + 
                                           getconpsw + "," + 
                                           data[6];
                                    userfound = true;
                                }
                                break;
                        }
                        sb.append(line).append("\n");
                    }
                    
                    br.close();
                    
                    
                    if (userfound){
                        BufferedWriter bw = new BufferedWriter(new FileWriter(path));
                        bw.write(sb.toString());
                        bw.close();
                        JOptionPane.showMessageDialog(f,"Password has been changed!");
                    }
                    else{
                        JOptionPane.showMessageDialog(f,"User not exist!",
                                "Warning",JOptionPane.WARNING_MESSAGE);
                    }
                }catch(IOException ex){
                    System.out.println("ERROR!");
                }
            }
        });
        f.add(title);
        f.add(id);
        f.add(psw);
        f.add(reenter);
        f.add(txtid);
        f.add(txtpsw);
        f.add(txtreenter);
        f.add(btnchange);
        f.setVisible(true);
        f.setDefaultCloseOperation(f.DISPOSE_ON_CLOSE);   
    }
}
