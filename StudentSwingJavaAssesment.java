package org.tcs;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.StackWalker.StackFrame;
import java.sql.*;

public class StudentSwingJavaAssesment {
	 // JDBC Variables
    static final String JDBC_URL = "jdbc:mysql://localhost:3306/testdb"; // JDBC URL
    static final String JDBC_USER = "root"; //  DB username
    static final String JDBC_PASSWORD = "12345"; //  DB password
    
	public static void main(String[] args) {
		JFrame sframe=new JFrame("My Student_Swing_Example");
		sframe.setSize(500, 500);
		sframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sframe.setLayout(null);
		
		
		
		//Set Labels and TextFeilds
				JLabel studidLbl=new JLabel("ID:");
				studidLbl.setBounds(50, 20, 100, 30);
				sframe.add(studidLbl);
				
				JTextField studidTxt=new JTextField();
				studidTxt.setBounds(150, 20, 200, 30);
				sframe.add(studidTxt);
				
				JLabel FirstnameLbl=new JLabel("First Name:");
				FirstnameLbl.setBounds(50, 55, 100, 30);
				sframe.add(FirstnameLbl);
				
				JTextField FirstnameTxt=new JTextField();
				FirstnameTxt.setBounds(150, 55, 200, 30);
				sframe.add(FirstnameTxt);
				
				JLabel LastnameLbl=new JLabel("Last Name:");
				LastnameLbl.setBounds(50, 90, 100, 30);
				sframe.add(LastnameLbl);
				
				JTextField LastnameTxt=new JTextField();
				LastnameTxt.setBounds(150, 90, 200, 30);
				sframe.add(LastnameTxt);
				
				 JLabel emailLabel = new JLabel("Email:");
			        emailLabel.setBounds(50, 125, 100, 30);
			        sframe.add(emailLabel);

			        JTextField emailTxt = new JTextField();
			        emailTxt.setBounds(150, 125, 200, 30);
			        sframe.add(emailTxt);

			        JLabel mobileLabel = new JLabel("Mobile:");
			        mobileLabel.setBounds(50, 160, 100, 30);
			        sframe.add(mobileLabel);

			        JTextField mobileTxt = new JTextField();
			        mobileTxt.setBounds(150, 160, 200, 30);
			        sframe.add(mobileTxt);
			        
			        // Buttons
			        JButton insertButton = new JButton("INSERT");
			        insertButton.setBounds(50, 200, 150, 30);
			        sframe.add(insertButton);

			        JButton searchButton = new JButton("SEARCH");
			        searchButton.setBounds(250, 200, 150, 30);
			        sframe.add(searchButton);

			        JButton updateButton = new JButton("UPDATE");
			        updateButton.setBounds(50, 250, 150, 30);
			        sframe.add(updateButton);

			        JButton deleteButton = new JButton("DELETE");
			        deleteButton.setBounds(250, 250, 150, 30);
			        sframe.add(deleteButton);
			        
			        // Button Action Listeners logic
			        insertButton.addActionListener(e -> {
			            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
			                String query = "INSERT INTO students (firstname, lastname, email, mobile) VALUES (?, ?, ?, ?)";
			                PreparedStatement ps = connection.prepareStatement(query);
			                String mob=mobileTxt.getText();
			                if (mob.length() != 10) {
			                    JOptionPane.showMessageDialog(sframe, "Enter a valid mobile number");
			                }
			                else
			                {
			                	//ps.setString(1,regidTxt.getText());
			                	ps.setString(1, FirstnameTxt.getText());
			                	ps.setString(2, LastnameTxt.getText());
			                	ps.setString(3, emailTxt.getText());
			               
			                	ps.setString(4,mob);
			                	ps.executeUpdate();
			                	JOptionPane.showMessageDialog(sframe, "Record inserted successfully!");
			                }
			            } catch (SQLException ex) {
			                ex.printStackTrace();
			                JOptionPane.showMessageDialog(sframe, "Error inserting record.");
			            }
			        });
			        
			    
			        searchButton.addActionListener(e -> {
			            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
			                String query = "SELECT * FROM students WHERE studid = ?";
			                PreparedStatement ps = connection.prepareStatement(query);
			                ps.setInt(1, Integer.parseInt(studidTxt.getText()));
			                ResultSet rs = ps.executeQuery();
			                if (rs.next()) {
			                    FirstnameTxt.setText(rs.getString("firstname"));
			                    LastnameTxt.setText(rs.getString("lastname"));
			                    emailTxt.setText(rs.getString("email"));
			                    mobileTxt.setText(rs.getString("mobile"));
			                } else {
			                    JOptionPane.showMessageDialog(sframe, "Record not found.");
			                    System.out.println("No record found");
			                }
			            } catch (SQLException ex) {
			                ex.printStackTrace();
			                JOptionPane.showMessageDialog(sframe, "Error searching record.");
			            }
			        });
			        
			        
			        updateButton.addActionListener(e -> {
			            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
			                String query = "UPDATE students SET firstname = ?, lastname = ?, email = ?, mobile = ? WHERE studid = ?";
			                PreparedStatement ps = connection.prepareStatement(query);
			                
			                String mob=mobileTxt.getText();
			                if (mob.length() != 10) {
			                    JOptionPane.showMessageDialog(sframe, "Enter a valid mobile number");
			                }
			                else
			                {
			                ps.setString(1, FirstnameTxt.getText());
			                ps.setString(2, LastnameTxt.getText());
			                ps.setString(3, emailTxt.getText());
			                ps.setString(4, mob);
			                ps.setInt(5, Integer.parseInt(studidTxt.getText()));
			                ps.executeUpdate();
			                JOptionPane.showMessageDialog(sframe, "Record updated successfully!");
			                }
			                } catch (SQLException ex) {
			                ex.printStackTrace();
			                JOptionPane.showMessageDialog(sframe, "Error updating record.");
			            }
			        });
			        
			        deleteButton.addActionListener(e -> {
			            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
			                String query = "DELETE  FROM students WHERE studid = ?";
			                PreparedStatement ps = connection.prepareStatement(query);
			                ps.setInt(1, Integer.parseInt(studidTxt.getText()));
			     
			                ps.executeUpdate();
			                JOptionPane.showMessageDialog(sframe, "Record deleted successfully!");
				            } catch (SQLException ex) {
				                ex.printStackTrace();
				                JOptionPane.showMessageDialog(sframe, "Error in Deleting record.");
				            }
			            });
				sframe.setVisible(true);
	}

}
