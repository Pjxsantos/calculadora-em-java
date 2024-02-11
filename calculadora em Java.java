import java.util.ArrayList;
import java.util.List;

public class Calculadora {
    public static void main(String[] args) {
        Calculadora c = new Calculadora();
        System.out.println("Resultado: " + c.calculadora("5/5"));
    }

    public String calculadora(String expressao) {
        String resultado;
        List<Double> listaNumeros = new ArrayList<>();
        List<Character> listaOperadores = new ArrayList<>();

        listaNumeros = obterNumeros(expressao);
        listaOperadores = obterOperadores(expressao);

        resultado = calcularValor(listaNumeros, listaOperadores);

        return resultado;
    }

    private String calcularValor(List<Double> listaNumeros, List<Character> listaOperadores) {
        double total = 0.0;
        int j = 0;

        for (int i = 0; i < listaNumeros.size() - 1; i++) {
            if (total == 0.0) {
                double numero1 = listaNumeros.get(i);
                double numero2 = listaNumeros.get(i + 1);
                char operador = listaOperadores.get(i);
                total = executarOperacao(numero1, operador, numero2);
            } else if (total > 0.0) {
                double numero2 = listaNumeros.get(i);
                char operador = listaOperadores.get(j);
                total = executarOperacao(total, operador, numero2);
                j++;
            }
        }

        return String.valueOf(total);
    }

    private double executarOperacao(double numero1, char operador, double numero2) {
        double resultado = 0.0;

        switch (operador) {
            case '+':
                resultado = numero1 + numero2;
                break;
            case '-':
                resultado = numero1 - numero2;
                break;
            case '/':
                resultado = numero1 / numero2;
                break;
            case '*':
                resultado = numero1 * numero2;
                break;
        }

        return resultado;
    }

    private List<Double> obterNumeros(String expressao) {
        List<Double> listaNumeros = new ArrayList<>();
        String numeroEmString = "";

        for (int i = 0; i < expressao.length(); i++) {
            if (isOperador(expressao.charAt(i))) {
                Double numero = Double.valueOf(numeroEmString);
                listaNumeros.add(numero);
                numeroEmString = "";
            } else {
                numeroEmString += expressao.charAt(i);
            }
        }

        if (!numeroEmString.isEmpty()) {
            Double numero = Double.valueOf(numeroEmString);
            listaNumeros.add(numero);
        }

        return listaNumeros;
    }

    private List<Character> obterOperadores(String expressao) {
        List<Character> listaOperadores = new ArrayList<>();

        for (int i = 0; i < expressao.length(); i++) {
            if (isOperador(expressao.charAt(i))) {
                listaOperadores.add(expressao.charAt(i));
            }
        }

        return listaOperadores;
    }

    private boolean isOperador(char caracter) {
        return caracter == '+' || caracter == '-' || caracter == '*' || caracter == '/';
    }
}
