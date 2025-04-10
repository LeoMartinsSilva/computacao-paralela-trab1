package leitor;

public class ChamaLeitor {
	public DadosSaidaLeitor chamar(String tipoLeitor, String caminhoLista, String nome) {
		if(tipoLeitor.equals("sync")) {
			
			LeitorListaSync leitor = new LeitorListaSync();
			DadosSaidaLeitor dadosSaida = leitor.buscar(caminhoLista, nome);
			System.out.println("Tempo de busca: "+ dadosSaida.getTempo()+ " milisegundos");
			return dadosSaida;
		}else if(tipoLeitor.equals("async")){
			LeitorListaAsync leitor = new LeitorListaAsync();
			DadosSaidaLeitor dadosSaida = leitor.buscar(caminhoLista, nome);
			System.out.println("Tempo de busca: "+ dadosSaida.getTempo()+ " milisegundos");	
			return dadosSaida;
		} else if(tipoLeitor.equals("async2ThreadsPorArquivo")) {
			LeitorListaAsync leitor = new LeitorListaAsync();
			DadosSaidaLeitor dadosSaida = leitor.buscar(caminhoLista, nome);
			System.out.println("Tempo de busca: "+ dadosSaida.getTempo()+ " milisegundos");	
			return dadosSaida;
		} else {
			return null;
		}
	}
}
