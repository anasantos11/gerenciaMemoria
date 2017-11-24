import java.util.Collections;
import java.util.LinkedList;
import java.util.TreeMap;

public class MY extends Politica {

	Configuracao config;
	int[] tamMemoria;
	int hits;

	public MY(Configuracao config) {

		this.config = config;
		tamMemoria = config.gerarQuadros();
		hits = 0;

	}

	public void Processar() {

		if (this.config.getTipoSubstituicao().equals("Global")) {

			this.Global();

		} else {

			this.Local();

		}

	}

	public void Global() {

		int i;
		int espaco = 0;

		for (i = 0; i < tamMemoria.length; i++) {

			espaco += tamMemoria[i];

		}

		i = 0;

		hits = 0;

		LinkedList<RequisicaoMY> queue = new LinkedList<RequisicaoMY>();
		
		for (Requisicao req : config.getRequisicoes()) {
			
			RequisicaoMY my = new RequisicaoMY (req, config.getQtdQuadros()%config.getQtdProcessos());

			if (queue.size() < espaco) {// memoria nao cheia

				if (queue.contains(my)) {

					i++;
					hits++;
					
					for (RequisicaoMY r : queue) {

						r.setCont(1);

						if (my.equals(r)) {

							r.setHits(1);

						}

					}

					System.out.println(i + " HIT em " + my.getPagina() + "  " + queue);

				} else {

					queue.add(my);
					i++;
					System.out.println(i + " ADD " + queue);

					for (RequisicaoMY r : queue) {

						r.setCont(1);

					}

				}

			} else {// memoria cheia

				if (queue.contains(my)) {

					i++;
					hits++;

					for (RequisicaoMY r : queue) {

						r.setCont(1);
						if (my.equals(r)) {

							r.setHits(1);

						}

					}

					System.out.println(i + " HIT em " + my.getPagina() + "  " + queue);

				} else {
					// ESTA CHEIO E PRECISA REMOVER
					i++;

					queue.remove(Collections.min(queue));
					queue.add(my);
					System.out.println(i + " REM " + queue);

					for (RequisicaoMY r : queue) {

						r.setCont(1);

					}

				}
			}

		}

		//System.out.println(queue);
		//System.out.println(hits);

	}

	public void Local() {

		int i;

		TreeMap<String, LinkedList<?>> map = new TreeMap<String, LinkedList<?>>();
		TreeMap<String, Integer> mapMax = new TreeMap<String, Integer>();

		for (i = 0; i < config.getQtdProcessos(); i++) {

			map.put(config.getProcessos().get(i).getNomeProcesso(), new LinkedList<RequisicaoMY>());
			mapMax.put(config.getProcessos().get(i).getNomeProcesso(), tamMemoria[i]);

		}

		i = 0;
		hits = 0;

		for (Requisicao req : config.getRequisicoes()) {
			
			RequisicaoMY my = new RequisicaoMY (req, config.getQtdQuadros()%config.getQtdProcessos());

			
			@SuppressWarnings("unchecked")
			LinkedList<RequisicaoMY> queue = (LinkedList<RequisicaoMY>) map.get(my.getProcesso());

			if (queue.size() < mapMax.get(my.getProcesso())) {// memoria nao
																// cheia

				if (map.get(my.getProcesso()).contains(my)) {

					i++;
					hits++;
					for (RequisicaoMY r : queue) {

						r.setCont(1);

						if (my.equals(r)) {

							r.setHits(1);

						}

					}
					// System.out.println( i + " HIT em " + my.getPagina() +" " + map);

				} else {

					queue.add(my);
					i++;
					 //System.out.println( i + " ADD " + map);

					for (RequisicaoMY r : queue) {

						r.setCont(1);

					}

				}

			} else {// memoria cheia

				if (map.get(my.getProcesso()).contains(my)) {

					i++;
					hits++;

					for (Requisicao r : queue) {

						r.setCont(1);
						if (my.equals(r)) {

							r.setHits(1);

						}

					}

					//System.out.println( i + " HIT em " + my.getPagina() +" "  + map);

				} else {
					// ESTA CHEIO E PRECISA REMOVER
					i++;

					queue.remove(Collections.min(queue));
					queue.add(my);
					// System.out.println( i + " REM " + map);

					for (RequisicaoMY r : queue) {

						r.setCont(1);

					}

				}
			}

		}

		//System.out.println(map);
		//System.out.println(hits);

	}

}
