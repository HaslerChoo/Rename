package view;

import java.io.File;
import java.util.Arrays;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class FileTreeModel implements TreeModel
{
	private File root;
	
	public FileTreeModel(File root)
	{
		this.root=root;
	}
	
	@Override
	public void addTreeModelListener(TreeModelListener arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public Object getChild(Object parent, int index)
	{
		File f=(File)parent;
		System.out.println(f.listFiles()[index]);
		if (f.isDirectory())
		{
			return f.listFiles()[index];
		}
		return f.listFiles()[index];
	}

	@Override
	public int getChildCount(Object parent)
	{
		File f=(File)parent;
		if(!f.isDirectory())
		{
			return 0;
		}
		else
		return (!f.isDirectory())? 0 : f.listFiles().length;
	}

	@Override
	public int getIndexOfChild(Object parent, Object child)
	{
		File par=(File)parent;
		File chil=(File)child;
		return Arrays.asList(par.listFiles()).indexOf(chil);
	}

	@Override
	public Object getRoot()
	{
		return root;
	}

	@Override
	public boolean isLeaf(Object node)
	{
		File par=(File)node;
		return !par.isDirectory();
	}

	@Override
	public void removeTreeModelListener(TreeModelListener arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void valueForPathChanged(TreePath arg0, Object arg1)
	{
		// TODO Auto-generated method stub

	}

}
