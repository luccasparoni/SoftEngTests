public class Estudante {
    private char[7]nusp;
    private float p1, p2, p3;

    public Estudante(char[7] nusp, float p1, float p2, float p3)
    {
        this.nusp = nusp;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    public float CalculaMedia() {
        return (this.p1 + this.p2 + this.p3) / 3;
    }

    public boolean Aprovado() {
        return this.CalculaMedia() >= 5;
    }

    public String getNusp() {
        return this.nusp;
    }
}