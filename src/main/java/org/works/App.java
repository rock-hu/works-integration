package org.works;

import java.io.File;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {

		String basePath = "E:/Workspace/works-message/schemas/";
		final String regex = "mule*.xsd";
		File schema = new File(basePath);

		String[] filenames = schema.list(/*
										 * new FilenameFilter() {
										 * 
										 * @Override public boolean accept(File
										 * dir, String name) { Pattern pattern =
										 * Pattern.compile(regex); Matcher
										 * matcher = pattern.matcher(name);
										 * return matcher.matches(); } }
										 */);
		// http://www.mulesoft.org/schema/mule/vm
		String template = "<uri name=\"http://www.mulesoft.org/schema/mule/%s\" uri=\"%s\" />";
		// http://www.mulesoft.org/schema/mule/vm/current/mule-vm.xsd
		String templateCurrentVersion = "<uri name=\"http://www.mulesoft.org/schema/mule/current/%s\" uri=\"%s\" />";
		// http://www.mulesoft.org/schema/mule/vm/3.2/mule-vm.xsd
		String templateSpecVersion = "<uri name=\"http://www.mulesoft.org/schema/mule/%s/%s\" uri=\"%s\" />";

		String muleVersion = "3.3";

		// <uri name="http://www.mulesoft.org/schema/mule/ajax"
		// uri="mule-ajax.xsd" />
		// mule-cxf.xsd
		for (String filename : filenames) {

			String[] tokens = filename.split("-");

			if (tokens != null && tokens.length == 2) {
				String connector = tokens[1];
				connector = connector.substring(0, connector.lastIndexOf("."));

				Object[] params = new Object[] { connector, filename };
				System.out.println(String.format(template, params));
				System.out.println(String
						.format(templateCurrentVersion, params));
				params = new Object[] { muleVersion, filename, filename };
				System.out.println(String.format(templateSpecVersion, params));

			}

			// System.out.println(filename);
		}

	}
}
