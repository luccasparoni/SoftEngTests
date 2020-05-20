public class Estudante {
    private String nusp;
    private float p1, p2, p3;

    public Estudante(String nusp, float p1, float p2, float p3)
    {
        setNusp(nusp);
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    public float getNotaP1()
    {
        return this.p1;
    }

    public float getNotaP2()
    {
        return this.p2;
    }

    public float getNotaP3()
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

    public float calculaMedia() {
        return (this.p1 + this.p2 + this.p3) / 3;
    }

    public boolean isAprovado() {
        return this.calculaMedia() >= 5;
    }

    public String getNusp() {
        return this.nusp;
    }
}