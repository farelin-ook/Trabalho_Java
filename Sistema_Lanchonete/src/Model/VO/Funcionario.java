package Model.VO;


public class Funcionario {
    
    private int idFunc;
    private String nome;
    private String senha;
    private String cargo;

    public Funcionario(int idFunc, String nome, String senha, String cargo){
        this.idFunc = idFunc;
        this.nome = nome;
        this.senha = senha;
        this.cargo = cargo;
    }
    
    public Funcionario(){
    }
    
    public int getIdFunc() {
        return idFunc;
    }
    
    public void setIdFunc(int idFunc) {
        this.idFunc = idFunc;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getSenha() {
        return senha;
    }
    
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public String getCargo() {
        return cargo;
    }
    
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
}