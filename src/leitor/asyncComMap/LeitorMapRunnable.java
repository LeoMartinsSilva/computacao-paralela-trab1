package leitor.asyncComMap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeitorMapRunnable implements Runnable {

	private File arquivo;
	private List<Map<String, String>> list;

	@Override
	public void run() {
		Map<String, String> mapInterno = new HashMap<String, String>();
		
		try (InputStream is = new FileInputStream(arquivo)) {
			InputStreamReader scan = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(scan);
			
			String nomeArquivo = arquivo.getPath();
			
			String nome = br.readLine();
			while (nome != null) {
				mapInterno.put(nome, nomeArquivo);
				nome = br.readLine();
			}
			list.add(mapInterno);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public File getArquivo() {
		return arquivo;
	}

	public List<Map<String, String>> getList() {
		return list;
	}

	public void setList(List<Map<String, String>> list) {
		this.list = list;
	}



	public void setArquivo(File arquivo) {
		this.arquivo = arquivo;
	}
}
