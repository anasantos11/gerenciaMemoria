import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TreeMap;

public class FIFO extends Politica {
	
	Configuracao config;
	int [] tamMemoria;
	int hits;
	int QtdProcessos;
	List<Requisicao> requisicoes;
	ArrayList <Processo> processos;
	
	public FIFO ( Configuracao config ){
		this.config = config;
		tamMemoria = config.gerarQuadros();
		hits = 0;
		QtdProcessos = config.getQtdProcessos();
		requisicoes = config.getRequisicoes();
		processos = config.getProcessos();
	}
	
	public void Processar (){
		
		if ( this.config.getTipoSubstituicao().equals("Global") ){
			
			this.Global();
			
		}else{
			
			this.Local();
			
		}
		
	}
	@Override
	public void Local (){
		
		int i;
		
		TreeMap < String, Queue<?> > map  = new TreeMap< String , Queue<?> >();
		TreeMap < String, Integer > mapMax  = new TreeMap< String , Integer >();
				
		
		for ( i = 0 ; i < QtdProcessos ; i++ ){
			
			map.put( processos.get(i).getNomeProcesso() , new LinkedList<Object>() );
			mapMax.put(processos.get(i).getNomeProcesso() , tamMemoria[i]);
			
		}
		
		i = 0;
		hits = 0;
		
		for( Requisicao req : requisicoes ){
			
			@SuppressWarnings("unchecked")
			LinkedList<Integer> queue = (LinkedList<Integer>) map.get(req.getProcesso());
			
			if ( queue.size() <  mapMax.get(req.getProcesso()) ){
				
				if ( map.get(req.getProcesso()).contains(req.getPagina())){
					i++;
					hits++;
					//System.out.println( i + " HIT em " + req.getPagina() +"  " + map);

					
				}else{
					
					queue.add(req.getPagina());
					i++;
					//System.out.println( i + " ADD " + map);
					
				}
				
			}else{
				
				if ( map.get(req.getProcesso()).contains(req.getPagina())){
					i++;
					hits++;
					//System.out.println( i + " HIT em " + req.getPagina() +"  " + map);
					
				}else{	
				i++;
				queue.remove();
				queue.add(req.getPagina());
				//System.out.println(  i + " REM " + map);
				}
			}
				
		
			
		}
		
		//System.out.println(map);
		//System.out.println(hits);

				
		
		
	}
	@Override
	public void Global(){
		
		int i;
		
		TreeMap < String, Queue<?> > map  = new TreeMap< String , Queue<?> >();
		TreeMap < String, Integer > mapMax  = new TreeMap< String , Integer >();
				
		for ( i = 0 ; i < QtdProcessos ; i++ ){
			
			map.put( config.getProcessos().get(i).getNomeProcesso() , new LinkedList<Requisicao>() );
			mapMax.put(config.getProcessos().get(i).getNomeProcesso() , tamMemoria[i]);
			
		}
		
		int espaco = 0;
		
		for ( i = 0 ; i < tamMemoria.length ; i++ ){
			
			espaco += tamMemoria[i];
			
		}
		
		i = 0;
		
		hits = 0;
		
		Queue <Requisicao> queue  =  new LinkedList <Requisicao> ();
		
		for( Requisicao req : config.getRequisicoes() ){
			
			
			if ( queue.size() < espaco ){
				
				if ( queue.contains(req) ){
					
					i++;
					hits++;
					//System.out.println( i + " HIT em " + req.getPagina() +"  " + queue);
					
					
				}else{// ADICIONA
					
					 queue.add( req );
					 i++;
					//System.out.println( i + " ADD " + queue);
					
				}
				
			}else{
				
				if ( queue.contains(req)){
					i++;
					hits++;
					//System.out.println( i + " HIT em " + req.getPagina() +"  " + queue);
					
				}else{	
					i++;
					queue.remove();
					queue.add(req);
					//System.out.println(  i + " REM " + queue);
				}
				
			}
				
		
			
		}
		
		//System.out.println(queue);
		//System.out.println(hits);

				
		
		
	}
	

}
