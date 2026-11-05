import javax.swing.JFrame;
import java.awt.Color;
import java.awt.event.*;
import java.io.*;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class UserRegister {
    
    static String path = "database.txt";
    
    public UserRegister(){
        JFrame f = new JFrame("User Registration");
        f.setSize(700,600);
        f.setLayout(null);
        f.getContentPane().setBackground(Color.gray);
        
        JLabel title = new JLabel("User Registration");
        title.setBounds(100,20,200,50);
        
        JLabel userid = new JLabel("User ID");
        JLabel psw = new JLabel("Password"); 
        JLabel confpsw = new JLabel("Confirm Password"); 
        JLabel name = new JLabel("Name");
        JLabel roleLabel = new JLabel("Role");
        userid.setBounds(50,80,100,30);
        psw.setBounds(50,120,100,30);
        confpsw.setBounds(50,160,150,30);
        name.setBounds(50,200,100,30);
        roleLabel.setBounds(50,240,100,30);
        
        JTextField txtuser = new JTextField();
        JTextField txtpsw = new JTextField();
        JTextField txtconfpsw = new JTextField();
        JTextField txtname = new JTextField();
        txtuser.setBounds(200,80,120,30);
        txtpsw.setBounds(200,120,120,30);
        txtconfpsw.setBounds(200,160,120,30);
        txtname.setBounds(200,200,120,30);
        
        
        // Just for customer role only(if role is customer it will trigger)
        JLabel phoneLabel = new JLabel("Phone");
        JLabel emailLabel = new JLabel("Email");
        JLabel addressLabel = new JLabel("Address");
        JLabel plateLabel = new JLabel("Plate No.");
        phoneLabel.setBounds(50,280,100,30);
        emailLabel.setBounds(50,320,100,30);
        addressLabel.setBounds(50,360,100,30);
        plateLabel.setBounds(50,400,100,30);
        
        JTextField txtphone = new JTextField();
        JTextField txtemail = new JTextField();
        JTextField txtaddress = new JTextField();
        JTextField txtplate = new JTextField();
        txtphone.setBounds(200,280,120,30);
        txtemail.setBounds(200,320,120,30);
        txtaddress.setBounds(200,360,120,30);
        txtplate.setBounds(200,400,120,30);
        
        phoneLabel.setVisible(false);
        emailLabel.setVisible(false);
        addressLabel.setVisible(false);
        plateLabel.setVisible(false);
        txtphone.setVisible(false);
        txtemail.setVisible(false);
        txtaddress.setVisible(false);
        txtplate.setVisible(false);
        //
        
        
        String[] roles = {"Manager", "Counter Staff", "Technician", "Customer"};
        JComboBox<String> rolebox = new JComboBox<>(roles);
        rolebox.setBounds(200,240,120,30);
        
        JButton register = new JButton("Register");
        JButton cancel = new JButton("Cancel");
        register.setBounds(150,450,120,30);
        cancel.setBounds(280,450,80,30);
        
        rolebox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                boolean isCustomer = rolebox.getSelectedItem().equals("Customer");
                phoneLabel.setVisible(isCustomer);
                emailLabel.setVisible(isCustomer);
                addressLabel.setVisible(isCustomer);
                plateLabel.setVisible(isCustomer);
                txtphone.setVisible(isCustomer);
                txtemail.setVisible(isCustomer);
                txtaddress.setVisible(isCustomer);
                txtplate.setVisible(isCustomer);
            }
        });
        
        register.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
               String user = txtuser.getText();
               String password = new String(txtpsw.getText());
               String confirmpsw = new String(txtconfpsw.getText());
               String role = (String) rolebox.getSelectedItem();
               String name = txtname.getText();
               
               if (user.isEmpty() || password.isEmpty() || confirmpsw.isEmpty() || name.isEmpty()){
                   JOptionPane.showMessageDialog(f,"Please fill all fields!");
               }
               
               if (!password.equals(confirmpsw)){
                   JOptionPane.showMessageDialog(f,"Passwords are inconsistent! Try Again!",
                           "Warning",JOptionPane.WARNING_MESSAGE);
                   return;
               }
               
               if (role.equals("Customer")){
                   if (txtphone.getText().isEmpty() ||
                        txtemail.getText().isEmpty() || 
                        txtaddress.getText().isEmpty() ||
                        txtplate.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(f, "Please fill all customer fields!");
                        return;
                    }
               }
               
                                  
               String newUser = user + "," + 
                       confirmpsw + "," + 
                       role  + "," + 
                       name;
                   
               try{
                   BufferedReader br = new BufferedReader(new FileReader(path));
                   StringBuilder message = new StringBuilder();
                   
                   String line;
                   String currentSection = "";
                   boolean userExists = false;
                   
                   while ((line = br.readLine()) != null){
                       if (line.startsWith("[")){
                           currentSection = line;
                       }
                       
                       if (currentSection.equals("[Users]") && !line.startsWith("[")){
                           String[] data = line.split(",");
                           if (data[0].equals(user)){
                               userExists = true;
                           }
                       }
                       
                       message.append(line).append("\n");
                   }
                   br.close();
                
                   if (userExists){
                       JOptionPane.showMessageDialog(f,"User ID already exists!", 
                               "WARNING",JOptionPane.WARNING_MESSAGE);
                       return;
                   }

                   
                   String content = message.toString();
                   String finalData = content.replace("[Customers]", newUser + "\n[Customers]");
                   
                   if (role.equals("Customer")) {
                        String phone = txtphone.getText();
                        String email = txtemail.getText();
                        String address = txtaddress.getText();
                        String plate = txtplate.getText();
                        
                        String customer = user + "," +
                                          name + "," + 
                                          phone + "," + 
                                          plate + "," + 
                                          confirmpsw;
                        
                        String customerdetails = user + "," + 
                                                 name + "," + 
                                                 email + "," + 
                                                 phone + "," + 
                                                 address + "," + 
                                                 password + 
                                                 ",Customer";
                        
                       finalData = finalData.replace("[CustomersDetails]", customer + "\n[CustomersDetails]");
                       finalData = finalData.replace("[Price]", customerdetails + "\n[Price]");
                   }
                        
                   BufferedWriter bw = new BufferedWriter(new FileWriter(path));
                   bw.write(finalData);
                   bw.close();
                   
                   JOptionPane.showMessageDialog(f, "User registered successfully!");
               }catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }); 
        
        cancel.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f.dispose();
            }
        });
        
               
        
        f.add(title);
        f.add(userid);
        f.add(psw);
        f.add(confpsw);
        f.add(roleLabel);
        f.add(name);
        f.add(phoneLabel);
        f.add(emailLabel);
        f.add(addressLabel);
        f.add(plateLabel);
        f.add(txtuser);
        f.add(txtpsw);
        f.add(txtconfpsw);
        f.add(rolebox);
        f.add(txtname);
        f.add(txtphone);
        f.add(txtemail);
        f.add(txtaddress);
        f.add(txtplate);
        f.add(register);
        f.add(cancel);
        
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
                

