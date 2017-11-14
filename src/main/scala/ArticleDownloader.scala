import scala.concurrent.{ExecutionContext, Future}
import scala.io.Source
import ExecutionContext.Implicits.global

object ArticleDownloader {
  def download(downloadId: Int, article: Article):Future[ArticleDownloadResult] = Future {
    val initialDownloadNanoTime = System.nanoTime
    val httpContent = Source.fromURL(article.url, "ISO-8859-1").mkString

    val articleDownloadResult  = new ArticleDownloadResult(downloadId, article, httpContent, initialDownloadNanoTime)

    println(articleDownloadResult.printString)

    articleDownloadResult
  }
}
