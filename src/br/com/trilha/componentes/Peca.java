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
	
	/**
	 * Retorna True se o espaço da peça estiver em branco, e as Peças de moinhos (em volta da peça em branco) forem do adversário.
	 */
	public Peca isPossivelMoinho(){
		Peca possivelMoinho = null;
		//if (tipoPeca == TipoPeca.EM_BRANCO) {
		int qtdInicial = 0;
		if (tipoPeca != TipoPeca.EM_BRANCO) {
			qtdInicial++;
		}
		
		int qtd = 0;
		for (List<Peca> pecas : moinhos) {
			qtd = qtdInicial;
			
			if (pecas.get(0).getTipoPeca() == this.tipoPeca && this.tipoPeca != TipoPeca.EM_BRANCO) {
				qtd++;
			}
			
			if (pecas.get(1).getTipoPeca() == this.tipoPeca && this.tipoPeca != TipoPeca.EM_BRANCO) {
				qtd++;
			}
			
			if (qtd >= 2) { //Possível Moinho
				if (pecas.get(0).getTipoPeca() == TipoPeca.EM_BRANCO) {
					possivelMoinho = pecas.get(0); 
				}
				if (pecas.get(1).getTipoPeca() == TipoPeca.EM_BRANCO) {
					possivelMoinho = pecas.get(1); 
				}
				break;
			}
		}
		
		return possivelMoinho;
	}
	
	public boolean isMoinho(){
		boolean moinho = false;
		for (List<Peca> pecas : moinhos) {
			if (tipoPeca != TipoPeca.EM_BRANCO
				&& pecas.get(0).getTipoPeca() == tipoPeca
				&& pecas.get(1).getTipoPeca() == tipoPeca) {
				moinho = true;
				break;
			}
		}
		
		return moinho;
	}
	
	public List<List<Peca>> getMoinhos(){
		return this.moinhos;
	}
	
	public void removerPecasVizinhas(){
		pecasVizinhas.removeAll(pecasVizinhas);
	}
	
	public void removerMoinhos(){
		moinhos.removeAll(moinhos);
	}
}
