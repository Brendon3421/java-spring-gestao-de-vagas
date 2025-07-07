package br.com.Brendon.gestao_vagas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;

public class PrimeiroTeste {

    // teste nao tem retorno
    @Test
    public void deveSerPossivelCalcularDoisNumeros() {
        double num1 = 5.0;
        double num2 = 3.0;
        double resultado = Calculate(num1, num2);
        assertEquals(resultado, 8);

    }

    @Test
    public void ValidarValorIncorreto() {
        double num1 = 5.0;
        double num2 = 3.0;
        double resultado = Calculate(num1, num2);
        assertNotEquals(resultado, 5);
    }

    public double Calculate(double num1, double num2) {
        return num1 + num2;
    }

    public static void main(String[] args) {
        PrimeiroTeste teste = new PrimeiroTeste();
        double resultado = teste.Calculate(5.0, 3.0);
        System.out.println("Resultado: " + resultado); // Deve imprimir: Resultado: 8.0
    }
}
