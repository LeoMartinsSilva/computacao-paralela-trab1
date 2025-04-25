package leitor.doisPorArquivo;

import java.io.File;
import java.io.IOException;
import java.lang.Thread.State;
import java.nio.file.Files;
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

			String dados;
			try {
				dados = new String(Files.readAllBytes(arquivo.toPath()));

				List<String> nomes = List.of(dados.split("\r\n"));

				LeitorParImparAsync leitor = new LeitorParImparAsync();
				leitor.setNomes(nomes);
				leitor.setPathArquivo(arquivo.getPath());
				leitor.setNomeEscolhido(nomeEscolhido);
				leitor.setRetorno(retorno);
				leitor.setPar(false);
				
				Thread t = new Thread(leitor);
				t.start();
				threads.add(t);
				
				LeitorParImparAsync leitorPar = new LeitorParImparAsync();
				leitorPar.setNomes(nomes);
				leitorPar.setPathArquivo(arquivo.getPath());
				leitorPar.setNomeEscolhido(nomeEscolhido);
				leitorPar.setRetorno(retorno);
				leitorPar.setPar(true);
				
				Thread tPar = new Thread(leitorPar);
				tPar.start();
				threads.add(tPar);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		boolean finalizou = false;
		while (!finalizou) {
			finalizou = true;
			for (Thread t : threads) {
				if (!t.getState().equals(State.TERMINATED)) {
					finalizou = false;
				}
			}
			if (retorno.isAchou()) {
				finalizou = true;
			}
		}

		if (retorno.isAchou()) {
			System.out.println("Achou no arquivo " + retorno.getArquivoEncontrada());
		} else {
			System.out.println("Não achou");
		}

		long tempoFinal = System.currentTimeMillis();

		return new DadosSaidaLeitor("async2PorArquivo", caminhoLista, retorno.getArquivoEncontrada(), nomeEscolhido,
				tempoFinal - tempoInicial);
	}
}
