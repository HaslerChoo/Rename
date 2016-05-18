package controller;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import net.sf.image4j.codec.ico.ICOEncoder;

import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;

public class Utis_Image
{
	private static String iconPath = "";

	public static void changeIconPath(String newPath)
	{
			System.out.println("new " + newPath);
			System.out.println("old " + iconPath);

			String command="move \"" + iconPath + "\" \"" + newPath + "\"";
			Utis_console.cmdCommand(command);
			command="attrib +r +a +s +h /s /d \"" + newPath+"\"";
			Utis_console.cmdCommand(command);
	}

	public static Image ScaledImage(Image img)
	{
		BufferedImage resize = new BufferedImage(280, 200,
				BufferedImage.TYPE_4BYTE_ABGR);

		Graphics2D g2 = resize.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(img, 0, 0, 280, 200, null);
		g2.dispose();
		try
		{
			File file = new File("icon.ico");
			iconPath = file.getAbsolutePath();
			ICOEncoder.write(resize, file);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return resize;
	}

	public static void change_folder_ico(File ficheiro, File icon)
	{
		try
		{
			File path = new File(ficheiro.getAbsolutePath() + "/desktop.ini");

			if (path.exists())
			{
				path.delete();
			}
			path.createNewFile();
			Wini ini = new Wini(path);

			ini.put(".ShellClassInfo", "IconResource", icon.getAbsolutePath()
					+ ",0");
			ini.put("ViewState", "Mode", "");
			ini.put("ViewState", "Vid", "");
			ini.put("ViewState", "FolderType", "Generic");
			ini.store();

			String command="attrib +r +a +s +h /s /d \"" + path.getAbsolutePath()+"\"";
			Utis_console.cmdCommand(command);

		} catch (InvalidFileFormatException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
