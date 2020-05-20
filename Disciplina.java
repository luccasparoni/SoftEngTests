import java.util.ArrayList;
import java.util.regex.*;  

public class Disciplina {
    private String Codigo;
    private ArrayList<Estudante> estudantesMatriculados;

    public Disciplina(String codigo) {
        setDisciplina(codigo);
        this.estudantesMatriculados = new ArrayList<Estudante>();
    }

    public setDisciplina(String codigo)
    {
        Pattern padrao = Pattern.compile("[A-Z]{3}\\d{4}");
        if(!padrao.matcher(codigo).matches())
            throw new Error("Código de disciplina inválido. Deve conter 3 letras e 4 dígitos (ex: 'SCC0620')");
        this.codigo = codigo;
    }

    public adicionarEstudante(char[7] nusp, float p1, float p2, float p3)
    {
        Estudante estudante = new Estudante(nusp, p1, p2, p3);
        this.estudantesMatriculados.add(estudante);
    }

    public getMatriculados()
    {
        return this.estudantesMatriculados.size();
    }

    public getAprovados()
    {
        return estudantesMatriculados.stream().filter(p -> p.isAprovado() == true);
    }

    public getReprovados()
    {
        return estudantesMatriculados.stream().filter(p -> p.isAprovado() == false);
    }

    public getEstudante(String nusp)
    {
        return estudantesMatriculados.stream().filter(p -> p.getNusp() == nusp);
    }

}