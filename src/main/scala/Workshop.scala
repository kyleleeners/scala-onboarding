import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

object Intro extends App {
  /*
  Welcome to Scala on team Cerberus. This project is meant to serve as an introduction to some of the Scala you'll
  encounter on the team. The idea is to introduce a concept, provide some examples, and then have you implement a few
  methods to get some tests to pass.

  Each section will be split into a different scala object. If you're using intellij, you should be able to run it by
  hitting the green triangle.

  Try running the `Intro` object. This will compile things and execute the code below
   */
  println("Good job so far!")
}

object Basics extends App {
  /*
  Primitive types in Scala are similar to what you would see in Java. String, Int, Long, Boolean, etc. To assign a value
  use the `val = xyz`. To assign a variable (something that can be overwritten) use `var = abc`.
  */

  val a: String = "a"
  val b: Int = 2
  // private just means its scoped to within this object (same as Java)
  private var c: Boolean = true
  c = false

  // note that all types have a toString method
  println(a)
  println(b)
  println(c)

  /*
  Below is the syntax for functions. The curly braces are only required when you're doing value assignment at top-level
  of the function. Note that there is not `return`. Scala will simply return whatever is on the last line of the function.
  */
  def myFunctionWithBraces(anInt: Int): String = {
    val anIntPlusOne = anInt + 1
    anIntPlusOne.toString
  }

  def myFunctionWithoutBraces(anInt: Int): String = anInt.toString

  print(myFunctionWithBraces(1))
  print(myFunctionWithoutBraces(2))


  // If/else works as expected
  def basicConditional(anInt: Int): String =
    if (anInt > 0) {
      s"Provided int: $anInt was greater than 0"
    } else {
      s"Provided int: $anInt was less than 0"
    }

  println(basicConditional(10))
  println(basicConditional(-10))

  // TODO: create a function which takes a string and returns true if the string equals "Hootsuite"
  def isHootsuite(s: String): Boolean = ???


  // If/else is fine, but more often than not, you'll end up using a match
  def matchConditional(s: String): Boolean = s match {
    case "Hootsuite" => true // this is like `if` and will get checked first
    case "Facebook" => false // this is like `if else` and will get checked next
    case _ => false // this is like `else` and will get checked last
  }

  println(matchConditional("Hootsuite"))
  println(matchConditional("Other"))

  // TODO: This function is supposed to take an integer, convert 1 to "Hootsuite", 2 to "Facebook", and any other
  // integer to "Twitter" but there is a bug. Can you fix it?
  def conditionalBugFix(i: Int): String = i match {
    case 1 => "Hootsuite"
    case _ => "Twitter"
    case 2 => "Facebook"
  }
}

object ClassesAndSuch extends App {

  // For the most point you'll be dealing with 4 types of "classes"
  val myClass = new MyClass(1)

  println(MyObject.myFunction(123))
  val myCaseClass = MyCaseClass(1)

  // traits. Like java interfaces expect can have some implementation in them
  trait MyTrait {
    def myMethod(i: Int): String

    def myOtherMethod(i: Int): String = i.toString
  }

  println(myClass.myFunction)

  // class, basically your standard java class.
  class MyClass(i: Int) {
    def myFunction: String = i.toString
  }

  // case class. We usually use this to represent data, rather than a class / object which generally perform an action.
  // That's not to say however that case classes don't support functions; the function should just be pretty light-weight.
  // You should always add the `final` modifier to case classes which prevent then from being extended. Weird stuff can
  // happen when you try to extend case classes
  final case class MyCaseClass(i: Int)

  // println calls toString behind the scenes and remember we can toString anything, however it might not print as expected
  println(myCaseClass)

  // For the most part traits are then extended by one of the previous types which forces you to implement the methods.
  // Note that even though its not listed here, these have access to myOtherMethod. Also the `override` isn't necessary
  // but helps the reader know where the method is coming from
  final case class MyCaseClass2() extends MyTrait {
    override def myMethod(i: Int): String = myOtherMethod(i)
  }

  class MyClass2() extends MyTrait {
    override def myMethod(i: Int): String = myOtherMethod(i)
  }

