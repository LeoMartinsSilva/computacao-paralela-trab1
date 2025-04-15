package leitor.async;

import java.io.File;
import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.List;

import model.DadosSaidaLeitor;
import model.RetornoAsync;
import util.ArquivoUtil;

public class LeitorListaAsync {
	public DadosSaidaLeitor buscar(String caminhoLista, String nomeEscolhido) {
		long tempoInicial = System.currentTimeMillis();
		List<File> arquivos = ArquivoUtil.listar(caminhoLista, "txt");
		RetornoAsync retorno = new RetornoAsync();
		List<Thread> threads = new ArrayList<Thread>();
		for (File arquivo : arquivos) {
			LeitorArquivoAsync leitor = new LeitorArquivoAsync();
			leitor.setArquivo(arquivo);
			leitor.setNomeEscolhido(nomeEscolhido);
			leitor.setRetorno(retorno);
			Thread t = new Thread(leitor);
			t.start();
			threads.add(t);
		}
		
		boolean finalizou = false;
		while(!finalizou) {
			finalizou = true;
			for(Thread t : threads) {
				if(!t.getState().equals(State.TERMINATED)) {
					finalizou = false;
				}
			}
			if(retorno.isAchou()) {
				finalizou = true;
			}
		}
		
		if (retorno.isAchou()) {
			System.out.println("Achou no arquivo " + retorno.getArquivoEncontrada());
		}else {
			System.out.println("Não achou");
		}
		
		long tempoFinal = System.currentTimeMillis();

		return new DadosSaidaLeitor("async", caminhoLista, retorno.getArquivoEncontrada(), nomeEscolhido, tempoFinal - tempoInicial);
	}
}
