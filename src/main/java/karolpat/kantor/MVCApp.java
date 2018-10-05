package karolpat.kantor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MVCApp extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JPanel panel;
	private JLabel label;
	private JLabel txtFieldLbl;
	private JTextField textField;
	private JTextField resultField;
	private JButton button;
	private JButton swap;
	private JComboBox<String> combo;
	private JComboBox<String> combo2;

	String code;
	String code1;
	int tempCode;
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
		
		txtFieldLbl = new JLabel("Wprowadz kwotę");
		txtFieldLbl.setBounds(10,30,200,25);
		txtFieldLbl.setFont(new Font("Courier New", 15, 15));
		txtFieldLbl.setForeground(Color.BLACK);
		panel.add(txtFieldLbl);
		
		textField = new JTextField();
		textField.setBounds(10, 55, 150, 20);
		panel.add(textField);

		combo = new JComboBox<String>(codeList());
		combo.setBounds(10, 85, 100, 25);
		panel.add(combo);
		
		swap = new JButton("Odwróć");
		swap.setBounds(130, 85, 100, 25);
		swap.addActionListener(this);		
		panel.add(swap);

		combo2 = new JComboBox<String>(codeList());
		combo2.setBounds(250, 85, 100, 25);
		panel.add(combo2);

		button = new JButton("Go on");
		button.setBounds(10, 115, 100, 25);
		button.addActionListener(this);
		panel.add(button);
		
		resultField = new JTextField();
		resultField.setBounds(10,140,150,25);
		panel.add(resultField);
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

		if(e.getSource() == button) {
			code = (String) combo.getSelectedItem();
			code1 = (String) combo2.getSelectedItem();
			value = Double.parseDouble(textField.getText());
			resultField.setText((App.getResult(code, code1, value).toString()));
		}else if(e.getSource() == swap){
			tempCode = combo.getSelectedIndex();
			combo.setSelectedIndex(combo2.getSelectedIndex());
			combo2.setSelectedIndex(tempCode);
		}
		
		

	}

}
