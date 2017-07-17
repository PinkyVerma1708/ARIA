package utils;

import java.io.File;

public class test {

	public static void main(String... s) {
		File file = new File("Screenshots");
		if (!file.exists()) {
			file.mkdirs();
		}
	}
}
