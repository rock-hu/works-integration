package org.works.message.component;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class URLAlertComponent {
	public Object process(Object object) throws Exception {
		return getData((String) object);
	}

	Map getData(String email) throws ParseException {

		Map result = new HashMap();

		String[] fields = email.split("\n|\r");

		for (String field : fields) {
			if (field.matches("^Host:.*")) {
				result.put("HOST", splitField(field));
			}

			if (field.matches("^Message:.*")) {
				result.put("MESSAGE", splitField(field));
			}

			if (field.matches("^Alert Time:.*")) {
				SimpleDateFormat format = new SimpleDateFormat(
						"yyyy/MM/dd HH:mm:ss.S");
				result.put(
						"TIMESTAMP",
						new Timestamp(format.parse(splitField(field)).getTime()));
			}
		}

		return result;

	}

	String splitField(String field) {
		return field.split(":\\s*", 2)[1];
	}
}
