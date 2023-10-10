package employee_view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import bank.management.system.connect;

public class unregistered extends JFrame implements ActionListener {
    String admin;
    JButton button1, button2, button3, button4;
    JLabel timeLabel; // Added JLabel for displaying the time
    Timer timer; // Added a timer to update the time label
    JTable dataTable;
    JScrollPane scrollPane;

    unregistered(String admin) {
        super("Manav's Bank admin -unregistered");
        this.admin= admin;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bank.png"));
        Image i2 = i1.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(25,10,100,100);
        add(image);

        button1 = new JButton("Registered User");
        button1.setFont(new Font("Raleway",Font.BOLD, 14));
        button1.setBackground(Color.BLACK);
        button1.setForeground(Color.WHITE);
        button1.setBounds(400,15,150,20);
        button1.addActionListener(this);
        add(button1);

        button2 = new JButton("Un-registered");
        button2.setFont(new Font("Raleway",Font.BOLD, 14));
        button2.setBackground(Color.BLACK);
        button2.setForeground(Color.WHITE);
        button2.setBounds(600,15,150,20);
        button2.addActionListener(this);
        add(button2);

        button3 = new JButton("Balance");
        button3.setFont(new Font("Raleway",Font.BOLD, 14));
        button3.setBackground(Color.BLACK);
        button3.setForeground(Color.WHITE);
        button3.setBounds(800,15,150,20);
        button3.addActionListener(this);
        add(button3);

        // ... (other code)

        button4 = new JButton("Log out");
        button4.setFont(new Font("Raleway", Font.BOLD, 14));
        button4.setBackground(Color.RED);
        button4.setForeground(Color.BLACK);
        button4.setBounds(1200, 15, 150, 20);
        button4.addActionListener(this);
        add(button4);

        // Create a JLabel for displaying the time
        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Raleway", Font.BOLD, 14));
        timeLabel.setForeground(Color.BLACK);
        timeLabel.setBounds(1200, 35, 200, 20);
        updateTimeLabel(); // Initialize the time label with the current time
        add(timeLabel);

        // Create a timer to update the time label every second
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTimeLabel();
            }
        });
        timer.start(); // Start the timer

        // ... (other code)

        // Create the table model to hold the data
        DefaultTableModel tableModel = new DefaultTableModel();

        // Create the JTable using the table model
        dataTable = new JTable(tableModel);

        // Create a scroll pane and add the table to it
        scrollPane = new JScrollPane(dataTable);
        scrollPane.setBounds(100, 150, 1200, 400);
        add(scrollPane);

        // Populate the table with data from the SQL view
        try {
            connect c = new connect();
            Connection connection = c.statement.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM unregistered");

            // Get column names from the ResultSet's metadata
            int columnCount = resultSet.getMetaData().getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                tableModel.addColumn(resultSet.getMetaData().getColumnName(i));
            }

            // Add rows to the table model
            while (resultSet.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = resultSet.getObject(i);
                }
                tableModel.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        getContentPane().setBackground(new Color(222, 255, 228));
        setLayout(null);
        setSize(1380, 725);
        setLocation(0, 0);
        setVisible(true);
    }

    // Method to update the time label with the current system time
    private void updateTimeLabel() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = dateFormat.format(new Date());
        timeLabel.setText(  currentTime);
    }

    public static void main(String[] args) {
        new unregistered("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        connect c = new connect();
        Date date = new Date();
        if(e.getSource()==button1){//registered
            setVisible(false);
            new registered(admin);

        }else if(e.getSource()==button2){//unregistered

        } else if (e.getSource()==button3) {//balance
            setVisible(false);
            new balance(admin);

        } else if (e.getSource()==button4) {//logout
            try{
                c.statement.executeUpdate("insert into login_time values('"+admin+"', '"+date+"','log-out')");
                setVisible(false);
                new admin();

            }catch (Exception E){
                E.printStackTrace();
            };
        }

    }
}