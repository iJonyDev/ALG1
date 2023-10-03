package EjercicioTarea2;

public class Tarea2 {
    public static void main(String args[]){
        funciones2 pri= new funciones2();
        int n = 1;
        if (pri.primo(n, n-1)){
            System.out.println("Es Primo");
        }else{
            System.out.println("No es primo");
        }
    }
}
