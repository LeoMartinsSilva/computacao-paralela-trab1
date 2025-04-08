package principal;

public class ChamaLeitor {
	public void chamar(String tipoLeitor, String caminhoLista, String nome) {
		if(tipoLeitor.equals("sync")) {
			
			LeitorListaPequena leitor = new LeitorListaPequena();
			Long tempo = leitor.buscar(caminhoLista, nome);
			System.out.println("Tempo de busca: "+ tempo+ " milisegundos");
		}else {
			LeitorListaAsync leitor = new LeitorListaAsync();
			Long tempo = leitor.buscar(caminhoLista, nome);
			System.out.println("Tempo de busca: "+ tempo+ " milisegundos");	
		}
	}
}
