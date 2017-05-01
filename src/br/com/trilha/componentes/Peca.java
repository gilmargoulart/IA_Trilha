package br.com.trilha.componentes;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import br.com.trilha.enums.TipoPeca;

@SuppressWarnings("serial")
public class Peca extends JLabel {
	
	private TipoPeca tipoPeca;
	private List<Peca> pecasVizinhas = new ArrayList<>();
	private List<List<Peca>> moinhos = new ArrayList<>();
	
	public Peca(){
		
	}
	
	/**
	 * Inicializar Peca com TipoPeca em Branco, tamanho pré-definido, e com MouseListener pré-definido.
	 * @param name
	 */
	public Peca(String name){
		setName(name);
		setTipoPeca(TipoPeca.EM_BRANCO);
		setSize(60, 60);
		setMinimumSize(new Dimension(60, 60));
	}
	
	/**
	 * Inicializa Peca apenas com o TipoPeca.
	 * @param tipoPeca
	 */
	public Peca(TipoPeca tipoPeca){
		setTipoPeca(tipoPeca);
	}
	
	/**
	 * Define o tipo de peça
	 * @param tipoPeca
	 */
	public void setTipoPeca(TipoPeca tipoPeca) {
		if (tipoPeca == null) {
			this.tipoPeca = TipoPeca.EM_BRANCO;
		} else {
			this.tipoPeca = tipoPeca;
			setIcon(tipoPeca.getImageIcon());
			repaint();
		}
	}
	
	public TipoPeca getTipoPeca(){
		return this.tipoPeca;
	}
	
	@Override
	public String toString(){
		return "Peça: " + tipoPeca.getTipoPeca() + " - " + tipoPeca.name() + ", " + getName();
	}
	
	/**
	 * Retorna lista de peças vizinhas.
	 * @return
	 */
	public List<Peca> getPecasVizinhas() {
		return pecasVizinhas;
	}
	
	/**
	 * Adiciona peça na lista de peças vizinhas.
	 * Será utilizado para validar as jogadas.
	 * @param peca
	 */
	public void addPecaVizinha(Peca peca) {
		pecasVizinhas.add(peca);
	}
	
	/**
	 * Adiciona peças na lista de peças vizinhas.
	 * Será utilizado para validar as jogadas.
	 * @param peca
	 */
	public void addPecaVizinha(List<Peca> pecas){
		pecasVizinhas.addAll(pecas);
	}
	
	/**
	 * Adiciona peças na lista de possíveis moinhos
	 * @param peca
	 */
	public void addPecaMoinho(List<Peca> pecas){
		moinhos.add(pecas);
	}
}
