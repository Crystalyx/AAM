package aam.utils;

public class Pair<K, V>
{
	public K key;
	public V value;

	public Pair(K key, V value)
	{
		this.key = key;
		this.value = value;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof Pair)
		{
			Pair p = (Pair) obj;
			return p.key == this.key && p.value == this.value;
		}
		return false;
	}
}
