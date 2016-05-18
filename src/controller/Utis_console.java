package controller;

import java.io.IOException;


public class Utis_console
{
	public static void cmdCommand(String command)
	{
		String[] commands=new String[3];
		commands[0]="cmd.exe";
		commands[1]="/c";	
		commands[2]=command;
	    try
		{
			Runtime.getRuntime().exec(commands);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
