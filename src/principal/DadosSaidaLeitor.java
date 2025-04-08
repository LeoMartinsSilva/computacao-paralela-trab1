package principal;

public class DadosSaidaLeitor {
	private String modoDeBusca;
	private String listagem;
	private String arquivo;
	private String nome;
	private Long tempo;
	
	public DadosSaidaLeitor() {
	}
	public DadosSaidaLeitor(String modoDeBusca, String listagem, String arquivo, String nome, Long tempo) {
		this.modoDeBusca = modoDeBusca;
		this.listagem = listagem;
		this.arquivo = arquivo;
		this.nome = nome;
		this.tempo = tempo;
	}
	public String getModoDeBusca() {
		return modoDeBusca;
	}
	public void setModoDeBusca(String modoDeBusca) {
		this.modoDeBusca = modoDeBusca;
	}
	public String getListagem() {
		return listagem;
	}
	public void setListagem(String listagem) {
		this.listagem = listagem;
	}
	public String getArquivo() {
		return arquivo;
	}
	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Long getTempo() {
		return tempo;
	}
	public void setTempo(Long tempo) {
		this.tempo = tempo;
	}
	
	
}
