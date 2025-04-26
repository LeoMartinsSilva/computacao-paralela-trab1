package leitor;

import leitor.async.LeitorAsync;
import model.DadosSaidaLeitor;

public class ChamaLeitor {
	public DadosSaidaLeitor chamar(String tipoLeitor, String caminhoLista, String nome) {
		if(tipoLeitor.equals("sync")) {
			
			LeitorListaSync leitor = new LeitorListaSync();
			DadosSaidaLeitor dadosSaida = leitor.buscar(caminhoLista, nome);
			System.out.println("Tempo de busca: "+ dadosSaida.getTempo()+ " milisegundos");
			return dadosSaida;
		}else if(tipoLeitor.equals("async")){
			LeitorAsync leitor = new LeitorAsync();
			DadosSaidaLeitor dadosSaida = leitor.buscar(caminhoLista, nome);
			System.out.println("Tempo de busca: "+ dadosSaida.getTempo()+ " milisegundos");	
			return dadosSaida;
		} else if(tipoLeitor.equals("async2PorArquivo")) {
			leitor.doisPorArquivo.LeitorAsync leitor = new leitor.doisPorArquivo.LeitorAsync();
			DadosSaidaLeitor dadosSaida = leitor.buscar(caminhoLista, nome);
			System.out.println("Tempo de busca: "+ dadosSaida.getTempo()+ " milisegundos");
			return dadosSaida;
		} else if (tipoLeitor.equals("asyncComMap")){ 
			leitor.asyncComMap.LeitorAsync leitor = new leitor.asyncComMap.LeitorAsync();
			DadosSaidaLeitor dadosSaida = leitor.buscar(caminhoLista, nome);
			System.out.println("Tempo de busca: "+ dadosSaida.getTempo()+ " milisegundos");
			return dadosSaida;
		} else {
			return null;
		}
	}
}
