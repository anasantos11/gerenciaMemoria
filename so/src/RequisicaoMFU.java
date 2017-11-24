
public class RequisicaoMFU extends Requisicao {

	public RequisicaoMFU(String processo, int pag) {
		super(processo, pag);
	}
	
	public RequisicaoMFU(){
		super();
	}
	
	public RequisicaoMFU(Requisicao c){
		super(c);
	}
	
	@Override
	public int compareTo(Requisicao reqs){

		if (super.getHits() == reqs.getHits()){
			if (super.getCont() > reqs.getCont() )
				return 1;  
			else
				return -1;
		}else if (super.getHits()> reqs.getHits() )
			return 1;
		else
			return -1;
		
	}


}
