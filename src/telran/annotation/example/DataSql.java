package telran.annotation.example;

import java.lang.reflect.Field;
import java.util.Arrays;

import telran.annotation.*;

public class DataSql {
	public static void dislayTableInfo(Class<?> clazz) {
		String className = clazz.getSimpleName();
		Table table = clazz.getAnnotation(Table.class);
		if(table == null) {
			System.out.printf("class %s does not present a table\n", className);
			return;
		}
		String tableName = table.name();
		System.out.printf("Table name is %s\n", tableName.isEmpty() ? className : tableName);
		System.out.println("-----Columns-----");
		Arrays.stream(clazz.getDeclaredFields()).forEach(DataSql::columnInfo);
	}
	private static void columnInfo(Field field) {
		System.out.printf("column: %s, unique: %s, prime_key: %s\n", getColumnName(field), isUnique(field), field.isAnnotationPresent(Id.class));
	}
	private static boolean isUnique(Field field) {
		if(field.isAnnotationPresent(Id.class)) {
			return true;
		}
		Column column = field.getAnnotation(Column.class);
		return column == null ? false : column.unique();
	}
	private static Object getColumnName(Field field) {
		Column column = field.getAnnotation(Column.class);
		return column == null ? field.getName() : column.name();
	}
}
