package AAM.Utils.Functions;

import java.util.List;

public abstract class Function<T>
{
	public abstract T count(T t);

	public abstract T count2(T t1, T t2);

	public abstract List<T> countl(T t1, T t2);

}
