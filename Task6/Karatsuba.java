
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Karatsuba {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        System.out.println(karatsuba(x,y));
        Random random = new Random();
        for(int i = 0;i < 10000;i++){
            x = random.nextInt(1000);
            y = random.nextInt(1000);
            if(x*y != karatsuba(x,y)) System.out.println(x+ " " + y);
        }



    }

    public static int karatsuba(int x,int y){
        boolean[] arrX = binaryRepresentation(x);
        boolean[] arrY = binaryRepresentation(y);
        return decimalRepresentation(multiply(arrX,arrY));
    }

    public static boolean[] multiply(boolean[] x, boolean[] y){
        if(x.length==1){
            if(x[0])return y;
            else return new boolean[y.length];
        }
        if(y.length==1){
            if(y[0])return x;
            else return new boolean[x.length];
        }
        int n = Math.max(x.length,y.length);
        boolean[] xL = new boolean[n/2+n%2];
        boolean[] xR = new boolean[n/2];
        boolean[] yL = new boolean[n/2+n%2];
        boolean[] yR = new boolean[n/2];
        for(int i=0;i<n/2;i++){
            if(i<x.length)xR[i]=x[i];
            if(i<y.length)yR[i]=y[i];
        }
        for(int i=n/2;i<(n);i++){
            if(i<x.length)xL[i-n/2]=x[i];
            if(i<y.length)yL[i-n/2]=y[i];
        }
        boolean[] term1 = multiply(xL, yL);
        boolean[] term2 = multiply(xR, yR);
        boolean[] term3 = multiply(sum(xL,xR),sum(yL,yR));
        return sum(shift(term1,2*(n/2)),sum(term2,shift(sub(term3, sum(term1,term2)),(n/2))));
    }

    public static boolean[] binaryRepresentation(int n){
        int length = (int)Math.floor(Math.log(n)/Math.log(2))+1;
        boolean[] digit = new boolean[length > 0 ? length : 1];
        int i = 0;
        while (n > 0){
            if(n % 2 == 1)digit[i]=true;
            i++;
            n/=2;
        }
        return digit;
    }

    public static boolean[] sub(boolean[] first,boolean[] sec){
            int c=0;
            int ind = 0;
            boolean[] res = new boolean[first.length];
            for(int i=0;i<first.length;i++){
                if(first[i])c++;
                if(i<sec.length && sec[i])c--;
                if(c==1){
                    ind = i;
                    res[i]=true;
                    c=0;
                }
                if(c<0){
                    c+=2;
                    if(c==1){
                        res[i]=true;
                        ind = i;
                    }
                    c=-1;
                }
            }
            if(ind != res.length-1)return Arrays.copyOfRange(res,0,ind+1);
            return res;
    }



    public static boolean[] shift(boolean[] arr,int n){
        boolean[] newArr = new boolean[n+arr.length];
        for(int i=n;i<newArr.length;i++)newArr[i]=arr[i-n];
        return newArr;
    }

    public static int decimalRepresentation(boolean[] digit){
        int number = 0;
        int k = 1;
        for(int i = 0;i<digit.length;i++){
            if(digit[i])number += k;
            k *= 2;
        }
        return number;
    }

    public static boolean[] sum(boolean[] x, boolean[] y){
        int min = Math.min(x.length, y.length);
        int max = Math.max(x.length, y.length);
        int ind = 0;
        boolean[] bigger = x.length > y.length ? x : y;
        boolean[] res = new boolean[max+1];
        int count=0;
        for(int i = 0;i < min; i++){
            if(x[i])count++;
            if(y[i])count++;
            if(count % 2 ==1){
                res[i]=true;
                ind = i;
            }
            if(count > 1)count = 1;
            else count = 0;
        }
        for (int i = min;i < max; i++){
            if(bigger[i])count++;
            if(count == 1){
                res[i] = true;
                ind = i;
            }
            if(count == 2)count = 1;
            else count = 0;
        }
        if(count == 1){
            res[max] = true;
            ind = max;
        }
        if(ind != res.length-1)return Arrays.copyOfRange(res,0,ind+1);
        return res;
    }
}
