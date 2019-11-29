/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prova_ed_2;

/**
 *
 * @author 1181123221
 */
public class PagamentosIptu {
    
    private int anoDoPagamento;
    private Imovel imovelDoPagamento;
    private Double valorDoIptu;
    
    public PagamentosIptu(int anoDoPagamento, Imovel imovelDoPagamento,Double valorDoIptu) {
        this.anoDoPagamento = anoDoPagamento;
        this.imovelDoPagamento = imovelDoPagamento;
        this.valorDoIptu = valorDoIptu;
    }

    public Double getValorDoIptu() {
        return valorDoIptu;
    }
    
    
}
