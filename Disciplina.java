public class Disciplina {
    private String codigo;
    private ArrayList<Estudante> estudantesMatriculados;

    public Disciplina(String codigo) {
        setDisciplina(codigo);
        this.estudantesMatriculados = new ArrayList<Estudante>();
    }

    public void setDisciplina(String codigo)
    {
        Pattern padrao = Pattern.compile("[A-Z]{3}\\d{4}");
        if(!padrao.matcher(codigo).matches())
            throw new Error("Código de disciplina inválido. Deve conter 3 letras e 4 dígitos (ex: 'SCC0620')");
        this.codigo = codigo;
    }

    public void adicionarEstudante(String nusp, float p1, float p2, float p3)
    {
        Estudante estudante = new Estudante(nusp, p1, p2, p3);
        this.estudantesMatriculados.add(estudante);
    }

    public Integer getMatriculados()
    {
        return this.estudantesMatriculados.size();
    }

    public List<Estudante> getAprovados()
    {
        return this.estudantesMatriculados.stream().filter(p -> p.isAprovado() == true).collect(Collectors.toList());
    }

    public List<Estudante> getReprovados()
    {
        return this.estudantesMatriculados.stream().filter(p -> p.isAprovado() == false).collect(Collectors.toList());
    }

    public Estudante getEstudante(String nusp)
    {
        return this.estudantesMatriculados.stream().filter(p -> p.getNusp() == nusp).findAny().orElse(null);
    }

    public String getNotasEstudante(String nusp)
    {
        Estudante estudante = this.estudantesMatriculados.stream().filter(p -> p.getNusp() == nusp).findAny().orElse(null);
        return String.format("P1: %d, P2: %d, P3: %d", estudante.getNotaP1(), estudante.getNotaP2(), estudante.getNotaP3());
    }
}