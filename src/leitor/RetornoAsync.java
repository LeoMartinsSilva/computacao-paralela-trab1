package leitor;

public class RetornoAsync {
	private String arquivoEncontrada;
	private boolean achou;
	synchronized public String getArquivoEncontrada() {
		return arquivoEncontrada;
	}
	synchronized public void setArquivoEncontrada(String arquivoEncontrada) {
		this.arquivoEncontrada = arquivoEncontrada;
	}
	synchronized public boolean isAchou() {
		return achou;
	}
	synchronized public void setAchou(boolean achou) {
		this.achou = achou;
	}
	
	
}
