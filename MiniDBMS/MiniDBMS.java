/////////////////////////////////////////////////////////////////////////////
//  File Name   : MiniDBMS.java
//  Description : Mini Database Management System (DBMS) with GUI using Java Swing.
//                Features include:
//                - Insert, Update, Delete employee records
//                - Display all employees in a table
//                - Search employees by ID, Name, Age range, Salary range
//                - Sort employees by Name, Age, or Salary
//                - Backup and restore using serialization
//                - Export employee data to CSV
//                - Input validation for all operations
//  Author      : Shardul Tapkire
//  Date        : 01/10/2025
/////////////////////////////////////////////////////////////////////////////

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.io.*;
import java.util.*;

class Employee implements Serializable
{
    public int EmpID;
    public String EmpName;
    public int EmpAge;
    public String EmpAddress;
    public int EmpSalary;

    private static int Counter;

    static
    {
        Counter = 1;
    }

    public Employee(String name, int age, String address, int salary)
    {
        this.EmpID = Counter++;
        this.EmpName = name;
        this.EmpAge = age;
        this.EmpAddress = address;
        this.EmpSalary = salary;
    }

    public String[] toTableRow()
    {
        return new String[]
        {
            String.valueOf(EmpID), EmpName, String.valueOf(EmpAge),
            EmpAddress, String.valueOf(EmpSalary)
        };
    }
}

class MarvellousDBMS implements Serializable
{
    private LinkedList<Employee> Table;

    public MarvellousDBMS()
    {
        Table = new LinkedList<>();
    }

    public void InsertEmployee(String name, int age, String address, int salary)
    {
        if (name.isEmpty() || age <= 0 || salary < 0)
        {
            return;
        }
        Table.add(new Employee(name, age, address, salary));
    }

    public LinkedList<Employee> getAllEmployees()
    {
        return Table;
    }

    public Employee getEmployeeByID(int id)
    {
        for (Employee e : Table)
        {
            if (e.EmpID == id)
            {
                return e;
            }
        }
        return null;
    }

    public void deleteEmployee(int id)
    {
        Table.removeIf(e -> e.EmpID == id);
    }

    public void updateEmployee(int id, String name, int age, String address, int salary)
    {
        Employee e = getEmployeeByID(id);
        if (e != null)
        {
            if (!name.isEmpty())
            {
                e.EmpName = name;
            }
            if (age > 0)
            {
                e.EmpAge = age;
            }
            if (!address.isEmpty())
            {
                e.EmpAddress = address;
            }
            if (salary >= 0)
            {
                e.EmpSalary = salary;
            }
        }
    }

    public void takeBackup()
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("MiniDBMS.ser")))
        {
            oos.writeObject(this);
        }
        catch (Exception e)
        {
            System.out.println("Backup failed");
        }
    }

    public static MarvellousDBMS restoreBackup()
    {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("MiniDBMS.ser")))
        {
            return (MarvellousDBMS) ois.readObject();
        }
        catch (Exception e)
        {
            return new MarvellousDBMS();
        }
    }

    public void exportCSV(String filename)
    {
        try (PrintWriter pw = new PrintWriter(new File(filename)))
        {
            pw.println("EmpID,Name,Age,Address,Salary");
            for (Employee e : Table)
            {
                pw.println(e.EmpID + "," + e.EmpName + "," + e.EmpAge + "," + e.EmpAddress + "," + e.EmpSalary);
            }
        }
        catch (Exception e)
        {
            System.out.println("Export failed");
        }
    }

    // Sort employees by Name, Age, or Salary
    public void sortByName()
    {
        Table.sort(Comparator.comparing(e -> e.EmpName));
    }

    public void sortByAge()
    {
        Table.sort(Comparator.comparingInt(e -> e.EmpAge));
    }

    public void sortBySalary()
    {
        Table.sort(Comparator.comparingInt(e -> e.EmpSalary));
    }

    // Search employees by Age range
    public LinkedList<Employee> searchByAgeRange(int min, int max)
    {
        LinkedList<Employee> result = new LinkedList<>();
        for (Employee e : Table)
        {
            if (e.EmpAge >= min && e.EmpAge <= max)
            {
                result.add(e);
            }
        }
        return result;
    }

    // Search employees by Salary range
    public LinkedList<Employee> searchBySalaryRange(int min, int max)
    {
        LinkedList<Employee> result = new LinkedList<>();
        for (Employee e : Table)
        {
            if (e.EmpSalary >= min && e.EmpSalary <= max)
            {
                result.add(e);
            }
        }
        return result;
    }
}

