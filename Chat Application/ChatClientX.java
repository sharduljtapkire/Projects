import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatClientX extends JFrame {
    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton, endButton;

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    private BufferedWriter logWriter;

    public ChatClientX() {
        setTitle("Chat Client");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);

        inputField = new JTextField();
        sendButton = new JButton("Send");
        endButton = new JButton("End Chat");

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(inputField, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(sendButton);
        buttonPanel.add(endButton);
        bottomPanel.add(buttonPanel, BorderLayout.EAST);

        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        sendButton.addActionListener(e -> sendMessage());
        inputField.addActionListener(e -> sendMessage());
        endButton.addActionListener(e -> endChat());

        setupLogFile();

        new Thread(() -> startClient()).start();
    }

    private void setupLogFile() {
        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File logFile = new File("ChatHistory_Client_" + timestamp + ".txt");
            logWriter = new BufferedWriter(new FileWriter(logFile, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void logMessage(String msg) {
        try {
            String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
            logWriter.write("[" + time + "] " + msg + "\n");
            logWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startClient() {
        try {
            socket = new Socket("localhost", 12345);
            appendMessage("Connected to server.");

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            String msg;
            while ((msg = in.readLine()) != null) {
                appendMessage("Server: " + msg);
                logMessage("Server: " + msg);

                if (msg.equalsIgnoreCase("bye")) {
                    appendMessage("Server ended the chat.");
                    break;
                }
            }
            closeConnections();
        } catch (IOException e) {
            appendMessage("Connection closed.");
        }
    }

    private void appendMessage(String msg) {
        SwingUtilities.invokeLater(() -> chatArea.append(msg + "\n"));
    }

    private void sendMessage() {
        String msg = inputField.getText().trim();
        if (!msg.isEmpty() && out != null) {
            out.println(msg);
            appendMessage("Client: " + msg);
            logMessage("Client: " + msg);
            inputField.setText("");

            if (msg.equalsIgnoreCase("bye")) {
                endChat();
            }
        }
    }

    private void endChat() {
        if (out != null) {
            out.println("bye");
            appendMessage("You ended the chat.");
            logMessage("Client ended the chat.");
        }
        closeConnections();
        System.exit(0);
    }

    private void closeConnections() {
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (socket != null) socket.close();
            if (logWriter != null) logWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ChatClientX().setVisible(true));
    }
}