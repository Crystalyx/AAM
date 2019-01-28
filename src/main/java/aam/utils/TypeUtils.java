package aam.utils;

import java.util.ArrayList;
import java.util.List;

public class TypeUtils
{
	// ===========================Strings==================================

	public static void initStr()
	{
		initAlphabet();
		initNum();
	}

	public static void initAlphabet()
	{
		for(char i='A';i<='Z';i++)
		alphUC.add(i+"");

		for(char i='a';i<='a';i++)
			alphUC.add(i+"");
	}

	public static void initNum()
	{
		num.add("0");
		num.add("1");
		num.add("2");
		num.add("3");
		num.add("4");
		num.add("5");
		num.add("6");
		num.add("7");
		num.add("8");
		num.add("9");

	}

	/**
	 * List of Strings with letters in upper case
	 */
	public static List<String> alphUC = new ArrayList<>();
	/**
	 * List of Strings with letters in lower case
	 */
	public static List<String> alphLC = new ArrayList<>();
	/**
	 * List of Strings with numbers
	 */
	public static List<String> num = new ArrayList<>();

	/**
	 * @param s
	 *            String
	 * @param Case
	 *            is upper case?
	 * @return
	 */
	public static int indexOfCase(String s, boolean Case)
	{
		if (Case)
		{
			for (int i = 0; i < s.length(); i++)
			{
				String Char = s.substring(i, i + 1);
				if (alphUC.contains(Char))
				{
					return i;
				}
			}
		}
		else
		{
			for (int i = 0; i < s.length(); i++)
			{
				String Char = s.substring(i, i + 1);
				if (alphLC.contains(Char))
				{
					return i;
				}
			}
		}
		return -1;
	}

	/**
	 * @param s
	 *            String
	 * @param Case
	 *            is upper case?
	 * @param index
	 *            start index
	 * @return
	 */
	public static int indexOfCase(String s, boolean Case, int index)
	{
		if (Case)
		{
			for (int i = index; i < s.length(); i++)
			{
				String Char = s.substring(i, i + 1);
				if (alphUC.contains(Char))
				{
					return i;
				}
			}
		}
		else
		{
			for (int i = index; i < s.length(); i++)
			{
				String Char = s.substring(i, i + 1);
				if (alphLC.contains(Char))
				{
					return i;
				}
			}
		}
		return -1;
	}

	/**
	 * @param s
	 *            String
	 * @param index
	 *            start index
	 * @return
	 */
	public static int indexOfWord(String s, int index)
	{
		for (int i = index; i < s.length(); i++)
		{
			String Char = s.substring(i, i + 1);
			if (alphUC.contains(Char) || alphLC.contains(Char))
			{
				return i;
			}
		}
		return -1;
	}

	/**
	 * @param s
	 *            String
	 * @return
	 */
	public static int indexOfWord(String s)
	{
		for (int i = 0; i < s.length(); i++)
		{
			String Char = s.substring(i, i + 1);
			if (alphUC.contains(Char) || alphLC.contains(Char))
			{
				return i;
			}
		}
		return -1;
	}

	/**
	 * @param s
	 *            String
	 * @param index
	 *            start index
	 * @return
	 */
	public static int indexOfNum(String s, int index)
	{
		for (int i = index; i < s.length(); i++)
		{
			String Char = s.substring(i, i + 1);
			if (num.contains(Char))
			{
				return i;
			}
		}
		return -1;
	}

	/**
	 * @param s
	 *            String
	 * @return
	 */
	public static int indexOfNum(String s)
	{
		for (int i = 0; i < s.length(); i++)
		{
			String Char = s.substring(i, i + 1);
			if (num.contains(Char))
			{
				return i;
			}
		}
		return -1;
	}

	/**
	 * 
	 * @param s
	 *            String
	 * @return last index of number
	 */
	public static int fullIntIndex(String s)
	{
		int lastId = -1;
		for (int i = 0; i < s.length(); i++)
		{
			String Char = s.substring(i, i + 1);
			if (num.contains(Char))
			{
				lastId = i + 1;
			}
			else
			{
				return lastId;
			}
		}
		return lastId;
	}

	/**
	 * 
	 * @param s
	 *            String
	 * @param index
	 *            start index
	 * @return start and last index of number
	 */
	public static Pair<Integer, Integer> fullIntIndex(String s, int index)
	{
		for (int i = index; i < s.length(); i++)
		{
			String Char = s.substring(i, i + 1);
			if (!num.contains(Char))
			{
				return new Pair<>(index, i);
			}
		}
		return new Pair<>(index, -1);
	}

	/**
	 * 
	 * @param s
	 *            String
	 * @return last index of number
	 */
	public static boolean startWithInt(String s)
	{
		for (int i = 0; i < num.size(); i++)
		{
			if (s.startsWith(num.get(i)))
			{
				return true;
			}
		}
		return false;
	}
}
