import java.util.Scanner;

public class Operation // Программа может принимать на вход как одну строку разом (через пробелы) так и по одному числу 
{						// Читать, что просит ввести программа, не будет работать пока не получит ответ
	
 	public   void	met_print(Double rez) // Метод вывода
	{
		System.out.print("Твое число - ");
		System.out.println(rez);
	}
	
	public 	double	execute(String[] arr) // Бесполезный метод материнского класса
	{
		System.out.println("Я этого не умею");
		return (0);
	}


	public   String[]	met_split(String str_in)	// Метод, создающий массив рабочих параметров из строк(и)
	{
		String[] arr;
    	String[] buff;

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

  public   String	met_buff(String str_in)	// Метод работающий с плавающей точкой, проверка на их количество 
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
	public  String[]	met_check(String str_in)	// Метод проверки адекватности ввода строкой
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