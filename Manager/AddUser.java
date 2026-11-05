package Manager;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
import java.io.*;

public class AddUser extends UserForm{
    public AddUser(){ 
        super("Add User");
        
        JLabel title = new JLabel("Add User Dashboard");
        title.setBounds(50,20,200,50);
        
        JButton add = new JButton("Add");
        add.setBounds(50,300,120,30);
       
        // Add Button Function
        add.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String id = txtuser.getText();
                String psw = txtpsw.getText();
                String role = (String) rolebox.getSelectedItem();
                String name = txtname.getText();
                
                if (id.isEmpty() || psw.isEmpty()){
                    empty();
                }
                else if (Manage.userExists(id)){
                    JOptionPane.showMessageDialog(f,"User Exist!","Error",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    int c = JOPConfirmEx();
                    if (c == JOptionPane.YES_OPTION){
                        try{
                        Manage.addUser(id, psw, role, name); 
                        JOptionPane.showMessageDialog(f,"Added Successfully!","Alert",JOptionPane.WARNING_MESSAGE);
                        }catch(Exception ex){
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(f,"Error saving data!","Error",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else if (c == JOptionPane.NO_OPTION){
                        JOptionPane.showMessageDialog(f,"Add cancelled");
                    }
                }
            }
        });
        
        f.add(title);
        f.add(add);
    }
    
    public int JOPConfirmEx(){
        return JOptionPane.showConfirmDialog(null,"Are you sure you want to add it?","Confirm Add",JOptionPane.YES_NO_OPTION);
    }  
    
    public static void empty(){
        JOptionPane.showMessageDialog(null,"Please fill all fields!","Alert",JOptionPane.WARNING_MESSAGE);
    }
}
