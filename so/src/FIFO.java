import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;

public class FIFO extends Politica {
	
	Configuracao config;
	int [] tamMemoria;
	
	
	public FIFO ( Configuracao config ){
		
		this.config = config;
		tamMemoria = config.gerarQuadros();
		
	}
	
	public void testeFIFO (){
		
		config.getQtdProcessos();
		config.getQtdQuadros();
		
		int i;
		
		TreeMap < String, Queue > map  = new TreeMap< String , Queue >();
		TreeMap < String, Integer > mapMax  = new TreeMap< String , Integer >();
		
		
		//ArrayList <TreeMap> a1 = new ArrayList<TreeMap>();
		
		for ( i = 0 ; i < config.getQtdProcessos() ; i++ ){
			
			map.put( config.getProcessos().get(i).getNomeProcesso() , new LinkedList() );
			mapMax.put(config.getProcessos().get(i).getNomeProcesso() , tamMemoria[i]);
			
		}
		i = 0;
		int hits = 0;
		for( Requisicao req : config.getRequisicoes()){
			
			LinkedList queue = (LinkedList) map.get(req.getProcesso());
			
			if ( queue.size() <  mapMax.get(req.getProcesso()) ){
				
				if ( map.get(req.getProcesso()).contains(req.getPagina())){
					i++;
					hits++;
					System.out.println( i + " HIT em " + req.getPagina() +"  " + map);

					
				}else{
					
					queue.add(req.getPagina());
					i++;
					System.out.println( i + " ADD " + map);
					
				}
				
			}else{
				
				if ( map.get(req.getProcesso()).contains(req.getPagina())){
					i++;
					hits++;
					System.out.println( i + " HIT em " + req.getPagina() +"  " + map);
					
				}else{	
				i++;
				queue.remove();
				queue.add(req.getPagina());
				System.out.println(  i + " REM " + map);
				}
			}
				
		
			
		}
		
		System.out.println(map);
		System.out.println(hits);

				
		
		
	}
	

}
