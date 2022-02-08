import scala.util.{Failure, Success, Try}

// Suppose you need to call an API which, when provided a name String, returns either a failure if the call fails,
// a list of person objects whose name matches the provided name, or None if there are no matches. Can you write a
// function to call this API and return a more sensible object? Maybe an Either[Error, List[CapitalizedPerson]], where
// the reason parameter inside the Failure is the exception message in the case of the a Failure and the name of
// CapitalizedPerson is the same name as that in Person but capitalized (hint use: .toUpperCase)

final case class Person(name: String)

final case class Error(reason: String)

final case class CapitalizedPerson(name: String)

trait BadApi {
  def getPersons(nameString: String): Try[Option[List[Record]]]
}

class DefaultBadApi extends BadApi {
  // don't worry about whats happening here, just focus in the input and output types
  def getPersons(nameString: String): Try[Option[List[Record]]] = {
    val people = List(Record("personA"), Record("personB"), Record("personC"))
    if (nameString.isEmpty) Failure(new Exception("cannot match on empty string"))
    else Success {
      val filtered = people.filter(p => p.name.contains(nameString))
      if (filtered.isEmpty) None
      else Some(filtered)
    }
  }
}

class Converter(badApi: BadApi) {
  // TODO: implement this
  def callAndConvert(nameString: String): Either[Error, List[CapitalizedPerson]] = ???
}
