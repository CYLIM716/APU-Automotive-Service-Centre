package Manager;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
public class Manage{
    private String id;
    private String psw;
    private String role;
    private String name;
    
    static String path = "database.txt";
    
    public Manage(String id, String psw, String role, String name){
        this.id = id;
        this.psw = psw;
        this.role = role;
        this.name = name;
    }
    
    public static void addUser(String id, String psw, String role, String name){
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            int lineCount = 0;
            while ((br.readLine()) != null){
                lineCount++;
            }
            br.close();
            
            String[] lines = new String[lineCount];
            
            br = new BufferedReader(new FileReader(path));
            
            for (int i = 0; i < lineCount; i++){
                lines[i] = br.readLine();
            }
            br.close();
            
            // Find the insertion point for the [Users] block
            int insert = -1;  // Not found the insert space yet, so = -1
            boolean inUsers = false;
            
            for(int i = 0; i< lines.length; i++){
                String currentLine = lines[i].trim();
                
                if (currentLine.equals("[Users]")){
                    inUsers = true;
                    continue;
                }
                if (inUsers && currentLine.startsWith("[") && !currentLine.equals("[Users]")) {
                    insert = i;
                    break;
                }
                if (inUsers && i == lines.length - 1){
                    insert = lines.length;
                }
            }
            
            if (insert == -1){
                insert = lines.length;
            }
            
            String[] newLines = new String[lines.length + 1];
            
            for(int i = 0; i < insert; i++){
                newLines[i] = lines[i];
            }
            
            newLines[insert] = id + "," + psw + "," + role + "," + name;
            
            for (int i = insert; i <lines.length; i++){
                newLines[i + 1] = lines[i];
            }
            
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            for (int i = 0; i < newLines.length; i++) {
                bw.write(newLines[i]);
                bw.newLine();
            }
            bw.close();
            }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static boolean updateUser(String id,String psw, String role, String name){
        try {
            List<String> lines = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(path));

            String line;
            boolean found = false;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length >= 4 && data[0].trim().equals(id.trim())) {
                    lines.add(id + "," + psw + "," + role + "," + name);
                    found = true;
                } else {
                    lines.add(line);
                }
            }
            br.close();

            BufferedWriter bw = new BufferedWriter(new FileWriter(path));

            for (int i = 0; i < lines.size(); i++) {
                bw.write(lines.get(i));
                bw.newLine();
            }

            bw.close();

            if (found) {
                System.out.println("Update Successful!");
            } else {
                System.out.println("User Not Found!");
            }

            return found;
        } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
    
    
    public static boolean delUser(String id, String psw, String role, String name) {
        File file = new File(path);

        java.util.ArrayList<String> lines = new java.util.ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        boolean found = false;

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] data = line.split(",");

            if (data.length >= 4) {
                boolean match = data[0].trim().equals(id.trim()) && 
                        data[1].trim().equals(psw.trim()) && 
                        data[2].trim().equals(role.trim()) && 
                        data[3].trim().equals(name.trim());
                
                if (match) { 
                    lines.remove(i); 
                    found = true; i--; 
                }
            }
        }
        if (!found) { 
            return false; 
        }
                
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
            }
                
        return true;
    }
    
    public static boolean userExists(String id){
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            
            while((line = br.readLine()) != null){
                String[] data = line.split(",");
                if(data[0].equals(id)){
                    br.close();
                    return true;
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
    public static boolean userNotExists(String id){
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            
            while((line = br.readLine()) != null){
                String[] data = line.split(",");
                if(data[0] != id){
                    br.close();
                    return true;
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    

    
}
