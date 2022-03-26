package telran.annotation.calidation.constraints;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Stream;

import telran.annotation.validation.constraints.Max;

/* V.R. There are not following files:
 * 1. Class XXX.java with fields and annotation
 * 2. Appl.java with main
 * It is impossible to test something without these files
 */

public class Validator { 
	/**
	 * validates the given object against the constraints in the package telran.annotation.validation.constraints
	 * @param obj
	 * @return list constraint violation messages or empty list if no violations
	 */
	// V.R. What is this? 
	// Annotation has to be calculated as field.getAnnotation(Max.class)
	// for each of fields.
	@Max(value = 1000000)
	Max maxAnnotation;
	@Min(value = 0)
	Min minAnnotation;
	@Pattern(value = "")
	Pattern patternAnnotation;
	
	
		public List<String> validate(Object obj) {
		//TODO
			
			List<String> listViolations = new LinkedList<>();
			
			Field[] fields = obj.getClass().getDeclaredFields();
			Stream.of(fields).forEach(f -> {
				// V.R. "!-" has to be changed to "=="
				if(f.getAnnotation(Valid.class)!=null) {
					addViolationsMasseges(listViolations, f, obj);
				}else {
					try {
						/* V.R. This recursive call will clear the list of 
						 * violations and previously save violations
						 * will be cleared.
						 */
						listViolations.addAll(validate(f.get(obj)));
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					} 
				}
				
			});
			return listViolations;
			
		}
		private void addViolationsMasseges(List<String> list, Field f, Object obj) {
			String maxViolation = max(f,obj);
			if(!maxViolation.isEmpty()) {
				list.add(maxViolation);
			}
			String minViolation = min(f,obj);
			if(!minViolation.isEmpty()) {
				list.add(minViolation);
			}
			String patternViolation = pattern(f,obj);
			if(!patternViolation.isEmpty()) {
				list.add(patternViolation);
			}
			
		}
		private String max(Field field, Object obj) {
			//TODO
				String res = "";
				try {
					double fieldValue = field.getDouble(obj);
					if(fieldValue >= maxAnnotation.value()) {
						res = maxAnnotation.message() + fieldValue + " is equal or more then " + maxAnnotation.value();
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				} 
				return res;
		}
		private String min(Field field, Object obj) {
			String res = "";
			try {
				double fieldValue = field.getDouble(obj);
				if(fieldValue < minAnnotation.value()) {
					res = minAnnotation.message() + fieldValue + " is less then " + minAnnotation.value();
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			} 
			return res;
		}
		private String pattern(Field field, Object obj) {
			String res = "";
			try {
				String fieldValue = String.valueOf(field.get(obj));
				if(!fieldValue.equals(patternAnnotation.value())) {
					res = patternAnnotation.message() + fieldValue + " is not equal to " + patternAnnotation.value();
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			} 
			return res;
		}
}
