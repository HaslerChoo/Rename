package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainView extends JFrame
{
	public MainView() {
		
		JTabbedPane tab = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tab, BorderLayout.CENTER);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		tab.addTab("Pasta Unica", null, new SingleFolder(), null);
		tab.addTab("Pasta Multi", null, new MultiFolder(), null);
		setSize(800,350);
		setResizable(false);
	}
	
	public static void main(String[] args)
	{
		try
		{
			UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e)
		{
			e.printStackTrace();
		}
		new MainView().setVisible(true);
	}

}
