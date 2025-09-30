import MarvellousPackerUnpacker.MarvellousPacker;
import MarvellousPackerUnpacker.MarvellousUnpacker;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

// Main Class
public class MarvellousPackUnpackGUI {
    public static void main(String[] args) {
        new LoginFrame();
    }
}

// Login Screen
class LoginFrame extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JLabel lblDate, lblTime;

    public LoginFrame() {
        setTitle("Marvellous Packer Unpacker - Login");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblTitle = new JLabel("Marvellous Packer Unpacker");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setBounds(100, 20, 300, 30);
        add(lblTitle);

        JLabel lblUser = new JLabel("Username:");
        lblUser.setBounds(100, 80, 100, 25);
        add(lblUser);

        txtUsername = new JTextField();
        txtUsername.setBounds(200, 80, 150, 25);
        add(txtUsername);

        JLabel lblPass = new JLabel("Password:");
        lblPass.setBounds(100, 120, 100, 25);
        add(lblPass);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(200, 120, 150, 25);
        add(txtPassword);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(180, 170, 100, 30);
        add(btnLogin);

        lblDate = new JLabel();
        lblDate.setBounds(10, 230, 200, 20);
        add(lblDate);

        lblTime = new JLabel();
        lblTime.setBounds(350, 230, 200, 20);
        add(lblTime);

        // Live Date & Time
        DateTimeThread dateTimeThread = new DateTimeThread(lblDate, lblTime);
        dateTimeThread.start();

        btnLogin.addActionListener(e -> {
            String user = txtUsername.getText();
            String pass = new String(txtPassword.getPassword());

            if (user.equals("Marvellous") && pass.equals("Marvellous")) {
                new MainMenuFrame();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Credentials!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }
}

// Main Menu
class MainMenuFrame extends JFrame {
    private JLabel lblDate, lblTime;

    public MainMenuFrame() {
        setTitle("Marvellous Packer Unpacker - Main Menu");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblTitle = new JLabel("Select Your Choice");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setBounds(150, 20, 300, 30);
        add(lblTitle);

        JButton btnPack = new JButton("Pack");
        btnPack.setBounds(150, 80, 200, 40);
        add(btnPack);

        JButton btnUnpack = new JButton("Unpack");
        btnUnpack.setBounds(150, 140, 200, 40);
        add(btnUnpack);

        lblDate = new JLabel();
        lblDate.setBounds(10, 230, 200, 20);
        add(lblDate);

        lblTime = new JLabel();
        lblTime.setBounds(350, 230, 200, 20);
        add(lblTime);

        DateTimeThread dateTimeThread = new DateTimeThread(lblDate, lblTime);
        dateTimeThread.start();

        btnPack.addActionListener(e -> new PackFrame());
        btnUnpack.addActionListener(e -> new UnpackFrame());

        setVisible(true);
    }
}

// Pack Frame
class PackFrame extends JFrame {
    private JTextField txtDir, txtDest;
    private JPasswordField txtKey; // New field for the key

    public PackFrame() {
        setTitle("Marvellous Packer");
        setSize(500, 350); // Increased height
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblDir = new JLabel("Directory Name:");
        lblDir.setBounds(50, 80, 120, 25);
        add(lblDir);

        txtDir = new JTextField();
        txtDir.setBounds(180, 80, 200, 25);
        add(txtDir);

        JLabel lblDest = new JLabel("Destination File:");
        lblDest.setBounds(50, 120, 120, 25);
        add(lblDest);

        txtDest = new JTextField();
        txtDest.setBounds(180, 120, 200, 25);
        add(txtDest);

        // --- NEW KEY FIELD START ---
        JLabel lblKey = new JLabel("Encryption Key:");
        lblKey.setBounds(50, 160, 120, 25);
        add(lblKey);

        txtKey = new JPasswordField();
        txtKey.setBounds(180, 160, 200, 25);
        add(txtKey);
        // --- NEW KEY FIELD END ---

        JButton btnPack = new JButton("Pack");
        btnPack.setBounds(180, 210, 100, 30); // Moved position
        add(btnPack);

        btnPack.addActionListener(e -> {
            String key = new String(txtKey.getPassword()); // Get the key
            
            if (key.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter an Encryption Key.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                // Pass the key to the packer constructor
                MarvellousPacker packer = new MarvellousPacker(txtDest.getText(), txtDir.getText(), key);
                packer.PackingActivity();
                JOptionPane.showMessageDialog(this, "Packing Completed!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }
}

// Unpack Frame
class UnpackFrame extends JFrame {
    private JTextField txtFile;
    private JPasswordField txtKey; // New field for the key

    public UnpackFrame() {
        setTitle("Marvellous Unpacker");
        setSize(500, 300); // Increased height
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblFile = new JLabel("Packed File:");
        lblFile.setBounds(50, 80, 100, 25);
        add(lblFile);

        txtFile = new JTextField();
        txtFile.setBounds(180, 80, 200, 25);
        add(txtFile);
        
        // --- NEW KEY FIELD START ---
        JLabel lblKey = new JLabel("Decryption Key:");
        lblKey.setBounds(50, 120, 120, 25);
        add(lblKey);

        txtKey = new JPasswordField();
        txtKey.setBounds(180, 120, 200, 25);
        add(txtKey);
        // --- NEW KEY FIELD END ---

        JButton btnUnpack = new JButton("Unpack");
        btnUnpack.setBounds(180, 170, 100, 30); // Moved position
        add(btnUnpack);

        btnUnpack.addActionListener(e -> {
            String key = new String(txtKey.getPassword()); // Get the key

            if (key.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a Decryption Key.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                // Pass the key to the unpacker constructor
                MarvellousUnpacker unpacker = new MarvellousUnpacker(txtFile.getText(), key);
                unpacker.UnpackingActivity();
                JOptionPane.showMessageDialog(this, "Unpacking Completed!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }
}

// Date & Time Thread
class DateTimeThread extends Thread {
    private JLabel lblDate, lblTime;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

    public DateTimeThread(JLabel lblDate, JLabel lblTime) {
        this.lblDate = lblDate;
        this.lblTime = lblTime;
    }

    public void run() {
        while (true) {
            Date now = new Date();
            lblDate.setText("Date: " + dateFormat.format(now));
            lblTime.setText("Time: " + timeFormat.format(now));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}