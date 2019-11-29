/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prova_ed_2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author 1181123221
 */
public class BancoDeDados {

    //HashMap de imoveis pelo seu numero da inscricao imobiliaria
    private HashMap<Integer, Imovel> imoveisDoSistema;

    //HashMap de imoveis separados pelas regioes
    private HashMap<Regiao, LinkedList<Imovel>> imoveisDoSistemaPorRegiao;

    //HashMap de regioes do municipio pelo codigo
    private HashMap<Integer, Regiao> regioesDoSistema;

    //HashMap de contribuintes pelo seu cpf
    private HashMap<String, Contribuinte> contribuintesDoSistema;

    //HashMap de compras realizadas pelo ano
    private HashMap<Integer, LinkedList<Compra>> listaDeCompras;

    //HashMap de pagamentos de iptu pelo ano
    private HashMap<Integer, LinkedList<PagamentosIptu>> listaDePagamentosIptu;

    public BancoDeDados() {
        this.regioesDoSistema = new HashMap<>();
        this.imoveisDoSistema = new HashMap<>();
        this.imoveisDoSistemaPorRegiao = new HashMap<>();
        this.contribuintesDoSistema = new HashMap<>();
        this.listaDeCompras = new HashMap<>();
        this.listaDePagamentosIptu = new HashMap<>();
    }

    public String cadastrarRegiao(int codigo, String nome, String descricao, Double valorDoMetroQuadrado) {
        if (!this.regioesDoSistema.containsKey(codigo)) {
            Regiao novaRegiao = new Regiao(codigo, nome, descricao, valorDoMetroQuadrado);
            this.regioesDoSistema.put(codigo, novaRegiao);
            return "Regiao cadastrada com sucesso";
        }
        return "ja existe uma regiao com o codigo informado cadastrada no sistema";
    }

    public String cadastrarImovel(int numeroDaInscricaoImobiliaria, String nome, String endereco, int codigoDaRegiao, Double area, String cpfDoProprietario) {
        if (!this.imoveisDoSistema.containsKey(numeroDaInscricaoImobiliaria)) {
            Regiao regiao = this.regioesDoSistema.get(codigoDaRegiao);
            Contribuinte contribuinte = this.contribuintesDoSistema.get(cpfDoProprietario);
            if (regiao != null && contribuinte != null) {
                Double iptuDoImovel = this.calcularIptu(area, regiao);
                Imovel novoImovel = new Imovel(numeroDaInscricaoImobiliaria, nome, endereco, regiao, area, contribuinte, iptuDoImovel);
                this.imoveisDoSistema.put(numeroDaInscricaoImobiliaria, novoImovel);
                if (this.imoveisDoSistemaPorRegiao.get(regiao) == null) {
                    LinkedList<Imovel> listaDeImoveisRegiao = new LinkedList();
                    listaDeImoveisRegiao.add(novoImovel);
                    this.imoveisDoSistemaPorRegiao.put(regiao, listaDeImoveisRegiao);
                } else {
                    this.imoveisDoSistemaPorRegiao.get(regiao).add(novoImovel);
                }
                contribuinte.cadastrarNovoImovel(novoImovel);
                return "Imovel cadastrado com sucesso";
            }
            return "Regiao ou Contribuinte inexistente no sistema";
        }
        return "Ja existe um imovel com o numero de inscricao imobiliaria informado";
    }

    public String cadastrarContribuinte(String nome, String cpf) {
        if (!this.contribuintesDoSistema.containsKey(cpf)) {
            Contribuinte novoContribuinte = new Contribuinte(nome, cpf);
            this.contribuintesDoSistema.put(cpf, novoContribuinte);
            return "Contribuinte cadastrado com sucesso";
        }
        return "Ja existe um contribuinte com o cpf informado cadastrado no sistema";
    }

    public String tranferirImovel(String cpfDoVendedor, String cpfDoComprador, int numeroDaInscricaoImobiliaria, Double valorDaCompra, int ano) {
        Contribuinte vendedor = this.contribuintesDoSistema.get(cpfDoVendedor);
        Contribuinte comprador = this.contribuintesDoSistema.get(cpfDoComprador);
        if (vendedor != null && comprador != null) {
            Imovel imovelASerTransferido = this.imoveisDoSistema.get(numeroDaInscricaoImobiliaria);
            if (imovelASerTransferido != null) {
                Double valorDoItbi = this.calcularItbi(valorDaCompra);
                imovelASerTransferido.setItbi(valorDoItbi);
                System.out.println(valorDoItbi);
                if (vendedor.transferirImovel(imovelASerTransferido, comprador)) {
                    Compra novaCompra = new Compra(comprador, vendedor, imovelASerTransferido, valorDaCompra, ano, valorDoItbi);
                    if (this.listaDeCompras.get(ano) == null) {
                        LinkedList<Compra> listaDeComprasDoAno = new LinkedList();
                        listaDeComprasDoAno.add(novaCompra);
                        this.listaDeCompras.put(ano, listaDeComprasDoAno);
                    } else {
                        this.listaDeCompras.get(ano).add(novaCompra);
                    }
                    return "Imovel transferido com sucesso";
                }
                return "O vendedor nao possui o imovel informado";
            }
            return "Nao existe imovel com numero de inscricao informado";
        }
        return "Vendedor ou comprador nao cadastrados no sistema";
    }

