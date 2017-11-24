
public class RequisicaoMY extends Requisicao {
	
	private int maxCont;

	public RequisicaoMY(String processo, int pag) {
		super(processo, pag);
	}
	
	public RequisicaoMY(){
		super();
	}
	
	public RequisicaoMY(Requisicao c, int max){
		super(c);
		this.maxCont = max;
	}
	
	@Override
	public int compareTo(Requisicao reqs){
		/*
		if (super.getHits() == reqs.getHits()){
			if (super.getCont() >= maxCont )
				return -1;  
			else
				return 1;
		}else if (super.getHits()> reqs.getHits() )
			return 1;
		else
			return -1;
		*/
		/*
		if ( super.getCont() > this.maxCont && reqs.getCont() > this.maxCont ){// ambos sao maiores que o maxCont
			
			if (super.getHits()> reqs.getHits() ){// analisa o hit deles
				
				return 1;
				
			}else if (super.getHits() == reqs.getHits()){
				
				return 0;
			}else
				
				return -1;
			
		}else if ( super.getCont() == this.maxCont ){
					return 1;
			
		}else{
			return -1;
			
		}
		*/
		
		if ( super.getHits() == reqs.getHits() ){//verifica se os hits sao iguais, caso sim entra na politica real do my
			
			if (super.getCont() > this.maxCont && reqs.getCont() > this.maxCont){// ambos estouraram o limite, analisa o maior entre eles
				
				if( super.getCont() > reqs.getCont() ){// retorna -1 para caso o primeiro seja maior 
				
					return -1;
				
				}else{
				
					return 1;
					
				}
				
			}else if ( super.getCont() < this.maxCont )// ainda esta no limite, esse objeto eh maior que a comparacao
				
				return 1;
			
			else
				
				return -1;// vai tirar o menor, que eh o outro objeto

		}else if ( super.getHits() > reqs.getHits() ){// hits nao iguais e hit do super maior que o reqs
			
			return 1;
			
		}else{ // hits desse objeto menor que comparacao
			
			return -1;
			
		}
	
	}//end compareTo


}//end class
