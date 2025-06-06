package leitor.async;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import model.RetornoAsync;

public class LeitorArquivoRunnable implements Runnable {

	private File arquivo;
	private String nomeEscolhido;
	private RetornoAsync retorno;
	
	@Override
	public void run() {
		try (InputStream is = new FileInputStream(arquivo)) {
			InputStreamReader scan = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(scan);
			String nome = br.readLine();

			while (nome != null) {
				if (nome.equals(nomeEscolhido)) {
					retorno.setArquivoEncontrada(arquivo.getPath());
					retorno.setAchou(true);
					break;
				}
				
				nome = br.readLine();
			}
			
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

	public void setArquivo(File arquivo) {
		this.arquivo = arquivo;
	}

	public String getNomeEscolhido() {
		return nomeEscolhido;
	}

	public void setNomeEscolhido(String nomeEscolhido) {
		this.nomeEscolhido = nomeEscolhido;
	}

	public RetornoAsync getRetorno() {
		return retorno;
	}

	public void setRetorno(RetornoAsync retorno) {
		this.retorno = retorno;
	}

	
}
