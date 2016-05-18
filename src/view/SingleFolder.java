package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.dnd.DropTarget;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import controller.DragDropListener;
import controller.Utils_rename;
import controller.Utis_Image;

public class SingleFolder extends JPanel implements ActionListener
{
	private JProgressBar loading_bar;
	private JTextField tf_anime_name;
	private JTextField tf_anime_folder;
	private JLabel lb_anime_name;
	private JLabel lb_anime_folder;
	private JFileChooser fc_search_folder;
	private JButton bn_search_folder;
	private JPanel jp_icon;
	private JLabel lb_icon;
	private JButton bn_actualizar;
	private DragDropListener dragAndDorp_system;

	private void inicializar()
	{
		// label
		lb_anime_name = new JLabel("Nome da Serie");
		lb_anime_folder = new JLabel("Pasta da Serie");
		lb_icon = new JLabel("");
		// jbutton
		bn_actualizar = new JButton("Actualizar");
		bn_search_folder = new JButton("...");
		// JfileChooser
		fc_search_folder = new JFileChooser(System.getProperty("user.dir"));
		// JTextField
		tf_anime_name = new JTextField();
		tf_anime_folder = new JTextField();
		// JPanel
		jp_icon = new JPanel();
		// Jloading Bar
		loading_bar = new JProgressBar();
		// dragAndDorp listener
		dragAndDorp_system = new DragDropListener();
	}

	private void propriedades()
	{
		layout_system();
		border_system();
		lb_anime_name.setBounds(10, 41, 111, 50);
		lb_anime_folder.setBounds(10, 108, 111, 16);
		bn_actualizar.setBounds(12, 162, 80, 24);
		bn_search_folder.setBounds(402, 100, 41, 32);
		fc_search_folder.setBounds(10, 248, 778, 340);
		tf_anime_name.setBounds(102, 50, 289, 32);
		tf_anime_folder.setBounds(102, 100, 289, 32);
		jp_icon.setBounds(468, 28, 289, 220);
		lb_icon.setBounds(12, 23, 265, 185);
		loading_bar.setBounds(12, 211, 431, 22);

		fc_search_folder.setMultiSelectionEnabled(true);
		fc_search_folder.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		tf_anime_name.setColumns(10);
		tf_anime_folder.setColumns(10);
		ImageIcon imageIcon = new ImageIcon(
				"C:\\Users\\Hasler Choo\\Desktop\\green_sword.jpg");
		Image scaledImage = Utis_Image.ScaledImage(imageIcon.getImage());
		imageIcon.setImage(scaledImage);
		lb_icon.setIcon(imageIcon);
		loading_bar.setStringPainted(true);
		loading_bar.setVisible(false);
	}
	


	private void border_system()
	{
		//color system
		Color border_color = new Color(0, 255, 255);
		LineBorder line_border_style = new LineBorder(border_color, 4);

		//border system for jp_icon
		String title_icon_border = "Selecionar Icon da Pasta";
		TitledBorder borderStyle = new TitledBorder(line_border_style,
				title_icon_border, TitledBorder.CENTER, TitledBorder.TOP, null,
				border_color);		
		jp_icon.setBorder(borderStyle);
	}
	

	private void layout_system()
	{
		jp_icon.setLayout(null);
		setLayout(null);
	}
	private void adding_order()
	{
		// adding to jp_icon
		jp_icon.add(lb_icon);
		// adding to frame
		add(lb_anime_name);
		add(lb_anime_folder);
		add(tf_anime_name);
		add(tf_anime_folder);
		add(bn_search_folder);
		add(jp_icon);
		add(loading_bar);
		add(bn_actualizar);
	}

	private void action()
	{
		//button system
		bn_actualizar.addActionListener(this);
		bn_search_folder.addActionListener(this);
		//dragAndDrop system
		new DropTarget(lb_icon, dragAndDorp_system);
		new DropTarget(tf_anime_folder, dragAndDorp_system);
	}

	public SingleFolder()
	{
		inicializar();
		propriedades();
		adding_order();
		action();
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
			}
			System.out.println(result);

		}
		if (action.getSource() == bn_actualizar)
		{
			loading_bar.setVisible(true);
			Utils_rename.rename(tf_anime_name.getText(), tf_anime_folder.getText(), loading_bar);
			System.out.println();
			String iconPath = tf_anime_folder.getText()+"\\icon.ico";
			Utis_Image.changeIconPath(iconPath);
			File iconFile=new File(iconPath);
			Utis_Image.change_folder_ico(new File(tf_anime_folder.getText()), iconFile);
		}

	}

}
