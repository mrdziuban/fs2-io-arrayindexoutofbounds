package example

import cats.effect.{Blocker, IO}
import fs2.{Chunk, Stream}
import java.io.OutputStream
import java.util.concurrent.Executors
import scala.concurrent.ExecutionContext

object Test {
  implicit val contextShift = IO.contextShift(ExecutionContext.global)
  val blocker = Blocker.liftExecutionContext(ExecutionContext.fromExecutor(Executors.newCachedThreadPool()))
  val outputWriteEc = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(1))

  val byteStream = Stream
    .chunk[IO, Byte](Chunk.array(("foobar" * 50000).getBytes("UTF-8")))
    .repeatN(7200L) // 6 * 50,000 * 7,200 == 2,160,000,000

  def writeToOutputStream(out: OutputStream): IO[Unit] =
    contextShift.evalOn(outputWriteEc)(byteStream
      .through(fs2.io.writeOutputStream(IO(out), blocker))
      .compile
      .drain)

  def main(args: Array[String]): Unit =
    fs2.io.readOutputStream[IO](blocker, 1024 * 8)(writeToOutputStream)
      .compile
      .drain
      .unsafeRunSync()
}
