import java.util.regex.Pattern;

public class Estudante {
    private String nusp;
    private Double p1, p2, p3;

    public Estudante(String nusp, Double p1, Double p2, Double p3) {
        setNusp(nusp);
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

    public int addNota(String prova, Double nota) {
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

    private void setNusp(String nusp) {
        Pattern padrao = Pattern.compile("\\d{7}");
        if (!padrao.matcher(nusp).matches())
            throw new Error("Numero USP inválido. Deve conter 7 dígitos (ex: '0000123')");
        this.nusp = nusp;
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