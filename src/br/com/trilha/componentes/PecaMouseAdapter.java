package br.com.trilha.componentes;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import br.com.trilha.enums.TipoPeca;
import br.com.trilha.tela.Tabuleiro;

public class PecaMouseAdapter extends MouseAdapter {
	
	private Peca selectedPeca;
	private Tabuleiro tabuleiro;
	private Peca destinoPeca;
	
	public PecaMouseAdapter(Tabuleiro tabuleiro){
		this.tabuleiro = tabuleiro;
	}
	
	public void mouseClicked(MouseEvent e) {
		//Verificar se o objeto é uma peça
		if(e.getSource() != null && e.getSource() instanceof Peca){
			//Verificar se todas as peças já foram adicionadas no jogo
			if(tabuleiro.gameStarted){
				//Verificar se a peça de origem já foi selecionada
				if (selectedPeca == null){
					selectedPeca = (Peca) e.getSource();
					System.out.println("Selected: " + selectedPeca.toString());
					//Verificar se a peça selecionada pertence ao jogador da vez
					if (selectedPeca.getTipoPeca() != tabuleiro.getJogadorDaVez().getTipoPeca()) {
						selectedPeca = null;
						JOptionPane.showMessageDialog(null, "Selecione sua peça!", "Jogada inválida", JOptionPane.ERROR_MESSAGE);
					}
				} else if (selectedPeca != null){
					destinoPeca = (Peca) e.getSource();
					System.out.println("Destino: " + destinoPeca.toString());
					//Verificar se a peça de destino está em branco
					if (destinoPeca.getTipoPeca() != TipoPeca.EM_BRANCO) {
						destinoPeca = null;
						selectedPeca = null;
						JOptionPane.showMessageDialog(null, "Jogada inválida. Tente novamente!", "Jogada inválida", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				//Verificar se a jogada foi feita corretamente
				if (!(selectedPeca == null || destinoPeca == null)) {
					
					boolean isDestinoRangeSelected = selectedPeca.getPecasVizinhas().contains(destinoPeca);
					//Verificar se a jogada é permitida dentro do range permitido
					if (isDestinoRangeSelected || tabuleiro.getJogadorDaVez().isMovimentaPecasEmQualquerLugar()) {
						destinoPeca.setTipoPeca(selectedPeca.getTipoPeca());
						selectedPeca.setTipoPeca(TipoPeca.EM_BRANCO);
						
						//TODO Identificar moinhos
						//TODO Se identificado moinho, permitir remover uma peça do adversário.
						//TODO Validar para não permitir remover peça de uma trilha do adversário.
						
						tabuleiro.getJogadorDaVez().passarJogada();
						tabuleiro.refreshStatusCaptions();
						
						destinoPeca = null;
						selectedPeca = null;
					}
					if (!isDestinoRangeSelected){
						JOptionPane.showMessageDialog(null, "Movimento não permitido. Tente novamente.", "Jogada inválida", JOptionPane.ERROR_MESSAGE);
						destinoPeca = null;
						selectedPeca = null;
					}
				}
			} else {
				Peca selected = (Peca) e.getSource();
				System.out.println(selected.toString());
				
				if (selected.getTipoPeca() == TipoPeca.EM_BRANCO){
					selected.setTipoPeca(tabuleiro.getJogadorDaVez().getTipoPeca());
					tabuleiro.getJogadorDaVez().addPeca(selected);
					tabuleiro.getJogadorDaVez().diminuiQtdPecasIniciais();
					tabuleiro.getJogadorDaVez().passarJogada();
					if (tabuleiro.getPlayer1().getQtdPecasIniciaisRestantes() == 0 && tabuleiro.getPlayer2().getQtdPecasIniciaisRestantes() == 0) {
						tabuleiro.gameStarted = true;
						System.out.println("Peças colocadas. Jogo começa agora.");
					}
					tabuleiro.refreshStatusCaptions();
				} else {
					JOptionPane.showMessageDialog(null, "Selecione um espaço vazio para efetuar a jogada!", "Jogada inválida", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
