package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class Deposit extends JFrame implements ActionListener {
    String pin;
    String cardno;
    TextField textField;

    JButton b1, b2;
    Deposit(String pin , String cardno) {
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1380,685,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0,0,1380,685);
        add(l3);

        JLabel label1 = new JLabel("ENETR AMOUNT YOU WANT TO DEPOSIT");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("System", Font.BOLD, 15));
        label1.setBounds(405,140,400,31);
        l3.add(label1);

        textField = new TextField();
        textField.setBackground(new Color(65,125,128));
        textField.setForeground(Color.WHITE);
        textField.setBounds(405,185,320,25);
        textField.setFont(new Font("Raleway", Font.BOLD,22));
        l3.add(textField);

        b1 = new JButton("DEPOSIT");
        b1.setBounds(600,300,150,25);
        b1.setBackground(new Color(65,125,128));
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        l3.add(b1);

        b2 = new JButton("BACK");
        b2.setBounds(600,340,150,25);
        b2.setBackground(new Color(65,125,128));
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        l3.add(b2);

        this.pin = pin;
        this.cardno=cardno;
        setLayout(null);
        setSize(1380, 725);
        setLocation(0, 0);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String amount = textField.getText(); // reading entered valuE
            Date date = new Date();
            if (e.getSource()==b1){
                if (textField.getText().equals("")){ // IF amount empty
                    JOptionPane.showMessageDialog(null,"Please enter the Amount you want to Deposit");
                }else {
                    connect c = new connect();
                    ResultSet resultSet = c.statement.executeQuery("select * from bank where cardno = '" + cardno + "' and  pin = '" + pin + "'");
                    int balance = 0;  // creating variable to fetch balance from database
                    while (resultSet.next()) { //to check if data base se aya and store hua ki nahi
                        if (resultSet.getString("type").equals("Deposit")) {  //column name match karenge
                            balance += Integer.parseInt(resultSet.getString("amount"));
                        } else {
                            balance -= Integer.parseInt(resultSet.getString("amount"));
                        }
                    }

                    balance = balance+Integer.parseInt(amount);
                    String updateQuery = "UPDATE balance SET balance = ? WHERE cardno = ?";
                    PreparedStatement preparedStatement = c.connection.prepareStatement(updateQuery);
                    preparedStatement.setInt(1, balance);
                    preparedStatement.setString(2, cardno);
                    preparedStatement.executeUpdate();

                    c.statement.executeUpdate("insert into bank values('"+pin+"', '"+date+"','Deposit', '"+amount+"','"+cardno+"')");
                    JOptionPane.showMessageDialog(null,"Rs. "+amount+" Deposited Successfully");
                    setVisible(false);
                   new main_Class(pin , cardno);
                }
            }else if (e.getSource()==b2){
                setVisible(false);
                new main_Class(pin , cardno);
            }
        }catch (Exception E){
            E.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new Deposit("","");

    }


}





