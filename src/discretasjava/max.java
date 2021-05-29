/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package discretasjava;

/**
 *
 * @author Juan
 */
public class max {
    public static void main(String[] args) {
        int[] num = {3,2,6,5};
        int max = num[0];
        for (int i = 0; i < num.length; i++) {
            if(num[i]>max){
                max = num[i];
            }
        }
        System.out.println(max);
                
    }
}
