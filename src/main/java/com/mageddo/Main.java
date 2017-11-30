package com.mageddo;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Main {
	public static void main(String[] args) throws Exception {

		final URL resource = Main.class.getResource("/myprogram");
		if (resource == null) {
			throw new RuntimeException("program not found");
		}

		final File targetProgram = new File("/tmp/myprogram");
		Files.copy(resource.openStream(), targetProgram.toPath(), StandardCopyOption.REPLACE_EXISTING);
		targetProgram.setExecutable(true);

		final Process p = Runtime.getRuntime().exec(targetProgram.getAbsolutePath());
		System.out.println(p.waitFor());

		for (int c; (c = p.getInputStream().read()) != -1; ) {
			System.out.print((char) c);
		}
		System.out.println();

		for (int c; (c = p.getErrorStream().read()) != -1; ) {
			System.out.print((char) c);
		}
		System.out.println();
	}
}
