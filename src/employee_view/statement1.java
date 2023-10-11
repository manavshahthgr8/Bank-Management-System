package employee_view;

import bank.management.system.connect;
import bank.management.system.main_Class;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

public class statement1 extends JFrame implements ActionListener {
    JLabel label;
    JTextField textField;
    String admin;
    JButton button1, button2, button3, button4,button5;
    JLabel timeLabel; // Added JLabel for displaying the time
    Timer timer; // Added a timer to update the time label
    statement1(String admin){
        super("Manav's Bank admin - stmnt input");
        this.admin=admin;

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
        button3.setBackground(Color.YELLOW);
        button3.setForeground(Color.BLACK);
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

        label = new JLabel("Enter cardno below :");
        label.setForeground(Color.black); //text color
        label.setFont(new Font("AvantGarde",Font.BOLD,38));
        label.setBounds(500,250,450,40);
        add(label);

        textField = new JTextField(15);
        textField.setBounds(450,300,450,30);
        textField.setFont(new Font("Arial",Font.BOLD,14));
        textField.setBackground(Color.BLACK);
        textField.setForeground(Color.white);
        add(textField);

        button5 = new JButton("Next");
        button5.setFont(new Font("Raleway", Font.BOLD, 14));
        button5.setBackground(Color.GRAY);
        button5.setForeground(Color.BLACK);
        button5.setBounds(600, 380, 150, 20);
        button5.addActionListener(this);
        add(button5);



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


        getContentPane().setBackground(new Color(222, 255, 228));
        setLayout(null);
        setSize(1380, 725);
        setLocation(0, 0);
        setVisible(true);
    }
    private void updateTimeLabel() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = dateFormat.format(new Date());
        timeLabel.setText(  currentTime);
    }

    public static void main(String[] args) {
        new statement1("");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        connect c = new connect();
        Date date = new Date();
        if(e.getSource()==button1){//registered
            setVisible(false);
            new registered(admin);

        }else if(e.getSource()==button2){//unregistered
            setVisible(false);
            new unregistered(admin);

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
        } else if(e.getSource()==button5){ //next
            try {
                String input = textField.getText();
                if (textField.getText().equals("")) {   //if amount emptier than popping error
                    JOptionPane.showMessageDialog(null, "Please enter the Cardno");
                } else {
                    //connect c = new connect();
                    String q = "select * from login where card_number = '" + input + "'";
                    ResultSet resultSet = c.statement.executeQuery(q);
                    if(resultSet.next()){
                        setVisible(false);
                        new statement2(admin ,input);
                    }else {
                        JOptionPane.showMessageDialog(null,"User not found try again");
                    }
                }

            }catch (Exception E){
                E.printStackTrace();

            }

        }
    }
}
