//package test;

import java.sql.Time;
import java.util.Random;
import java.util.Scanner;

public class Main {
	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		int m=1;
		for(int c=0; c<11; c++,m=m*2){
		int n=m;
		int p=m;
		int[][] A=new int[m][n];
		int[][] B=new int[n][p];
		//System.out.println("Enter matrix A:");
		for(int i=0; i<m; i++){
			for(int j=0; j<n; j++){
				A[i][j]=(int)Math.random()*20;
			}				
		}
		//System.out.println("Enter matrix B:");
		for(int i=0; i<n; i++){
			for(int j=0; j<p; j++){
				B[i][j]=(int)Math.random()*1;
			}				
		}
		Matrix A_matrix = new Matrix(m, n, A);
		Matrix B_matrix = new Matrix(n,p,B);
		System.out.println("Input size:"+m+"*"+m);
		long millis = System.currentTimeMillis();
		Matrix C_matrix=A_matrix.multiply_matrix_iterate(B_matrix);
		//C_matrix.display();
		long millis1 = System.currentTimeMillis();
		System.out.println("Time for iterative method:"+(millis1-millis)+",");
		Matrix C_strassen=A_matrix.multiply_matrix_strassens(B_matrix);
		//C_strassen.display();
		millis = System.currentTimeMillis();
		
		System.out.println("Time for recursive method:"+(millis-millis1)+",");
		System.out.println("Iterative - no. of operations:"+(2*A_matrix.multiplication+A_matrix.addition)+",");
		System.out.println("Recursive -no. of operations:"+(2*A_matrix.mul_strassen+A_matrix.add_strassen));
		
		
	}
	}
}
