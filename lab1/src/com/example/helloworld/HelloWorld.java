package com.example.helloworld;

public class HelloWorld {
    public static void main(String[] args){
        //1
        System.out.println("Hello World!");
        //2
        String[] languages = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        //3
        int n = (int) (Math.random() * 1_000_000);
        System.out.println(n);
        /*
        multiply n by 3;
        add the binary number 10101 to the result;
        add the hexadecimal number FF to the result;
        multiply the result by 6;
        */

        n=n*3;
        System.out.println(n);

        String binaryString="10101";
        int nr=Integer.parseInt(binaryString,2);

        System.out.println(nr);

        n=n+nr;
        System.out.println(n);

        //
        String hexString="FF";
        nr=Integer.parseInt(hexString,16);

        System.out.println(nr);

        n=n+nr;
        System.out.println(n);

        n=n*6;
        System.out.println(n);

        //suma cifre

        int c=n; //copie
        int notFinished=1;
        int sum=0;

        while(notFinished>0)
        {
            sum=0;
            while(c>0)
            {
                sum+=c%10;
                c=c/10;
            }
            if(sum>9)
                c=sum;
            else
                notFinished=0;
        }

        System.out.println("SUM=");
        System.out.println(sum);

        int result = sum;

        //Display on the screen the message: "Willy-nilly, this semester I will learn " + languages[result]
        System.out.println("Willy-nilly, this semester I will learn " + languages[result]);

    }
}
