import java.util.regex.Pattern;

public class Estudante {
    private String nusp;
    private Double p1, p2, p3;

    public Estudante(String nusp, Double p1, Double p2, Double p3) throws Error {
        setNusp(nusp);
        if (p1 > 10 || p1 < 0 || p2 > 10 || p2 < 0 || p3 > 10 || p3 < 0)
            throw new Error("Nota inválida! As notas devem estar entre 0 e 10.0");
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    public Estudante(String nusp) {
        setNusp(nusp);
        this.p1 = 0.00;
        this.p2 = 0.00;
        this.p3 = 0.00;
    }

    public int addNota(String prova, Double nota) throws Error {
        if (nota > 10 || nota < 0)
            throw new Error("Nota inválida! As notas devem estar entre 0 e 10.0");
        if (prova == "P1")
            this.p1 = nota;
        if (prova == "P2")
            this.p2 = nota;
        if (prova == "P3")
            this.p3 = nota;
        return 0;
    }

    public Double getNotaP1() {
        return this.p1;
    }

    public Double getNotaP2() {
        return this.p2;
    }

    public Double getNotaP3() {
        return this.p3;
    }

    private int setNusp(String nusp) {
        Pattern padrao = Pattern.compile("\\d{7}");
        if (!padrao.matcher(nusp).matches())
            throw new Error("Numero USP inválido. Deve conter 7 dígitos (ex: '0000123')");
        this.nusp = nusp;
        return 0;
    }

    public Double calculaMedia() {
        return (this.p1 + this.p2 + this.p3) / 3;
    }

    public boolean isAprovado() {
        return this.calculaMedia() >= 5;
    }

    public String getNusp() {
        return this.nusp;
    }

    public Integer getNuspAsInteger() {
        return Integer.parseInt(this.nusp);
    }

    @Override
    public String toString() {
        return this.nusp;
    }
}