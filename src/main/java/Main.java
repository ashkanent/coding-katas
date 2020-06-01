import problems.string.MostCommonWord;

public class Main {
	public static void main(String[] args) {
		MostCommonWord problem = new MostCommonWord();
		String paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.";
		String[] bannedWords = {"hit"};
		String answer = problem.solve(paragraph, bannedWords);

		System.out.println(answer);
	}
}
