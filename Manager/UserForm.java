package Manager;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class UserForm {
    
    protected JFrame f;
    protected JTextField txtuser, txtpsw, txtname;
    protected JComboBox<String> rolebox;
    protected JButton cancel;
    
    public UserForm(String title){

        f = new JFrame(title);
        f.setSize(600, 500);
        f.setLayout(null);
        f.getContentPane().setBackground(Color.gray);
        
        JLabel userid = new JLabel("User ID");
        JLabel psw = new JLabel("Password");
        JLabel role = new JLabel("Role");
        JLabel name = new JLabel("Name");
        
        userid.setBounds(50,80,100,30);
        psw.setBounds(50,120,100,30);
        role.setBounds(50,170,100,30);
        name.setBounds(50,220,100,30);
        
        txtuser = new JTextField();
        txtpsw = new JTextField();
        txtname = new JTextField();
        txtuser.setBounds(150,80,120,30);
        txtpsw.setBounds(150,120,120,30);
        txtname.setBounds(150,220,120,30);
        
        String[] roles = {"Manager", "Counter Staff", "Technician"};
        rolebox = new JComboBox<>(roles);
        rolebox.setBounds(150,170,120,30);
        
        cancel = new JButton("Cancel");
        cancel.setBounds(190,300,120,30);
        
        cancel.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f.dispose();
            }
        });
        
        f.add(userid);
        f.add(psw);
        f.add(role);
        f.add(name);
        f.add(txtuser);
        f.add(txtpsw);
        f.add(txtname);
        f.add(rolebox);
        f.add(cancel);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setVisible(true);

    }
}
