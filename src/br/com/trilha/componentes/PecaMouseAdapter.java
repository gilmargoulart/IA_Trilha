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
		
		//N�o tratar eventos ao t�rmino do jogo.
		if (tabuleiro.gameOver) return;
		
		//Verificar se o objeto � uma pe�a
		if(e.getSource() != null && e.getSource() instanceof Peca){
			
			if (tabuleiro.getJogadorDaVez().isMoinhoDetectado()) {
				Peca p = (Peca) e.getSource();
				if (p.getTipoPeca() == tabuleiro.getJogadorDaVez().getAdversario().getTipoPeca()) {
					p.setTipoPeca(TipoPeca.EM_BRANCO);
					
					tabuleiro.getJogadorDaVez().getAdversario().removerPeca(p);
					tabuleiro.getJogadorDaVez().addPecaAdversario(p);
					tabuleiro.getJogadorDaVez().setMoinhoDetectado(false);
					tabuleiro.getJogadorDaVez().passarJogada();
					tabuleiro.refreshStatusCaptions();
				}
				return;
			}
			
			//Verificar se � o BOT jogando, e n�o permitir o evento.
			if (tabuleiro.getPlayer1().isVezDoJogador()) return;
			
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
						
						boolean moinhoDetectado = destinoPeca.isMoinho(); 
						if (moinhoDetectado){
							tabuleiro.getJogadorDaVez().setMoinhoDetectado(true);
							JOptionPane.showMessageDialog(null, "Moinho detectado. Selecione uma pe�a do advers�rio para remover.", "Moinho detectado", JOptionPane.INFORMATION_MESSAGE);
						} else {
							tabuleiro.getJogadorDaVez().passarJogada();
						}
						
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
				final Peca selected = (Peca) e.getSource();
				System.out.println(selected.toString());
				
				if (selected.getTipoPeca() == TipoPeca.EM_BRANCO){
					selected.setTipoPeca(tabuleiro.getJogadorDaVez().getTipoPeca());
					tabuleiro.getJogadorDaVez().addPeca(selected);
					tabuleiro.getJogadorDaVez().diminuiQtdPecasIniciais();
					boolean moinhoDetectado = selected.isMoinho(); 
					if (moinhoDetectado){
						tabuleiro.getJogadorDaVez().setMoinhoDetectado(true);
						JOptionPane.showMessageDialog(null, "Moinho detectado. Selecione uma pe�a do advers�rio para remover.", "Moinho detectado", JOptionPane.INFORMATION_MESSAGE);
					} else {
						tabuleiro.getJogadorDaVez().passarJogada();
					}
					tabuleiro.refreshStatusCaptions();
				} else {
					JOptionPane.showMessageDialog(null, "Selecione um espa�o vazio para efetuar a jogada!", "Jogada inv�lida", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
