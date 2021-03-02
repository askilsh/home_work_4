public class Multiply extends Operation
{
	@Override
	public  double	execute(String[] arr)
	{
		double x;
		double y;
		x = Double.valueOf(arr[0]);
		y = Double.valueOf(arr[2]);
		System.out.println("Умножаем? Это я могу, без проблем!");
		return (x * y);
	}
}