package Exercicio_POO;

import Exercicio_POO.repository.Funcionario;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class main {
    static List<Funcionario> funcionarios = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        boolean sair = false;
        Inicializacao();
        while (!sair){
            try{
                System.out.println("Bem Vindo à AJ Soluções");
                System.out.println("01. Cadastrar um novo fúncionário");
                System.out.println("02. Ver detalhes de um fúncionário");
                System.out.println("03. Atualizar as informações de um fúncionário");
                System.out.println("04. Demitir um funcionário");
                System.out.println("05. Listar os funcionários");
                System.out.println("99. Sair");
                int opcao = scanner.nextInt();
                switch (opcao){
                    case 1:
                        Cadastrar();
                        break;
                    case 2:
                        Detalhes();
                        break;
                    case 3:
                        Atualizar();
                        break;
                    case 4:
                        Demitir();
                        break;
                    case 5:
                        Listar();
                        break;
                    case 99:
                        sair = true;
                        break;
                    default:
                        System.out.println("Selecione uma opção válida");
                        break;
                }
            }catch (Exception e){
                System.out.println("Selecione uma opção válida");
            }
        }
    }

    public static void Inicializacao(){
        for(int i = 1; i<= getRandom(1,99); i++){
            funcionarios.add(new Funcionario("Gerente de Projetos " + String.format("%02d", i),
                    Funcionario.TipoFuncionario.GerenteProjetos,
                    new BigDecimal(getRandom(12000,20000)),
                    cpf(),
                    new GregorianCalendar(getRandom(1990,2021),getRandom(1,12),getRandom(1,28))));
        }
        for(int i = 1; i<= getRandom(1,99); i++){
            funcionarios.add(new Funcionario("Coordenador " + String.format("%02d", i),
                    Funcionario.TipoFuncionario.Coordenador,
                    new BigDecimal(getRandom(8000,12000)),
                    cpf(),
                    new GregorianCalendar(getRandom(1990,2021),getRandom(1,12),getRandom(1,28))));
        }
        for(int i = 1; i<= getRandom(1,99); i++){
            funcionarios.add(new Funcionario("Programador " + String.format("%02d", i),
                    Funcionario.TipoFuncionario.Programador,
                    new BigDecimal(getRandom(3000,8000)),
                    cpf(),
                    new GregorianCalendar(getRandom(1990,2021),getRandom(1,12),getRandom(1,28))));
        }
    }

    private static int getRandom(int inicial, int fim) {
        Random random = new Random();
        int numero = random.ints(inicial, fim).findFirst().getAsInt();
        return numero;
    }
    private static String cpf(){
        return cpf(true);
    }
    private static String cpf(boolean comPontos) {
        int n = 9;
        int n1 = randomiza(n);
        int n2 = randomiza(n);
        int n3 = randomiza(n);
        int n4 = randomiza(n);
        int n5 = randomiza(n);
        int n6 = randomiza(n);
        int n7 = randomiza(n);
        int n8 = randomiza(n);
        int n9 = randomiza(n);
        int d1 = n9 * 2 + n8 * 3 + n7 * 4 + n6 * 5 + n5 * 6 + n4 * 7 + n3 * 8 + n2 * 9 + n1 * 10;

        d1 = 11 - (mod(d1, 11));

        if (d1 >= 10)
            d1 = 0;

        int d2 = d1 * 2 + n9 * 3 + n8 * 4 + n7 * 5 + n6 * 6 + n5 * 7 + n4 * 8 + n3 * 9 + n2 * 10 + n1 * 11;

        d2 = 11 - (mod(d2, 11));

        String retorno = null;

        if (d2 >= 10)
            d2 = 0;
        retorno = "";

        if (comPontos)
            retorno = "" + n1 + n2 + n3 + '.' + n4 + n5 + n6 + '.' + n7 + n8 + n9 + '-' + d1 + d2;
        else
            retorno = "" + n1 + n2 + n3 + n4 + n5 + n6 + n7 + n8 + n9 + d1 + d2;

        return retorno;
    }
    private static int randomiza(int n) {
        int ranNum = (int) (Math.random() * n);
        return ranNum;
    }
    private static int mod(int dividendo, int divisor) {
        return (int) Math.round(dividendo - (Math.floor(dividendo / divisor) * divisor));
    }

    private static void Cadastrar() throws ParseException {
        System.out.println("Qual é o nome do novo funcionario?");
        String nome = scanner.next();
        System.out.println("Qual é o CPF do novo funcionário?");
        String cpf = scanner.next();
        System.out.println("Qual é o tipo do novo Funcionario?");
        int count = 0;
        for (Funcionario.TipoFuncionario c :
                Funcionario.TipoFuncionario.values()) {
            System.out.println( ++count + " - " + c.name());
        }
        int op = scanner.nextInt();
        Funcionario.TipoFuncionario tipoFuncionario = null;
        switch (op){
            case 1:
                tipoFuncionario = Funcionario.TipoFuncionario.GerenteProjetos;
                break;
            case 2:
                tipoFuncionario = Funcionario.TipoFuncionario.Coordenador;
                break;
            case 3:
                tipoFuncionario = Funcionario.TipoFuncionario.Programador;
                break;
            default:
                tipoFuncionario = Funcionario.TipoFuncionario.Programador;
                break;
        }
        System.out.println("Qual é o salário inical?");
        BigDecimal salario = scanner.nextBigDecimal();
        System.out.println("Qual foi a data de admissão?");

        String strDate = scanner.next();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse(strDate);
        Calendar data = Calendar.getInstance();
        data.setTime(date);
        
        funcionarios.add(new Funcionario(nome, tipoFuncionario, salario, cpf, data));
    }
    private static void Detalhes(){
        System.out.println("Qual é o CPF do novo funcionário?");
        String cpf = scanner.next();
        Funcionario funcionario = funcionarios.stream().filter(x -> x.getCPF().equals(cpf)).findFirst().get();
        System.out.println(funcionario.toString());
    }
    private static void Atualizar() throws ParseException {
        System.out.println("Qual é o CPF do novo funcionário?");
        String cpf = scanner.next();
        Funcionario funcionario = funcionarios.stream().filter(x -> x.getCPF().equals(cpf)).findFirst().get();

        System.out.println("Qual é o novo nome do funcionario (nome antigo: "+funcionario.getNome()+")?");
        scanner.nextLine();
        String nome = scanner.nextLine();
        funcionario.setNome(nome);

        System.out.println("Qual é o novo CPF do funcionário (cpf antigo:"+funcionario.getCPF()+" )?");
        funcionario.setCPF(scanner.next());
        System.out.println("Qual é o novo tipo do Funcionario (tipo de funcionário antigo: "+funcionario.getTipoFuncionario()+")?");
        int count = 0;
        for (Funcionario.TipoFuncionario c :
                Funcionario.TipoFuncionario.values()) {
            System.out.println( ++count + " - " + c.name());
        }
        int op = scanner.nextInt();
        switch (op){
            case 1:
                funcionario.setTipoFuncionario(Funcionario.TipoFuncionario.GerenteProjetos);
                break;
            case 2:
                funcionario.setTipoFuncionario(Funcionario.TipoFuncionario.Coordenador);
                break;
            case 3:
                funcionario.setTipoFuncionario(Funcionario.TipoFuncionario.Programador);
                break;
            default:
                funcionario.setTipoFuncionario(Funcionario.TipoFuncionario.Programador);
                break;
        }
        System.out.println("Qual é o novo salário inical (salário inicial antiga: "+funcionario.getSalarioInicial()+")?");
        funcionario.setSalarioInicial(scanner.nextBigDecimal());
        System.out.println("Qual é a nova data de admissão (data de admissão antiga: "+funcionario.getDataAdmissaoFormat()+")?");

        String strDate = scanner.next();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse(strDate);
        Calendar data = Calendar.getInstance();
        data.setTime(date);
        funcionario.setDataAdmissao(data);
    }
    private static void Demitir(){
        System.out.println("Qual é o CPF do funcionário que você deseja demitir?");
        String cpf = scanner.next();
        Funcionario funcionario = funcionarios.stream().filter(x -> x.getCPF().equals(cpf)).findFirst().get();
        funcionarios.remove(funcionario);
    }
    private static void Listar()
    {
        for (Funcionario funcionario :
                funcionarios) {
            System.out.println(funcionario.toString());
            System.out.println("===============================");
        }
    }
}
