package com.mora.flapbird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class MyGdxGame extends ApplicationAdapter {

	private  SpriteBatch batch;
	private  Texture[] passaro;
	private Texture fundo;
	private Texture canoBaixo;
	private Texture canoTopo;
	private BitmapFont fonte;

	//Atributos de configuração
	private  int movimento = 30;
	private int larguraDispositivo;
	private int alturaDispositivo;
	private  int estadoJogo = 0; // 0 -> jogo não iniciado ,1 -> jogo iniciado
	private int pontuacao = 0;



	private float variacao = 0;
	private  float velocidadeQueda = 0;
	private float posicaoInicialVertical;
	private float posicaoMovimentoCanoHorizontal;
	private  float espacoEntreCanos;
	private  float deltaTime;
	private Random randomico;
	private float alturaEntreCanosRandomica;
	private  boolean marcouPonto = false;

	@Override
	public void create () {


		batch = new SpriteBatch();
		randomico = new Random();
		fonte = new BitmapFont();
		fonte.setColor(Color.WHITE);
		fonte.getData().setScale(6);

		passaro = new Texture[3];
		passaro[0] = new Texture("passaro1.png");
		passaro[1] = new Texture("passaro2.png");
		passaro[2] = new Texture("passaro3.png");

		fundo = new Texture("fundo.png");
		canoBaixo = new Texture("cano_baixo.png");
		canoTopo = new Texture("cano_topo.png");

		larguraDispositivo = Gdx.graphics.getWidth();
		alturaDispositivo = Gdx.graphics.getHeight();
		posicaoInicialVertical = alturaDispositivo / 2;
		posicaoMovimentoCanoHorizontal = larguraDispositivo ;
		espacoEntreCanos = 250;


	}

	@Override
	public void render () {
		//movimento ++;

		deltaTime = Gdx.graphics.getDeltaTime();
		variacao += deltaTime * 7; //velocidade de bater asas
		if (variacao > 2) {variacao = 0;}

		if (estadoJogo == 0){ // Não iniciado o jogo
			if (Gdx.input.justTouched()){
				estadoJogo = 1;
			}

		}else {


			posicaoMovimentoCanoHorizontal -= deltaTime * 200;
			velocidadeQueda++;





			if (Gdx.input.justTouched()) {
				velocidadeQueda = -12;

			}

			if (posicaoInicialVertical > 0 || velocidadeQueda < 0)
				posicaoInicialVertical = posicaoInicialVertical - velocidadeQueda;

			// Verifica se o cano saiu inteiramente da tela
			if (posicaoMovimentoCanoHorizontal < -canoTopo.getHeight()) {
				posicaoMovimentoCanoHorizontal = larguraDispositivo;
				alturaEntreCanosRandomica = randomico.nextInt(400) - 200;
				marcouPonto = false;

			}
				// Verifica ponuação
				if (posicaoMovimentoCanoHorizontal < 120){
					if ( !marcouPonto){
						pontuacao ++;
						marcouPonto = true;
					}


				}



		}
		batch.begin();


		batch.draw(fundo,0,0,larguraDispositivo,alturaDispositivo);// a sequencia de imagem em relevancia
		batch.draw(canoTopo,posicaoMovimentoCanoHorizontal,alturaDispositivo / 2 + espacoEntreCanos / 2 + alturaEntreCanosRandomica);
		batch.draw(canoBaixo,posicaoMovimentoCanoHorizontal,alturaDispositivo / 2 - canoBaixo.getHeight() - espacoEntreCanos / 2 + Gdx.graphics.getDeltaTime() );
		batch.draw(passaro[ (int)variacao ],120,posicaoInicialVertical);
		fonte.draw(batch,String.valueOf(pontuacao),larguraDispositivo / 2,alturaDispositivo - 30);
		batch.end();

	}
	

}
