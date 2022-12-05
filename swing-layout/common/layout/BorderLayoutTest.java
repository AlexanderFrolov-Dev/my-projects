package common.layout;

// Пример полярного расположения компонентов - BorderLayout

import java.awt.*;
import javax.swing.*;

public class BorderLayoutTest extends JFrame
{
	private static final long serialVersionUID = 1L;
	public BorderLayoutTest()
	{
		super("BorderLayoutTest");
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		setSize(250, 250);
		// Панель содержимого
		Container container = getContentPane();
		/*
		 *  Размещаем в панели компоненты
		 *  В качестве параметров можно использовать 
		 *  строки и константы класса BorderLayout
		 */
		container.add(new JButton("Север" ), "North"); 
		container.add(new JButton("Юг"    ), "South");
		container.add(new JLabel ("Запад" ), BorderLayout.WEST);
		container.add(new JLabel ("Восток"), BorderLayout.EAST);
		// При отсутствии параметра компонент автоматически размещается в центре
		container.add(new JButton("Центр"));
		// Открываем окно
		setVisible(true);
	}
	public static void main(String[] args)
	{
		new BorderLayoutTest();
	}
}
