

public class MY extends Politica{
	
	Configuracao config;
	int [] tamMemoria;
	int hits;
	
	public MY ( Configuracao config ){
		
		this.config = config;
		tamMemoria = config.gerarQuadros();
		hits = 0;
		
	}

}
