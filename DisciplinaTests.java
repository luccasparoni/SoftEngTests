import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;

public class DisciplinaTests {
    Disciplina disciplina;
    String result;

    @Before
    public void init() {
        disciplina = new Disciplina("SSS0000");
        result = "";
    }

    @Test
    public void casoTeste1 () {
        result = disciplina.setDisciplina("SSC0984");
        assertEquals("SSC0984", result);
    }
}