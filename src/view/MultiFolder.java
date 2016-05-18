package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.dnd.DropTarget;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import controller.DragDropListener;
import controller.Utils_rename;

import javax.swing.JCheckBox;

import java.awt.GridLayout;

import javax.swing.JScrollPane;
import javax.swing.JScrollBar;

public class MultiFolder extends JPanel implements ActionListener
{
	private JProgressBar loading_bar;
	private JTextField tf_anime_folder;
	private JLabel lb_anime_folder;
	private JFileChooser fc_search_folder;
	private JButton bn_search_folder;
	private JButton bn_refresh_list;
	private JButton bn_select_all;
	
	private JPanel jp_icon;
	private JButton bn_actualizar;
	private DragDropListener dragAndDorp_system;
	private ArrayList<JCheckBox> check_box_system;
	
	private JScrollPane scrollPane;
	private JButton bn_unselect_all;
	private void inicializar()
	{
		lb_anime_folder = new JLabel("Pasta da Serie");
		// jbutton
		bn_actualizar = new JButton("Actualizar");
		bn_search_folder = new JButton("...");
		bn_refresh_list = new JButton("refresh List");
		bn_select_all = new JButton("Selecionar todos");
		bn_unselect_all = new JButton("Deslecionar todos");
		// JfileChooser
		fc_search_folder = new JFileChooser(System.getProperty("user.dir"));
		tf_anime_folder = new JTextField(System.getProperty("user.dir"));
		// Jloading Bar
		loading_bar = new JProgressBar();
		// dragAndDorp listener
		dragAndDorp_system = new DragDropListener();
		check_box_system=new ArrayList<JCheckBox>();
		jp_icon=new JPanel();
		
		scrollPane = new JScrollPane();
	}

	private void propriedades()
	{
		layout_system();
		scrollPane.setBounds(516, 30, 252, 258);
		lb_anime_folder.setBounds(12, 47, 111, 16);
		bn_actualizar.setBounds(12, 163, 80, 24);
		bn_search_folder.setBounds(402, 39, 41, 32);
		bn_refresh_list.setBounds(12, 104, 121, 24);
		bn_select_all.setBounds(256, 104, 134, 24);
		bn_unselect_all.setBounds(256, 163, 134, 24);
		fc_search_folder.setBounds(10, 248, 778, 340);
		tf_anime_folder.setBounds(101, 39, 289, 32);
		loading_bar.setBounds(12, 211, 431, 22);
		
		File file=new File(System.getProperty("user.dir"));
		refreshList(file);

		fc_search_folder.setMultiSelectionEnabled(true);
		fc_search_folder.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		tf_anime_folder.setColumns(10);
		loading_bar.setStringPainted(true);
	}

	

	private void layout_system()
	{
		setLayout(null);
		jp_icon.setLayout(new GridLayout(1, 0, 0, 0));
	}
	private void adding_order()
	{
		addCheckBox();
		scrollPane.setViewportView(jp_icon);
		add(lb_anime_folder);
		add(tf_anime_folder);
		add(bn_search_folder);
		add(loading_bar);
		add(bn_actualizar);
		add(bn_refresh_list);
		add(bn_select_all);
		add(scrollPane);
		add(bn_unselect_all);
	}

	private void addCheckBox()
	{
		int rows=check_box_system.size()+1;
		jp_icon.setLayout(new GridLayout(rows, 0, 0, 0));;
		for (JCheckBox jCheckBox : check_box_system)
		{
			jp_icon.add(jCheckBox);
		}

	}

	private void action()
	{
		//button system
		bn_actualizar.addActionListener(this);
		bn_search_folder.addActionListener(this);
		bn_refresh_list.addActionListener(this);
		bn_select_all.addActionListener(this);
		bn_unselect_all.addActionListener(this);
		new DropTarget(tf_anime_folder, dragAndDorp_system);
	}

	public MultiFolder()
	{
		inicializar();
		propriedades();
		adding_order();
		action();
	}

	private void refreshList(File test)
	{
		try
		{
			check_box_system.clear();
			jp_icon.removeAll();
			for (int i = 0; i < test.listFiles().length; i++)
			{
				File temp_file=test.listFiles()[i];					
				if (temp_file.isDirectory())
				{
					JCheckBox temp_check_box = new JCheckBox(temp_file.getName());
					check_box_system.add(temp_check_box);
				}
			}
			addCheckBox();
			scrollPane.setViewportView(jp_icon);			
		}
		catch(NullPointerException e)
		{
			JOptionPane.showMessageDialog(this, "Caminho invalido");
		}
	}

	@Override
	public void actionPerformed(ActionEvent action)
	{
		if (action.getSource() == bn_search_folder)
		{
			int result = fc_search_folder.showDialog(null, "Seleccionar");
			if (result == JFileChooser.APPROVE_OPTION)
			{
				tf_anime_folder.setText(fc_search_folder.getSelectedFile()
						.getAbsolutePath());
				
				File test=new File(fc_search_folder.getSelectedFile()
						.getAbsolutePath());
				
				refreshList(test);
			}
			System.out.println(result);

		}
		if (action.getSource() == bn_actualizar)
		{
			File caminho=new File(tf_anime_folder.getText());
			if (caminho.exists()) {
				File[] listFiles = caminho.listFiles();
			
				for (int i = 0; i < listFiles.length; i++) {
					String name=listFiles[i].getName();
					String path=caminho+"\\"+name;				
					Utils_rename.rename(name, path,null);
					loading_bar.setValue((i+1)*100/listFiles.length);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Caminho invalido");
			}
		}
		if (action.getSource() == bn_refresh_list)
		{
			File test=new File(tf_anime_folder.getText());	
			refreshList(test);	
		}
		if (action.getSource() == bn_select_all)
		{
			
			for (JCheckBox jCheckBox : check_box_system)
				jCheckBox.setSelected(true);
			}
		
		if (action.getSource() == bn_unselect_all)
		{
			
			for (JCheckBox jCheckBox : check_box_system)
			{
				jCheckBox.setSelected(false);
			}
		}

	}

	
}
