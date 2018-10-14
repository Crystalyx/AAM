package aam.utils;

import java.util.ArrayList;
import java.util.List;

import aam.api.Interface.INBTSave;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class NBTHelper
{
	public static NBTTagList saveList(List<? extends INBTSave> l)
	{
		NBTTagList tagl = new NBTTagList();
		for (INBTSave save : l)
		{
			NBTTagCompound tg = new NBTTagCompound();
			save.saveToNBT(tg);
			tg.setString("Class", save.getClass().getName());
			tagl.appendTag(tg);
		}
		return tagl;
	}

	public static List<? extends INBTSave> loadList(NBTTagList tagl)
	{
		List<INBTSave> l = new ArrayList<>();
		for (int i = 0; i < tagl.tagCount(); i++)
		{
			NBTTagCompound tg = tagl.getCompoundTagAt(i);
			try
			{
				Class clazz = Class.forName(tg.getString("Class"));
				Object instance = clazz.newInstance();
				if (instance instanceof INBTSave)
				{
					INBTSave save = (INBTSave) instance;
					save.loadFromNBT(tg);
					l.add(save);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

		return l;
	}
}
