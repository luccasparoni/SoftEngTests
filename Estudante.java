public class Estudante {
    private String nusp;
    private Float p1, p2, p3;

    public Estudante(String nusp, Float p1, Float p2, Float p3)
    {
        setNusp(nusp);
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    public Float getNotaP1()
    {
        return this.p1;
    }

    public Float getNotaP2()
    {
        return this.p2;
    }

    public Float getNotaP3()
    {
        return this.p3;
    }
    
    private void setNusp(String nusp)
    {
        Pattern padrao = Pattern.compile("\\d{7}");
        if(!padrao.matcher(nusp).matches())
            throw new Error("Numero USP inválido. Deve conter 7 dígitos (ex: '0000123')");
        this.nusp = nusp;
    }

    public Float calculaMedia() {
        return (this.p1 + this.p2 + this.p3) / 3;
    }

    public boolean isAprovado() {
        return this.calculaMedia() >= 5;
    }

    public String getNusp() {
        return this.nusp;
    }

    public Integer getNuspAsInteger() {
        return new Integer(this.nusp);
    }
}