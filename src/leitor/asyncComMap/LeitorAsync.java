package leitor.asyncComMap;

import java.io.File;
import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.DadosSaidaLeitor;
import util.ArquivoUtil;

public class LeitorAsync {
	public DadosSaidaLeitor buscar(String caminhoLista, String nomeEscolhido) {
		long tempoInicial = System.currentTimeMillis();
		List<File> arquivos = ArquivoUtil.listar(caminhoLista, "txt");
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		List<Thread> threads = new ArrayList<Thread>();
		for (File arquivo : arquivos) {
			LeitorMapRunnable leitor = new LeitorMapRunnable();
			leitor.setList(list);
			leitor.setArquivo(arquivo);
				
			Thread t = new Thread(leitor);
			t.start();
			threads.add(t);
		}

		boolean finalizou = false;
		while (!finalizou) {
			finalizou = true;
			for (Thread t : threads) {
				if (!t.getState().equals(State.TERMINATED)) {
					finalizou = false;
				}
			}
		}
		
		String arquivoEncontrado = null;
		for(Map<String, String> map : list) {
			arquivoEncontrado = map.get(nomeEscolhido);
			if (arquivoEncontrado!=null) {
				break;
			}
		}

		if (arquivoEncontrado!=null) {
			System.out.println("Achou no arquivo " + arquivoEncontrado);
		} else {
			System.out.println("Não achou");
		}

		long tempoFinal = System.currentTimeMillis();

		return new DadosSaidaLeitor("asyncComMap", caminhoLista, arquivoEncontrado, nomeEscolhido,
				tempoFinal - tempoInicial);
	}
}