public class MiniDBMS
{
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private MarvellousDBMS db;

    public MiniDBMS()
    {
        db = MarvellousDBMS.restoreBackup();
        initialize();
        refreshTable(db.getAllEmployees());
    }

    private void initialize()
    {
        frame = new JFrame("Mini DBMS - GUI Version");
        frame.setBounds(100, 100, 900, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        // Table
        tableModel = new DefaultTableModel(new String[] { "ID", "Name", "Age", "Address", "Salary" }, 0);
        table = new JTable(tableModel);
        frame.getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);

        // Buttons panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 4, 5, 5));

        JButton btnInsert = new JButton("Insert");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
        JButton btnSearchID = new JButton("Search by ID");
        JButton btnSearchName = new JButton("Search by Name");
        JButton btnSearchAge = new JButton("Search by Age Range");
        JButton btnSearchSalary = new JButton("Search by Salary Range");
        JButton btnSortName = new JButton("Sort by Name");
        JButton btnSortAge = new JButton("Sort by Age");
        JButton btnSortSalary = new JButton("Sort by Salary");
        JButton btnBackup = new JButton("Backup");
        JButton btnExport = new JButton("Export CSV");

        panel.add(btnInsert);
        panel.add(btnUpdate);
        panel.add(btnDelete);
        panel.add(btnSearchID);
        panel.add(btnSearchName);
        panel.add(btnSearchAge);
        panel.add(btnSearchSalary);
        panel.add(btnSortName);
        panel.add(btnSortAge);
        panel.add(btnSortSalary);
        panel.add(btnBackup);
        panel.add(btnExport);

        frame.getContentPane().add(panel, BorderLayout.SOUTH);

        // Button actions
        btnInsert.addActionListener(e -> insertEmployee());
        btnUpdate.addActionListener(e -> updateEmployee());
        btnDelete.addActionListener(e -> deleteEmployee());
        btnSearchID.addActionListener(e -> searchByID());
        btnSearchName.addActionListener(e -> searchByName());
        btnSearchAge.addActionListener(e -> searchByAgeRange());
        btnSearchSalary.addActionListener(e -> searchBySalaryRange());

        btnSortName.addActionListener(e ->
        {
            db.sortByName();
            refreshTable(db.getAllEmployees());
        });

        btnSortAge.addActionListener(e ->
        {
            db.sortByAge();
            refreshTable(db.getAllEmployees());
        });

        btnSortSalary.addActionListener(e ->
        {
            db.sortBySalary();
            refreshTable(db.getAllEmployees());
        });

        btnBackup.addActionListener(e ->
        {
            db.takeBackup();
            JOptionPane.showMessageDialog(frame, "Backup done");
        });

        btnExport.addActionListener(e ->
        {
            db.exportCSV("EmployeeData.csv");
            JOptionPane.showMessageDialog(frame, "Exported CSV");
        });

        frame.setVisible(true);
    }

    private void refreshTable(LinkedList<Employee> list)
    {
        tableModel.setRowCount(0);
        for (Employee e : list)
        {
            tableModel.addRow(e.toTableRow());
        }
    }

