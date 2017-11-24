import java.util.Collections;
import java.util.LinkedList;
import java.util.TreeMap;

public class LFU{

	Configuracao config;
	int[] tamMemoria;
	int hits;

	public LFU(Configuracao config) {

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

		LinkedList<Requisicao> list = new LinkedList<Requisicao>();

		for (Requisicao req : config.getRequisicoes()) {

			if (list.contains(req)) {

				i++;
				hits++;
				for (Requisicao r : list) {

					r.setCont(1);

					if (req.equals(r)) {

						r.setHits(1);

					}

				}
				// System.out.println( i + " HIT em " + req.getPagina() +" " +
				// list);

			} else if (list.size() < espaco) {// memoria nao cheia

				list.add(req);
				i++;
				// System.out.println( i + " ADD " + map);

				for (Requisicao r : list) {

					r.setCont(1);

				}

			} else {// memoria cheia

				i++;

				list.remove(Collections.min(list));
				list.add(req);
				// System.out.println( i + " REM " + list);

				for (Requisicao r : list) {

					r.setCont(1);

				}

			}
		}// end for de requisicoes

		// System.out.println(list);
		// System.out.println(hits);

	}// end void 

	public void Local() {

		int i;

		TreeMap<String, LinkedList<?>> map = new TreeMap<String, LinkedList<?>>();
		TreeMap<String, Integer> mapMax = new TreeMap<String, Integer>();

		for (i = 0; i < config.getQtdProcessos(); i++) {

			map.put(config.getProcessos().get(i).getNomeProcesso(), new LinkedList<Requisicao>());
			mapMax.put(config.getProcessos().get(i).getNomeProcesso(), tamMemoria[i]);

		}

		i = 0;
		hits = 0;

		for (Requisicao req : config.getRequisicoes()) {

			@SuppressWarnings("unchecked")
			LinkedList<Requisicao> queue = (LinkedList<Requisicao>) map.get(req.getProcesso());

			if (queue.size() < mapMax.get(req.getProcesso())) {// memoria nao
																// cheia

				if (map.get(req.getProcesso()).contains(req)) {

					i++;
					hits++;
					for (Requisicao r : queue) {

						r.setCont(1);

						if (req == r) {

							r.setHits(1);

						}

					}
					// System.out.println( i + " HIT em " + req.getPagina() +" "
					// + map);

				} else {

					queue.add(req);
					i++;
					// System.out.println( i + " ADD " + map);

					for (Requisicao r : queue) {

						r.setCont(1);

					}

				}

			} else {// memoria cheia

				if (map.get(req.getProcesso()).contains(req)) {

					i++;
					hits++;

					for (Requisicao r : queue) {

						r.setCont(1);
						if (req == r) {

							r.setHits(1);

						}

					}

					// System.out.println( i + " HIT em " + req.getPagina() +" "
					// + map);

				} else {
					// ESTA CHEIO E PRECISA REMOVER
					i++;

					queue.remove(Collections.min(queue));
					queue.add(req);
					// System.out.println( i + " REM " + map);

					for (Requisicao r : queue) {

						r.setCont(1);

					}

				}
			}

		}

		System.out.println(map);
		System.out.println(hits);

	}

}
