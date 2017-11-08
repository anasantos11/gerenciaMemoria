import javax.swing.JOptionPane;

public class Aplicacao {
	private static Arquivo arquivo;

	public static void main(String[] args) throws Exception {
		try {
			arquivo = new Arquivo();
			String dadosArquivo = arquivo.pegarDadosArquivo();
			//System.out.println(dadosArquivo);
			arquivo.gerarConfiguracao(dadosArquivo);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Mensagem", JOptionPane.WARNING_MESSAGE);
		} 

	}
}
