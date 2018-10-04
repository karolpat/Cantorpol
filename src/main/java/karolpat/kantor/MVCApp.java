package karolpat.kantor;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MVCApp extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JPanel panel;
	private JLabel label;
	private JTextField textField;
	private JButton button;
	private JComboBox<String> combo;
	private JComboBox<String> combo2;

	String code;
	String code1;
	double value;

	public MVCApp() {
		super("Cantorpol");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 270);

		panel = new JPanel();
		panel.setLayout(null);
		add(panel);

		label = new JLabel("Witaj w Cantorpol");
		label.setBounds(10, 10, 200, 25);
		label.setFont(new Font("Courier New", 15, 15));
		label.setForeground(Color.RED.darker());
		panel.add(label);

		textField = new JTextField("Text field");
		textField.setBounds(10, 45, 150, 20);
		panel.add(textField);

		button = new JButton("Go on");
		button.setBounds(10, 105, 100, 25);
		button.addActionListener(this);
		panel.add(button);

		combo = new JComboBox<String>(codeList());
		combo.setBounds(10, 75, 100, 25);
		panel.add(combo);

		combo2 = new JComboBox<String>(codeList());
		combo2.setBounds(200, 75, 100, 25);
		panel.add(combo2);
	}

	public static String[] codeList() {

		List<Rate> list = new ArrayList<Rate>();
		try {
			list = App.receiveData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] codeArray = new String[list.size()];

		codeArray = App.codeList(list);
		return codeArray;
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("dupa");
		code = (String) combo.getSelectedItem();
		code1 = (String) combo2.getSelectedItem();
		value = Double.parseDouble(textField.getText());
		System.out.println(code + " " + code1);
		System.out.println(value);
		System.out.println(App.getResult(code, code1, value));
		

	}

}
