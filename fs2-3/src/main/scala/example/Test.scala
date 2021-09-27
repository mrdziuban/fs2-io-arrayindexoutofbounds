package example

import cats.effect.IO
import cats.effect.unsafe.implicits.global
import fs2.{Chunk, Stream}
import java.io.OutputStream
import java.util.concurrent.Executors
import scala.concurrent.ExecutionContext

object Test {
  val outputWriteEc = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(1))

  val byteStream = Stream
    .chunk[IO, Byte](Chunk.array(("foobar" * 50000).getBytes("UTF-8")))
    .repeatN(7200L) // 6 * 5,000 * 7,200 == 2,160,000,000

  def writeToOutputStream(out: OutputStream): IO[Unit] =
    byteStream
      .through(fs2.io.writeOutputStream(IO(out)))
      .compile
      .drain
      .evalOn(outputWriteEc)

  def main(args: Array[String]): Unit =
    fs2.io.readOutputStream[IO](1024 * 8)(writeToOutputStream)
      .compile
      .drain
      .unsafeRunSync()
}
