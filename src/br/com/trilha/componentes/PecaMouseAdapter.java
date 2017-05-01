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
		//Verificar se o objeto � uma pe�a
		if(e.getSource() != null && e.getSource() instanceof Peca){
			//Verificar se todas as pe�as j� foram adicionadas no jogo
			if(tabuleiro.gameStarted){
				//Verificar se a pe�a de origem j� foi selecionada
				if (selectedPeca == null){
					selectedPeca = (Peca) e.getSource();
					System.out.println("Selected: " + selectedPeca.toString());
					//Verificar se a pe�a selecionada pertence ao jogador da vez
					if (selectedPeca.getTipoPeca() != tabuleiro.getJogadorDaVez().getTipoPeca()) {
						selectedPeca = null;
						JOptionPane.showMessageDialog(null, "Selecione sua pe�a!", "Jogada inv�lida", JOptionPane.ERROR_MESSAGE);
					}
				} else if (selectedPeca != null){
					destinoPeca = (Peca) e.getSource();
					System.out.println("Destino: " + destinoPeca.toString());
					//Verificar se a pe�a de destino est� em branco
					if (destinoPeca.getTipoPeca() != TipoPeca.EM_BRANCO) {
						destinoPeca = null;
						selectedPeca = null;
						JOptionPane.showMessageDialog(null, "Jogada inv�lida. Tente novamente!", "Jogada inv�lida", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				//Verificar se a jogada foi feita corretamente
				if (!(selectedPeca == null || destinoPeca == null)) {
					
					boolean isDestinoRangeSelected = selectedPeca.getPecasVizinhas().contains(destinoPeca);
					//Verificar se a jogada � permitida dentro do range permitido
					if (isDestinoRangeSelected || tabuleiro.getJogadorDaVez().isMovimentaPecasEmQualquerLugar()) {
						destinoPeca.setTipoPeca(selectedPeca.getTipoPeca());
						selectedPeca.setTipoPeca(TipoPeca.EM_BRANCO);
						
						//TODO Identificar moinhos
						//TODO Se identificado moinho, permitir remover uma pe�a do advers�rio.
						//TODO Validar para n�o permitir remover pe�a de uma trilha do advers�rio.
						
						tabuleiro.getJogadorDaVez().passarJogada();
						tabuleiro.refreshStatusCaptions();
						
						destinoPeca = null;
						selectedPeca = null;
					}
					if (!isDestinoRangeSelected){
						JOptionPane.showMessageDialog(null, "Movimento n�o permitido. Tente novamente.", "Jogada inv�lida", JOptionPane.ERROR_MESSAGE);
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
						System.out.println("Pe�as colocadas. Jogo come�a agora.");
					}
					tabuleiro.refreshStatusCaptions();
				} else {
					JOptionPane.showMessageDialog(null, "Selecione um espa�o vazio para efetuar a jogada!", "Jogada inv�lida", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
