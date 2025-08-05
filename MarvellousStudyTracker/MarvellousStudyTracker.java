import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.io.*;
import java.util.*;

class StudyLog {
    public LocalDate Date;
    public String Subject;
    public double Duration;
    public String Description;

    public StudyLog(LocalDate A, String B, double C, String D) {
        this.Date = A;
        this.Subject = B;
        this.Duration = C;
        this.Description = D;
    }

    public LocalDate getDate() { return Date; }
    public String getSubject() { return Subject; }
    public double getDuration() { return Duration; }
    public String getDescription() { return Description; }
}

class StudyTracker {
    private ArrayList<StudyLog> Database = new ArrayList<>();

    public void insertLog(StudyLog log) {
        Database.add(log);
    }

    public ArrayList<StudyLog> getLogs() {
        return Database;
    }

    public void exportCSV(String filename) {
        if (Database.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No data to export!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try (FileWriter fwobj = new FileWriter(filename)) {
            fwobj.write("Date,Subject,Duration,Description\n");
            for (StudyLog sobj : Database) {
                fwobj.write(sobj.getDate() + "," +
                        sobj.getSubject().replace(",", " ") + "," +
                        sobj.getDuration() + "," +
                        sobj.getDescription().replace(",", " ") + "\n");
            }
            JOptionPane.showMessageDialog(null, "CSV Exported Successfully!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error exporting CSV!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Map<LocalDate, Double> summaryByDate() {
        Map<LocalDate, Double> map = new TreeMap<>();
        for (StudyLog sobj : Database) {
            map.put(sobj.getDate(), map.getOrDefault(sobj.getDate(), 0.0) + sobj.getDuration());
        }
        return map;
    }

    public Map<String, Double> summaryBySubject() {
        Map<String, Double> map = new TreeMap<>();
        for (StudyLog sobj : Database) {
            map.put(sobj.getSubject(), map.getOrDefault(sobj.getSubject(), 0.0) + sobj.getDuration());
        }
        return map;
    }
}

public class MarvellousStudyTracker extends JFrame {
    private StudyTracker tracker;
    private DefaultTableModel tableModel;

    public MarvellousStudyTracker() {
        tracker = new StudyTracker();
        setTitle("Marvellous Study Tracker");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Table
        tableModel = new DefaultTableModel(new String[]{"Date", "Subject", "Duration", "Description"}, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Buttons
        JButton addBtn = new JButton("Add Study Log");
        JButton displayAllBtn = new JButton("Display All Logs");
        JButton reportsBtn = new JButton("Reports");
        JButton exportBtn = new JButton("Export to CSV");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addBtn);
        buttonPanel.add(displayAllBtn);
        buttonPanel.add(reportsBtn);
        buttonPanel.add(exportBtn);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add log button
        addBtn.addActionListener(e -> {
            JTextField subjectField = new JTextField();
            JTextField durationField = new JTextField();
            JTextField descriptionField = new JTextField();
            Object[] fields = {
                    "Subject:", subjectField,
                    "Duration (hours):", durationField,
                    "Description:", descriptionField
            };
            int option = JOptionPane.showConfirmDialog(null, fields, "Add Study Log", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                try {
                    String subject = subjectField.getText();
                    double duration = Double.parseDouble(durationField.getText());
                    String description = descriptionField.getText();
                    StudyLog log = new StudyLog(LocalDate.now(), subject, duration, description);
                    tracker.insertLog(log);
                    tableModel.addRow(new Object[]{log.getDate(), log.getSubject(), log.getDuration(), log.getDescription()});
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Display all logs button
        displayAllBtn.addActionListener(e -> {
            JFrame logsFrame = new JFrame("All Study Logs");
            logsFrame.setSize(600, 400);
            logsFrame.setLocationRelativeTo(null);

            DefaultTableModel logsModel = new DefaultTableModel(new String[]{"Date", "Subject", "Duration", "Description"}, 0);
            for (StudyLog log : tracker.getLogs()) {
                logsModel.addRow(new Object[]{log.getDate(), log.getSubject(), log.getDuration(), log.getDescription()});
            }
            JTable logsTable = new JTable(logsModel);
            logsFrame.add(new JScrollPane(logsTable));
            logsFrame.setVisible(true);
        });

        // Reports button
        reportsBtn.addActionListener(e -> {
            JFrame reportFrame = new JFrame("Reports");
            reportFrame.setSize(700, 500);
            reportFrame.setLocationRelativeTo(null);
            reportFrame.setLayout(new GridLayout(2, 1));

            // Summary by Date
            DefaultTableModel dateModel = new DefaultTableModel(new String[]{"Date", "Total Hours"}, 0);
            for (Map.Entry<LocalDate, Double> entry : tracker.summaryByDate().entrySet()) {
                dateModel.addRow(new Object[]{entry.getKey(), entry.getValue()});
            }
            JTable dateTable = new JTable(dateModel);
            reportFrame.add(new JScrollPane(dateTable));

            // Summary by Subject
            DefaultTableModel subjectModel = new DefaultTableModel(new String[]{"Subject", "Total Hours"}, 0);
            for (Map.Entry<String, Double> entry : tracker.summaryBySubject().entrySet()) {
                subjectModel.addRow(new Object[]{entry.getKey(), entry.getValue()});
            }
            JTable subjectTable = new JTable(subjectModel);
            reportFrame.add(new JScrollPane(subjectTable));

            reportFrame.setVisible(true);
        });

        // Export CSV button
        exportBtn.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                tracker.exportCSV(fileChooser.getSelectedFile().getAbsolutePath());
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MarvellousStudyTracker().setVisible(true));
    }
}
