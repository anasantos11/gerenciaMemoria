import java.util.ArrayList;
import java.util.List;

public class LFU extends Politica{
	
	Configuracao config;
	int [] tamMemoria;
	int QtdProcessos;
	List<Requisicao> requisicoes;
	ArrayList <Processo> processos;
	int hits;;
	
	public LFU ( Configuracao config ){
		
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
	
	public void Global (){
		
		
		
	}
	
	public void Local (){
		
		
		
	}

}
