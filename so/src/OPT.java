import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class OPT extends Politica {

	private Configuracao config;
	private int[] tamMemoria;
	private int hits;
	private int qtdProcessos;
	private List<Requisicao> requisicoes;
	private ArrayList<Processo> processos;

	public OPT(Configuracao config) {
		this.config = config;
		this.tamMemoria = config.gerarQuadros();
		this.hits = 0;
		this.qtdProcessos = config.getQtdProcessos();
		this.requisicoes = config.getRequisicoes();
		this.processos = config.getProcessos();
	}
	
	public void Processar (){
		
		if ( this.config.getTipoSubstituicao().equals("Global") ){
			
			this.Global();
			
		}else{
			
			this.Local();
			
		}
		
	}
	
	public void Local() {
		TreeMap<String, Queue<?>> mapQuadros = new TreeMap<String, Queue<?>>();
		TreeMap<String, Integer> mapQuadrosMax = new TreeMap<String, Integer>();
		Map<String, LinkedList<Integer>> requisicoesFuturas = new TreeMap<String, LinkedList<Integer>>();

		// Criando Maps para todos os processos que serao processados
		for (int i = 0; i < qtdProcessos; i++) {
			mapQuadros.put(processos.get(i).getNomeProcesso(), new LinkedList<Object>());
			requisicoesFuturas.put(processos.get(i).getNomeProcesso(), new LinkedList<Integer>());
			mapQuadrosMax.put(processos.get(i).getNomeProcesso(), tamMemoria[i]);
		}
		//System.out.println("Total de quadros por processo" + mapQuadrosMax);

		// Colocando todas as requisicoes em uma lista para saber as requisicoes futuras
		for (Requisicao requisicao : requisicoes) {
			requisicoesFuturas.get(requisicao.getProcesso()).add(requisicao.getPagina());
		}
		//System.out.println("Lista de requisicoes futuras" + requisicoesFuturas);

		//System.out.println("\n \n \n");
		// Realizar processamento das paginas

		for (Requisicao requisicao : requisicoes) {
			@SuppressWarnings("unchecked")
			LinkedList<Integer> quadro = (LinkedList<Integer>) mapQuadros.get(requisicao.getProcesso());

			if (quadro.contains(requisicao.getPagina())) {
				hits++;
				removerRequisicaoFutura(requisicoesFuturas, requisicao.getProcesso());
				//System.out.println("HIT - " + requisicao + " " + mapQuadros);
			} else {
				if (quadro.size() < mapQuadrosMax.get(requisicao.getProcesso())) {
					quadro.add(requisicao.getPagina());
					removerRequisicaoFutura(requisicoesFuturas, requisicao.getProcesso());
					//System.out.println("Pagina adicionada SEM remocao - " + requisicao + " " + mapQuadros);
				} else {
					int indicePag = 0;
					if (quadro.size() > 1) {
						indicePag = quadro.indexOf(retirarPaginaLocal(quadro, requisicoesFuturas, requisicao.getProcesso()));
						if (indicePag == -1) {
							indicePag = 0;
						}

					}
					quadro.remove(indicePag);
					quadro.add(indicePag, requisicao.getPagina());
					removerRequisicaoFutura(requisicoesFuturas, requisicao.getProcesso());
					//System.out.println("Pagina adicionada COM substituicao - " + requisicao + " " + mapQuadros);
				}
			}

		}

	}
	
	public void Global() {
		TreeMap<String, Queue<?>> mapQuadros = new TreeMap<String, Queue<?>>();
		// TreeMap<String, Integer> mapQuadrosMax = new TreeMap<String, Integer>();
		int tamQuadroMax = 0;
		// Map<String, LinkedList<Integer>> requisicoesFuturas = new TreeMap<String,
		// LinkedList<Integer>>();
		LinkedList<Requisicao> requisicoesFuturas = new LinkedList<Requisicao>();

		// Criando Maps para todos os processos que serao processados
		for (int i = 0; i < qtdProcessos; i++) {
			mapQuadros.put(processos.get(i).getNomeProcesso(), new LinkedList<Object>());
			//// requisicoesFuturas.put(processos.get(i).getNomeProcesso(), new
			//// LinkedList<Integer>());
			tamQuadroMax += tamMemoria[i];
		}
		//System.out.println("Total de quadros por processo" + tamQuadroMax);

		// Colocando todas as requisicoes em uma lista para saber as requisicoes futuras
		requisicoesFuturas.addAll(requisicoes);

		//System.out.println("Lista de requisicoes futuras" + requisicoesFuturas);

		//System.out.println("\n \n \n");
		// Realizar processamento das paginas

		LinkedList<Requisicao> quadro = new LinkedList<Requisicao>();

		for (Requisicao requisicao : requisicoes) {

			if (quadro.contains(requisicao)) {
				hits++;
				requisicoesFuturas.remove();
				//System.out.println("HIT - " + requisicao + " " + mapQuadros);
			} else {
				if (quadro.size() < tamQuadroMax) {
					quadro.add(requisicao);
					requisicoesFuturas.remove();
					//System.out.println("Pagina adicionada SEM remocao - " + requisicao + " " + mapQuadros);
				} else {
					int indicePag = 0;
					Requisicao retorno = retirarPaginaGlobal(quadro, requisicoesFuturas);
					if (quadro.size() > 1) {						
						indicePag = retorno == null ? 0 : quadro.indexOf(retorno);
					}
					quadro.remove(indicePag);
					quadro.add(indicePag, requisicao);
					requisicoesFuturas.remove();
					//System.out.println("Pagina adicionada COM substituicao - " + requisicao + " " + mapQuadros);
				}
			}

		}

	}

	public void removerRequisicaoFutura(Map<String, LinkedList<Integer>> requisicoesFuturas, String processo) {
		requisicoesFuturas.get(processo).remove();
		// System.out.println("Nova lista de requisicoes futuras" + requisicoesFuturas);
	}

	public int retirarPaginaLocal(LinkedList<Integer> quadroLocal, Map<String, LinkedList<Integer>> requisicoesFuturasLocal,
			String processo) {

		Map<Integer, Integer> indicesPagFuturas = new TreeMap<Integer, Integer>();
		LinkedList<Integer> paginasSemRequisicaoFuturo = new LinkedList<Integer>();
		paginasSemRequisicaoFuturo.addAll(quadroLocal);

		for (Integer pagina : requisicoesFuturasLocal.get(processo)) {
			if (quadroLocal.contains(pagina) && !indicesPagFuturas.containsValue(pagina)) {
				indicesPagFuturas.put(requisicoesFuturasLocal.get(processo).indexOf(pagina), pagina);
				paginasSemRequisicaoFuturo.remove(paginasSemRequisicaoFuturo.indexOf(pagina));
			}
		}
		if (paginasSemRequisicaoFuturo.size() > 0) {
			return paginasSemRequisicaoFuturo.getFirst();
		} else {
			LinkedList<Integer> x = new LinkedList<Integer>();
			x.addAll(indicesPagFuturas.values());

			//System.out.println("Indices das paginas " + indicesPagFuturas);
			if (x.size() > 0) {
				return x.getLast();
			} else {
				return -1;
			}
		}

	}

	public Requisicao retirarPaginaGlobal(LinkedList<Requisicao> quadroGlobal, LinkedList<Requisicao> requisicoesFuturasGlobal) {

		Map<Integer, Requisicao> indicesPagFuturas = new TreeMap<Integer, Requisicao>();
		LinkedList<Requisicao> paginasSemRequisicaoFuturo = new LinkedList<Requisicao>();
		paginasSemRequisicaoFuturo.addAll(quadroGlobal);

		for (Requisicao pagina : requisicoesFuturasGlobal) {
			if (quadroGlobal.contains(pagina) && !indicesPagFuturas.containsValue(pagina)) {
				indicesPagFuturas.put(requisicoesFuturasGlobal.indexOf(pagina), pagina);
				paginasSemRequisicaoFuturo.remove(paginasSemRequisicaoFuturo.indexOf(pagina));
			}
		}
		if (paginasSemRequisicaoFuturo.size() > 0) {
			return paginasSemRequisicaoFuturo.getFirst();
		} else {
			LinkedList<Requisicao> x = new LinkedList<Requisicao>();
			x.addAll(indicesPagFuturas.values());

			//System.out.println("Indices das paginas " + indicesPagFuturas);
			if (x.size() > 0) {
				return x.getLast();
			} else {
				return null;
			}
		}

	}

	public int getHits() {
		return hits;
	}

	public List<Requisicao> getRequisicoes() {
		return requisicoes;
	}

}