    public String calcularEPagarIptu(int numeroDaInscricaoImobiliaria, int anoDePagamento) {
        Imovel imovelASerPgo = this.imoveisDoSistema.get(numeroDaInscricaoImobiliaria);
        if (imovelASerPgo != null) {
            PagamentosIptu novoPagamento = new PagamentosIptu(anoDePagamento, imovelASerPgo, imovelASerPgo.getIptu());
            if (this.listaDePagamentosIptu.get(anoDePagamento) == null) {
                LinkedList<PagamentosIptu> listaDePagamentosDoAno = new LinkedList();
                listaDePagamentosDoAno.add(novoPagamento);
                this.listaDePagamentosIptu.put(anoDePagamento, listaDePagamentosDoAno);
            } else {
                this.listaDePagamentosIptu.get(anoDePagamento).add(novoPagamento);
            }
            return "Pagamento registrado com sucesso";
        }
        return "Nao existe imovel com o numero de inscricao informado";
    }

    public String atualizarValorDeRegiao(int codigoDaRegiao, double novoValorDoMetroQuadrado) {
        Regiao regiaoAtual = this.regioesDoSistema.get(codigoDaRegiao);
        if (regiaoAtual != null) {
            regiaoAtual.setValorDoMetroQuadrado(novoValorDoMetroQuadrado);
            LinkedList<Imovel> imoveisPorRegiao = this.imoveisDoSistemaPorRegiao.get(regiaoAtual);
            Double novoValorDoIptu;
            for (Imovel imovel : imoveisPorRegiao) {
                novoValorDoIptu = this.calcularIptu(imovel.getArea(), regiaoAtual);
                imovel.setIptu(novoValorDoIptu);
            }
            return "Valores das regioes atualizados com sucesso";
        }
        return "Nao ha regiao com codigo informado no sistema";
    }

    public String listarImoveisDeUmContribuinte(String cpf) {
        StringBuilder construtorDeString = new StringBuilder();
        Contribuinte contribuinte = this.contribuintesDoSistema.get(cpf);
        if (contribuinte != null) {
            HashMap<Integer, Imovel> listaDeImoveisDoContribuinte = contribuinte.getListaDeImoveis();
            if (listaDeImoveisDoContribuinte.values().isEmpty()) {
                return "Este Contribuinte nao tem imovei em seu nome";
            }
            for (Imovel imovel : listaDeImoveisDoContribuinte.values()) {
                construtorDeString.append("Nome do imovel: ").append(imovel.getNome()).append("\n");
                construtorDeString.append("Nome do proprietario: ").append(imovel.getProprietario().getNome()).append("\n");
                construtorDeString.append("Nome da regiao: ").append(imovel.getRegiao().getNome()).append("\n");
                construtorDeString.append("-----------------------------------------------------------------------").append("\n");
            }
            return construtorDeString.toString();
        }
        return "Nao ha contribuinte com codigo informado";
    }

    public ArrayList<Imovel> listarImoveisDoMunicipioDescrescenteIPTU() {
        ArrayList<Imovel> listaDeImoveisDoMunicipio = new ArrayList<>();
        //Transferindo os itens para um array list e ordenando em ordem decrescente
        for (Imovel Imovel : this.imoveisDoSistema.values()) {
            listaDeImoveisDoMunicipio.add(Imovel);
        }
        Collections.sort(listaDeImoveisDoMunicipio);
        Collections.reverse(listaDeImoveisDoMunicipio); 
        return listaDeImoveisDoMunicipio;
    }

    public String listarTranfereciasRealizadaEmUmAno(int ano) {
        StringBuilder construtorDeString = new StringBuilder();
        LinkedList<Compra> listaDeCompraPeloAno = this.listaDeCompras.get(ano);
        if (listaDeCompraPeloAno != null) {
            for (Compra compra : listaDeCompraPeloAno) {
                construtorDeString.append("Nome do comprador: ").append(compra.getComprador().getNome()).append("\n");
                construtorDeString.append("Nome do vendedor: ").append(compra.getVendedor().getNome()).append("\n");
                construtorDeString.append("Valor da compra: ").append(compra.getValorDaCompra()).append("\n");
                construtorDeString.append("-----------------------------------------------------------------------").append("\n");
            }
            return construtorDeString.toString();
        }
        return "Nao foram realizadas trasnferencias no ano imformado";
    }

    public String informarValorArrecadadoEmUmAnoItbiIptu(int ano) {
        StringBuilder contrutor = new StringBuilder();
        LinkedList<PagamentosIptu> listaDeIptu = this.listaDePagamentosIptu.get(ano);
        LinkedList<Compra> listaDeItbi = this.listaDeCompras.get(ano);
        if (listaDeIptu != null && listaDeItbi != null) {
            Double cobrancaComIptu = 0.0;
            Double cobrancaComItbi = 0.0;
            for (PagamentosIptu pagamento : listaDeIptu) {
                System.out.println(pagamento.getValorDoIptu());
                cobrancaComIptu += pagamento.getValorDoIptu();
            }
            for (Compra compra : listaDeItbi) {
                cobrancaComItbi += compra.getItbi();
            }
            contrutor.append("O valor total de itbi foi: ").append(cobrancaComItbi).append("\n");
            contrutor.append("O valor total de iptu foi: ").append(cobrancaComIptu).append("\n");
            
            return contrutor.toString();
        }
        return "Ano informado invalido";
    }

    private Double calcularIptu(Double areaDoimovel, Regiao regiao) {
        Double valorDoImovelPeloMetroQuadrado = areaDoimovel * regiao.getValorDoMetroQuadrado();
        Double iptuDoImovel = (0.008d * valorDoImovelPeloMetroQuadrado);
        return iptuDoImovel;
    }

    private Double calcularItbi(Double valorDaVenda) {
        Double valorDoItbi = (0.02d * valorDaVenda);
        return valorDoItbi;
    }
}
