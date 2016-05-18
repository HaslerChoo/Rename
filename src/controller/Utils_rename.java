package controller;

import java.io.File;

import javax.swing.JProgressBar;

public class Utils_rename
{
	public static void rename(String name, String folder_path,
			JProgressBar loading_bar)
	{
		File pasta = new File(folder_path);
		boolean hasLoad = loading_bar != null;
		if (!pasta.isDirectory())
		{
			System.out.println("is not directory");
			return;
		}

		File[] listFiles = pasta.listFiles();
		int totalLoad = listFiles.length;
		for (int i = 0; i < listFiles.length; i++)
		{
			if (!listFiles[i].isDirectory())
			{
				String nome = listFiles[i].getName();
				if (hasLoad)
				{
					loading_bar.setValue(((i+1)*100)/totalLoad);
				}
				String number = extratNumber(nome);
				String extensao = extrarExtensao(listFiles[i]);
				if (extensao.toLowerCase().contains(".ini") ||extensao.toLowerCase().contains(".ico"))
				{
					continue;
				}
				String new_name = pasta.getAbsoluteFile() + "\\" + name + " "
						+ number + extensao;
				listFiles[i].renameTo(new File(new_name));
			} 

		}

		System.out.println("Done");
	}

	public static String extratNumber(String nome)
	{
		String number = "";
		boolean haveNumber = false;
		for (int i = 0; i < nome.length(); i++)
		{

			if (Character.isDigit(nome.charAt(i)))
			{
				number += nome.charAt(i);
				haveNumber = true;
				continue;
			}
			if (haveNumber)
			{
				break;
			}
		}

		return number;
	}

	public static String extrarExtensao(File ficheiro)
	{
		String nome = ficheiro.getName();
		int posicao = 0;
		if (!ficheiro.isDirectory())
		{
			for (int i = 0; i < nome.length(); i++)
			{
				posicao = i;
				if ((nome.charAt(i) + "").equals("."))
				{
					break;
				}
			}
		}
		return nome.substring(posicao);
	}

}
