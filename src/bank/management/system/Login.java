package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

//step 3making frame via extending jframe  & step 8 : jo dikhaya use kam karane ke liye action listner
public class Login extends JFrame implements ActionListener {
    JLabel label1,label2,label3;  //making global thatswhy declared here step 6 part
    JTextField textField2;   //input for text
    JPasswordField passwordField3;  //input for password
    JButton button1,button2,button3;  //step 7

    //step 2 creating constructor
    Login() {
        super("Bank Management System");//giving title to opening frame/window
        //Step 5 bringing images to frame
        //Creating object I1 and in that storing image from system. also .get.... is used to read system image location
        ImageIcon I1 = new ImageIcon(ClassLoader.getSystemResource("icon/bank.png"));
        Image I2 = I1.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT); //we need to scale stored image as per need
        //now to make image visible in frame we need to make icon again from image
        ImageIcon I3 = new ImageIcon(I2);
        JLabel image = new JLabel(I3); //jlabel is used so we can show text on image
        image.setBounds(350,10,100,100); // fixing positon of image
        add(image); //yeh Jlabel wala image hai add to show

        //similarly adding card image
        ImageIcon Ii1 = new ImageIcon(ClassLoader.getSystemResource("icon/card.png"));
        Image Ii2 = Ii1.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
        ImageIcon Ii3 = new ImageIcon(Ii2);
        JLabel iimage = new JLabel(Ii3);
        iimage.setBounds(630,350,100,100); // fixing positon of image
        add(iimage);

        //Step 6 Showing text on images/ frame
        label1 = new JLabel("WELCOME TO ATM");
        label1.setForeground(Color.white); //text color
        label1.setFont(new Font("AvantGarde",Font.BOLD,38));
        label1.setBounds(230,125,450,40);
        add(label1);

        label2 = new JLabel("Card NO:");
        label2.setForeground(Color.white);
        label2.setFont(new Font("Ralway",Font.BOLD,28));
        label2.setBounds(150,190,375,30);
        add(label2);

        textField2 = new JTextField(15);
        textField2.setBounds(325,198,230,30);
        textField2.setFont(new Font("Arial",Font.BOLD,14));
        add(textField2);

        label3 = new JLabel("PIN:  ");
        label3.setForeground(Color.white);
        label3.setFont(new Font("Ralway",Font.BOLD,28));
        label3.setBounds(150,250,375,30);
        add(label3);

        passwordField3 = new JPasswordField(15);
        passwordField3.setFont(new Font("Arial",Font.BOLD,14));
        passwordField3.setBounds(325,250,230,30);
        add(passwordField3);

        //step 7 adding button
        button1 = new JButton("Sign in");
        button1.setFont(new Font("Arial",Font.BOLD,14));
        button1.setForeground(Color.white);
        button1.setBackground(Color.BLACK);
        button1.setBounds(325,300,100,30);
        button1.addActionListener(this);   //due to need in step 8
        add(button1);

        button2 = new JButton("Clear");
        button2.setFont(new Font("Arial",Font.BOLD,14));
        button2.setForeground(Color.white);
        button2.setBackground(Color.BLACK);
        button2.setBounds(455,300,100,30);
        button2.addActionListener(this);
        add(button2);

        button3 = new JButton("Sign UP");
        button3.setFont(new Font("Arial",Font.BOLD,14));
        button3.setForeground(Color.white);
        button3.setBackground(Color.BLACK);
        button3.setBounds(325,350,230,30);
        button3.addActionListener(this);
        add(button3);




        //similarly adding background  {always below} step 5 part
        ImageIcon Iii1 = new ImageIcon(ClassLoader.getSystemResource("icon/backbg.png"));
        Image Iii2 = Iii1.getImage().getScaledInstance(850,480  ,Image.SCALE_DEFAULT);
        ImageIcon Iii3 = new ImageIcon(Iii2);
        JLabel iiimage = new JLabel(Iii3);
        iiimage.setBounds(0,0,850,480);
        add(iiimage); //yeh Jlabel wala image hai


        setLayout(null); //to impose our layout
        //making front view step 4
        setSize(850,480);
        setLocation(450,200);//making frame open from centere of screen
        setVisible(true); //making frame visible // always in last lines

    }

    //step 8
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if(e.getSource()==button1){
                if (e.getSource()==button1) {
                    if(textField2.getText().equals("")|| passwordField3.getText().equals("")){
                        JOptionPane.showMessageDialog(null,"Please enter some value");
                    }else{
                        connect c = new connect();
                        String cardno = textField2.getText();
                        String pin = passwordField3.getText();
                        String q = "select * from login where card_number = '" + cardno + "' and  pin = '" + pin + "'";
                        ResultSet resultSet = c.statement.executeQuery(q); // this is at end of project and this time its not ipdate its execute
                        //Stored value in result set
                        //now checking if data aya ki nahi in result set
                        if(resultSet.next()){
                            setVisible(false);
                            new main_Class(pin);
                        }else {
                            JOptionPane.showMessageDialog(null,"User not found try again");
                        }

                    }
                }

            } else if (e.getSource()==button2) {  //clear
                textField2.setText("");
                passwordField3.setText("");
            }
            else if (e.getSource()==button3){
                new Signup();
                setVisible(false);
            }

        }catch (Exception E){
            E.printStackTrace();  //jo bhi error ayega woh print hoga
        };

    }

    public static void main(String[] args) {
        //step 1 making object
        new Login();
    }
}
