/**
 * JITコンパイラの挙動を確認するためのクラスです。
 */
public class JITCompilerSample {

	// 定数
	public static final int LOOP_COUNT = 100_000;
	public static final int SPLIT_TERM = 500;
	
	public static void main(String[] args) {
		
		// ストップウォッチの開始
		long startNanosec = System.nanoTime();
		
		for (int i = 0; i < LOOP_COUNT; i++) {
			// 実行
			execute(i);
			
			// 一定の期間内のタイムを計測する
			if (i % SPLIT_TERM == 0) {
				long elapsedNano = System.nanoTime() - startNanosec;
				System.out.println(
						String.format("%06d", i) + "：" + String.format("% 10d", elapsedNano / 1000) + " MicroSec");
				startNanosec = System.nanoTime();
			}
		}
	}

	/** 実行 */
	public static String execute(int i) {
		// とある文字が大量に続く文字列を作成する
		 int ch = 'a' + (char)(i % 25);
		StringBuilder builder = new StringBuilder();
		for (int j = 0; j < 10000; j++) {
			builder.append((char)ch);
		}
		
		return builder.toString();
	}
}
