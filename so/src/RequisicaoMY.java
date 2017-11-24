
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
		
		if ( super.getHits() == reqs.getHits() ){//verifica se os hits sao iguais, caso sim entra na politica real do my
			
			if (super.getCont() > this.maxCont && reqs.getCont() > this.maxCont){// ambos estouraram o limite, analisa o maior entre eles
				
				if( super.getCont() > reqs.getCont() ){// retorna 1 para caso o primeiro seja maior, permitindo ficar mais tempo!
				
					return 1;
				
				}else{
				
					return -1;
					
				}
				
			}else if ( super.getCont() < this.maxCont )//(esse if limita o infinito do atual objeto) 
														//quando comparado a outro objeto que ainda nao venceu o limite, fica o outro.
				return -1;
			
			else
				
				return 1;// vai ordenar como menor o objeto que for menor que o maxCont

		}else if ( super.getHits() > reqs.getHits() ){// hits nao iguais e hit do super maior que o reqs
			
			return 1;
			
		}else{ // hits desse objeto menor que comparacao
			
			return -1;
			
		}
	
	}//end compareTo


}//end class
