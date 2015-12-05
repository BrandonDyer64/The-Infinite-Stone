package ml.dpgames.infinite.stone.main.desktop;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Color;

public class Achievement extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Achievement dialog = new Achievement("Default");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Achievement(String name) {
		getContentPane().setBackground(Color.BLACK);
		getContentPane().setForeground(Color.WHITE);
		setSize(450, 61);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JLabel lblLabel = new JLabel("New Achievement: " + name);
		lblLabel.setForeground(Color.WHITE);
		getContentPane().add(lblLabel);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBackground(Color.BLACK);
		btnOk.setForeground(Color.WHITE);
		btnOk.setActionCommand("ok");
		btnOk.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		Component horizontalGlue = Box.createHorizontalGlue();
		getContentPane().add(horizontalGlue);
		getContentPane().add(btnOk);
	}
}
