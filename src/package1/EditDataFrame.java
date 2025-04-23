package package1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;

public class EditDataFrame extends JFrame {
    private VC vc = VC.getInstance();
    private JTable jobTable, carTable;
    private DefaultTableModel jobModel, carModel;
    private JButton editJobBtn, deleteJobBtn, editCarBtn, deleteCarBtn;

    public EditDataFrame() {
        setTitle("Edit Jobs and Cars");
        setSize(700, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(1, 2));

        // Job Panel
        JPanel jobPanel = new JPanel(new BorderLayout());
        String[] jobColumns = {"Job ID", "Client ID", "Duration", "TimeStamp"};
        jobModel = new DefaultTableModel(jobColumns, 0);

        for (Job job : vc.getJobList()) {
            jobModel.addRow(new Object[]{
                job.getJobID(),
                job.getClientID(),
                job.getJobDuration(),
                job.getDate()
            });
        }
        jobTable = new JTable(jobModel);   

      
        jobPanel.add(new JScrollPane(jobTable), BorderLayout.CENTER);
        


        JPanel jobBtnPanel = new JPanel();
        editJobBtn = new JButton("Edit Job");
        deleteJobBtn = new JButton("Delete Job");
        jobBtnPanel.add(editJobBtn);
        jobBtnPanel.add(deleteJobBtn);
        jobPanel.add(jobBtnPanel, BorderLayout.SOUTH);

        // Car Panel
        JPanel carPanel = new JPanel(new BorderLayout());
        String[] carColumns = {"Car ID", "Owner ID", "Make", "Model", "Year", "Residency"};
        carModel = new DefaultTableModel(carColumns, 0);

        for (Car car : vc.getCarList()) {
            carModel.addRow(new Object[]{
                car.getCarID(),
                car.getOwnerID(),
                car.getMake(),
                car.getModel(),
                car.getYear(),
                car.getRes()
            });
        }

        carTable = new JTable(carModel);
        carPanel.add(new JScrollPane(carTable), BorderLayout.CENTER);

        JPanel carBtnPanel = new JPanel();
        editCarBtn = new JButton("Edit Car");
        deleteCarBtn = new JButton("Delete Car");
        carBtnPanel.add(editCarBtn);
        carBtnPanel.add(deleteCarBtn);
        carPanel.add(carBtnPanel, BorderLayout.SOUTH);

        // Add to main frame
        add(jobPanel);
        add(carPanel);

        addListeners();
        setVisible(true);
    }

    private void addListeners() {
        deleteJobBtn.addActionListener(e -> {
        	int row = jobTable.getSelectedRow();
            if (row != -1) {
                int clientID = Integer.parseInt(jobModel.getValueAt(row, 1).toString());
                vc.deleteJob(clientID);
                jobModel.removeRow(row);
                Database db = new Database();
                db.deleteJobData(clientID);
            }
        });

        editJobBtn.addActionListener(e -> {
        	int row = jobTable.getSelectedRow();
            if (row != -1) {
                try {
                    int jobID = Integer.parseInt(jobModel.getValueAt(row, 0).toString());
                    int clientID = Integer.parseInt(jobModel.getValueAt(row, 1).toString());

                    String durationStr = JOptionPane.showInputDialog(this, "Edit Duration:", jobModel.getValueAt(row, 2));
                    String deadline = jobModel.getValueAt(row, 3).toString();

                    int duration = Integer.parseInt(durationStr);
                    System.out.println("Trying to update Job_ID: " + jobID);

                    Job updated = new Job(jobID, clientID, duration, deadline);
                    vc.updateJob(updated);
                    Database db = new Database();
                    db.updateClientData(clientID, duration, deadline);
                    
                    
                    jobModel.setValueAt(duration, row, 2);
                    jobModel.setValueAt(deadline, row, 3);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid input: duration must be a number.");
                }
            }
        });

        deleteCarBtn.addActionListener(e -> {
        	int row = carTable.getSelectedRow();
            if (row != -1) {
                int ownerID = Integer.parseInt(carModel.getValueAt(row, 1).toString());
                vc.deleteCar(ownerID);
                carModel.removeRow(row);
                Database db = new Database();
                db.deleteCarData(ownerID);
            }
        });
        editCarBtn.addActionListener(e -> {
        	int row = carTable.getSelectedRow();
            if (row != -1) {
                try {
                    int carID = Integer.parseInt(carModel.getValueAt(row, 0).toString());
                    int ownerID = Integer.parseInt(carModel.getValueAt(row, 1).toString());

                    String make = JOptionPane.showInputDialog(this, "Edit Make:", carModel.getValueAt(row, 2));
                    String model = JOptionPane.showInputDialog(this, "Edit Model:", carModel.getValueAt(row, 3));
                    int year = Integer.parseInt(JOptionPane.showInputDialog(this, "Edit Year:", carModel.getValueAt(row, 4)));
                    int res = Integer.parseInt(JOptionPane.showInputDialog(this, "Edit Residency Time:", carModel.getValueAt(row, 5)));
                    System.out.println("Trying to update Car_ID: " + carID);

                    Car updated = new Car(carID, ownerID, make, model, year, res);
                    vc.updateCar(updated);
                    Database db = new Database();
                    db.updateOwnerData(ownerID, make, model, year, res);
                    
                    
                    carModel.setValueAt(make, row, 2);
                    carModel.setValueAt(model, row, 3);
                    carModel.setValueAt(year, row, 4);
                    carModel.setValueAt(res, row, 5);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid year or residency time.");
                }
            }
        });
    }

    public static void main(String[] args) {
        new EditDataFrame();
    }
}