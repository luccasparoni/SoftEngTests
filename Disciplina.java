import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Disciplina {
    private String codigo;
    private ArrayList<Estudante> estudantesMatriculados;

    enum Status {
        APROVADOS, REPROVADOS, TODOS
    };

    // Função de criação de disciplina
    public Disciplina(String codigo) {
        this.codigo = this.setDisciplina(codigo);
        this.estudantesMatriculados = new ArrayList<Estudante>();
    }

    // Funções de Apoio
    private List<Estudante> listAprovados() {
        return this.estudantesMatriculados.stream().filter(p -> p.isAprovado() == true).collect(Collectors.toList());
    }

    private List<Estudante> listReprovados() {
        return this.estudantesMatriculados.stream().filter(p -> p.isAprovado() == false).collect(Collectors.toList());
    }

    private String formataListaEstudantes(List<Estudante> estudantes, boolean media) {
        StringBuilder alunosFormatados = new StringBuilder();
        for (Estudante e : estudantes) {
            alunosFormatados.append("NUSP:" + e.getNusp() + "\t");
            alunosFormatados.append("P1: " + e.getNotaP1().toString() + "\t");
            alunosFormatados.append("P2: " + e.getNotaP2().toString() + "\t");
            alunosFormatados.append("P3: " + e.getNotaP3().toString() + "\t");
            if (media)
                alunosFormatados.append("Media: " + e.calculaMedia().toString());
            alunosFormatados.append("\n");
        }
        return alunosFormatados.toString();
    }

    public Estudante getEstudante(String nusp) {
        return this.estudantesMatriculados.stream().filter(p -> p.getNusp() == nusp).findAny().orElse(null);
    }

    // Função de adição de disciplina
    public String setDisciplina(String codigo) {
        Pattern padrao = Pattern.compile("[A-Z]{3}\\d{4}");
        if (!padrao.matcher(codigo).matches())
            throw new Error("Código de disciplina inválido. Deve conter 3 letras e 4 dígitos (ex: 'SCC0620')");
        return codigo;
    }

    // Função de adicionar um estudante à disciplina
    public void adicionarEstudante(String nusp, Double p1, Double p2, Double p3) {
        Estudante estudante = new Estudante(nusp, p1, p2, p3);
        this.estudantesMatriculados.add(estudante);
    }

    // Função de adicionar notas à um estudante?

    // Funções de consulta
    public Integer getQtdeMatriculados() {
        return this.estudantesMatriculados.size();
    }

    public int getQtdeAprovados() {
        return this.listAprovados().size();
    }

    public int getQtdeReprovados() {
        return this.listReprovados().size();
    }

    public String getNotasEstudante(String nusp) {
        Estudante estudante = this.estudantesMatriculados.stream().filter(p -> p.getNusp() == nusp).findAny()
                .orElse(null);
        return String.format("P1: %d, P2: %d, P3: %d", estudante.getNotaP1(), estudante.getNotaP2(),
                estudante.getNotaP3());
    }

    public String getEstudantesPorMedia() {
        ArrayList<Estudante> estudantesOrdenados = this.estudantesMatriculados;
        estudantesOrdenados.sort((obj1, obj2) -> obj1.calculaMedia().compareTo(obj2.calculaMedia()));
        return this.formataListaEstudantes(estudantesOrdenados, false);
    }

    public String getEstudantesPorNusp() {
        ArrayList<Estudante> estudantesOrdenados = this.estudantesMatriculados;
        estudantesOrdenados.sort((obj1, obj2) -> obj1.getNuspAsInteger().compareTo(obj2.getNuspAsInteger()));
        return this.formataListaEstudantes(estudantesOrdenados, false);
    }

    // h. imprimir aprovados com nusp, notas e média { método }
    public String getAprovados() {
        List<Estudante> aprovados = this.listAprovados();
        return this.formataListaEstudantes(aprovados, true);
    }

    // g. imprimir reprovados com nusp, notas e média { método }
    public String getReprovados() {
        List<Estudante> reprovados = this.listReprovados();
        return this.formataListaEstudantes(reprovados, true);
    }
}

