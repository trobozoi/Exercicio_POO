package Exercicio_POO.repository;

import javax.print.attribute.standard.DateTimeAtCreation;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Funcionario implements Pessoa {
    public enum TipoFuncionario{
        GerenteProjetos,
        Coordenador,
        Programador
    }

    private String nome;
    private TipoFuncionario tipoFuncionario;
    private BigDecimal salarioInicial;
    private BigDecimal salarioAtual;
    private String CPF;
    private Calendar dataAdmissao;

    private static final BigDecimal taxaBonus = new BigDecimal(0.10);

    public static BigDecimal getTaxaBonus() {
        return taxaBonus;
    }

    public String getNome() {
        return nome;
    }

    public TipoFuncionario getTipoFuncionario() {
        return tipoFuncionario;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipoFuncionario(TipoFuncionario tipoFuncionario) {
        this.tipoFuncionario = tipoFuncionario;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public Calendar getDataAdmissao() {
        return dataAdmissao;
    }

    public String getDataAdmissaoFormat() {
        Date date = this.dataAdmissao.getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = dateFormat.format(date);
        return strDate;
    }

    public void setDataAdmissao(Calendar dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public BigDecimal getSalarioInicial() {
        return salarioInicial;
    }

    public void setSalarioInicial(BigDecimal salarioInicial) {
        this.salarioInicial = salarioInicial;
    }

    public BigDecimal getSalarioAtual() {
        return salarioAtual;
    }

    public Funcionario(String nome, TipoFuncionario tipoFuncionario, BigDecimal salarioInicial, String CPF, GregorianCalendar dataAdmissao){
        this.nome = nome;
        this.tipoFuncionario = tipoFuncionario;
        this.salarioInicial = salarioInicial;
        this.CPF = CPF;
        this.dataAdmissao = dataAdmissao;
        calcularSalarioAtual();
    }

    public Funcionario(String nome, TipoFuncionario tipoFuncionario, BigDecimal salarioInicial, String CPF, Calendar dataAdmissao){
        this.nome = nome;
        this.tipoFuncionario = tipoFuncionario;
        this.salarioInicial = salarioInicial;
        this.CPF = CPF;
        this.dataAdmissao = dataAdmissao;
        calcularSalarioAtual();
    }

    private void calcularSalarioAtual(){
        Calendar today = new GregorianCalendar();
        today.setTime(new Date());
        int monthsDiff = (today.get(Calendar.YEAR) - dataAdmissao.get(Calendar.YEAR)) * 12 + (today.get(Calendar.MONTH) - dataAdmissao.get(Calendar.MONTH));
        this.salarioAtual = this.salarioInicial;
        if(this.tipoFuncionario == TipoFuncionario.Programador){
            BigDecimal taxaFixa = this.taxaBonus.add(new BigDecimal(1));
            BigDecimal valor = taxaFixa.pow((int)(monthsDiff/3));
            this.salarioAtual = this.salarioAtual.multiply(valor);
        }
    }
    @Override
    public String toString(){


        String s = "Nome: " + this.nome;
        s += "\nCPF: " + this.CPF;
        s += "\nTipo Funcionario: " + this.tipoFuncionario;
        s += "\nSalário Inicial: " + NumberFormat.getCurrencyInstance().format(this.salarioInicial);
        s += "\nSalário Atual: " + NumberFormat.getCurrencyInstance().format(this.salarioAtual);
        s += "\nDataAdmissao: " + getDataAdmissaoFormat();

        return s;
    }


}
