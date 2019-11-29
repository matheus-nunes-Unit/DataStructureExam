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
public class Compra {
    
    private int ano;
    private Contribuinte comprador;
    private Contribuinte vendedor;
    private Imovel imovelDaCompra;
    private Double valorDaCompra;
    private Double itbi;

    public Compra(Contribuinte comprador, Contribuinte vendedor, Imovel imovelDaCompra, Double valorDaCompra, int ano,Double valorDoItbi) {
        this.comprador = comprador;
        this.vendedor = vendedor;
        this.imovelDaCompra = imovelDaCompra;
        this.valorDaCompra = valorDaCompra;
        this.ano = ano;
        this.itbi = valorDoItbi;
    }

    public Contribuinte getComprador() {
        return comprador;
    }

    public Contribuinte getVendedor() {
        return vendedor;
    }

    public Imovel getImovelDaCompra() {
        return imovelDaCompra;
    }

    public Double getValorDaCompra() {
        return valorDaCompra;
    }

    public Double getItbi() {
        return itbi;
    }
    
    
}
