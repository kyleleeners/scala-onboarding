import java.net.URL

import scala.concurrent.{ExecutionContext, Future}

// Suppose you are calling a social network to get information regarding a direct message. As part of the response
// payload there is an attachment object with media like so:
final case class VideoDTO(url: String)
final case class ImageDTO(url: String)
final case class AttachmentDTO(videoData: List[VideoDTO], imageData: List[ImageDTO])
final case class DirectMessageDTO(id: String, attachmentDTO: Option[AttachmentDTO])

// we'd like to add media support to our internal DirectMessage. We'd also like to make a HTTP HEAD request on the url
// to determine the mime type of the media which should help us render it on the front-end easier.
final case class Media(url: URL, mimeType: Option[String])
final case class DirectMessage(id: String, media: List[Media])

// using traits is good practice
trait MimeTypeRetriever {
  def getMimeType(url: URL)(implicit ec: ExecutionContext): Future[Option[String]]
}

class DefaultMimeTypeRetriever extends MimeTypeRetriever {
  // imagine this actually made a HEAD request
  def getMimeType(url: URL)(implicit ec: ExecutionContext): Future[Option[String]] =
    Future {
      val urlString = url.toExternalForm
      if (urlString.contains(".jpeg")) {
        Some("image/jpeg")
      } else if (urlString.contains(".mp4")) {
        Some("video/mpf")
      } else None
  }
}

class DirectMessageConverter(mimeTypeRetriever: MimeTypeRetriever) {

  // TODO: Implement this function and write some code to convert between dto and model
  def convert(dto: DirectMessageDTO): Future[DirectMessage] = ???
}
