import Technician.TechniciansForm;
import CounterStaff.CounterStaffDashboard;
import Customer.CustomerDashboard;
import Manager.ManagerDashboard;
import Customer.CustomerService;
import Customer.Customer;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class Login extends JFrame {
    private String id;
    private String psw;
    private String role;
    
    public Login(String id, String psw, String role){
        this.id = id;
        this.psw = psw;
        this.role = role;
    }
    public Login(){
        setTitle("Login System");
        setSize(550,500);
        setLayout(null);
        getContentPane().setBackground(Color.gray);
        
        JLabel title = new JLabel("APU Automotive Service Centre");
        title.setBounds(100,20,200,50);
        
        JLabel userid = new JLabel("User ID");
        JLabel psw = new JLabel("Password"); 
        JLabel roleLabel = new JLabel("Role");
        userid.setBounds(50,80,100,30);
        psw.setBounds(50,120,100,30);
        roleLabel.setBounds(50,170,100,30);
        
        JTextField txtuser = new JTextField();
        JTextField txtpsw = new JTextField();
        txtuser.setBounds(150,80,120,30);
        txtpsw.setBounds(150,120,120,30);
        
        String[] roles = {"Manager", "Counter Staff", "Technician", "Customer"};
        JComboBox<String> rolebox = new JComboBox<>(roles);
        rolebox.setBounds(150,170,120,30);
        
        JButton login = new JButton("Login");
        JButton reset = new JButton("Reset");
        JButton forgot = new JButton("Forgot Password");
        JButton register = new JButton("User Register");
        login.setBounds(50,250,120,30);
        reset.setBounds(180,250,120,30);
        forgot.setBounds(50,290,250,30);
        register.setBounds(50,330,250,30);
        
        // Add Button Function
        login.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               String user = txtuser.getText();
               String password = new String(txtpsw.getText());
               String role = (String) rolebox.getSelectedItem();
               
               if (user.isEmpty() || password.isEmpty()){
                   JOptionPane.showMessageDialog(Login.this,"Please fill all fields!");

               }
               else{
                   boolean found = false;
                   try{
                       Login u = new Login(user,password,role);
                       found = u.checkLogin();
                   }
                   catch(Exception ex){
                       ex.printStackTrace();
                   }
                   if (found){
                       
                       switch(role){
                            case "Manager":
                                new ManagerDashboard().setVisible(true);
                                break;
                            case "Counter Staff":
                                new CounterStaffDashboard(user).setVisible(true);
                                break;
                            case "Technician":
                                new TechniciansForm(user).setVisible(true);
                                break;
                            case "Customer":
                                CustomerService service = new CustomerService();
                                Customer customer = service.findById(user);
                                if (customer != null) {
                                    new CustomerDashboard(customer).setVisible(true);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Customer not found!");
                                }
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Unknown role!");
                       }
                   }
                   
               }
            }
        });
        
        reset.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                txtuser.setText("");
                txtpsw.setText("");
            }
        });
        
        forgot.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new ResetPsw();
            }
        });
        
        register.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new UserRegister();
            }
        });
        
        add(title);
        add(userid);
        add(roleLabel);
        add(psw);
        add(txtuser);
        add(txtpsw);
        add(rolebox);
        add(login);
        add(reset);
        add(forgot);
        add(register);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    
    public boolean checkLogin(){
    try{
            BufferedReader br = new BufferedReader(new FileReader("database.txt"));
                       String line;
                       
                       while((line = br.readLine()) != null){
                           String[] data = line.split(",");
                           
                           if(this.id.equals(data[0]) && 
                              this.psw.equals(data[1]) && 
                              this.role.equals(data[2])){
                               loginSuccess();
                               return true;
                           }
                       }
                       br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        loginFail();
        return false;
    }
    
    public void loginSuccess(){    
        JOptionPane.showMessageDialog(this,"Welcome to APU Automotive Service Centre!");
    }
    
    public void loginFail(){
        JOptionPane.showMessageDialog(this,"Invalid ID or Password! Please Try Again!", "Error", JOptionPane.ERROR_MESSAGE);

    }
    
}
