package leitor.doisPorArquivo;

import java.util.List;

import model.RetornoAsync;

public class LeitorParImparAsync implements Runnable {

	private List<String> nomes;
	private String pathArquivo;
	private String nomeEscolhido;
	private boolean par;
	private RetornoAsync retorno;

	@Override
	public void run() {

		for (int i = (par ? 0 : 1); i < nomes.size(); i += 2) {
			try {
				if (nomes.get(i).equals(nomeEscolhido)) {
					retorno.setArquivoEncontrada(pathArquivo);
					retorno.setAchou(true);
					break;
				}
			} catch (Exception e) {

			}
		}

	}

	public String getNomeEscolhido() {
		return nomeEscolhido;
	}

	public void setNomeEscolhido(String nomeEscolhido) {
		this.nomeEscolhido = nomeEscolhido;
	}

	public RetornoAsync getRetorno() {
		return retorno;
	}

	public void setRetorno(RetornoAsync retorno) {
		this.retorno = retorno;
	}

	public boolean isPar() {
		return par;
	}

	public void setPar(boolean par) {
		this.par = par;
	}

	public List<String> getNomes() {
		return nomes;
	}

	public void setNomes(List<String> nomes) {
		this.nomes = nomes;
	}

	public String getPathArquivo() {
		return pathArquivo;
	}

	public void setPathArquivo(String pathArquivo) {
		this.pathArquivo = pathArquivo;
	}
	

}
