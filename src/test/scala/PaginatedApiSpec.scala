import org.scalatest.concurrent.ScalaFutures.convertScalaFuture

class PaginatedApiSpec extends BaseSpec {
  "getRecords" should {

    val paginatedApi = new DefaultPaginatedApi
    val apiClient = new ApiClient(paginatedApi)

    "return all the records" in {
      val result = apiClient.getRecords.futureValue

      result should be(
        List(
          Record("record0"),
          Record("record1"),
          Record("record2"),
          Record("record3"),
          Record("record4"),
          Record("record5"),
          Record("record6"),
          Record("record7")
        )
      )
    }
  }
}
