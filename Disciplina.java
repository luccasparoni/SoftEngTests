import java.util.List;

public class Disciplina {
    private String Codigo;
    private List<Estudante> estudantesMatriculados;

    public Disciplina(String codigo) {
        setDisciplina(codigo);
        this.estudantesMatriculados = new List<Estudante>();
    }

    public setDisciplina(String codigo)
    {
        Pattern Padrao = Pattern.compile("");
        if(!padrao.matcher(input).matches())
            throw new Error("Código de disciplina inválido");
        this.codigo = codigo;
    }

    public AdicionarEstudante(char[7] nusp, float p1, float p2, float p3)
    {
        Estudante estudante = new Estudante(nusp, p1, p2, p3);
        this.estudantesMatriculados.Add(estudante);
    }

    public getMatriculados()
    {
        return estudantesMatriculados.Length();
    }

    public getAprovados()
    {
        return estudantesMatriculados.stream().filter(p -> p.Aprovado() == true);
    }

    public getReprovados()
    {
        return estudantesMatriculados.stream().filter(p -> p.Aprovado() == false);
    }

    public getEstudante(String nusp)
    {
        return estudantesMatriculados.stream().filter(p -> p.getNusp() == nusp);
    }

}