package com.mycompany.notetakingapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, createAccountButton;

    public LoginFrame() {
        setTitle("Login");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");
        createAccountButton = new JButton("Create New Account");

        // Login Button Action
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Validate login credentials using the map of users and password hashing
                Map<String, String> users = FileManager.loadUsersFromFile();
                String hashedPassword = PasswordHash.hashPassword(password);

                // Check if the username exists and the hashed password matches
                if (users.containsKey(username) && users.get(username).equals(hashedPassword)) {
                    new MainMenuFrame(username).setVisible(true);  // Open the main menu
                    dispose();  // Close the login frame
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid credentials");  // Show error message
                }
            }
        });

        // Create Account Button Action
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateAccountFrame().setVisible(true);  // Open the create account frame
                dispose();  // Close the login frame
            }
        });

        setLayout(new FlowLayout());
        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(loginButton);
        add(createAccountButton);
    }
}
