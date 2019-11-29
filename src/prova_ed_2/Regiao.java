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
public class Regiao {
    
    private int codigo;
    private String nome;
    private String descricao;
    private Double valorDoMetroQuadrado;

    public Regiao(int codigo, String nome, String descricao, Double valorDoMetroQuadrado) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.valorDoMetroQuadrado = valorDoMetroQuadrado;
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
        final Regiao other = (Regiao) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.codigo;
        return hash;
    }

    public String getNome() {
        return nome;
    }
    
    public Double getValorDoMetroQuadrado() {
        return valorDoMetroQuadrado;
    }

    public void setValorDoMetroQuadrado(Double valorDoMetroQuadrado) {
        this.valorDoMetroQuadrado = valorDoMetroQuadrado;
    }
}
