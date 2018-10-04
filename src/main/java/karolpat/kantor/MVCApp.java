package karolpat.kantor;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MVCApp extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel panel;
	private JLabel label;
	private JTextField textField;
	private JButton button;
	
	public MVCApp(){
		super("First frame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,400,270);
		
		panel= new JPanel();
		panel.setLayout(null);
		add(panel);
		
		label = new JLabel("Witaj w Cantorpol");
		label.setBounds(10,10,200,25);
		label.setFont(new Font("Courier New",15, 15));
		label.setForeground(Color.RED.darker());
		panel.add(label);
		
		textField = new JTextField("Text field");
		textField.setBounds(10, 45, 150, 20);
		panel.add(textField);
		
		button = new JButton("Go on");
		button.setBounds(10, 75, 100, 25	);
		panel.add(button);
	}
	

}
