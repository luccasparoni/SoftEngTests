import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class DisciplinaTests {
    Disciplina disciplina;
    String result;

    @Before
    public void init() {
        disciplina = new Disciplina("SSS0000");
        result = "";
    }

    @Test
    public void casoTeste1() {
        result = disciplina.setDisciplina("SSC0620");
        assertEquals("SSC0620", result);
    }

    @Test
    public void casoTeste2() {
        assertThrows(Error.class, () -> { disciplina.setDisciplina("SS60620"); });
    }

    @Test
    public void casoTeste3() {
        assertThrows(Error.class, () -> { disciplina.setDisciplina("SSC0C20"); });
    }

    @Test
    public void casoTeste4() {
        assertThrows(Error.class, () -> { disciplina.setDisciplina("S2C"); });
    }

    @Test
    public void casoTeste5() {
        assertThrows(Error.class, () -> { disciplina.setDisciplina(""); });
    }
}