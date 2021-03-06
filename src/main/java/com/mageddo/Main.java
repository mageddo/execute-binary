package com.mageddo;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
public class Main {
	public static void main(String[] args) throws Exception {

		final InputStream in = Main.class.getResourceAsStream("/myprogram");
		if (in == null) {
			throw new RuntimeException("program not found");
		}

		final File tmpProgram = Files.createTempFile("mypgrogram", ".tmp").toFile();
		Files.copy(in, tmpProgram.toPath(), StandardCopyOption.REPLACE_EXISTING);
		tmpProgram.deleteOnExit();
		tmpProgram.setExecutable(true);

		final Process p = Runtime.getRuntime().exec(tmpProgram.getAbsolutePath());
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

