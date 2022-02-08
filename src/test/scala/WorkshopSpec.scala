import scala.util.{Failure, Success}

class WorkshopSpec extends BaseSpec {

  "Basics" should {
    "isHootsuite" in {
      Basics.isHootsuite("Hootsuite") should be (true)
      Basics.isHootsuite("Twitter") should be (false)
    }

    "conditionalBugFix" in {
      Basics.conditionalBugFix(1) should be("Hootsuite")
      Basics.conditionalBugFix(2) should be("Twitter")
      Basics.conditionalBugFix(3) should be("Facebook")
    }
  }

  "Options" should {
    "optionTest" in {
      Options.optionTest(Some(1)) should be("1")
      Options.optionTest(Some(-1)) should be("Received a negative number")
      Options.optionTest(None) should be("Received a None")
    }
  }

  "Eithers" should {
    "toEither" in {
      Eithers.toEither(Some(1)) should be(Right(1))
      Eithers.toEither(None) should be(Left("Received a None"))
    }
  }

  "Trys" should {
    "divide" in {
      Trys.divide(10, 1) should be(Success(10))
      Trys.divide(10, 0) should be(Failure(new Exception("Boom!")))
    }
  }

  "Seqs" should {
    "seqConversion" in {

    }
  }
}
