import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Configuracao {
	private int tamanhoQuadros;
	private Map<String, Processo> paginasProcessos;
	private String tipoAlocacao;
	private String tipoSubstituicao;
	private List<Requisicao> requisicoes;
	private Politica politica;

	public Configuracao() {
		setTamanhoQuadros(-1);
		setPaginasProcessos(new LinkedHashMap<String, Processo>());
		setTipoAlocacao(null);
		setTipoSubstituicao(null);
		setRequisicoes(new ArrayList<Requisicao>());
	}

	public Configuracao(int tamanhoQuadros, LinkedHashMap<String, Processo> paginasProcesso, String tipoAlocacao,
			String tipoSubstituicao) {
		setTamanhoQuadros(tamanhoQuadros);
		setPaginasProcessos(paginasProcesso);
		setTipoAlocacao(tipoAlocacao);
		setTipoSubstituicao(tipoSubstituicao);
		setRequisicoes(requisicoes);
	}

	/**
	 * Getters and Setters
	 */
	public int getTamanhoQuadros() {
		return tamanhoQuadros;
	}

	public void setTamanhoQuadros(int tamanhoQuadros) {
		this.tamanhoQuadros = tamanhoQuadros;
	}

	public Map<String, Processo> getPaginasProcessos() {
		return paginasProcessos;
	}

	public void setPaginasProcessos(Map<String, Processo> paginasProcessos) {
		this.paginasProcessos = paginasProcessos;
	}

	public String getTipoAlocacao() {
		return tipoAlocacao;
	}

	public void setTipoAlocacao(String tipoAlocacao) {
		this.tipoAlocacao = tipoAlocacao;
	}

	public String getTipoSubstituicao() {
		return tipoSubstituicao;
	}

	public void setTipoSubstituicao(String tipoSubstituicao) {
		this.tipoSubstituicao = tipoSubstituicao;
	}

	public List<Requisicao> getRequisicoes() {
		return requisicoes;
	}

	public void setRequisicoes(List<Requisicao> requisicoes) {
		this.requisicoes = requisicoes;
	}
	
	public void addPaginasProcessos(String nomeProcesso, int totalPaginas) {
		paginasProcessos.put(nomeProcesso, new Processo(nomeProcesso, totalPaginas));
	}
	
	public void addSequencia(String processo, int valor) {
		requisicoes.add(new Requisicao(processo, valor));
	}

	@Override
	public String toString() {
		return "Configuracao [tamanhoQuadros=" + tamanhoQuadros + "\n paginasProcessos=" + paginasProcessos.toString()
				+ "\n tipoAlocacao=" + tipoAlocacao + "\n tipoSubstituicao=" + tipoSubstituicao + "\n requisicoes=\n"
				+ requisicoes.toString();
	}

	public Politica getPolitica() {
		return politica;
	}

	public void setPolitica(Politica politica) {
		this.politica = politica;
	}



}
