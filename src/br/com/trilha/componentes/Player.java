package br.com.trilha.componentes;

import java.util.ArrayList;
import java.util.List;

import br.com.trilha.enums.TipoPeca;

public class Player {
	
	private List<Peca> pecas;
	private List<Peca> pecasAdversario;
	private String nome;
	private boolean vezDoJogador;
	private boolean movimentaPecasEmQualquerLugar;
	private TipoPeca tipoPeca;
	private Player adversario;
	private int qtdPecasIniciaisRestantes;
	
	public Player(String nome, TipoPeca tipoPeca){
		setNome(nome);
		setVezDoJogador(false);
		setTipoPeca(tipoPeca);
		setQtdPecasIniciaisRestantes(0);
		pecas = new ArrayList<>();
		pecasAdversario = new ArrayList<>();
		movimentaPecasEmQualquerLugar = false;
	}

	public String getNome() {
		return nome;
	}

	private void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Adiciona Peça.
	 * @param peca
	 */
	public void addPeca(Peca peca){
		pecas.add(peca);
	}
	
	/**
	 * Adiciona quantidade específica de Peças
	 * @param qtdPecas
	 * @param tipoPeca
	 */
	public void addPeca(int qtdPecas, TipoPeca tipoPeca){
		for (int i = 0; i < qtdPecas; i++) {
			pecas.add(new Peca(tipoPeca));
		}
	}
	
	public int getQtdPecas(){
		return pecas.size();
	}
	
	public int getQtdPecasAdversario(){
		return pecasAdversario.size();
	}
	
	/**
	 * Remove a última peça da lista e a retorna.
	 * @return
	 */
	public Peca removerPeca(){
		Peca peca = pecas.get(getQtdPecas()-1); 
		pecas.remove(peca);
		return peca;
	}
	
	/**
	 * Adiciona 
	 */
	public void addPecaAdversario(Peca peca){
		pecasAdversario.add(peca);
	}

	public boolean isVezDoJogador() {
		return vezDoJogador;
	}

	public void setVezDoJogador(boolean vezDoJogador) {
		this.vezDoJogador = vezDoJogador;
	}

	public TipoPeca getTipoPeca() {
		return tipoPeca;
	}

	private void setTipoPeca(TipoPeca tipoPeca) {
		this.tipoPeca = tipoPeca;
	}

	public boolean isMovimentaPecasEmQualquerLugar() {
		return movimentaPecasEmQualquerLugar;
	}

	public void setMovimentaPecasEmQualquerLugar() {
		this.movimentaPecasEmQualquerLugar = true;
	}

	public void setAdversario(Player adversario) {
		this.adversario = adversario;
	}
	
	public void passarJogada(){
		setVezDoJogador(false);
		adversario.setVezDoJogador(true);
	}

	public int getQtdPecasIniciaisRestantes() {
		return qtdPecasIniciaisRestantes;
	}

	public void setQtdPecasIniciaisRestantes(int qtdPecasIniciaisRestantes) {
		this.qtdPecasIniciaisRestantes = qtdPecasIniciaisRestantes;
	}
	
	public void diminuiQtdPecasIniciais(){
		if (qtdPecasIniciaisRestantes > 0) {
			qtdPecasIniciaisRestantes -= 1;
		}
	}
}
