import java.util.Arrays;
import java.util.Random;

public class MergeSortRec {
    public static void main(String[] args){
        Random r = new Random();
        int[] arr = new int[10];
        for(int i = 0; i < 10; i++){
            arr[i] = r.nextInt(1000);
            System.out.print(arr[i]+"  ");
        }
        System.out.println();
        mergeSortRec(arr,0,arr.length-1);
        for(int i = 0; i < 10; i++){
            System.out.print(arr[i]+"  ");
        }
    }


    public static void mergeSortRec(int[] arr,int start,int end){
        if(start == end)return;
        mergeSortRec(arr, start,(start+end)/2);
        mergeSortRec(arr, 1+(start+end)/2,end);
        int c1 = start;
        int c2 = 1 + (start+end)/2;
        merge(arr,start,(start+end)/2,1 + (start+end)/2,end);
        for(int i = start; i <= end; i++){
            if(arr[c1]<=arr[c2])c1++;
        }
        return;
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
