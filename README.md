# java-vm
Java Virtual Machineの動作を確認するためのプロジェクトです。

# VM引数の確認方法
基本的なVMオプションの出力
```
java -XX:+PrintFlagsFinal -version
```

チューニング用のVMオプションも出力
```
java -XX:+PrintFlagsFinal -XX:+UnlockDiagnosticVMOptions -version
```

実験的なVMオプションも出力
```
java -XX:+PrintFlagsFinal -XX:+UnlockDiagnosticVMOptions -XX:+UnlockExperimentalVMOptions -version
```

# JITコンパイラの確認プロジェクト
`JITCompilerSample`を実行することにより確認することができます。

|JVMオプション|説明|
|-XX:+PrintCompilation|メソッドがコンパイルされたことをログ出力する|
|-XX:CompileThreshold=n|メソッドがネイティブコードへコンパイルされるしきい値|
