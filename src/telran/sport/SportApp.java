package telran.sport;

import java.lang.reflect.Constructor;

public class SportApp {

	private static final String BASE_PACKAGE = "telran.sport.";

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception{
	if (args.length < 2) {
		System.out.println("Usage - first argument should contain a class name,"
				+ " second argument a proper property");
		return;
	}
//	Class<Sportsman> clazz = (Class<Sportsman>) Class.forName(BASE_PACKAGE + args[0]);
//	Constructor<Sportsman> constructor = clazz.getConstructor(String.class);
//	Sportsman sportsman = constructor.newInstance(args[1]);
	Sportman sportsman = (Sportman) Class.forName(BASE_PACKAGE + args[0])
			.getConstructor(String.class).newInstance(args[1]);
	sportsman.sportAction();

	}

}
