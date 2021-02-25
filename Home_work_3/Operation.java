import java.util.Scanner;

public class Operation
{	
	public static void		met_print(Double rez)
	{
		System.out.print("Твое число - ");
		System.out.println(rez);
	}

	public static double	execute(String[] arr)
	{
		System.out.println("Я этого не умею");
		return (0);
	}

	public static double	met_opers(String[] arr)
	{
		double	rez;

		if(arr[1].equals("+"))
		{
			rez = Plus.execute(arr);
			return rez;
		}
		if(arr[1].equals("-"))
		{
			rez = Minus.execute(arr);
			return rez;
		}
		if(arr[1].equals("/"))
		{
			rez = Dev.execute(arr);
			return rez;
		}	
		if(arr[1].equals("*"))
		{
			rez = Multiply.execute(arr);
			return rez;
		}
		else
		{
			System.out.println("Чет не пошло, давай-ка еще раз");
			return (Double.POSITIVE_INFINITY);
		}
	}

	public static String[]	met_split(String str_in)
	{
		String[] arr;
		int i = 0;

		if (str_in.charAt(i) == ' ')
		{
			arr = str_in.split(" ");
			String[] buff = arr;
			buff[0].replace(".", "");
			buff[0].replace("-", "");
			buff[2].replace(".", "");
			buff[2].replace("-", "");
			if ((arr.length == 3) && (matches("\\d+")) && (buff[2].matches("\\d+")))
				return (arr);
			else
			{
				System.out.print("Ты мне что-то негодное суешь. Второй пробел не забы? Проверь знаки!");
				System.out.println("Давай по новой, Друг!");
				return null;
			}
		}
		String buff_2 = str_in;
		String[] puff = new String[2];
		while (!(puff[2].matches("\\d")))
		{
			puff[0] = str_in;
			System.out.println("Ну окей, значит по одному. Какое действие? Дай мне знак {-}{+}{*}{/}");
			Scanner into = new Scanner(System.in);
			str_in = into.next();
			
			puff[1] = str_in;
			System.out.println("Еще одно число");
			puff[2] = str_in;
			buff_2 = buff_2.replace(".","");
		}
		return (puff);
	}

	public static String[]	met_check(String str_in)
	{
		String[] arr;

		int i = 0;
		while(i < str_in.length())
		{
			if((str_in.charAt(i) < 45) && (str_in.charAt(i) > 57) && (str_in.charAt(i) != '+') &&
			(str_in.charAt(i) != '*') && (str_in.charAt(i) != ' '))
			{
				System.out.println("Что-то ты мне не то втираешь, попробуй еще раз, Друг");
				return null;
			}
			i++;
		}
		arr = met_split(str_in);
		return arr;

	}

	public static void		met_calc()
	{
		String		str_in;
		String		str_in_off;
		String[]	arr;
		double		rez;
		boolean		mark_y;
		boolean		mark_fool;

		mark_y = true;
		while(mark_y)
		{
			Scanner into  =  new Scanner(System.in);
			System.out.println("Что считать? Напиши мне: {a (операция) b}, пробелы не забудь");
			System.out.println("Или вводи по одному числу. Короче, я разберусь. Если не хочешь считать, напиши {exit}");
			str_in = into.nextLine(); 
			if (str_in.equals("exit"))
			{
				break;
			}
			arr = met_check(str_in);
			if (arr == null)
				continue;
			rez = met_opers(arr);
			if (rez == (Double.POSITIVE_INFINITY))
				continue;
			met_print(rez);
			System.out.println("Может еще разок? {'y' - Да, 'n' - Нет}");
			mark_fool = true;
			while(mark_fool)
			{
				str_in_off = into.nextLine();
				if (str_in_off.equals("y"))
				{
					break;
				}
				if (str_in_off.equals("n"))
				{
					mark_y = false;
				}
				else
				{
					System.out.println("Попробуй еще раз, Друг! Я тебя не понял. {'y' - Да, 'n' - Нет}");
					continue;
				}
				mark_fool = false;
			}
		}
	}
}