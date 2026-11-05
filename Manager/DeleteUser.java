package Manager;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
import java.io.*;

public class DeleteUser extends UserForm{
    public DeleteUser(){
        super("Delete User");
        
        JLabel title = new JLabel("Delete User Dashboard");
        title.setBounds(50,20,200,50);
        
        JButton del = new JButton("Delete");
        del.setBounds(50,300,120,30);
        
        // Delete Button Function
        del.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String id = txtuser.getText();
                String psw = txtpsw.getText();
                String role = (String) rolebox.getSelectedItem();
                String name = txtname.getText();
                
                if (id.isEmpty() || psw.isEmpty()){
                    AddUser.empty();
                }
                else{
                    int a = JOPDeleteEx();
                    
                    if(a == JOptionPane.YES_OPTION){
                        try{
                            if(Manage.delUser(id,psw,role,name)){
                                JOptionPane.showMessageDialog(f, "Deleted Successfully!", "Alert",JOptionPane.WARNING_MESSAGE);
                            }
                            else{
                                JOptionPane.showMessageDialog(f, "Something Wrong Please Check!", "Alert",JOptionPane.WARNING_MESSAGE);
                            }
                        }catch (Exception ex){
                            ex.printStackTrace(); JOptionPane.showMessageDialog(f, "Error delete data", "Error",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else if (a == JOptionPane.NO_OPTION){
                        JOptionPane.showMessageDialog(f,"Delete cancelled");
                        f.dispose();
                    }
                    
                }
            }
        });
        
        
        f.add(title);
        f.add(del);
    }
    
    public int JOPDeleteEx(){
        return JOptionPane.showConfirmDialog(null,"Are you sure you want to delete it?","Confirm Delete",JOptionPane.YES_NO_OPTION);
    } 
    
}
