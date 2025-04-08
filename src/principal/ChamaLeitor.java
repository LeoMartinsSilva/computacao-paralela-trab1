package principal;

public class ChamaLeitor {
	public DadosSaidaLeitor chamar(String tipoLeitor, String caminhoLista, String nome) {
		if(tipoLeitor.equals("sync")) {
			
			LeitorListaPequena leitor = new LeitorListaPequena();
			DadosSaidaLeitor dadosSaida = leitor.buscar(caminhoLista, nome);
			System.out.println("Tempo de busca: "+ dadosSaida.getTempo()+ " milisegundos");
			return dadosSaida;
		}else {
			LeitorListaAsync leitor = new LeitorListaAsync();
			DadosSaidaLeitor dadosSaida = leitor.buscar(caminhoLista, nome);
			System.out.println("Tempo de busca: "+ dadosSaida.getTempo()+ " milisegundos");	
			return dadosSaida;
		}
	}
}
