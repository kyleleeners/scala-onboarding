import java.net.URL

import org.scalatest.concurrent.ScalaFutures.convertScalaFuture

class MediaHydrationSpec extends BaseSpec {
  "convert" should {
    val mimeTypeRetriever = new DefaultMimeTypeRetriever
    val converter = new DirectMessageConverter(mimeTypeRetriever)

    "not have any media when there is no attachment" in {
      val dmWithNoAttachment = DirectMessageDTO(id = "123", attachmentDTO = None)
      val expectedResult = DirectMessage(id = "123", media = List())

      val result = converter.convert(dmWithNoAttachment).futureValue

      result should be(expectedResult)
    }

    "convert and hydrate" in {
      val dmWithEverything = DirectMessageDTO(
        id = "123",
        Some(
          AttachmentDTO(
            List(
              VideoDTO("https://video1.mp4"),
              VideoDTO("https://video2.mp4")
            ),
            List(
              ImageDTO("https://image.jpeg"),
              ImageDTO("https://image.png")
            )
          )
        )
      )
      val expectedResult = DirectMessage(
        id = "123",
        List(
          Media(new URL("https://video1.mp4"), Some("video/mp4")),
          Media(new URL("https://video2.mp4"), Some("video/mp4")),
          Media(new URL("https://image1.jpeg"), Some("image/jpeg")),
          Media(new URL("https://image1.png"), None),
        )
      )

      val result = converter.convert(dmWithEverything).futureValue

      result should be(expectedResult)
    }
  }
}
