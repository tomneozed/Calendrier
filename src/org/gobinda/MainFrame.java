package org.gobinda;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import javafx.event.ActionEvent;

public class MainFrame extends JFrame {

	private JPanel contentPane;

	private JTextField[] textFields = new JTextField[42];
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 629, 419);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox yearSelectedComboBox = new JComboBox(getYears());
		yearSelectedComboBox.setBounds(10, 11, 189, 46);
		contentPane.add(yearSelectedComboBox);
		
		JComboBox monthSelectedComboBox = new JComboBox(getMonths());
		monthSelectedComboBox.setBounds(222, 11, 189, 46);
		contentPane.add(monthSelectedComboBox);
		
		JButton btnNewButton = new JButton("Show");
		btnNewButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(java.awt.event.ActionEvent arg0) 
					{
						int yearSelected = Integer.parseInt(yearSelectedComboBox.getSelectedItem().toString());
						int monthSelected = monthSelectedComboBox.getSelectedIndex();
						
						showCalendar(yearSelected, monthSelected);
						
						
					}
				});
		btnNewButton.setBounds(429, 11, 174, 46);
		contentPane.add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 68, 593, 301);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(7, 7, 0, 0));
		
		
		JLabel[] labels = new JLabel[7];
		String[] dayNames = {"Lun", "Mar", "Mer", "Jeu", "Ven", "Sam", "Dim"};
		for(int i=0; i<labels.length; i++)
		{
			labels[i] = new JLabel(dayNames[i]);
			labels[i].setHorizontalAlignment(JLabel.CENTER);
			panel.add(labels[i]);
			
		}
		
		
		for(int i=0; i<textFields.length; i++)
		{
			textFields[i] = new JTextField();
			textFields[i].setHorizontalAlignment(JLabel.CENTER);
			panel.add(textFields[i]);
			
		}
		
		
	}

	protected void showCalendar(int yearSelected, int monthSelected) 
	{
		int[] monthDays = {31,28,31,30,31,30,31,31,30,31,30,31};
		
		int year = 1900;
		int month =0;
		int day =2;
		
		while(colbeKina(yearSelected, monthSelected, year, month))
		{
			if(month == 1 && leapYear(year))
			{
				day +=29;
			}else
			{
				day += monthDays[month];
			}
			
			month++;
			if(month == 12)
			{
				month = 0;
				year++;
			}
			
			day = day % 7;
			
		}
		
		System.out.println(day);
		
		for(int i=0; i<textFields.length; i++ )
		{
			textFields[i].setText("");
		}
		
		int lastValue = monthDays[monthSelected];
		if(monthSelected == 1 && leapYear(yearSelected))
		{
			lastValue++;
		}
		
		for(int i = 1, j = day ; i< lastValue; i++, j++)
		{
			textFields[j].setText(String.valueOf(i));
		}
		
		
		
	}

	private boolean leapYear(int year) 
	{
		boolean ans = false;
		
		if(year % 4 == 0)
		{
			ans = true;
		}
		if(year % 100 == 0)
		{
			ans = false;
		}
		if(year % 400 ==0)
		{
			ans = true;
		}
		
		return ans;
	}

	private boolean colbeKina(int yearSelected, int monthSelected, int year, int month)
	{
		if(yearSelected == year && monthSelected == month)
		{
			return false;
		}
		return true;
	}

	private String[] getMonths() 
	{
		String[] str = {"Janvier", "Fevrier", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre", "Octobre", "Novembre", "Decembre"};
		return str;
	}

	private String[] getYears() 
	{
		String[] str = new String[201];
		
		for(int i=1900, j=0; i<=2100; i++, j++)
		{
			str[j] = String.valueOf(i);
		}
		return str;
	}
}
