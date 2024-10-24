package EPD2;

public class Ex2Fibo {

    public static long fibonacciIt(int n){
        long f1 = 0, f2 = 1, aux, naux = n;

        while (naux > 1){
            aux = f2;
            f2 += f1;
            f1 = aux;
            naux--;
        }
        return f2;
    }

    
}
