package util;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class ArquivoUtil {
	public static ArrayList<File> listar(String caminho, String extensao) {
		File pasta = new File(caminho);

		return listar(pasta, extensao);
	}

	private static ArrayList<File> listar(File pasta, String extensao) {
		ArrayList<File> arquivos;

		arquivos = new ArrayList<>(Arrays.asList(pasta.listFiles()));
		arquivos.removeIf(arquivo -> !arquivo.getName().endsWith(extensao));

		return arquivos;
	}
}
