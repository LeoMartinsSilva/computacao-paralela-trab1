package principal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import util.ArquivoUtil;

public class LeitorListaPequena {
	public DadosSaidaLeitor buscar(String caminhoLista, String nomeEscolhido) {
		long tempoInicial = System.currentTimeMillis();
		List<File> arquivos = ArquivoUtil.listar(caminhoLista, "txt");
		boolean achou = false;
		String arquivoOndeEncontrou = "";
		for (File arquivo : arquivos) {
			try (InputStream is = new FileInputStream(arquivo)) {
				InputStreamReader scan = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(scan);
				String nome = br.readLine();

				while (nome != null) {
					if (nome.equals(nomeEscolhido)) {
						arquivoOndeEncontrou = arquivo.getPath();
						achou = true;
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
			if (achou)
				break;
		}

		if (achou) {
			System.out.println("Achou no arquivo " + arquivoOndeEncontrou);
		}else {
			System.out.println("Não achou");
		}
		long tempoFinal = System.currentTimeMillis();

		return new DadosSaidaLeitor("sync", caminhoLista, arquivoOndeEncontrou, nomeEscolhido, tempoFinal - tempoInicial);
	}
}
