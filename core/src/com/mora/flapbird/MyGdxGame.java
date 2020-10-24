package com.mora.flapbird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {

	private  SpriteBatch batch;
	private  Texture[] passaro;
	private Texture fundo;

	//Atributos de configuração
	private  int movimento = 30;
	private int larguraDispositivo;
	private int alturaDispositivo;

	private float variacao = 0;
	private  float velocidadeQueda = 0;
	private float posicaoInicialVertical;

	@Override
	public void create () {

		batch = new SpriteBatch();
		passaro = new Texture[3];
		passaro[0] = new Texture("passaro1.png");
		passaro[1] = new Texture("passaro2.png");
		passaro[2] = new Texture("passaro3.png");
		fundo = new Texture("fundo.png");

		larguraDispositivo = Gdx.graphics.getWidth();
		alturaDispositivo = Gdx.graphics.getHeight();
		posicaoInicialVertical = alturaDispositivo / 2;

	}

	@Override
	public void render () {
		//movimento ++;
		variacao += Gdx.graphics.getDeltaTime() * 7; //velocidade de bater asas
		velocidadeQueda ++;

		if ( posicaoInicialVertical > 0 ){

			posicaoInicialVertical = posicaoInicialVertical - velocidadeQueda;
		}

		if (variacao > 2){
			variacao = 0;
		}

		batch.begin();
		batch.draw(fundo,0,0,larguraDispositivo,alturaDispositivo);
		batch.draw(passaro[ (int)variacao ],30,posicaoInicialVertical);
		batch.end();

	}
	

}
