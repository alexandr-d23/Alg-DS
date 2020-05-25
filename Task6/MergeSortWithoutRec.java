import java.util.Arrays;
import java.util.Random;

public class MergeSortWithoutRec {

    public static void main(String[] args){
        Random r = new Random();
        int[] arr = new int[10];
        for(int i = 0; i < 10; i++){
            arr[i] = r.nextInt(1000);
            System.out.print(arr[i]+"  ");
        }
        System.out.println();
        mergeSortWithoutRec(arr);
        for(int i = 0; i < 10; i++){
            System.out.print(arr[i]+"  ");
        }
    }

    public static void mergeSortWithoutRec(int[] arr){
        for(int i = 1; i<=arr.length;i*=2){
            for(int j = 0;j<arr.length;j+= 2*i){
                if(j+2*i-1<arr.length){
                    merge(arr,j,j+i-1,j+i,j+2*i-1);
                }
                else if(j+2*i-1>=arr.length && j+i<arr.length){
                    merge(arr,j,j+i-1,j+i,arr.length-1);
                }
            }
        }

    }

    public static void merge(int[] arr,int start1,int end1,int start2,int end2){
        int[] arr1 = Arrays.copyOfRange(arr,start1,end1+1);
        int c1 = 0;
        int c2 = start2;
        for(int i = start1; i <= end2; i++){
            if(c2 > end2){
                arr[i] = arr1[c1];
                c1++;
            }
            else if(c1<arr1.length && arr1[c1] <= arr[c2]){
                arr[i] = arr1[c1];
                c1++;
            }
            else{
                arr[i] = arr[c2];
                c2++;
            }

        }
    }
}
