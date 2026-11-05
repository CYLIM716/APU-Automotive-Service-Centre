package Manager;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;

public class ManageStaff{
    public ManageStaff(){
        JFrame f = new JFrame("Manage Staff");
        f.setSize(600, 500);
        f.setLayout(null);
        f.getContentPane().setBackground(Color.gray);
               
        JLabel manage = new JLabel("Manage Staff");
        manage.setBounds(150,20,200,50);
        
        JButton add = new JButton("Add Staff");
        add.setBounds(150,100,300,50);
        
        JButton update = new JButton("Update Staff");
        update.setBounds(150,180,300,50);
        
        JButton delete = new JButton("Delete Staff");
        delete.setBounds(150,260,300,50);
        
        JButton view = new JButton("View Staff");
        view.setBounds(150,340,300,50);
        
        add.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new AddUser();
            }
        });
        
        update.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new UpdateUser();
            }
        });
        
        delete.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new DeleteUser();
            }
        });
        view.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new Table_Staff();
            }
        });
        
        
        f.add(manage);
        f.add(add);
        f.add(update);
        f.add(delete);
        f.add(view);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
