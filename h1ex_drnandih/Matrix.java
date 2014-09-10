//package test;

public class Matrix {
	int m;
	int n;
	int[][] A;
	long addition;
	long multiplication;
	long add_strassen;
	long mul_strassen;
	Matrix(int m,int n, int[][] A){
		this.m=m;
		this.n=n;
		this.A=A;
		this.add_strassen=0;
		this.mul_strassen=0;
	}
	public void display(){
		for(int i=0; i<m; i++){
			for(int j=0; j<n; j++){
				System.out.print(A[i][j]+" ");
			}
			System.out.println("");
		}
	}
	public Matrix multiply_matrix_iterate(Matrix B){
		addition=0;
		this.multiplication=0;
		int p=B.n;
		int[][] D=B.A;
		int[][] C=new int[m][p];
		for(int i=0; i<m; i++){
			for(int j=0; j<p; j++){
				for(int k=0; k<n; k++){
					C[i][j]+=A[i][k]*D[k][j];
					addition++;
					//System.out.println("I"+i+"j:"+j);
					multiplication++;
				}
			}
		}
		//System.out.println("Total additions:"+addition+" Total multiplication:"+multiplication+" for matrix size "+this.m+"*"+this.n+" and "+B.n+"*"+B.n);
		return new Matrix(m, p, C);
	}
	public int[][] copy(int[][] A,int m,int n, int m1,int n1){
		if((m1-m)<=0&&(n1-n)<=0){
			return new int[0][0];
		}
		
		int [][] C= new int[m1-m+1][n1-n+1];
		for(int i=m,k=0; i<m1; i++,k++){
			for(int j=n,l=0; j<n1; l++,j++){
				C[k][l]=A[i][j];
			}
		}
		return C;
	}
	public int divide_by_2(int a){
		if(a%2==1){
			return a/2;			
		}
		else{
			return a/2-1;
		}
	}
	public Matrix add_matrices(Matrix M,Matrix N){
		int[][] C=new int[M.m][M.n];
		for(int i=0; i<M.m;i++){
			for(int j=0; j<M.n; j++){
				C[i][j]=M.A[i][j]+N.A[i][j];
				add_strassen++;
			}
		}
		return new Matrix(M.m,M.n,C);
	}
	public Matrix subtract_matrices(Matrix M,Matrix N){
		int[][] C=new int[M.m][M.n];
		for(int i=0; i<M.m;i++){
			for(int j=0; j<M.n; j++){
				C[i][j]=M.A[i][j]-N.A[i][j];
				add_strassen++;
			}
		}
		return new Matrix(M.m,M.n,C);
	}
	public Matrix merge_matrices(Matrix A11,Matrix A12,Matrix A21,Matrix A22){
		int[][] B = new int[A11.m+A21.m][A11.n+A12.n];
		int m = A11.m+A12.m;
		int n = A11.n+A12.n;
		int i=0,j=0;
		for(i=0; i<A11.m; i++){
			for(j=0; j<A11.n; j++){
				B[i][j]=A11.A[i][j];
			}
		}
		i=0;
		//j=A11.n;
		for(int k=0; k<A12.m; k++,i++){
			j=A11.n;
			for(int l=0; l<A12.n; l++,j++){
				B[i][j]=A12.A[k][l];
			}
		}
		
		i=A12.m;
		for(int k=0; k<A21.m; k++,i++){
			j=0;
			for(int l=0; l<A21.n; l++,j++){
				B[i][j]=A21.A[k][l];
			}
		}
		i=A12.m;
		
		for(int k=0; k<A22.m; k++,i++){
			j=A21.n;
			for(int l=0; l<A22.n; l++,j++){
				B[i][j]=A22.A[k][l];
			}
		}
		return new Matrix(m, n, B);
		
	}
	public Matrix multiply_strassens(Matrix M,Matrix N){
		if(M.m==1||M.n==1||N.m==1||N.n==1)
		{
			int[][] C=new int[M.m][N.n];
			for(int i=0; i<M.m; i++){
				for(int j=0; j<N.n; j++){
					for(int k=0; k<N.m; k++){
						C[i][j]+=M.A[i][k]*N.A[k][j];
						add_strassen++;
						//System.out.println("I"+i+"j:"+j);
						mul_strassen++;
					}
				}
			}
			return new Matrix(1,1,C);
		}
		Matrix A11 = new Matrix(divide_by_2(M.m)+1,divide_by_2(M.n)+1, copy(M.A,0,0,divide_by_2(M.m)+1,divide_by_2(M.n)+1));
		Matrix A12 = new Matrix(divide_by_2(M.m)+1,M.n-divide_by_2(M.n)-1,copy(M.A,0,divide_by_2(M.n)+1,divide_by_2(M.m)+1,M.n));
		Matrix A21 = new Matrix(M.m-divide_by_2(M.m)-1,divide_by_2(M.n)+1,copy(M.A,divide_by_2(M.m)+1,0,M.m,divide_by_2(M.n)+1));
		Matrix A22 = new Matrix(M.m-divide_by_2(M.m)-1,M.n-divide_by_2(M.n)-1, copy(M.A,divide_by_2(M.m)+1,divide_by_2(M.n)+1,M.m,M.n));
		Matrix B11 = new Matrix(divide_by_2(N.m)+1,divide_by_2(N.n)+1, copy(N.A,0,0,divide_by_2(N.m)+1,divide_by_2(N.n)+1));
		Matrix B12 = new Matrix(divide_by_2(N.m)+1,N.n-divide_by_2(N.n)-1,copy(N.A,0,divide_by_2(N.n)+1,divide_by_2(N.m)+1,N.n));
		Matrix B21 = new Matrix(N.m-divide_by_2(N.m)-1,divide_by_2(N.n)+1,copy(N.A,divide_by_2(N.m)+1,0,N.m,divide_by_2(N.n)+1));
		Matrix B22 = new Matrix(N.m-divide_by_2(N.m)-1,N.n-divide_by_2(N.n)-1, copy(N.A,divide_by_2(N.m)+1,divide_by_2(N.n)+1,N.m,N.n));
		Matrix P1=multiply_strassens(A11,subtract_matrices(B12, B22));
		Matrix P2=multiply_strassens(add_matrices(A11, A12), B22);
		Matrix P3=multiply_strassens(add_matrices(A21, A22), B11);
		Matrix P4=multiply_strassens(A22, subtract_matrices(B21, B11));
		Matrix P5=multiply_strassens(add_matrices(A11, A22), add_matrices(B11, B22));
		Matrix P6=multiply_strassens(subtract_matrices(A12,A22), add_matrices(B21, B22));
		Matrix P7=multiply_strassens(subtract_matrices(A11, A21),add_matrices(B11, B12));
		Matrix C11= subtract_matrices(add_matrices(add_matrices(P5, P4),P6),P2);
		//System.out.println("C11");
		//C11.display();
		Matrix C12= add_matrices(P1, P2);
		//System.out.println("C12");
		//C12.display();
		Matrix C21 = add_matrices(P3, P4);
		//System.out.println("C21");
		//C21.display();
		Matrix C22 = subtract_matrices(add_matrices(P1, P5), add_matrices(P3,P7));
		//System.out.println("C22");
		//C22.display();
		return merge_matrices(C11,C12,C21,C22);	
		
	}
	public Matrix multiply_matrix_strassens(Matrix B){
		return multiply_strassens(this,B);
	}
}
