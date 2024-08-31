package br.com.evonutri.EvoNutri.Model;

public class Meals {

    private String horario;
    private int calorias;
    private int proteinas;
    private int gorduras;
    private int minerais;

    public Meals(String horario, int calorias, int proteinas, int gorduras, int minerais) {
        this.horario = horario;
        this.calorias = calorias;
        this.proteinas = proteinas;
        this.gorduras = gorduras;
        this.minerais = minerais;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public int getCalorias() {
        return calorias;
    }

    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }

    public int getProteinas() {
        return proteinas;
    }

    public void setProteinas(int proteinas) {
        this.proteinas = proteinas;
    }

    public int getGorduras() {
        return gorduras;
    }

    public void setGorduras(int gorduras) {
        this.gorduras = gorduras;
    }

    public int getMinerais() {
        return minerais;
    }

    public void setMinerais(int minerais) {
        this.minerais = minerais;
    }

    @Override
    public String toString() {
        return "Meals [horario=" + horario + ", calorias=" + calorias + 
               ", proteinas=" + proteinas + ", gorduras=" + gorduras + ", minerais=" + minerais + "]";
    }
}
