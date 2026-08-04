import java.util.Scanner;

class Lab1 {
    Scanner sc = new Scanner(System.in);

    void leapYear() {
        int year = sc.nextInt();
        if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0))
            System.out.println("Leap Year");
        else
            System.out.println("Not Leap Year");
    }

    void alphabet() {
        char ch = sc.next().charAt(0);
        if ((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z'))
            System.out.println("Alphabet");
        else
            System.out.println("Not Alphabet");
    }

    void factorial() {
        int n = sc.nextInt(), fact = 1;
        for (int i = 1; i <= n; i++)
            fact *= i;
        System.out.println(fact);
    }

    void fibonacci() {
        int n = sc.nextInt();
        int a = 0, b = 1;
        for (int i = 1; i <= n; i++) {
            System.out.print(a + " ");
            int c = a + b;
            a = b;
            b = c;
        }
    }

    void gcd() {
        int a = sc.nextInt(), b = sc.nextInt();
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        System.out.println(a);
    }

    void lcm() {
        int a = sc.nextInt(), b = sc.nextInt();
        int x = a, y = b;
        while (y != 0) {
            int t = y;
            y = x % y;
            x = t;
        }
        System.out.println((a * b) / x);
    }

    void countDigits() {
        int n = sc.nextInt(), count = 0;
        while (n != 0) {
            count++;
            n /= 10;
        }
        System.out.println(count);
    }

    void reverse() {
        int n = sc.nextInt(), rev = 0;
        while (n != 0) {
            rev = rev * 10 + n % 10;
            n /= 10;
        }
        System.out.println(rev);
    }

    void power() {
        int base = sc.nextInt(), exp = sc.nextInt(), ans = 1;
        for (int i = 1; i <= exp; i++)
            ans *= base;
        System.out.println(ans);
    }

    void palindrome() {
        int n = sc.nextInt(), temp = n, rev = 0;
        while (temp != 0) {
            rev = rev * 10 + temp % 10;
            temp /= 10;
        }
        if (n == rev)
            System.out.println("Palindrome");
        else
            System.out.println("Not Palindrome");
    }

    public static void main(String[] args) {
        Lab1 obj = new Lab1();

        // Call any method as needed
        obj.leapYear();
        // obj.alphabet();
        // obj.factorial();
        // obj.fibonacci();
        // obj.gcd();
        // obj.lcm();
        // obj.countDigits();
        // obj.reverse();
        // obj.power();
        // obj.palindrome();
    }
}