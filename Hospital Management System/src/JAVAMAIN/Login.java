package JAVAMAIN;

//GUI Import
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//SQL import
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    JFrame frame;
    static JLabel username;
    JLabel password;
    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton;
    JButton resetButton;
    JLabel imageLabel;
    boolean isAuthenticated;

    public Login() {
        frame = new JFrame();
        frame.setTitle("Login");
        frame.setBounds(400, 100, 400, 300);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.CYAN);
        frame.setVisible(true);

        JOptionPane.showMessageDialog(frame, "Welcome to Hospital Management System Login page", "Info",
                JOptionPane.PLAIN_MESSAGE);
        ImageIcon img = new ImageIcon("src/login_image.png");
        frame.setIconImage(img.getImage());
        frame.repaint();

        username = new JLabel("Username");
        username.setBounds(40, 20, 100, 30);
        username.setFont(new Font("Tahoma", Font.BOLD, 15));
        frame.add(username);
        frame.repaint();
        password = new JLabel("Password");
        password.setBounds(40, 70, 100, 30);
        password.setFont(new Font("Tahoma", Font.BOLD, 15));
        frame.add(password);
        frame.repaint();
        usernameField = new JTextField();
        usernameField.setBounds(150, 20, 150, 30);
        usernameField.setFont(new Font("Times New Roman", Font.BOLD, 30));
        Border border = BorderFactory.createLineBorder(Color.BLUE, 5);
        usernameField.setBorder(border);
        frame.add(usernameField);
        frame.repaint();
        passwordField = new JPasswordField();
        passwordField.setBounds(150, 70, 150, 30);
        passwordField.setFont(new Font("Times New Roman", Font.BOLD, 20));
        passwordField.setBorder(border);
        frame.add(passwordField);
        frame.repaint();

        loginButton = new JButton("Login");
        loginButton.setBounds(30, 140, 100, 30);
        loginButton.setFocusPainted(false);
        loginButton.setBackground(Color.GREEN);
        loginButton.addActionListener(this);
        frame.add(loginButton);
        frame.repaint();

        resetButton = new JButton("Reset");
        resetButton.setBounds(170, 140, 100, 30);
        resetButton.setFocusPainted(false);
        resetButton.setBackground(Color.LIGHT_GRAY);
        resetButton.addActionListener(this);
        frame.add(resetButton);
        frame.repaint();

    }

    String username1, password1;

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            username1 = usernameField.getText();
            password1 = new String(passwordField.getPassword());
            if (username1.isEmpty() || password1.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter both username and password");
            } else {
                try {
                    DBConnect dc = new DBConnect();
                    String sql = "SELECT * FROM admin WHERE login_id = ? AND password = ?";
                    PreparedStatement stmt = dc.con.prepareStatement(sql);
                    stmt.setString(1, username1);
                    stmt.setString(2, password1);
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        isAuthenticated = true;
                        System.out.println("successful");
                        JOptionPane.showMessageDialog(frame, "Login successful!");
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setVisible(false);

                    } else {
                        JOptionPane.showMessageDialog(frame, "Invalid username or password. Please try again.");
                        usernameField.setText("");
                        passwordField.setText("");
                    }
                    rs.close();
                    dc.con.close();
                    stmt.close();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Database error: " + ex.getMessage());
                }
            }
        } else {
            usernameField.setText("");
            passwordField.setText("");
        }

    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

}

