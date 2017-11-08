import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Configuracao {
	private int tamanhoQuadros;
	private Map<String, Integer> paginasProcessos;
	private String tipoAlocacao;
	private String tipoSubstituicao;
	private List<Processo> sequencias;

	public Configuracao() {
		setTamanhoQuadros(-1);
		setPaginasProcessos(new LinkedHashMap<String, Integer>());
		setTipoAlocacao(null);
		setTipoSubstituicao(null);
		setSequencias(new ArrayList<Processo>());
	}

	public Configuracao(int tamanhoQuadros, LinkedHashMap<String, Integer> paginasProcesso, String tipoAlocacao,
			String tipoSubstituicao) {
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

	public List<Processo> getSequencias() {
		return sequencias;
	}

	public void setSequencias(List<Processo> sequencias) {
		this.sequencias = sequencias;
	}
	
	public void addPaginasProcessos(String processo, int paginas) {
		paginasProcessos.put(processo, paginas);
		
	}
	
	public void addSequencia(String processo, int valor) {
		sequencias.add(new Processo(processo, valor));
	}

	@Override
	public String toString() {
		return "Configuracao [tamanhoQuadros=" + tamanhoQuadros + "\n paginasProcessos=" + paginasProcessos.toString()
				+ "\n tipoAlocacao=" + tipoAlocacao + "\n tipoSubstituicao=" + tipoSubstituicao + "\n sequencias="
				+ sequencias.toString() + "]";
	}



}