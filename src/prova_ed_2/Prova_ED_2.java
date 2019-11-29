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
public class Prova_ED_2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BancoDeDados banco = new BancoDeDados();
        System.out.println();
        //Cadastrando contribuintes
        System.out.println(banco.cadastrarContribuinte("Matheus", "1"));
        System.out.println(banco.cadastrarContribuinte("Erik", "2"));
        System.out.println(banco.cadastrarContribuinte("Aldo", "3"));

        
        //Cadastrando regiao
        System.out.println(banco.cadastrarRegiao(1, "Aracaju", "tropical", 30.0));
        System.out.println(banco.cadastrarRegiao(2, "Paulo Afonso", "umido", 20.0));
        System.out.println(banco.cadastrarRegiao(3, "Alagoas", "quente", 50.0));
        
        //Cadastrando Imovel
        System.out.println(banco.cadastrarImovel(1, "Gonzaga", "Luzia", 1, 30.0, "1"));
        System.out.println(banco.cadastrarImovel(2, "Prainha", "Luzia", 2, 20.0, "2"));
        System.out.println(banco.cadastrarImovel(3, "Sao jorge", "Luzia", 3, 40.0, "3"));
        
        //Tranferir imovel
        System.out.println(banco.tranferirImovel("1", "2", 1,200.0, 2010));
        System.out.println(banco.tranferirImovel("2", "3", 2,300.0, 2011));
        
        //Pagar iptu
        System.out.println(banco.calcularEPagarIptu(2, 2010));
        System.out.println(banco.calcularEPagarIptu(3, 2010));

        //Atualizar valor da regiao
        System.out.println(banco.atualizarValorDeRegiao(2, 60.0));
        System.out.println(banco.atualizarValorDeRegiao(3, 160.0));
        
        //Listar imoveis de um contribuinte
        System.out.println(banco.listarImoveisDeUmContribuinte("2"));
        
        //Listar imoveis decrescentes iptu
        System.out.println(banco.listarImoveisDoMunicipioDescrescenteIPTU());
        
        //Listar transferencia realizadas em um ano
        System.out.println(banco.listarTranfereciasRealizadaEmUmAno(2011));
        
        //Listar valor de itpi e iptu arrecadado em um ano
        System.out.println(banco.informarValorArrecadadoEmUmAnoItbiIptu(2010));
        
    }
    
}
