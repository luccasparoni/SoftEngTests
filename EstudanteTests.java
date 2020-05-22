import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
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
        Estudante teste = new Estudante("7654321");
        assertNotNull(teste);
    }
}