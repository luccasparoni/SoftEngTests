import java.util.ArrayList;

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
    private ArrayList<Estudante> listAprovados() {
        return this.estudantesMatriculados.stream().filter(p -> p.isAprovado() == true).collect(Collectors.toList());
    }

    private ArrayList<Estudante> listReprovados() {
        return this.estudantesMatriculados.stream().filter(p -> p.isAprovado() == false).collect(Collectors.toList());
    }

    private String formataListaEstudantes(ArrayList<Estudante> estudantes, boolean media) {
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
    public void adicionarEstudante(String nusp, Float p1, Float p2, Float p3) {
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
        return this.formataListaEstudantes(estudantesOrdenados.toList(), false);
    }

    public String getEstudantesPorNusp() {
        ArrayList<Estudante> estudantesOrdenados = this.estudantesMatriculados;
        estudantesOrdenados.sort((obj1, obj2) -> Integer(obj1.getNusp()).compareTo(Integer(obj2.getNusp())));
        return this.formataListaEstudantes(estudantesOrdenados.toList(), false);
    }

    // h. imprimir aprovados com nusp, notas e média { método }
    public String getAprovados() {
        var aprovados = this.listAprovados();
        return this.formataListaEstudantes(aprovados, true);
    }

    // g. imprimir reprovados com nusp, notas e média { método }
    public String getReprovados() {
        var reprovados = this.listReprovados();
        return this.formataListaEstudantes(reprovados, true);
    }

    public String getAlunosENotas(Status status) {
        ArrayList<Estudante> estudantes;

        if (status == Status.APROVADOS) {
            estudantes = this.listAprovados();
        } else if (status == Status.REPROVADOS) {
            estudantes = this.listReprovados();
        } else {
            estudantes = this.estudantesMatriculados;
        }

        // checar se esta ordenando descendentemente
        estudantes = estudantes.sort((obj1, obj2) -> obj1.getNuspAsInteger().compareTo(obj2.getNuspAsInteger()));

        String stringResultado = new String();
        for (Estudante estudante : estudantes) {
            String stringToAdd = String.format("NUSP: %s, P1: %d, P2: %d, P3: %d", estudante.getNusp(),
                    estudante.getNotaP1(), estudante.getNotaP2(), estudante.getNotaP3());

            stringResultado.concat(stringToAdd + "\n");
        }

        return stringResultado;
    }
}