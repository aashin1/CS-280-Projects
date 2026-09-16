package assignments.algorithms;

public class Fib_Recursion {
    

public static long fib(int n) {
    if (n<=1)
        return 1;
    else
        return fib(n-1) + fib(n-2);
}

public static void main(String[] args) {
    for (int N=1; N<=45; N+=1){
        long start = System.nanoTime();
        long result = fib(N);
        long end = System.nanoTime();
        double duration = (end - start) / 1e9;
        System.out.println(N + "," + duration);
    }
}

}

