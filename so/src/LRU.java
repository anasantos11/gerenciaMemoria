	import java.util.LinkedList;
	import java.util.Queue;
	import java.util.TreeMap;

	public class LRU extends Politica {
		
		Configuracao config;
		int [] tamMemoria;
		int hits;
		
		
		public LRU ( Configuracao config ){
			
			this.config = config;
			tamMemoria = config.gerarQuadros();
			hits = 0;
			
		}
		
		public void LruLocal (){
			
			int i;
			
			TreeMap < String, Queue<?> > map  = new TreeMap< String , Queue<?> >();
			TreeMap < String, Integer > mapMax  = new TreeMap< String , Integer >();
					
			for ( i = 0 ; i < config.getQtdProcessos() ; i++ ){
				
				map.put( config.getProcessos().get(i).getNomeProcesso() , new LinkedList<Requisicao>() );
				mapMax.put(config.getProcessos().get(i).getNomeProcesso() , tamMemoria[i]);
				
			}
			
			i = 0;
			hits = 0;
			
			for( Requisicao req : config.getRequisicoes()){
				
				@SuppressWarnings("unchecked")
				LinkedList<Requisicao> queue = (LinkedList<Requisicao>) map.get(req.getProcesso());
				
				if ( queue.size() <  mapMax.get(req.getProcesso()) ){
					
					if ( map.get(req.getProcesso()).contains(req)){
						i++;
						hits++;
						System.out.println( i + " HIT em " + req.getPagina() +"  " + map);
						queue.remove(req);
						queue.add(req);
						
						
					}else{
						
						queue.add(req);
						i++;
						System.out.println( i + " ADD " + map);
						
					}
					
				}else{
					
					if ( map.get(req.getProcesso()).contains(req)){
						i++;
						hits++;
						System.out.println( i + " HIT em " + req.getPagina() +"  " + map);
						queue.remove(req);
						queue.add(req);
						
					}else{	
					i++;
					queue.remove();
					queue.add(req);
					System.out.println(  i + " REM " + map);
					}
				}
					
			
				
			}
			
			System.out.println(map);
			System.out.println(hits);

			
			
		}
		
		

	}

