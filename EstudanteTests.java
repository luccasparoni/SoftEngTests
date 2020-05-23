import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.Before;
import static org.junit.Assert.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/* Testes unitários da classe Estudante */
// Por algum motivo o @DisplayName não está funcionando no Vscode, por isso
//   comentamos acima de cada teste
@DisplayName("Testes para classe Estudante.")
public class EstudanteTests {
    Estudante estudante;
    String result;
    double valor;

    @Before
    public void init() {
        estudante = new Estudante("1234567");
        result = "";
        valor = 0.00;
    }

    /* Teste unitário: caso nUsp válido */
    @Test
    public void casoTeste1() {
        Estudante teste = new Estudante("1234567");
        assertNotNull(teste);
    }

    /* Teste unitário: caso nUsp válido */
    @Test
    public void casoTeste13 () {
        Estudante teste = new Estudante("987630");
        assertNotNull(teste);
    }

    /* Teste unitário: caso nUsp inválido -> letra */
    @Test
    @DisplayName("Teste1")
    public void casoTeste2() {
        assertThrows(Error.class, () -> {
            new Estudante("123A567");
        });
    }

    /* Teste unitário: caso nUsp inválido -> tamanho */
    @Test
    public void casoTeste3() {
        assertThrows(Error.class, () -> {
            new Estudante("123");
        });
    }

    /* Teste unitário: caso nUsp inválido -> vazio */
    @Test
    public void casoTeste4() {
        assertThrows(Error.class, () -> {
            new Estudante("");
        });
    }

    /* Teste unitário: caso nota válido */
    @Test
    public void casoTeste5() {
        int erro = estudante.addNota("P1", 0.0);
        assertEquals(0, erro);
    }

    /* Teste unitário: caso nota válido */
    @Test
    public void casoTeste6() {
        int erro = estudante.addNota("P2", 10.0);
        assertEquals(0, erro);
    }

    /* Teste unitário: caso nota válido */
    @Test
    public void casoTeste14() {
        int erro = estudante.addNota("P3", 7.5);
        assertEquals(0, erro);
    }

    /* Teste unitário: caso nota válido */
    @Test
    public void casoTeste15() {
        int erro = estudante.addNota("P3", 2.5);
        assertEquals(0, erro);
    }

    /* Teste unitário: caso nota inválido -> fora do intervalo */
    @Test
    public void casoTeste7() {
        assertThrows(Error.class, () -> {
            estudante.addNota("P1", -1.0);
        });
    }

    /* Teste unitário: caso nota inválido -> fora do intervalo */
    @Test
    public void casoTeste16() {
        assertThrows(Error.class, () -> { estudante.addNota("P1", -15.3); });
    }

    /* Teste unitário: caso nota inválido -> fora do intervalo */
    @Test
    public void casoTeste8() {
        assertThrows(Error.class, () -> { estudante.addNota("P2", 11.0); });
    }

    /* Teste unitário: caso nota inválido -> fora do intervalo */
    @Test
    public void casoTeste9() {
        assertThrows(Error.class, () -> { estudante.addNota("P3", 105.0); });
    }

    /* Teste unitário: caso valores válidos para média */
    @Test
    public void casoTeste10() {
        estudante.addNota("P1", 7.0);
        estudante.addNota("P2", 9.0);
        estudante.addNota("P3", 4.0);
        valor = estudante.calculaMedia();
        assertEquals(6.6, valor, 0.1);
    }

    /* Teste unitário: caso valores válidos para aprovação */
    @Test
    public void casoTeste11() {
        estudante.addNota("P1", 7.0);
        estudante.addNota("P2", 9.0);
        estudante.addNota("P3", 4.0);
        assertTrue(estudante.isAprovado());
    }

    /* Teste unitário: caso valores válidos para reprovação */
    @Test
    public void casoTeste12() {
        estudante.addNota("P1", 7.0);
        estudante.addNota("P2", 2.0);
        estudante.addNota("P3", 4.0);
        assertFalse(estudante.isAprovado());
    }
}