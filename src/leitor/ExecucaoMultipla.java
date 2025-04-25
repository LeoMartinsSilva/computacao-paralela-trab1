package leitor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.DadosSaidaLeitor;
import util.ArquivoUtil;

public class ExecucaoMultipla {
	public List<DadosSaidaLeitor> executar(int numeroDeNomes) {
		List<DadosSaidaLeitor> dados = new ArrayList<DadosSaidaLeitor>();
		
		List<String> nomesEscolhidos = escolherNomes(numeroDeNomes);
		ChamaLeitor chamaLeitor = new ChamaLeitor();
		for(String nomeEscolhido : nomesEscolhidos) {
			dados.add(chamaLeitor.chamar("sync", "res/P", nomeEscolhido));
			dados.add(chamaLeitor.chamar("async", "res/P", nomeEscolhido));
			dados.add(chamaLeitor.chamar("async2PorArquivo", "res/P", nomeEscolhido));
			dados.add(chamaLeitor.chamar("asyncComMap", "res/P", nomeEscolhido));
			dados.add(chamaLeitor.chamar("sync", "res/G", nomeEscolhido));
			dados.add(chamaLeitor.chamar("async", "res/G", nomeEscolhido));
			dados.add(chamaLeitor.chamar("async2PorArquivo", "res/G", nomeEscolhido));
			dados.add(chamaLeitor.chamar("asyncComMap", "res/G", nomeEscolhido));
		}

		System.out.println("Nomes realmente escolhidos: " + nomesEscolhidos.size());
		return dados;
	}
	
	private List<String> escolherNomes(int numeroDeNomes){
		List<String> nomes = new ArrayList<String>();
		
		List<File> arquivos = ArquivoUtil.listar("res/P", "txt");
		for (File arquivo : arquivos) {
			try (InputStream is = new FileInputStream(arquivo)) {
				InputStreamReader scan = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(scan);
				String nome = br.readLine();

				while (nome != null) {
					int numero = (new Random()).nextInt(25);

					if(nomes.size()>=numeroDeNomes) break;
					
					if(numero == 1) { // 4% de chance
						nomes.add(nome);
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
		return nomes;
	}
}
