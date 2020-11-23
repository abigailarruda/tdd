package leilao.teste;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Test;

import leilao.dominio.Lance;
import leilao.dominio.Leilao;
import leilao.dominio.Usuario;
import leilao.servico.Avaliador;

public class TesteDoAvaliador {

    @Test
    public void deveEntenderLancesEmOrdemCrescenteComOutrosValores() {
	Usuario joao = new Usuario("Jo�o");
	Usuario jose = new Usuario("Jos�");
	Usuario maria = new Usuario("Maria");

	Leilao leilao = new Leilao("Playstation 3 Novo");

	leilao.propoe(new Lance(joao, 1000.0));
	leilao.propoe(new Lance(jose, 2000.0));
	leilao.propoe(new Lance(maria, 3000.0));

	Avaliador leiloeiro = new Avaliador();
	leiloeiro.avalia(leilao);

	assertEquals(3000.0, leiloeiro.getMaiorLance(), 0.0001);
	assertEquals(1000.0, leiloeiro.getMenorLance(), 0.0001);
    }

    @Test
    public void deveEntenderLeilaoComApenasUmLance() {
	Usuario joao = new Usuario("Jo�o");
	Leilao leilao = new Leilao("Playstation 3 Novo");

	leilao.propoe(new Lance(joao, 1000.0));

	Avaliador leiloeiro = new Avaliador();
	leiloeiro.avalia(leilao);

	assertEquals(1000.0, leiloeiro.getMaiorLance(), 0.0001);
	assertEquals(1000.0, leiloeiro.getMenorLance(), 0.0001);
    }

    @Test
    public void deveEncontrarOsTresMaioresLances() {
	Usuario joao = new Usuario("Jo�o");
	Usuario maria = new Usuario("Maria");
	Leilao leilao = new Leilao("Playstation 3 Novo");

	leilao.propoe(new Lance(joao, 100.0));
	leilao.propoe(new Lance(maria, 200.0));
	leilao.propoe(new Lance(joao, 300.0));
	leilao.propoe(new Lance(maria, 400.0));

	Avaliador leiloeiro = new Avaliador();
	leiloeiro.avalia(leilao);

	List<Lance> maiores = leiloeiro.getTresMaiores();
	assertEquals(3, maiores.size());
	assertEquals(400.0, maiores.get(0).getValor(), 0.00001);
	assertEquals(300.0, maiores.get(1).getValor(), 0.00001);
	assertEquals(200.0, maiores.get(2).getValor(), 0.00001);
    }
}