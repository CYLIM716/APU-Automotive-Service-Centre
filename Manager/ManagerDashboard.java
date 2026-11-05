package Manager;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;

public class ManagerDashboard extends JFrame{
    public ManagerDashboard(){
        setTitle("Manager Dashboard - APU Automative");
        setSize(600, 500);
        setLayout(null);
        getContentPane().setBackground(Color.gray);
        
        JLabel manager = new JLabel("Manager - APU Automative");
        manager.setBounds(150,20,200,50);
        
        JButton manage_staff = new JButton("Manage Staff");
        manage_staff.setBounds(150,100,300,50);
        
        JButton set_price = new JButton("Set Service Price");
        set_price.setBounds(150,180,300,50);
        
        JButton feedback = new JButton("View Feedback");
        feedback.setBounds(150,260,300,50);
        
        JButton report = new JButton("View Reports");
        report.setBounds(150,340,300,50);
        
        manage_staff.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new ManageStaff();
            }
        });
        
        set_price.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new SetPrice();
            }
        });
        feedback.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new ViewFeedback();
            }
        });
        report.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new Income();
            }
        });
        
        add(manager);
        add(manage_staff);
        add(set_price);
        add(feedback);
        add(report);       
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}