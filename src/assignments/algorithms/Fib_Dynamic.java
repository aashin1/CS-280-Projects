package assignments.algorithms;

public class Fib_Dynamic {
    
public static int fibonacci (int n) {
    if (n<=1)
        return 1;
    int last = 1;
    int nextToLast = 1;
    int answer = 1;

    for (int i = 2; i <=n; i++) {
        answer = last + nextToLast;
        nextToLast = last;
        last = answer;

    }
    return answer;
}

public static void main(String[] args) {
    for (int N =1; N<= 1000; N+=10) {
    long start = System.nanoTime();
    int result = fibonacci(N);
    long end = System.nanoTime();
    double duration = (end - start) / 1e9;
    System.out.println(N + "," + duration);
    }
    for (int N = 1000; N <= 100000; N += 1000) {
        long start = System.nanoTime();
        int result = fibonacci(N);
        long end = System.nanoTime();
        double duration = (end - start) / 1e9;
        System.out.println(N + "," + duration);
}

    for (int N = 100000; N <= 10000000; N += 100000) {
        long start = System.nanoTime();
        int result = fibonacci(N);
        long end = System.nanoTime();
        double duration = (end - start) / 1e9;
        System.out.println(N + "," + duration);

}


    for (int N = 10000000; N <= 1000000000; N += 10000000) {
        long start = System.nanoTime();
        int result = fibonacci(N);
        long end = System.nanoTime();
        double duration = (end - start) / 1e9;
        System.out.println(N + "," + duration);



}
}
}