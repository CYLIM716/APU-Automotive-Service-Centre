package Manager;

import java.awt.*;
import java.awt.Color;
import javax.swing.*;
import javax.swing.table.*;

public class CheckPrice {
    
    public CheckPrice() {
        JFrame f = new JFrame("Check Service Price");
        f.setSize(500, 350);
        f.setLayout(new BorderLayout());
        f.getContentPane().setBackground(Color.gray);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel title = new JLabel("Service Price Overview", SwingConstants.CENTER);

        String[] column = {"Service", "Current Price (RM)", "Initial Price (RM)"};
        DefaultTableModel model = new DefaultTableModel(column, 0) {
            public boolean isCellEditable(int row, int col) {
                return false; // make table read-only
            }
        };

        double norPrice = SetPrice.getCurrentNorPrice();
        double majPrice = SetPrice.getCurrentMajPrice();
        model.addRow(new Object[]{
            "Normal Service",
            String.format("%.2f", norPrice),
            String.format("%.2f", 80.00)
        });

        model.addRow(new Object[]{
            "Major Service",
            String.format("%.2f", majPrice),
            String.format("%.2f", 200.00)
        });

        JTable table = new JTable(model);

        f.add(title, BorderLayout.NORTH);
        f.add(new JScrollPane(table), BorderLayout.CENTER);
        f.setVisible(true);
    }

}
