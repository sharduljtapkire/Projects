import MarvellousPackerUnpacker.MarvellousPacker;
import MarvellousPackerUnpacker.MarvellousUnpacker;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.File;
import java.io.OutputStream;
import java.io.PrintStream; // Required for redirecting System.out

public class PackerUnpackerUtility extends JFrame { // Renamed class

    // Components for Packing Tab
    private JLabel lPackDirName, lPackFileName;
    private JTextField tfPackDirName, tfPackFileName;
    private JButton bPackBrowseDir, bPack;
    private JFileChooser packFileChooser;
    private JTextArea packOutputArea; // Added JTextArea for displaying packing output

    // Components for Unpacking Tab
    private JLabel lUnpackFileName;
    private JTextField tfUnpackFileName;
    private JButton bUnpackBrowseFile, bUnpack;
    private JFileChooser unpackFileChooser;
    private JTextArea unpackOutputArea; // Added JTextArea for displaying unpacking output

    public PackerUnpackerUtility() { // Renamed constructor
        super("Marvellous Packer Unpacker Utility"); // Set the title of the main window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation
        setSize(600, 550); // Increased window size to accommodate the output text areas
        setLocationRelativeTo(null); // Center the window on the screen

        // Create a JTabbedPane to hold the two functionalities
        JTabbedPane tabbedPane = new JTabbedPane();

        // --- Packing Panel ---
        JPanel packPanel = new JPanel(null); // Use null layout for absolute positioning

        // Input Directory Name components for Packing
        lPackDirName = new JLabel("Input Directory Name:");
        lPackDirName.setBounds(30, 30, 150, 30); // x, y, width, height
        packPanel.add(lPackDirName);

        tfPackDirName = new JTextField();
        tfPackDirName.setBounds(190, 30, 200, 30);
        packPanel.add(tfPackDirName);

        bPackBrowseDir = new JButton("Browse");
        bPackBrowseDir.setBounds(400, 30, 100, 30);
        bPackBrowseDir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                packFileChooser = new JFileChooser();
                packFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Allow only directory selection
                int returnValue = packFileChooser.showOpenDialog(PackerUnpackerUtility.this); // Show dialog relative to main frame
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedDirectory = packFileChooser.getSelectedFile();
                    tfPackDirName.setText(selectedDirectory.getAbsolutePath()); // Set selected directory path
                }
            }
        });
        packPanel.add(bPackBrowseDir);

        // Pack File Name components for Packing
        lPackFileName = new JLabel("Pack File Name:");
        lPackFileName.setBounds(30, 80, 150, 30);
        packPanel.add(lPackFileName);

        tfPackFileName = new JTextField();
        tfPackFileName.setBounds(190, 80, 200, 30);
        packPanel.add(tfPackFileName);

        // Pack Button
        bPack = new JButton("Pack");
        bPack.setBounds(200, 130, 150, 40); // Centered horizontally, adjusted vertically
        bPack.setFont(new Font("Arial", Font.BOLD, 16)); // Make button text larger and bold
        bPack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String dirName = tfPackDirName.getText();
                String packName = tfPackFileName.getText();

                // Clear previous output in the text area
                packOutputArea.setText("");

                // Input validation for packing
                if (dirName.isEmpty() || packName.isEmpty()) {
                    JOptionPane.showMessageDialog(PackerUnpackerUtility.this, "Please enter both directory and pack file names.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                File fobj = new File(dirName);
                if (!fobj.exists() || !fobj.isDirectory()) {
                    JOptionPane.showMessageDialog(PackerUnpackerUtility.this, "The specified directory does not exist or is not a valid directory.", "Directory Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Redirect System.out to the JTextArea for packing activity
                PrintStream originalSystemOut = System.out; // Store original System.out
                System.setOut(new PrintStream(new OutputStream() {
                    @Override
                    public void write(int b) {
                        // Append character to the text area
                        packOutputArea.append(String.valueOf((char) b));
                        // Ensure the caret is always at the end, so new text is visible
                        packOutputArea.setCaretPosition(packOutputArea.getDocument().getLength());
                    }
                }));

                try {
                    // Instantiate MarvellousPacker and perform packing
                    MarvellousPacker mobj = new MarvellousPacker(packName, dirName);
                    mobj.PackingActivity(); // Call the packing logic
                    JOptionPane.showMessageDialog(PackerUnpackerUtility.this, "Packing activity completed successfully! Check output below.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(PackerUnpackerUtility.this, "An error occurred during packing: " + ex.getMessage(), "Packing Error", JOptionPane.ERROR_MESSAGE);
                } finally {
                    // Restore original System.out after the operation
                    System.setOut(originalSystemOut);
                }
            }
        });
        packPanel.add(bPack);

        // Output area for Packing
        packOutputArea = new JTextArea();
        packOutputArea.setEditable(false); // Make it read-only
        // Wrap the text area in a JScrollPane for scrollability
        JScrollPane packScrollPane = new JScrollPane(packOutputArea);
        packScrollPane.setBounds(30, 180, 540, 300); // Position and size for the output area
        packPanel.add(packScrollPane);

        // --- Unpacking Panel ---
        JPanel unpackPanel = new JPanel(null); // Use null layout for absolute positioning

        // Pack File Name components for Unpacking
        lUnpackFileName = new JLabel("Pack File Name:");
        lUnpackFileName.setBounds(30, 30, 150, 30);
        unpackPanel.add(lUnpackFileName);

        tfUnpackFileName = new JTextField();
        tfUnpackFileName.setBounds(190, 30, 200, 30);
        unpackPanel.add(tfUnpackFileName);

        bUnpackBrowseFile = new JButton("Browse");
        bUnpackBrowseFile.setBounds(400, 30, 100, 30);
        bUnpackBrowseFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                unpackFileChooser = new JFileChooser();
                unpackFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY); // Allow only file selection
                int returnValue = unpackFileChooser.showOpenDialog(PackerUnpackerUtility.this); // Show dialog relative to main frame
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = unpackFileChooser.getSelectedFile();
                    tfUnpackFileName.setText(selectedFile.getAbsolutePath()); // Set selected file path
                }
            }
        });
        unpackPanel.add(bUnpackBrowseFile);

        // Unpack Button
        bUnpack = new JButton("Unpack");
        bUnpack.setBounds(200, 80, 150, 40); // Adjusted position to make space for output area
        bUnpack.setFont(new Font("Arial", Font.BOLD, 16)); // Make button text larger and bold
        bUnpack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String packName = tfUnpackFileName.getText();

                // Clear previous output in the text area
                unpackOutputArea.setText("");

                // Input validation for unpacking
                if (packName.isEmpty()) {
                    JOptionPane.showMessageDialog(PackerUnpackerUtility.this, "Please enter the pack file name.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                File fobj = new File(packName);
                if (!fobj.exists() || fobj.isDirectory()) {
                    JOptionPane.showMessageDialog(PackerUnpackerUtility.this, "The specified pack file does not exist or is not a valid file.", "File Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Redirect System.out to the JTextArea for unpacking activity
                PrintStream originalSystemOut = System.out; // Store original System.out
                System.setOut(new PrintStream(new OutputStream() {
                    @Override
                    public void write(int b) {
                        // Append character to the text area
                        unpackOutputArea.append(String.valueOf((char) b));
                        // Ensure the caret is always at the end, so new text is visible
                        unpackOutputArea.setCaretPosition(unpackOutputArea.getDocument().getLength());
                    }
                }));

                try {
                    // Instantiate MarvellousUnpacker and perform unpacking
                    MarvellousUnpacker mobj = new MarvellousUnpacker(packName);
                    mobj.UnpackingActivity(); // Call the unpacking logic
                    JOptionPane.showMessageDialog(PackerUnpackerUtility.this, "Unpacking activity completed successfully! Check output below.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(PackerUnpackerUtility.this, "An error occurred during unpacking: " + ex.getMessage(), "Unpacking Error", JOptionPane.ERROR_MESSAGE);
                } finally {
                    // Restore original System.out after the operation
                    System.setOut(originalSystemOut);
                }
            }
        });
        unpackPanel.add(bUnpack);

        // Output area for Unpacking
        unpackOutputArea = new JTextArea();
        unpackOutputArea.setEditable(false); // Make it read-only
        // Wrap the text area in a JScrollPane for scrollability
        JScrollPane unpackScrollPane = new JScrollPane(unpackOutputArea);
        unpackScrollPane.setBounds(30, 130, 540, 350); // Position and size for the output area
        unpackPanel.add(unpackScrollPane);

        // Add panels to the tabbed pane
        tabbedPane.addTab("Pack Files", packPanel);
        tabbedPane.addTab("Unpack Files", unpackPanel);

        // Add the tabbed pane to the main frame
        add(tabbedPane);

        setVisible(true); // Make the window visible
    }

    public static void main(String[] args) {
        // Run the GUI on the Event Dispatch Thread for thread safety
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PackerUnpackerUtility(); // Instantiating the renamed class
            }
        });
    }
}