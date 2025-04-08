package principal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String caminhoLista = "";
		String opcao = "";
		
		System.out.println("Qual lista buscar? \n 1-Pequena \n 2-Grande ");
		opcao = in.readLine();
		if(opcao.equals("1")) {
			caminhoLista = "res/P";
		}else if(opcao.equals("2")) {
			caminhoLista = "res/G";
		}else {
			System.out.println("Opção inválida");
			System.exit(0);
		}
		
		String tipoLeitor = "";
		System.out.println("Qual tipo de leitor? \n 1-Sincrono \n 2-Assincrono ");
		opcao = in.readLine();
		if(opcao.equals("1")) {
			tipoLeitor = "sync";
		}else if(opcao.equals("2")) {
			tipoLeitor = "Async";
		}else {
			System.out.println("Opção inválida");
			System.exit(0);
		}
		
		System.out.println("Digite o nome que deseja buscar: ");
		String nome = in.readLine();
		
		ChamaLeitor chamaLeitor = new ChamaLeitor();
		chamaLeitor.chamar(tipoLeitor, caminhoLista, nome);
	}

}
