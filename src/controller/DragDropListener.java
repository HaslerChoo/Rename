package controller;

import java.awt.Desktop;
import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class DragDropListener implements DropTargetListener
{

	@Override
	public void drop(DropTargetDropEvent event)
	{

		// Accept copy drops
		event.acceptDrop(DnDConstants.ACTION_COPY);

		// Get the transfer which can provide the dropped item data
		Transferable transferable = event.getTransferable();

		// Get the data formats of the dropped item
		DataFlavor[] flavors = transferable.getTransferDataFlavors();

		// Loop through the flavors
		Object label = event.getDropTargetContext().getComponent();
		for (DataFlavor flavor : flavors)
		{

			try
			{

				// If the drop items are files
				if (flavor.isFlavorJavaFileListType())
				{
					// Get all of the dropped files
					List<File> files = (List) transferable.getTransferData(flavor);
					// Loop them through
					for (File file : files)
					{
						// Print out the file path
						if (label instanceof JLabel)
						{
							ImageIcon imageIcon = new ImageIcon(file.getAbsolutePath());
							Image scaledImage = Utis_Image.ScaledImage(imageIcon.getImage());
							imageIcon.setImage(scaledImage);
							((JLabel) label).setIcon(imageIcon);
						}
						if (label instanceof JTextField)
						{
							if (file.isDirectory())
							{
								((JTextField) label).setText(file
										.getAbsolutePath());
							} else
							{
								JOptionPane.showMessageDialog(null,"Apenas Pastas", "Ficheiro Invalido",JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				}

			} catch (Exception e)
			{

				// Print out the error stack
				e.printStackTrace();
			}
		}

		// Inform that the drop is complete
		event.dropComplete(true);

	}

	@Override
	public void dragEnter(DropTargetDragEvent event)
	{
	}

	@Override
	public void dragExit(DropTargetEvent event)
	{
	}

	@Override
	public void dragOver(DropTargetDragEvent event)
	{
	}

	@Override
	public void dropActionChanged(DropTargetDragEvent event)
	{
	}

}