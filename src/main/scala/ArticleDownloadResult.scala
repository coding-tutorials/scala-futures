import java.util.concurrent.TimeUnit

class ArticleDownloadResult(downloadId: Int, article: Article, content: String, initialDownloadNanoTime: Long){
  val executionMilliseconds: Long = TimeUnit.SECONDS.convert(System.nanoTime - initialDownloadNanoTime, TimeUnit.NANOSECONDS)
  val size: String = content.size match {
    case size if size > 1000000 => "LARGE"
    case size if size > 500000 => "MEDIUM"
    case _ => "SMALL"
  }

  val printString: String = s"$downloadId - [${executionMilliseconds}s][$size] - ${article.title}"
}
