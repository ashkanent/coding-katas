import problems.graph.bfs.ZeroOneMatrix;

import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		ZeroOneMatrix problem = new ZeroOneMatrix();
		int[][] matrix = {{0, 0, 0}, {0, 1, 0}, {1, 1, 1}};

		int[][] answer = problem.updateMatrix(matrix);

		System.out.println(Arrays.deepToString(answer));
	}
}
