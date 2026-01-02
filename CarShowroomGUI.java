import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.UUID;

public class CarShowroomGUI extends JFrame {

    ArrayList<Showroom> showrooms = new ArrayList<>();
    ArrayList<Employee> employees = new ArrayList<>();
    ArrayList<Car> cars = new ArrayList<>();


    JTable dataTable;
    DefaultTableModel tableModel;

    public CarShowroomGUI() {
        setTitle("Car Showroom Management System");
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());


        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 102, 204)); // Professional Blue
        headerPanel.setPreferredSize(new Dimension(1100, 60));
        JLabel titleLabel = new JLabel("Car Showroom Management System");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        headerPanel.add(titleLabel);
        add(headerPanel, BorderLayout.NORTH);


        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(8, 1, 10, 15)); // Spacing
        menuPanel.setBackground(new Color(50, 50, 50)); // Dark Gray
        menuPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        menuPanel.setPreferredSize(new Dimension(220, 0));

        JButton btnAddShowroom = createStyledButton("Add Showroom");
        JButton btnAddEmployee = createStyledButton("Add Employee");
        JButton btnAddCar = createStyledButton("Add Car");
        

        JLabel viewLabel = new JLabel("View Data", SwingConstants.CENTER);
        viewLabel.setForeground(Color.LIGHT_GRAY);
        viewLabel.setFont(new Font("Segoe UI", Font.ITALIC, 14));

        JButton btnGetShowrooms = createStyledButton("View Showrooms");
        JButton btnGetEmployees = createStyledButton("View Employees");
        JButton btnGetCars = createStyledButton("View Cars");

        menuPanel.add(btnAddShowroom);
        menuPanel.add(btnAddEmployee);
        menuPanel.add(btnAddCar);
        menuPanel.add(viewLabel);
        menuPanel.add(btnGetShowrooms);
        menuPanel.add(btnGetEmployees);
        menuPanel.add(btnGetCars);

        add(menuPanel, BorderLayout.WEST);


        tableModel = new DefaultTableModel();
        dataTable = new JTable(tableModel);
        dataTable.setFillsViewportHeight(true);
        dataTable.setRowHeight(30);
        dataTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        dataTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        dataTable.getTableHeader().setBackground(new Color(230, 230, 230));
        dataTable.setSelectionBackground(new Color(0, 102, 204));
        dataTable.setSelectionForeground(Color.WHITE);
        
        JScrollPane scrollPane = new JScrollPane(dataTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        add(scrollPane, BorderLayout.CENTER);

        -
        btnAddShowroom.addActionListener(e -> addShowroom());
        btnAddEmployee.addActionListener(e -> addEmployee());
        btnAddCar.addActionListener(e -> addCar());

        btnGetShowrooms.addActionListener(e -> viewShowrooms());
        btnGetEmployees.addActionListener(e -> viewEmployees());
        btnGetCars.addActionListener(e -> viewCars());
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setFocusPainted(false);
        button.setBackground(Color.WHITE);
        button.setForeground(new Color(50, 50, 50));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        return button;
    }

    private void viewShowrooms() {
        String[] columns = {"Name", "Address", "Manager", "Employees", "Cars"};
        tableModel.setColumnIdentifiers(columns);
        tableModel.setRowCount(0); // Clear existing data
        
        for (Showroom s : showrooms) {
            Object[] row = {s.showroom_name, s.showroom_address, s.manager_name, s.total_employees, s.total_cars};
            tableModel.addRow(row);
        }
    }

    private void viewEmployees() {
        String[] columns = {"ID", "Name", "Age", "Department", "Showroom"};
        tableModel.setColumnIdentifiers(columns);
        tableModel.setRowCount(0);

        for (Employee e : employees) {
            Object[] row = {e.emp_id, e.emp_name, e.emp_age, e.emp_dept, e.showroom_name};
            tableModel.addRow(row);
        }
    }

    private void viewCars() {
        String[] columns = {"Name", "Color", "Fuel", "Price", "Type", "Transmission"};
        tableModel.setColumnIdentifiers(columns);
        tableModel.setRowCount(0);

        for (Car c : cars) {
            Object[] row = {c.car_name, c.car_color, c.car_fuel_type, c.car_price, c.car_type, c.car_transmission};
            tableModel.addRow(row);
        }
    }

    private void addShowroom() {
        JTextField nameField = new JTextField();
        JTextField addressField = new JTextField();
        JTextField managerField = new JTextField();
        JTextField empCountField = new JTextField();
        JTextField carCountField = new JTextField();

        Object[] message = {
            "Showroom Name:", nameField,
            "Address:", addressField,
            "Manager Name:", managerField,
            "Total Employees:", empCountField,
            "Total Cars:", carCountField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add Showroom", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            Showroom s = new Showroom();
            s.showroom_name = nameField.getText();
            s.showroom_address = addressField.getText();
            s.manager_name = managerField.getText();
            try {
                s.total_employees = Integer.parseInt(empCountField.getText());
                s.total_cars = Integer.parseInt(carCountField.getText());
            } catch (NumberFormatException e) {
                s.total_employees = 0;
                s.total_cars = 0;
            }
            showrooms.add(s);
            JOptionPane.showMessageDialog(this, "Showroom Added Successfully!");
            viewShowrooms(); // Refresh table
        }
    }

    private void addEmployee() {
        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField deptField = new JTextField();
        JTextField showroomField = new JTextField();

        Object[] message = {
            "Employee Name:", nameField,
            "Age:", ageField,
            "Department:", deptField,
            "Showroom Name:", showroomField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add Employee", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            Employee e = new Employee();
            e.emp_id = UUID.randomUUID().toString();
            e.emp_name = nameField.getText();
            try {
                e.emp_age = Integer.parseInt(ageField.getText());
            } catch (NumberFormatException ex) {
                e.emp_age = 0;
            }
            e.emp_dept = deptField.getText();
            e.showroom_name = showroomField.getText();
            employees.add(e);
            JOptionPane.showMessageDialog(this, "Employee Added Successfully!");
            viewEmployees();
        }
    }

    private void addCar() {
        JTextField nameField = new JTextField();
        JTextField colorField = new JTextField();
        JTextField fuelField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField typeField = new JTextField();
        JTextField transField = new JTextField();

        Object[] message = {
            "Car Name:", nameField,
            "Color:", colorField,
            "Fuel Type:", fuelField,
            "Price:", priceField,
            "Type:", typeField,
            "Transmission:", transField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add Car", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            Car c = new Car();
            c.car_name = nameField.getText();
            c.car_color = colorField.getText();
            c.car_fuel_type = fuelField.getText();
            try {
                c.car_price = Integer.parseInt(priceField.getText());
            } catch (NumberFormatException ex) {
                c.car_price = 0;
            }
            c.car_type = typeField.getText();
            c.car_transmission = transField.getText();
            cars.add(c);
            JOptionPane.showMessageDialog(this, "Car Added Successfully!");
            viewCars();
        }
    }

    public static void main(String[] args) {

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {

        }

        SwingUtilities.invokeLater(() -> {
            new CarShowroomGUI().setVisible(true);
        });
    }
}
