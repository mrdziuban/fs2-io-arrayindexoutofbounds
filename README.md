# fs2-io-arrayindexoutofbonds

This is a repository to reproduce an `ArrayIndexOutOfBondsException` using [fs2](https://github.com/typelevel/fs2).

Clone this repository, `cd` into the directory, and run one of the following commands to reproduce the error on either fs2 v2.x or v3.x:

```bash
sbt fs2-2/run
sbt fs2-3/run
```

The stack traces you should see are:

### fs2 v2.x stack trace

```
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException
  at java.lang.System.arraycopy(Native Method)
  at fs2.io.internal.PipedStreamBuffer$$anon$2.circularWrite(PipedStreamBuffer.scala:374)
  at fs2.io.internal.PipedStreamBuffer$$anon$2.write(PipedStreamBuffer.scala:302)
  at java.io.OutputStream.write(OutputStream.java:75)
  at fs2.io.package$.$anonfun$writeOutputStream$3(io.scala:136)
  at unsafeRunSync @ example.Test$.main(Test.scala:29)
  at delay$extension @ fs2.io.file.FileHandle$.fromPath(FileHandle.scala:107)
  at map @ fs2.internal.CompileScope.interruptibleEval(CompileScope.scala:413)
  at flatMap @ fs2.internal.FreeC$.go$1(Algebra.scala:503)
  at flatMap @ fs2.internal.FreeC$.interruptGuard$1(Algebra.scala:436)
  at flatMap @ fs2.internal.FreeC$.interruptGuard$1(Algebra.scala:436)
  at flatMap @ fs2.internal.FreeC$.interruptGuard$1(Algebra.scala:436)
  at flatMap @ fs2.internal.FreeC$.interruptGuard$1(Algebra.scala:436)
  at flatMap @ fs2.internal.FreeC$.$anonfun$compile$7(Algebra.scala:463)
  at flatMap @ fs2.internal.FreeC$.go$1(Algebra.scala:460)
  at flatMap @ fs2.internal.FreeC$.$anonfun$compile$7(Algebra.scala:463)
  at flatMap @ fs2.internal.FreeC$.go$1(Algebra.scala:460)
  at flatMap @ fs2.internal.FreeC$.outerLoop$1(Algebra.scala:595)
  at flatMap @ fs2.internal.FreeC$.interruptGuard$1(Algebra.scala:436)
  at unsafeRunSync @ example.Test$.main(Test.scala:29
```

### fs2 v3.x stack trace

```
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException
  at java.lang.System.arraycopy(Native Method)
  at fs2.io.internal.PipedStreamBuffer$$anon$2.circularWrite(PipedStreamBuffer.scala:374)
  at fs2.io.internal.PipedStreamBuffer$$anon$2.write(PipedStreamBuffer.scala:302)
  at java.io.OutputStream.write(OutputStream.java:75)
  at fs2.io.package$.$anonfun$writeOutputStream$3(io.scala:111)
  at blocking @ fs2.io.package$.$anonfun$writeOutputStream$2(io.scala:111)
  at flatMap @ fs2.Compiler$TargetLowPriority$MonadErrorTarget.flatMap(Compiler.scala:152
  at handleErrorWith @ fs2.Compiler$TargetLowPriority$MonadErrorTarget.handleErrorWith(Compiler.scala:150)
  at flatMap @ fs2.Compiler$TargetLowPriority$MonadErrorTarget.flatMap(Compiler.scala:152
  at flatMap @ fs2.Pull$.goEval$1(Pull.scala:1035)
  at flatMap @ fs2.Pull$.fs2$Pull$$interruptGuard$1(Pull.scala:904)
  at flatMap @ fs2.Pull$.fs2$Pull$$interruptGuard$1(Pull.scala:904)
  at flatMap @ fs2.Pull$.fs2$Pull$$interruptGuard$1(Pull.scala:904)
  at flatMap @ fs2.Pull$.$anonfun$compile$26(Pull.scala:1197)
  at >>$extension @ fs2.Pull$.goFlatMapOut$1(Pull.scala:990)
  at >>$extension @ fs2.Pull$.goFlatMapOut$1(Pull.scala:990)
  at blocking @ fs2.io.package$.$anonfun$writeOutputStream$2(io.scala:111)
  at flatMap @ fs2.Compiler$TargetLowPriority$MonadErrorTarget.flatMap(Compiler.scala:152
  at handleErrorWith @ fs2.Compiler$TargetLowPriority$MonadErrorTarget.handleErrorWith(Compiler.scala:150)
  at flatMap @ fs2.Compiler$TargetLowPriority$MonadErrorTarget.flatMap(Compiler.scala:152
  at flatMap @ fs2.Pull$.goEval$1(Pull.scala:1035)
```
