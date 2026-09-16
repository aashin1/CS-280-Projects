package assignments.algorithms;

public class Binet {
    

public static long fib(int n) {
    double sqrt5 = Math.sqrt(5);
    double phi = (1 + sqrt5) / 2;
    double psi = (1-sqrt5) / 2;
    double result = (Math.pow(phi, n) - Math.pow(psi, n)) / sqrt5;
    return Math.round(result);
}




public static void main(String [] args) {
    for (int N=1; N<=1000; N+= 10) {
        long start = System.nanoTime();
        long result = fib(N);
        long end = System.nanoTime();
        double duration = (end - start) / 1e9;
        System.out.println(N + "," + duration);

    }

    for (int N=1000; N<=10000; N+= 1000) {
        long start = System.nanoTime();
        long result = fib(N);
        long end = System.nanoTime();
        double duration = (end - start) / 1e9;
        System.out.println(N + "," + duration);
        
    }

    for (int N=10000; N<=100000; N+= 1000) {
        long start = System.nanoTime();
        long result = fib(N);
        long end = System.nanoTime();
        double duration = (end - start) / 1e9;
        System.out.println(N + "," + duration);
        
    }

    for (int N=100000; N<=10000000; N+= 100000) {
        long start = System.nanoTime();
        long result = fib(N);
        long end = System.nanoTime();
        double duration = (end - start) / 1e9;
        System.out.println(N + "," + duration);
        
    }

    for (int N=10000000; N<=1000000000; N+= 10000000) {
        long start = System.nanoTime();
        long result = fib(N);
        long end = System.nanoTime();
        double duration = (end - start) / 1e9;
        System.out.println(N + "," + duration);
}

}
}