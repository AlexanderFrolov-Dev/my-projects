package common.layout;

// Пример использования менеджера расположения SpringLayout

import java.awt.Component;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class SpringLayoutTest
{
	public static void main(String args[])
	{
		JFrame frame = new JFrame("SpringLayoutTest");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container contentPane = frame.getContentPane();

		SpringLayout layout = new SpringLayout();
		contentPane.setLayout(layout);

		Component label = new JLabel("Метка");
		Component field = new JTextField(15);

		contentPane.add(label);
		contentPane.add(field);
		layout.putConstraint(SpringLayout.WEST , label, 10, SpringLayout.WEST , contentPane);
		layout.putConstraint(SpringLayout.NORTH, label, 25, SpringLayout.NORTH, contentPane);
		layout.putConstraint(SpringLayout.NORTH, field, 25, SpringLayout.NORTH, contentPane);
		layout.putConstraint(SpringLayout.WEST , field, 20, SpringLayout.EAST , label      );

		frame.setSize(300, 110);
		frame.setVisible(true);
	}
}
