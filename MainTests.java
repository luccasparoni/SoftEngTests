import org.junit.Test;
import static org.junit.Assert.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/* Testes de integração das classes */
public class MainTests {

    /* Testa se a criação de disciplina está ok */
    @Test
    public void casoTeste1() {
        MainStub.iniciaDisciplina("SSC0620");
        assertNotNull(MainStub.disciplina);
    }

    /* Testa se a criação de disciplina com nome inválido retorna null para a Main */
    @Test
    public void casoTeste2() {
        MainStub.iniciaDisciplina("S6aC0620");
        assertNull(MainStub.disciplina);
    }

    /* Testa se a adição de 1 aluno está ok */
    @Test
    public void casoTeste3() {
        MainStub.iniciaDisciplina("SSC0620");
        MainStub.AdicionarAlunos("1234567", "9.0", "8.0", "7.0", "0");
        Integer nAlunos = MainStub.disciplina.getQtdeMatriculados();
        assertEquals(Integer.valueOf(1), nAlunos);
    }

    /* Testa se a adição de mais de 1 aluno está ok */
    @Test
    public void casoTeste4() {
        MainStub.iniciaDisciplina("SSC0620");
        MainStub.AdicionarAlunos("1234567", "9.0", "8.0", "7.0", "1");
        Integer nAlunos = MainStub.disciplina.getQtdeMatriculados();
        assertEquals(Integer.valueOf(2), nAlunos);
    }

    /* Testa se a adição de 1 aluno com nota inválida resulta na não adição do mesmo */
    @Test
    public void casoTeste5() {
        MainStub.iniciaDisciplina("SSC0620");
        MainStub.AdicionarAlunos("1234567", "911.0", "8.0", "7.0", "0");
        Integer nAlunos = MainStub.disciplina.getQtdeMatriculados();
        assertEquals(Integer.valueOf(0), nAlunos);
    }

    /* Testa se a adição de 1 aluno com nota e nome inválido resulta na não adição do mesmo */
    @Test
    public void casoTeste6() {
        MainStub.iniciaDisciplina("SSC0620");
        MainStub.AdicionarAlunos("1A34567", "911.0", "8.0", "7.0", "0");
        Integer nAlunos = MainStub.disciplina.getQtdeMatriculados();
        assertEquals(Integer.valueOf(0), nAlunos);
    }

    /* Testa se a busca de 1 aluno cadastrado resulta na retorno do mesmo */
    @Test
    public void casoTeste7() {
        MainStub.iniciaDisciplina("SSC0620");
        MainStub.AdicionarAlunos("1234567", "1.0", "8.0", "7.0", "0");
        Estudante aluno = MainStub.buscaAluno("1234567");
        assertNotNull(aluno);
    }

    /* Testa se a busca de 1 aluno não cadastrado resulta na retorno de null */
    @Test
    public void casoTeste8() {
        MainStub.iniciaDisciplina("SSC0620");
        MainStub.AdicionarAlunos("1234567", "911.0", "8.0", "7.0", "0");
        Estudante aluno = MainStub.buscaAluno("7654321");
        assertNull(aluno);
    }
}

class MainStub extends Main {

    public static void iniciaDisciplina(String aula) throws Error {
        System.out.println(ANSI_WHITE + "\tPara iniciar a utilização deste programa, digite o código da disciplina\n");

        String comando = aula;
        try {
            MainStub.disciplina = new Disciplina(comando);
            return;
        } catch (Error e) {
            System.out.println(ANSI_RED + e.getMessage());
        }
    }

    public static void AdicionarAlunos(String nUsp, String nota1, String nota2, String nota3, String addOutro) {
        System.out.println(
                "\t\t Para adicionar um aluno, digite o número usp, e as notas referentes à P1, P2 e P3 no formato: \n"
                        + ANSI_CYAN + "\t\t1234567 2.0 5.0 6.0");
        String nusp = nUsp;
        Double p1 = Double.parseDouble(nota1);
        Double p2 = Double.parseDouble(nota2);
        Double p3 = Double.parseDouble(nota3);
        disciplina.adicionarEstudante(nusp, p1, p2, p3);
        System.out.println("Estudante Adicionado! Deseja adicionar outro estudante?\n \t" + ANSI_BLUE + "\t1."
                + ANSI_RESET + "Sim\n" + ANSI_BLUE + "\t\t2." + ANSI_RESET + "Não\n");
        String comando = addOutro;
        if (comando.equals("1"))
            //Main.AdicionarAlunos();
            disciplina.adicionarEstudante("7654321", 8.0, 9.0, 10.0);
        //else
            //Main.imprimeMenuInicial();
    }

    public static Estudante buscaAluno(String nUsp) {
        System.out.println("\t Qual é o numero usp do aluno que você deseja obter as notas?");
        String nusp = nUsp;
        Estudante estudante = disciplina.getEstudante(nusp);
        if (estudante == null) {
            System.out.println(ANSI_RED + "\t Aluno não encontrado! Tente novamente!\n" + ANSI_RESET);
            //Main.buscaAluno();
        }
        
        return estudante;
    }
}