    private void insertEmployee()
    {
        JTextField name = new JTextField();
        JTextField age = new JTextField();
        JTextField address = new JTextField();
        JTextField salary = new JTextField();
        Object[] message =
        {
            "Name:", name, "Age:", age, "Address:", address, "Salary:", salary
        };

        int option = JOptionPane.showConfirmDialog(frame, message, "Insert Employee", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION)
        {
            try
            {
                db.InsertEmployee(name.getText(), Integer.parseInt(age.getText()), address.getText(), Integer.parseInt(salary.getText()));
                refreshTable(db.getAllEmployees());
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(frame, "Invalid input");
            }
        }
    }

    private void updateEmployee()
    {
        JTextField idField = new JTextField();
        JTextField name = new JTextField();
        JTextField age = new JTextField();
        JTextField address = new JTextField();
        JTextField salary = new JTextField();
        Object[] message =
        {
            "Employee ID:", idField, "New Name:", name, "New Age:", age, "New Address:", address, "New Salary:", salary
        };

        int option = JOptionPane.showConfirmDialog(frame, message, "Update Employee", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION)
        {
            try
            {
                // This is a common pattern, keep it on one line to reflect the single method call signature.
                db.updateEmployee(Integer.parseInt(idField.getText()), name.getText(),
                                  Integer.parseInt(age.getText()), address.getText(),
                                  Integer.parseInt(salary.getText()));
                refreshTable(db.getAllEmployees());
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(frame, "Invalid input");
            }
        }
    }

    private void deleteEmployee()
    {
        String idStr = JOptionPane.showInputDialog(frame, "Enter Employee ID to delete:");

        try
        {
            db.deleteEmployee(Integer.parseInt(idStr));
            refreshTable(db.getAllEmployees());
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(frame, "Invalid ID");
        }
    }

    private void searchByID()
    {
        String idStr = JOptionPane.showInputDialog(frame, "Enter Employee ID to search:");

        try
        {
            Employee e = db.getEmployeeByID(Integer.parseInt(idStr));
            if (e != null)
            {
                refreshTable(new LinkedList<>(Arrays.asList(e)));
            }
            else
            {
                JOptionPane.showMessageDialog(frame, "No record found");
            }
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(frame, "Invalid ID");
        }
    }

    private void searchByName()
    {
        String name = JOptionPane.showInputDialog(frame, "Enter Employee Name to search:");
        LinkedList<Employee> result = new LinkedList<>();

        for (Employee e : db.getAllEmployees())
        {
            if (e.EmpName.equalsIgnoreCase(name))
            {
                result.add(e);
            }
        }

        if (result.isEmpty())
        {
            JOptionPane.showMessageDialog(frame, "No record found");
        }

        refreshTable(result);
    }

    private void searchByAgeRange()
    {
        JTextField min = new JTextField();
        JTextField max = new JTextField();
        Object[] message =
        {
            "Min Age:", min, "Max Age:", max
        };

        int option = JOptionPane.showConfirmDialog(frame, message, "Search by Age Range", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION)
        {
            try
            {
                LinkedList<Employee> result = db.searchByAgeRange(Integer.parseInt(min.getText()), Integer.parseInt(max.getText()));
                if (result.isEmpty())
                {
                    JOptionPane.showMessageDialog(frame, "No records found");
                }
                refreshTable(result);
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(frame, "Invalid input");
            }
        }
    }

    private void searchBySalaryRange()
    {
        JTextField min = new JTextField();
        JTextField max = new JTextField();
        Object[] message =
        {
            "Min Salary:", min, "Max Salary:", max
        };

        int option = JOptionPane.showConfirmDialog(frame, message, "Search by Salary Range", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION)
        {
            try
            {
                LinkedList<Employee> result = db.searchBySalaryRange(Integer.parseInt(min.getText()), Integer.parseInt(max.getText()));
                if (result.isEmpty())
                {
                    JOptionPane.showMessageDialog(frame, "No records found");
                }
                refreshTable(result);
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(frame, "Invalid input");
            }
        }
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> new MiniDBMS());
    }
}