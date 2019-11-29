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
public class Imovel implements Comparable<Imovel>{
    
    private int numeroDaInscricaoImobiliaria;
    private String nome;
    private String endereco;
    private Regiao regiao;
    private Double area;
    private Contribuinte proprietario;
    private Double iptu;
    private Double itbi;

    public Imovel(int numeroDaInscricaoImobiliaria, String nome, String endereco, Regiao regiao, Double area, Contribuinte proprietario,Double iptu) {
        this.numeroDaInscricaoImobiliaria = numeroDaInscricaoImobiliaria;
        this.nome = nome;
        this.endereco = endereco;
        this.regiao = regiao;
        this.area = area;
        this.proprietario = proprietario;
        this.iptu = iptu;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Imovel other = (Imovel) obj;
        if (this.numeroDaInscricaoImobiliaria != other.numeroDaInscricaoImobiliaria) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.numeroDaInscricaoImobiliaria;
        return hash;
    }    

    public String getNome() {
        return nome;
    }

    public void setProprietario(Contribuinte proprietario) {
        this.proprietario = proprietario;
    }

    public int getNumeroDaInscricaoImobiliaria() {
        return numeroDaInscricaoImobiliaria;
    }

    public Double getArea() {
        return area;
    }

    public Double getIptu() {
        return iptu;
    }

    public Regiao getRegiao() {
        return regiao;
    }

    public Contribuinte getProprietario() {
        return proprietario;
    }
    
    public void setIptu(Double iptu) {
        this.iptu = iptu;
    }

    public void setItbi(Double itbi) {
        this.itbi = itbi;
    }

    @Override
    public int compareTo(Imovel o) {
        return this.iptu.compareTo(o.getIptu());
    }

    @Override
    public String toString() {
        return this.nome;
    }
    
}
