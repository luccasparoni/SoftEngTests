import java.io.BufferedReader;
import java.util.Scanner;
import java.io.IOException;

public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    static Scanner scanIn = new Scanner(System.in);
    public static Disciplina disciplina;

    public static void main(String[] args) {
        System.out.println(ANSI_GREEN + "\tBem vindo ao sistema de controle de alunos!\n\n\n" + ANSI_RESET);
        try {
            Main.iniciaPrograma();
        } catch (Exception e) {
            System.out.println(ANSI_RED + e.toString());
        }

    }

    public static void iniciaPrograma() throws Error {
        Main.iniciaDisciplina();
        Main.imprimeMenuInicial();
    }

    public static void iniciaDisciplina() throws Error {
        System.out.println(ANSI_WHITE + "\tPara iniciar a utilização deste programa, digite o código da disciplina\n");

        String comando = lerComando();
        try {
            Main.disciplina = new Disciplina(comando);
            return;
        } catch (Error e) {
            System.out.println(ANSI_RED + e.getMessage());
            Main.iniciaPrograma();
        }
    }

    public static void imprimeMenuInicial() {
        System.out.println(ANSI_WHITE + "Que tipo de operação você deseja realizar? Digite o número correspondente\n");
        System.out.println(ANSI_BLUE + "\t1." + ANSI_RESET + "Adicionar Dados\n");
        System.out.println(ANSI_BLUE + "\t2." + ANSI_RESET + "Buscar Dados\n");

        String comando = Main.lerComando();
        if (comando.contains("1"))
            Main.imprimeMenuAdicionar();
        else if (comando.contains("2"))
            Main.imprimeMenuBuscar();
        else {
            System.out.println(ANSI_RED + "\tComando Inválido! Tente novamente!\n");
            Main.imprimeMenuInicial();
        }
    }

    public static String lerComando() {
        String comando;
        comando = scanIn.next();
        return comando;
    }

    public static void imprimeMenuAdicionar() {
        System.out.println(ANSI_GREEN + "\t Digite o número correspondente à operação desejada:\n\n");
        System.out.println(ANSI_BLUE + "\t1." + ANSI_RESET + "Adicionar Alunos à uma disciplina\n");
        // System.out.println(ANSI_BLUE + "\t2." + ANSI_RESET + "Adicionar Notas de um
        // aluno\n");
        System.out.println(ANSI_BLUE + "\t0." + ANSI_RESET + "Retornar ao menu inicial\n");
        String comando = lerComando();
        switch (comando) {
        case "1":
            Main.AdicionarAlunos();
            break;
        case "0":
            Main.imprimeMenuInicial();
            break;
        default:
            break;
        }
    }

    public static void AdicionarAlunos() {
        System.out.println(
                "\t\t Para adicionar um aluno, digite o número usp, e as notas referentes à P1, P2 e P3 no formato: \n"
                        + ANSI_CYAN + "\t\t1234567 2.0 5.0 6.0");
        String nusp = Main.lerComando();
        Double p1 = Double.parseDouble(Main.lerComando());
        Double p2 = Double.parseDouble(Main.lerComando());
        Double p3 = Double.parseDouble(Main.lerComando());
        disciplina.adicionarEstudante(nusp, p1, p2, p3);
        System.out.println("Estudante Adicionado! Deseja adicionar outro estudante?\n \t" + ANSI_BLUE + "\t1."
                + ANSI_RESET + "Sim\n" + ANSI_BLUE + "\t2." + ANSI_RESET + "Não\n");
        String comando = lerComando();
        if (comando.equals("1"))
            Main.AdicionarAlunos();
        else
            Main.imprimeMenuAdicionar();
    }

    public static void imprimeMenuBuscar() {
        System.out.println(ANSI_GREEN + "\t Digite o número correspondente A operação desejada:\n\n");
        System.out.println(ANSI_BLUE + "\t1." + ANSI_RESET + "Consultar número de alunos matriculados\n");
        System.out.println(ANSI_BLUE + "\t2." + ANSI_RESET + "Consultar número de alunos aprovados\n");
        System.out.println(ANSI_BLUE + "\t3." + ANSI_RESET + "Consultar número de alunos reprovados\n");
        System.out.println(ANSI_BLUE + "\t4." + ANSI_RESET + "Consultar notas e média de um aluno\n");
        System.out.println(ANSI_BLUE + "\t5." + ANSI_RESET + "Consultar alunos e notas, ordenado por Número USP\n");
        System.out.println(ANSI_BLUE + "\t6." + ANSI_RESET + "Consultar alunos e notas, ordenado por média\n");
        System.out.println(
                ANSI_BLUE + "\t7." + ANSI_RESET + "Consultar alunos reprovados com NUMERO USP, NOTAS, e MÉDIA\n");
        System.out.println(
                ANSI_BLUE + "\t8." + ANSI_RESET + "Consultar alunos aprovados com NUMERO USP, NOTAS, e MÉDIA\n");
        System.out.println(ANSI_BLUE + "\t0." + ANSI_RESET + "Retornar ao menu inicial\n");

        String comando = Main.lerComando();
        switch (comando) {
        case "1":
            Integer matriculados = disciplina.getQtdeMatriculados();
            System.out.println(
                    ANSI_YELLOW + "\t\tQuantidade de alunos matriculados:" + ANSI_RESET + matriculados.toString());
            break;
        case "2":
            Integer aprovados = disciplina.getQtdeAprovados();
            System.out.println(ANSI_YELLOW + "\t\tQuantidade de alunos aprovados:" + ANSI_RESET + aprovados.toString());
            break;
        case "3":
            Integer reprovados = disciplina.getQtdeReprovados();
            System.out
                    .println(ANSI_YELLOW + "\t\tQuantidade de alunos aprovados:" + ANSI_RESET + reprovados.toString());
            break;
        case "4":
            Main.buscaAluno();
            break;
        case "5":
            String estudantes = disciplina.getEstudantesPorNusp();
            System.out.println(estudantes);
            break;
        case "6":
            String estudantesPorMedia = disciplina.getEstudantesPorMedia();
            System.out.println(estudantesPorMedia);
            break;
        case "7":
            String listaReprovados = disciplina.getReprovados();
            System.out.println("\n\n" + listaReprovados);
        case "8":
            String listaAprovados = disciplina.getAprovados();
            System.out.println("\n\n" + listaAprovados);
        default:
            System.out.println(ANSI_RED + "\tComando Inválido! Tente novamente!\n");
            break;
        }
        Main.imprimeMenuBuscar();
    }

    public static void buscaAluno() {
        System.out.println("\t Qual é o numero usp do aluno que você deseja obter as notas?");
        String nusp = Main.lerComando();
        Estudante estudante = disciplina.getEstudante(nusp);
        if (estudante == null)
            System.out.println(ANSI_RED + "\t Aluno não encontrado! Tente novamente!\n");
        System.out.println(
                "\t\tSobre o estudante de número usp: " + ANSI_GREEN + nusp + " Obtemos as seguintes informações:");
        System.out.println("\n\t\tP1: " + estudante.getNotaP1().toString());
        System.out.println("\n\t\tP2: " + estudante.getNotaP2().toString());
        System.out.println("\n\t\tP3: " + estudante.getNotaP3().toString());
        System.out.println("\n\t\tMedia: " + estudante.calculaMedia().toString());
        return;
    }

}
