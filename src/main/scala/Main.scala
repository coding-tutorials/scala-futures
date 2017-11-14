import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}
import ExecutionContext.Implicits.global

object Main extends App {
  // Start downloading articles
  val downloadsPromises:Iterable[Future[ArticleDownloadResult]] = WikipediaArticles.articles.map(article => {
    val index = WikipediaArticles.articles.indexOf(article)
    ArticleDownloader.download(index, article)
  })

  // Group all promises for future single await
  val groupedPromise:Future[Iterable[ArticleDownloadResult]] = Future.sequence(downloadsPromises)

  // Await for all promises (downloads) finish
  val downloadsResults:Iterable[ArticleDownloadResult] = Await.result(groupedPromise, Duration.Inf)

  print(s"Finished downloading ${downloadsResults.size} articles!")

  downloadsResults.foreach(result => println(result.printString))
}





