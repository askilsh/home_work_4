import java.util.Scanner;

public class Operation // Программа может принимать на вход как одну строку разом (через пробелы) так и по одному числу 
{						// Читать, что просит ввести программа, не будет работать пока не получит ответ
 	public static void		met_print(Double rez) // Метод вывода
	{
		System.out.print("Твое число - ");
		System.out.println(rez);
	}

	public static double	execute(String[] arr) // Бесполезный метод материнского класса
	{
		System.out.println("Я этого не умею");
		return (0);
	}

	public static double	met_opers(String[] arr) // Метод распределяющий тип вычисления
	{
		double	rez;

		if(arr[1].equals("+")) // Смотрим какой знак. Он хранится во втором элементе массива.
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
		else // Если программа не знает этот знак или нам дали вместо знака что-то противное
		{
			System.out.println("Чет не пошло, проверь знак! Давай-ка еще раз");
			return (Double.POSITIVE_INFINITY);
		}
	}

	public static void		met_calc() // Главный исполняемый метод
	{
		String		str_in;
		String		str_in_off;
		String[]	arr;
		double		rez;
		boolean		mark_y;
		boolean		mark_fool;

		mark_y = true;	// Для цикличности функции, если в конце программы подать сигнал о выходе, флаг сменится 
		while(mark_y)	// и цикл завершится 
		{
			Scanner into  =  new Scanner(System.in);
			System.out.println("Что считать? Напиши мне: {a (операция) b}, пробелы не забудь!");
			System.out.println("Или вводи по одному числу. Короче, я разберусь. Если не хочешь считать, напиши {exit}");
			str_in = into.nextLine(); 	// Принимаем строку на вход. Если увидели одно число, то будет впереди еще один 
			if (str_in.equals("exit"))	// сканер, чтобы остальное считать. А если строка с пробелами, то возьмет в ней
			{							
				break;					// Если захотели выйти 
			}
			arr = met_check(str_in);	// Вызов метода первой проверки адекватности ввода 
			if (arr == null)	// Если ввод неадекватный
				continue;
			rez = met_opers(arr);	// Вызов метода выбора операций
			if (rez == (Double.POSITIVE_INFINITY))	// Если введена неадекватная операция
				continue;
			met_print(rez); // Вывод
			System.out.println("Может еще разок? {'y' - Да, 'n' - Нет}");
			mark_fool = true;	// Флаг для работы цикла ожидания адекватного ответа от пользователя
			while(mark_fool)
			{
				str_in_off = into.nextLine(); // Ждем 'y', если новый запуск, или 'n' для завершения
				if (str_in_off.equals("y"))
				{
					break;
				}
				if (str_in_off.equals("n"))
				{
					mark_y = false;
				}
				else // Если получили неадекватный ответ
				{
					System.out.println("Попробуй еще раз, Друг! Я тебя не понял. {'y' - Да, 'n' - Нет}");
					continue;
				}
				mark_fool = false;
			}
		}
	}

	public static String[]	met_split(String str_in)	// Метод, создающий массив рабочих параметров из строк(и)
	{
		String[] arr;
    	String[] buff;
		int i = 0;

		if ((str_in.indexOf(" ") != -1)) // Если видим в строке пробел, то мы ожидаем задания параметров одной строкой
		{
      		System.out.println(" ");	// Пустая строка, чтобы ввод не сливался с выводом
			arr = str_in.split(" ");	// Делим строку подстроки от пробела
      		if ((arr.length == 3))		
			{							// Проверка на адекватность ввода
				buff = arr;
				if (((met_buff(buff[0]).matches("\\d+")) &&
				((met_buff(buff[2]).matches("\\d+")))))
				return (arr);
				else
					arr = null;			
			}							// Если ввод неадекватный
			else
			{
				System.out.println("Ты мне что-то негодное суешь. Пробел не забыл? Проверь знаки!");
				System.out.println("Давай по новой, Друг!");
				return null;
			}
		}
		String[] puff = new String[3]; // Если в строке пробела не встретили значит нам подают по одному числу
		while (puff[2] == null) // Цикл будет принимать числа и знаки пока массив не заполнится до 3
		{						
			puff[0] = str_in;
			if (!(met_buff(str_in).matches("\\d+"))) // Проверки на адекватность ввода чисел
			{
				System.out.print("Ты мне дал ");
				System.out.print(str_in);
				System.out.println(", и что это? Давай по новой, пробелы не забудь!");
				return null;
			}
			System.out.println("Ну окей, значит по одному. Какое действие? Дай мне знак {-}{+}{*}{/}");
				Scanner into = new Scanner(System.in);
				str_in = into.next();
				puff[1] = str_in;
				System.out.println("Еще одно число");
				str_in = into.next();
			if (!(met_buff(str_in).matches("\\d+")))
			{
				System.out.print("Ты мне дал ");
				System.out.print(str_in);
				System.out.println(", и что это? Давай по новой, пробелы не забудь!");
				return null;
			}
			puff[2] = str_in;
		}
		return (puff);	// После всех проверок возвращаем наш массив чисел с операцией
	}

  public static String	met_buff(String str_in)	// Метод работающий с плавающей точкой, проверка на их количество 
	{
		int i = 0;
		boolean dot = false;
		String buff_2 = str_in;
		while (i < str_in.length())
		{
		if (str_in.charAt(i) == '.')
		{
			if (dot)
			{
			System.out.println("Одной точки вполне достаточно. По новой!");
			return buff_2;
			}
			dot = true; 
		}
		i++; 
		}
		buff_2 = buff_2.replace(".","");
		return buff_2;
	}
	public static String[]	met_check(String str_in)	// Метод проверки адекватности ввода строкой
	{
		String[] arr;

		int i = 0;
		int dot = 0;
		while(i < str_in.length())
		{
			if((str_in.charAt(i) < 45) && (str_in.charAt(i) > 57) && (str_in.charAt(i) != '+') &&
			(str_in.charAt(i) != '*') && (str_in.charAt(i) != ' '))
			{
				System.out.println("Что-то ты мне не то втираешь, попробуй еще раз, Друг");
				return null;
			}
			if (str_in.charAt(i) == '.')
			{
				if (dot == 2)
				{
					System.out.println("Одной точки в числе вполне достаточно. По новой!");
					return null;
				}
				dot++; 
			}
				i++;
		}
		arr = met_split(str_in);  // Вызов метода дробящий строку на элементы массива
		return arr;
	}
}