  // object, which you've already seen. Think of it like a singleton. It can't be created so it can't take any inputs
  object MyObject {
    def myFunction(s: Int): String = s.toString
  }

  object MyObject2 extends MyTrait {
    override def myMethod(i: Int): String = myOtherMethod(i)
  }
}

object Options extends App {
  /*
  Option is the first of many monads that exist in Scala. All you really need to know about monads at this point is that
  they're a kinda wrapper around another type and they have a `.map` and a `.flatMap` method. Map allows you to "get inside"
  the Monad, and do stuff with its inner type. FlatMap is basically `.map` but it also combines monads together in a sense.
  More on .flatMap later.

  Option means the thing basically exists or it doesn't. The two states are either Some(thing) or None.
  */

  val optionalVal: Option[String] = Some("hootsuite")
  val anotherOptionalValue: Option[String] = None

  // feel free to check out all the functions that work on Options
  def valueInOptionExists(optionVal: Option[String]): Boolean = optionVal.nonEmpty

  println(valueInOptionExists(optionalVal))
  println(valueInOptionExists(anotherOptionalValue))

  def maybeIncrease(optionVal: Option[Int]): Option[Int] = optionVal.map(insideVal => insideVal + 1)

  println(maybeIncrease(Some(1)))
  println(maybeIncrease(None))

  // TODO: Implement the below function which takes an Option[Int], converts it to a string if the value >= 0
  // returns "Received a negative number" if the value < 0, or "Received a None"
  def optionTest(option: Option[Int]): String = ???
}

object Eithers extends App {

  /*
  Either's are another monad. They're meant to convey that the type is either A or B (Either[A,B].) Generally the thing
  on the left is an error state however that's not hard requirement.
  */
  val aRight: Either[String, Int] = Right(1)
  val aLeft: Either[String, Int] = Left("s")

  // you can map on an either, which will get you into the thing on the right
  def eitherFunc(either: Either[String, Int]): Either[String, Boolean] = either.map(right => right > 0)

  println(eitherFunc(Right(1)))
  println(eitherFunc(Right(-1)))
  println(eitherFunc(Left("s")))

  // TODO: you're given an Option[Int], convert it to an Either[String, Int] where you map the Int to a Right if it exists
  // or otherwise return left of "Received a None"
  def toEither(option: Option[Int]): Either[String, Int] = ???

}

object Trys extends App {
  /*
  Try's are another. Try's can either be Success(thing) or Failure(exception). Similar to the others, mapping on a Try
  will give you an instance of the Success case
  */

  val aSuccess: Try[String] = Success("Yay!")
  val aFailure: Try[String] = Failure(new Exception("uh oh!"))

  def aTryMap(aTry: Try[String]): Try[String] = aTry.map(s => s"$s + some other stuff")

  println(aTryMap(aSuccess))
  println(aTryMap(aFailure))

  // TODO write a function which can divide two numbers or return a Failure with exception message "Boom!" if the denom is 0
  def divide(num: Int, denum: Int): Try[Int] = ???
}

object Seqs extends App {

  //Sequences/Lists can also be mapped on. Here you provide a function for what you should do to each element in the collections
  val aList: List[Int] = List(1, 2, 3)

  // plusOne and plusOneFn are doing the same thing here
  def plusOne(list: List[Int]): List[Int] = list.map(item => item + 1)
  def plusOneFn(list: List[Int]): List[Int] = list.map(pls)
  def pls(i: Int): Int = i + 1

  // TODO: write a function... not sure hwat to do here...
  def seqConversion(seq: Seq[Int]): List[_] = ???
}

object FlatMapDigression extends App {
  /*
  Sometimes you'll get a gross nested type and want to make it into something a bit more logical. For example, suppose
  you end up with a List[Option[String]] which will look something like List(Some("a"), None, Some("b"), None). Carrying
  around a list with a bunch of None's in it is kinda silly, so what we can do is `.flatten` the list.
  */

  // remember, you dont need to add types here, this is just to make things clearer for this workshop
  val optionList: List[Option[String]] = List(Some("a"), None, Some("b"), None)
  val flattenedList: List[String] = optionList.flatten
  println(optionList)
  println(flattenedList)

