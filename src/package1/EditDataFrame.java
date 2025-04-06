package package1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EditDataFrame extends JFrame {
    private VC vc = VC.getInstance();
    private JList<Job> jobList;
    private JList<Car> carList;
    private DefaultListModel<Job> jobModel;
    private DefaultListModel<Car> carModel;
    private JButton editJobBtn, deleteJobBtn, editCarBtn, deleteCarBtn;

    public EditDataFrame() {
        setTitle("Edit Jobs and Cars");
        setSize(700, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(1, 2));

        // Job Panel
        JPanel jobPanel = new JPanel(new BorderLayout());
        jobModel = new DefaultListModel<>();
        for (Job job : vc.getJobList()) {
            jobModel.addElement(job);
        }
        //-------------------------------------------------------------------------
        String[] jobColumns = {"Job ID", "Client ID", "Duration", "Job Deadline"};
        Object[][] jobData = new Object[jobModel.size()][4];
        for (int i = 0; i < jobModel.size(); i++) {
            Job job = jobModel.get(i);
            jobData[i][0] = job.getJobID();
            jobData[i][1] = job.getClientID();
            jobData[i][2] = job.getJobDuration();
            //jobData[i][3] = job.getDate();
        }
        JTable jobTable = new JTable(jobData, jobColumns);
      
        jobPanel.add(new JScrollPane(jobTable), BorderLayout.NORTH);
        
        
        jobList = new JList<>(jobModel);
        jobPanel.add(new JScrollPane(jobList), BorderLayout.CENTER);

        JPanel jobBtnPanel = new JPanel();
        editJobBtn = new JButton("Edit Job");
        deleteJobBtn = new JButton("Delete Job");
        jobBtnPanel.add(editJobBtn);
        jobBtnPanel.add(deleteJobBtn);
        jobPanel.add(jobBtnPanel, BorderLayout.SOUTH);

        // Car Panel
        JPanel carPanel = new JPanel(new BorderLayout());
        carModel = new DefaultListModel<>();
        for (Car car : vc.getCarList()) {
            carModel.addElement(car);
        }
        
        //------------------------------------------------------------------------------
        String[] carColumns = {"Car ID", "Owner ID", "Make", "Model", "Year", "Residency"};
        Object[][] carData = new Object[carModel.size()][6];
        for (int i = 0; i < carModel.size(); i++) {
            Car car = carModel.get(i);
            carData[i][0] = car.getCarID();
            carData[i][1] = car.getOwnerID();
            carData[i][2] = car.getMake();
            carData[i][3] = car.getModel();
            carData[i][4] = car.getYear();
            carData[i][5] = car.getRes();
        }
        JTable carTable = new JTable(carData, carColumns);
       
        carPanel.add(new JScrollPane(carTable), BorderLayout.NORTH);
        
        
        carList = new JList<>(carModel);
        carPanel.add(new JScrollPane(carList), BorderLayout.CENTER);

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
            Job selected = jobList.getSelectedValue();
            if (selected != null) {
                vc.deleteJob(selected.getJobID());
                jobModel.removeElement(selected);
            }
        });

        editJobBtn.addActionListener(e -> {
            Job selected = jobList.getSelectedValue();
            if (selected != null) {
                String durationStr = JOptionPane.showInputDialog(this, "Edit Duration:", selected.getJobDuration());
                String deadline = JOptionPane.showInputDialog(this, "Edit Deadline (MM/dd/yyyy):", selected.getDate());
                try {
                    int duration = Integer.parseInt(durationStr);
                    Job updated = new Job(selected.getJobID(), selected.getClientID(), duration, deadline);
                    vc.updateJob(updated);
                    jobModel.setElementAt(updated, jobList.getSelectedIndex());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid duration.");
                }
            }
        });

        deleteCarBtn.addActionListener(e -> {
            Car selected = carList.getSelectedValue();
            if (selected != null) {
                vc.deleteCar(selected.getCarID());
                carModel.removeElement(selected);
            }
        });

        editCarBtn.addActionListener(e -> {
            Car selected = carList.getSelectedValue();
            if (selected != null) {
                String make = JOptionPane.showInputDialog(this, "Edit Make:", selected.getMake());
                String model = JOptionPane.showInputDialog(this, "Edit Model:", selected.getModel());
                String yearStr = JOptionPane.showInputDialog(this, "Edit Year:", selected.getYear());
                String resStr = JOptionPane.showInputDialog(this, "Edit Residency Time:", selected.getRes());
                try {
                    int year = Integer.parseInt(yearStr);
                    int res = Integer.parseInt(resStr);
                    Car updated = new Car(selected.getCarID(), selected.getOwnerID(), make, model, year, res);
                    vc.updateCar(updated);
                    carModel.setElementAt(updated, carList.getSelectedIndex());
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
