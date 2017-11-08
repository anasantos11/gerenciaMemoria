import java.util.LinkedHashMap;
import java.util.Map;

public class Configuracao {
	private int tamanhoQuadros;
	private Map<String, Integer> paginasProcessos;
	private String tipoAlocacao;
	private String tipoSubstituicao;
	private Map<String, Integer> sequencias;


	public Configuracao() {
		setTamanhoQuadros(-1);
		setPaginasProcessos(new LinkedHashMap<String, Integer>());
		setTipoAlocacao(null);
		setTipoSubstituicao(null);
		setSequencias(new LinkedHashMap<String, Integer>());
	}

	public Configuracao(int tamanhoQuadros, LinkedHashMap paginasProcesso, String tipoAlocacao, String tipoSubstituicao,
			LinkedHashMap sequencias) {
		setTamanhoQuadros(tamanhoQuadros);
		setPaginasProcessos(paginasProcesso);
		setTipoAlocacao(tipoAlocacao);
		setTipoSubstituicao(tipoSubstituicao);
		setSequencias(sequencias);
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

	public Map<String, Integer> getPaginasProcessos() {
		return paginasProcessos;
	}

	public void setPaginasProcessos(Map<String, Integer> paginasProcessos) {
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

	public Map<String, Integer> getSequencias() {
		return sequencias;
	}

	public void setSequencias(Map<String, Integer> sequencias) {
		this.sequencias = sequencias;
	}

}