  // Flattening a list of Options is intuitive but what about a list of Eithers? Uncomment the next two lines and see what happens
//  val eitherList: List[Either[String, Int]] = List(Left("a"), Right(1), Left("b"), Right(2))
//  eitherList.flatten

  // Its probably complaining that its missing an implicit traversable blah blah blah. Basically scala doesn't know out of
  // the box how to combine these things. We could write out own if we felt so inclined.

  // next up is .flatMap which is basically just map and flatten bundled into one. In fact, everywhere you can use flatMap you could
  // also just map and flatten at the end, but for the most part flatMap is preferred.

  // Suppose you have a function which takes a jsonString from some API and converts it to a Scala case class representation.
  // You then want to validate some of the fields in the case class before converting it to an internal model. That might look
  // something like this:

  final case class MyInternalModel(id: Int) // using Int's for ID's is generally a bad call, but ignore it for now
  // our team often suffixes with DTO (Data Transfer Object) to indicate the scala representation of some Json object we got
  final case class SomeDTO(id: String)

  trait JsonStringConverter {
    def convert(s: String): Try[SomeDTO]
  }

  class MyClass(jsonStringConverter: JsonStringConverter) {

    def convertToModel(jsonString: String): Try[MyInternalModel] = {
      // remember flatMap here is first mapping (doing whatever function is passed) then flattening the two Try's
      jsonStringConverter.convert(jsonString).flatMap { dto =>
        // map here effectively matches on the Success case of the Try and automatically propagates the failure
        Try(dto.id.toInt).map(i => MyInternalModel(i))
      }
    }
  }
}

object Futures extends App {
  /*
  Finally we have Future, future's are meant to represent some async process. Generally Futures are used when calling
  other APIs and dealing with databases and such. They have a few more nuances than the other monads.
  */

  // First of which is they require a thing called an Execution Context.
  import scala.concurrent.ExecutionContext.Implicits.global

  // This basically manages where the Future has gone and what to do when it comes back. If you cmd+click on the `global`
  // part of the import you'll notice it has the `implicit` modifier. You'll often see functions/classes which have two
  // sets of parameters, the regular parameters and implicit parameters. The regular parameters must be passed in, the
  // implicits only need to be in the same scope as the function. Note: you can have any number of sets of parameters but
  // if you have implicits, they must come last
  def callWithImplicit(nonImplicitParam: Int)(implicit implicitParam: Int): Boolean = nonImplicitParam + implicitParam > 0

  // heres the implicit
  private implicit val implicitParam: Int = 3

  // notice how we're not passing the implicit, it gets pulled in automagically since its in scope
  val result = callWithImplicit(2)
  println(result)

  // Future's are monads so they can be mapped. Lots of times you'll get a Future back, immediately map it and then do
  // everything you need to do inside the map without having to worry about the future
  val aFuture: Future[Int] = Future(1).map(i => i + 1)
  println(aFuture)

  // They're also non-blocking
  val aFutureThatWaits = Future("some string").map { s =>
    Thread.sleep(1000)
    s"$s finished"
  }
  val fastFuture = Future("another string").map { s =>
    s"$s finished"
  }
  // Unit is basically void
  def printFutures(f: Future[String]): Future[Unit] = f.map(s => println(s))
  printFutures(aFutureThatWaits)
  printFutures(fastFuture)

  // You might see something weird in the previous example. The future with the sleep probably printed like: Future(<not completed>)?
  // What happened here was aFutureThatWaits went off and did its thing, then while its doing its thing we moved onto fastFuture which
  // completes immediately. So, when we print we get the result of fastFuture but not of aFutureThatWaits since its still off doing
  // work.

  // Future's either complete or they fail. If you want to catch the failure you need to recover
  final case class ABetterFailure(reason: String)
  val futureStates: Future[Either[ABetterFailure, Int]] =
    Future(1)
      .map(i => Right(i + 1))
      .recover {
        case e =>
          // could log the failure here or return something a bit less exception-y
          val errorMessage = e.getMessage
          println(errorMessage)
          Left(ABetterFailure(errorMessage))
      }
}
