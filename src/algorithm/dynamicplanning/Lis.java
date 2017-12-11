package algorithm.dynamicplanning;

import java.util.Arrays;
import java.util.Random;

/**
 * 最长递增子序列
 * （1）随机生成一个有10个元素的数组，然后输出它的最长递增子序列
 * （2）输出以其中某一个元素为结尾的最长递增子序列的长度
 */
public class Lis {

	public static void main(String[] args){
		System.out.println("generating a random array...");
		Lis lis=new Lis();
		int[] oldArray=lis.randomArray();
		for (int i = 0; i < oldArray.length; i++) {
			System.out.print(oldArray[i]+" ");
		}

		System.out.println();
		System.out.println("最长递增子序列的长度为");
		lis.lisGet(oldArray);

	}

	public  int[] randomArray(){
		Random random=new Random();
		int[] randomArray=new int[10];
		for (int i = 0; i < 10; i++) {
			randomArray[i]=random.nextInt(10);
		}
		return randomArray;
	}

	public void lisGet(int[] arrayL ){

		int[] lisLength=new int[arrayL.length];//用于记录当前个元素作为最大元素的最长递增序列的长度

		for (int i = 0; i < arrayL.length; i++) { //初始化
			lisLength[i]=1;
		}

		int max=1;

		for (int i = 1; i < arrayL.length; i++) {
			for (int j = 0; j <i; j++) {

				if (arrayL[j]<arrayL[i]&&(lisLength[j]+1)>lisLength[i]) {
					lisLength[i]=lisLength[j]+1;
				}

				if (max<lisLength[i]) { //得到当前最长递增序列的长度以及该子序列的最末元素的位置
					max=lisLength[i];
				}
			}

		}

		System.out.println(max);

		System.out.println("第i个元素结尾时最长递增子序列："+ Arrays.toString(lisLength)); //输出数组
	}

}