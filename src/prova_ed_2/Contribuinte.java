/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prova_ed_2;

import java.util.HashMap;
import java.util.Objects;

/**
 *
 * @author 1181123221
 */
public class Contribuinte {
    
    private String nome;
    private String cpf;
    
    //Lista de imoveis adquiridos pelo proprietario
    private HashMap<Integer,Imovel> listaDeImoveis;
    
    public Contribuinte(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.listaDeImoveis = new HashMap<>();
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
        final Contribuinte other = (Contribuinte) obj;
        if (!Objects.equals(this.cpf, other.cpf)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.cpf);
        return hash;
    }
    
    public void cadastrarNovoImovel(Imovel novoImovel){
        this.listaDeImoveis.put(novoImovel.getNumeroDaInscricaoImobiliaria(), novoImovel);
    }
    
    public boolean transferirImovel(Imovel imovelASerTransferido, Contribuinte contribuinte){
        //Verificando se o contribuinte possui o imovel a ser transferido
        if(this.listaDeImoveis.containsKey(imovelASerTransferido.getNumeroDaInscricaoImobiliaria())){
            this.listaDeImoveis.remove(imovelASerTransferido.getNumeroDaInscricaoImobiliaria());
            contribuinte.cadastrarNovoImovel(imovelASerTransferido);
            imovelASerTransferido.setProprietario(contribuinte);
            return true;
        }
        return false;
    }

    public String getNome() {
        return nome;
    }
    
    public HashMap<Integer, Imovel> getListaDeImoveis() {
        return listaDeImoveis;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
