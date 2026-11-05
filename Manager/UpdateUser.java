package Manager;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
import java.io.*;

public class UpdateUser extends UserForm{
    public UpdateUser(){
        super("Update User");
        
        JLabel title = new JLabel("Update User Dashboard");
        title.setBounds(50,20,200,50);
        
        JButton upd = new JButton("Update");
        upd.setBounds(50,300,120,30);

        //Update Button Function
        upd.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String id = txtuser.getText();
                String psw = txtpsw.getText();
                String role = (String) rolebox.getSelectedItem();
                String name = txtname.getText();
                
                if (id.isEmpty() || psw.isEmpty() || name.isEmpty()){
                    AddUser.empty();
                }
                else{
                        int a = JOPUpdateEx();
                        
                        if (a == JOptionPane.YES_OPTION){
                            try{
                                if (Manage.updateUser(id,psw,role,name)){
                                    JOptionPane.showMessageDialog(f, "Updated Successfully!", "Alert",JOptionPane.WARNING_MESSAGE);
                                }
                                else{
                                    JOptionPane.showMessageDialog(f, "User Not found!", "ERROR",JOptionPane.ERROR_MESSAGE);
                                }
                            }catch(Exception ex){
                                ex.printStackTrace();
                                JOptionPane.showMessageDialog(f, "Error updated data", "Error",JOptionPane.ERROR_MESSAGE);
                            }       
                        }
                        else if (a == JOptionPane.NO_OPTION){
                            JOptionPane.showMessageDialog(f,"Update cancelled");
                            f.dispose();
                        }
                }
            }
        });
        
        // Display 
        f.add(title);
        f.add(upd);
    }
    
    public int JOPUpdateEx(){
        return JOptionPane.showConfirmDialog(null,"Are you sure you want to update it?","Confirm Update",JOptionPane.YES_NO_OPTION);
    }   
}
