package Model.VO;

import java.sql.Date;
import java.util.List;

public class Venda {
    
    private int idVendedor;
    private int cod;
    private Date dataVenda;
    private double valorVenda;
    
    public Venda(int idVendedor, int cod, Date dataVenda, double valorVenda, List<Produto> lista) {
        this.idVendedor = idVendedor;
        this.cod = cod;
        this.dataVenda = dataVenda;
        this.valorVenda = valorVenda;
    }
    
    public Venda() {
    
    }
    
    public int getIdVendedor() {
        return idVendedor;
    }
    
    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }
    
    public int getCod() {
        return cod;
    }
    
    public void setCod(int cod) {
        this.cod = cod;
    }
    
    public Date getDataVenda() {
        return dataVenda;
    }
    
    public void setDataVenda(Date date) {
        this.dataVenda = date;
    }
    
    public double getValorVenda() {
        return valorVenda;
    }
    
    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }
    
}
