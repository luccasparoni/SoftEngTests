import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class DisciplinaTests {
    Disciplina disciplina;
    String result;

    @Before
    public void init() {
        disciplina = new Disciplina("SSS0000");
        result = "";
    }

    @Test
    public void casoTeste1() throws NoSuchFieldException, IllegalAccessException {
        disciplina.setDisciplina("SSC0620");
        final Field fieldCodigo = disciplina.getClass().getDeclaredField("codigo");
        fieldCodigo.setAccessible(true);
        assertEquals("SSC0620", fieldCodigo.get(disciplina));
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

    // Testes de listAprovados()
    @Test
    public void listaAprovadosQuandoExisteUmMatriculado() throws NoSuchFieldException, IllegalAccessException {
        ArrayList<Estudante> estudantes = new ArrayList<Estudante>();
        estudantes.add(new Estudante("0000000", 0.0, 0.0, 0.0));
        
        final Field field = disciplina.getClass().getDeclaredField("estudantesMatriculados");
        field.setAccessible(true);
        field.set(disciplina, estudantes);

        assertEquals(estudantes, field.get(disciplina));
    }

    @Test
    public void listAprovadosQuandoExistemAprovados() throws NoSuchFieldException, IllegalAccessException {
        ArrayList<Estudante> estudantes = new ArrayList<Estudante>();
        estudantes.add(new Estudante("0000000", 5.0, 5.0, 5.0));
        estudantes.add(new Estudante("0000001", 5.0, 5.0, 5.0));
        
        final Field fieldEstudantes = disciplina.getClass().getDeclaredField("estudantesMatriculados");
        fieldEstudantes.setAccessible(true);

        fieldEstudantes.set(disciplina, estudantes);
        assertEquals(disciplina.listAprovados(), estudantes);
    }

    @Test
    public void listAprovadosQuandoNaoExistemAprovados() throws NoSuchFieldException, IllegalAccessException {
        ArrayList<Estudante> estudantes = new ArrayList<Estudante>();
        estudantes.add(new Estudante("0000000", 4.0, 4.0, 4.0));
        estudantes.add(new Estudante("0000001", 4.0, 4.0, 4.0));
        
        final Field fieldEstudantes = disciplina.getClass().getDeclaredField("estudantesMatriculados");
        fieldEstudantes.setAccessible(true);

        fieldEstudantes.set(disciplina, estudantes);
        assertEquals(disciplina.listAprovados(), new ArrayList<Estudante>());
    }


    @Test
    public void listAprovadosQuandoNaoExistemMatriculados() throws NoSuchFieldException, IllegalAccessException {
        ArrayList<Estudante> estudantes = new ArrayList<Estudante>();
        
        final Field fieldEstudantes = disciplina.getClass().getDeclaredField("estudantesMatriculados");
        fieldEstudantes.setAccessible(true);

        fieldEstudantes.set(disciplina, estudantes);
        assertEquals(disciplina.listAprovados(), new ArrayList<Estudante>());
    }


    // Testes de listReprovados()
    @Test
    public void listReprovadosQuandoExistemReprovados() throws NoSuchFieldException, IllegalAccessException {
        ArrayList<Estudante> estudantes = new ArrayList<Estudante>();
        estudantes.add(new Estudante("0000000", 4.0, 4.0, 4.0));
        estudantes.add(new Estudante("0000001", 4.0, 4.0, 4.0));
        
        final Field fieldEstudantes = disciplina.getClass().getDeclaredField("estudantesMatriculados");
        fieldEstudantes.setAccessible(true);

        fieldEstudantes.set(disciplina, estudantes);
        assertEquals(disciplina.listReprovados(), estudantes);
    }

    @Test
    public void listReprovadosQuandoNaoExistemReprovados() throws NoSuchFieldException, IllegalAccessException {
        ArrayList<Estudante> estudantes = new ArrayList<Estudante>();
        estudantes.add(new Estudante("0000000", 5.0, 5.0, 5.0));
        estudantes.add(new Estudante("0000001", 5.0, 5.0, 5.0));
        
        final Field fieldEstudantes = disciplina.getClass().getDeclaredField("estudantesMatriculados");
        fieldEstudantes.setAccessible(true);

        fieldEstudantes.set(disciplina, estudantes);
        assertEquals(disciplina.listReprovados(), new ArrayList<Estudante>());
    }


    @Test
    public void listReprovadosQuandoNaoExistemMatriculados() throws NoSuchFieldException, IllegalAccessException {
        ArrayList<Estudante> estudantes = new ArrayList<Estudante>();
        
        final Field fieldEstudantes = disciplina.getClass().getDeclaredField("estudantesMatriculados");
        fieldEstudantes.setAccessible(true);

        fieldEstudantes.set(disciplina, estudantes);
        assertEquals(disciplina.listReprovados(), new ArrayList<Estudante>());
    }

    @Test
    public void casoTeste6() {
        ArrayList<Estudante> estudantes = new ArrayList<Estudante>();
        estudantes.add(new Estudante("0000000", 5.0, 5.0, 5.0));

        StringBuilder stringFormatada = new StringBuilder();
        stringFormatada.append("NUSP:0000000" + "\t");
        stringFormatada.append("P1: 5.0" + "\t");
        stringFormatada.append("P2: 5.0" + "\t");
        stringFormatada.append("P3: 5.0" + "\t");
        stringFormatada.append("\n");

        assertEquals(disciplina.formataListaEstudantes(estudantes, false), stringFormatada.toString());
    }

    @Test
    public void casoTeste7() { //ERROR!
        ArrayList<Estudante> estudantes = new ArrayList<Estudante>();
        assertThrows(Error.class, () -> { disciplina.formataListaEstudantes(estudantes, true); });
    }
}