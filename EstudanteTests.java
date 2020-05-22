import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

    @Test
    public void casoTeste1 () {
        Estudante teste = new Estudante("1234567");
        assertNotNull(teste);
    }

    @Test
    public void casoTeste2() {
        assertThrows(Error.class, () -> { new Estudante("123A567"); });
    }

    @Test
    public void casoTeste3() {
        assertThrows(Error.class, () -> { new Estudante("123"); });
    }

    @Test
    public void casoTeste4() {
        assertThrows(Error.class, () -> { new Estudante(""); });
    }

    @Test
    public void casoTeste5() {
        int erro = estudante.addNota("P1", 0.0);
        assertEquals(0, erro);
    }

    @Test
    public void casoTeste6() {
        int erro = estudante.addNota("P2", 10.0);
        assertEquals(10, erro);
    }

    @Test
    public void casoTeste7() {
        assertThrows(Error.class, () -> { estudante.addNota("P1", -1.0); });
    }

    @Test
    public void casoTeste8() {
        assertThrows(Error.class, () -> { estudante.addNota("P2", 11.0); });
    }

    @Test
    public void casoTeste9() {
        assertThrows(Error.class, () -> { estudante.addNota("P3", Double.parseDouble("a")); });
    }
}