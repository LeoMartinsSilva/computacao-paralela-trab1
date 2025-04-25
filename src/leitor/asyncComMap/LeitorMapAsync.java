package leitor.asyncComMap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class LeitorMapAsync implements Runnable {

	private File arquivo;
	private Map<String, String> map;

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
			map.putAll(mapInterno);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	

	public Map<String, String> getMap() {
		return map;
	}



	public void setMap(Map<String, String> map) {
		this.map = map;
	}


	public File getArquivo() {
		return arquivo;
	}

	public void setArquivo(File arquivo) {
		this.arquivo = arquivo;
	}
}
