import java.util.Scanner;

public class Body
{

	static Dev dev = new Dev();
	static Plus plus = new Plus();
	static Minus minus = new Minus();
	static Multiply mult = new Multiply();
	static Operation oper = new Operation();

	public static void main(String[] args) // Просто тело. Запуск основной программы.
	{
		met_calc();
	}

	static double met_opers(String[] arr) // Метод распределяющий тип вычисления
	{
		double rez;

		if (arr[1].equals("+")) // Смотрим какой знак. Он хранится во втором элементе массива.
		{
			rez = plus.execute(arr);
			return rez;
		}
		if (arr[1].equals("-")) {
			rez = minus.execute(arr);
			return rez;
		}
		if (arr[1].equals("/")) {
			rez = dev.execute(arr);
			return rez;
		}
		if (arr[1].equals("*")) {
			rez = mult.execute(arr);
			return rez;
		} else // Если программа не знает этот знак или нам дали вместо знака что-то противное
		{
			System.out.println("Чет не пошло, проверь знак! Давай-ка еще раз");
			return (Double.POSITIVE_INFINITY);
		}
	}

	public static void met_calc() // Главный исполняемый метод
	{
		String str_in;
		String str_in_off;
		String[] arr;
		double rez;
		boolean mark_y;
		boolean mark_fool;

		mark_y = true; // Для цикличности функции, если в конце программы подать сигнал о выходе, флаг
						// сменится
		while (mark_y) // и цикл завершится
		{
			Scanner into = new Scanner(System.in);
			System.out.println("Что считать? Напиши мне: {a (операция) b}, пробелы не забудь!");
			System.out.println("Или вводи по одному числу. Короче, я разберусь. Если не хочешь считать, напиши {exit}");
			str_in = into.nextLine(); // Принимаем строку на вход. Если увидели одно число, то будет впереди еще один
			if (str_in.equals("exit")) // сканер, чтобы остальное считать. А если строка с пробелами, то возьмет в ней
			{
				break; // Если захотели выйти
			}
			arr = oper.met_check(str_in); // Вызов метода первой проверки адекватности ввода
			if (arr == null) // Если ввод неадекватный
				continue;
			rez = met_opers(arr); // Вызов метода выбора операций
			if (rez == (Double.POSITIVE_INFINITY)) // Если введена неадекватная операция
				continue;
			oper.met_print(rez); // Вывод
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
}
