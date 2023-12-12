package Model.VO;

public class Produto {
    
    private String nome;
    private int codigo;
    private double valor;
    private String descricao;
    
    
    public Produto(String nome, int cod, double valor, String descricao){
        this.nome = nome;
        this.codigo = cod;
        this.valor = valor;
        this.descricao = descricao;
    }
    
    public Produto(){
        
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public int getCodigo() {
        return codigo;
    }
    
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public double getValor() {
        return valor;
    }
    
    public void setValor(double valor) {
        this.valor = valor;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
