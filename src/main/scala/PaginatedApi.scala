import scala.concurrent.{ExecutionContext, Future}

// A bit of a contrived example suppose you need to get a list of people from an API. The API takes a cursor and will
// return at maximum 2 results per request. The API docs also state they return an optional cursor to pointing to the
// next result. You need to write a function to call this API and return a list of all the people in the server using
// the cursor for each subsequent request.
final case class Record(name: String)
final case class APIResult(records: List[Record], cursor: Option[Int])

trait PaginatedApi {
  def getRecordsApi(cursor: Int)(implicit ec: ExecutionContext): Future[APIResult]
}

class DefaultPaginatedApi extends PaginatedApi {

  // ignore the implementation, just focus on the types
  def getRecordsApi(cursor: Int)(implicit ec: ExecutionContext): Future[APIResult] = {
    val recordsAndCursors = List(
      (Record("record0"), 0),
      (Record("record1"), 1),
      (Record("record2"), 2),
      (Record("record3"), 3),
      (Record("record4"), 4),
      (Record("record5"), 5),
      (Record("record6"), 6),
      (Record("record7"), 7)
    )
    var nextCursor: Option[Int] = None
    val filtered = recordsAndCursors.collect { case (p, c) if c >= cursor && c < cursor + 2 =>
      nextCursor = Some(c + 1)
      p
    }
    Future(APIResult(filtered, nextCursor))
  }
}

class ApiClient(paginatedApi: PaginatedApi) {
  // normally this would get wired in and injected
  import scala.concurrent.ExecutionContext.Implicits.global

  // TODO: implement this
  def getRecords: Future[List[Record]] = ???
}
