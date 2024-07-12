import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * GCによるパフォーマンスの計測を行うためのクラス
 */
public class GCSample {

	private static class SampleObject {
		@SuppressWarnings("unused")
		private String strValue;
		@SuppressWarnings("unused")
		private Integer intValue;
		@SuppressWarnings("unused")
		private long longValue;
		@SuppressWarnings("removal")
		public SampleObject() {
			strValue = JITCompilerSample.execute(1000);
			intValue = new Integer(100);
			longValue = new Long(100L);
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		// 大量にオブジェクトを生成する
		// たまに永続化する
		// 複数のスレッドで実行
		// 実行時間・スループット・レイテンシ
		List<SampleObject> cachedList = Collections.synchronizedList(new ArrayList<>());
		
		// 処理スレッドの生成
		Thread th1 = new Thread(() -> execute(cachedList));
		th1.start();
		Thread th2 = new Thread(() -> execute(cachedList));
		th2.start();
		Thread th3 = new Thread(() -> execute(cachedList));
		th3.start();
		
		// 計測開始
		long startNanosec = System.nanoTime();
		
		// 終了待機
		th1.join();
		th2.join();
		th3.join();
		
		// 計測終了
		long elapsedNano = System.nanoTime() - startNanosec;
		long elapsedMicro = elapsedNano / 1_000_000;
		System.out.println("経過時間：" + String.format("%,d", elapsedMicro) + " ms");
		long totalMem = Runtime.getRuntime().totalMemory();
		System.out.println("最終メモリ：" + String.format("%,d", totalMem) + " Bytes");
	}
	
	/** 処理を行います */
	private static void execute(List<SampleObject> cachedList) {
		for (int i = 0; i < 100_000; i++) {
			// オブジェクトの生成
			var obj = new SampleObject();
			
			// たまにキャッシュ化
			if (i % JITCompilerSample.SPLIT_TERM == 0) {
				cachedList.add(obj);
			}
		}
	}
}
