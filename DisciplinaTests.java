import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaTests {
    Disciplina disciplina;
    String result;

    @Before
    public void init() {
        disciplina = new Disciplina("SSS0000");
        result = "";
    }

    @Test
    /* Teste unitário: caso codigo disciplina -> 7 chars (3 letras e 4 numeros) */
    public void casoTeste1() throws NoSuchFieldException, IllegalAccessException {
        disciplina.setDisciplina("SSC0620");
        final Field fieldCodigo = disciplina.getClass().getDeclaredField("codigo");
        fieldCodigo.setAccessible(true);
        assertEquals("SSC0620", fieldCodigo.get(disciplina));
    }

    @Test
    /* Teste unitário: caso codigo disciplina -> 7 chars (3 letras e 4 numeros) */
    public void casoTeste8() throws NoSuchFieldException, IllegalAccessException {
        disciplina.setDisciplina("ABZ0099");
        final Field fieldCodigo = disciplina.getClass().getDeclaredField("codigo");
        fieldCodigo.setAccessible(true);
        assertEquals("ABZ0099", fieldCodigo.get(disciplina));
    }

    @Test
    /* Teste unitário: caso codigo disciplina -> !3 letras */
    public void casoTeste2() {
        assertThrows(Error.class, () -> {
            disciplina.setDisciplina("SS60620");
        });
    }

    @Test
    /* Teste unitário: caso codigo disciplina -> 4 numeros */
    public void casoTeste3() {
        assertThrows(Error.class, () -> {
            disciplina.setDisciplina("SSC0C20");
        });
    }

    @Test
    /* Teste unitário: caso codigo disciplina -> !3letras e !4 numeros e !7 chars */
    public void casoTeste4() {
        assertThrows(Error.class, () -> {
            disciplina.setDisciplina("S2C");
        });
    }

    @Test
    /* Teste unitário: caso codigo disciplina -> !3letras e !4 numeros e !7 chars */
    public void casoTeste5() {
        assertThrows(Error.class, () -> {
            disciplina.setDisciplina("");
        });
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
    /* Teste unitário: caso lista de estudantes -> contem um estudante valido */
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
    /* Teste unitário: caso lista de estudantes -> contem conteudo nulo */
    public void casoTeste7() { // ERROR!
        ArrayList<Estudante> estudantes = new ArrayList<Estudante>();
        assertThrows(Error.class, () -> {
            disciplina.formataListaEstudantes(estudantes, false);
        });
    }

    // Testes de getEstudante()
    @Test
    public void getEstudanteQuandoExisteNusp() {
        Estudante estudante = new Estudante("0000000", 10.0, 10.0, 9.0);
        disciplina.estudantesMatriculados.add(estudante);
        assertEquals(disciplina.getEstudante("0000000"), estudante);
    }

    @Test
    public void getEstudanteQuandoNaoExisteNusp() {
        Estudante estudante = new Estudante("0000000", 10.0, 10.0, 9.0);
        assertEquals(disciplina.getEstudante("0000001"), null);
    }

    // Testes de getEstudantesPorNusp()
    class GetEstudanteDisciplinaStub extends Disciplina {
        public GetEstudanteDisciplinaStub(String codigo) {
            super(codigo);

        }

        @Override
        public String formataListaEstudantes(List<Estudante> estudantes, boolean media) {
            return estudantes.toString();
        }
    }

    @Test
    public void getEstudantesPorNusp() {
        Estudante estudante1 = new Estudante("0000000", 10.0, 10.0, 9.0);
        Estudante estudante2 = new Estudante("1234567", 2.0, 3.0, 10.0);

        disciplina.estudantesMatriculados.add(estudante1);
        disciplina.estudantesMatriculados.add(estudante2);

        disciplina = new GetEstudanteDisciplinaStub("scc1234");

        String result = disciplina.getEstudantesPorNusp();
        assertEquals(result, disciplina.estudantesMatriculados.toString());
    }

    List<Estudante> listaEstudantes = new ArrayList<Estudante>();

    class DisciplinaStub extends Disciplina {
        public DisciplinaStub(String codigo) {
            super(codigo);

        }

        @Override
        public List<Estudante> listAprovados() {
            return listaEstudantes;
        }

        @Override
        public List<Estudante> listReprovados() {
            return listaEstudantes;
        }

        @Override
        public String formataListaEstudantes(List<Estudante> estudantes, boolean media) {
            return "Retorno de lista";
        }
    }

    @Test
    public void getAprovadosQuandoHaAprovados() {
        listaEstudantes.add(new Estudante("1234567", 10.0, 10.0, 10.0));

        disciplina = new DisciplinaStub("scc1234");

        String result = disciplina.getAprovados();
        assertEquals(result, "Retorno de lista");
    }

    @Test
    public void getReprovadosQuandoHaReprovados() {
        listaEstudantes.add(new Estudante("1234567", 0.0, 0.0, 0.0));
        disciplina = new DisciplinaStub("scc1234");

        String result = disciplina.getReprovados();
        assertEquals(result, "Retorno de lista");
    }
}