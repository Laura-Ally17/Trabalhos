import javax.swing.JOptionPane;

public class Adicao{
    public static void main(String[] args){
        String firstNumber=
            JOptionPane.showInputDialog("Insira o primeiro número: ");
        String secondNumber=
            JOptionPane.showInputDialog("Insira o segundo número: ");

        int num1= Integer.parseInt (firstNumber);
        int num2= Integer.parseInt (secondNumber);

        int sum= num1+num2; 

        JOptionPane.showInputDialog("A soma de "+ num1+ " e "+ num2+ " é " + sum);
    }
}