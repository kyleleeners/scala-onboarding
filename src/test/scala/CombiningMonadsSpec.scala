class CombiningMonadsSpec extends BaseSpec {
  "Converter" should {
    val badApi = new DefaultBadApi
    val converter = new Converter(badApi)

    "return empty list when no matchers are found" in {
      converter.callAndConvert("blah") should be(Right(List()))
    }

    "return a match when it is found" in {
      val expectedResult = List(CapitalizedPerson("PERSONA"))

      val result = converter.callAndConvert("personA")

      result should be(Right(expectedResult))
    }

    "return multiple matches when they are found" in {
      val expectedResult = List(CapitalizedPerson("PERSONA"), CapitalizedPerson("PERSONB"), CapitalizedPerson("PERSONC"))

      val result = converter.callAndConvert("person")

      result should be(Right(expectedResult))
    }

    "return error on empty string" in {
      val expectedResult = Error("cannot match on empty string")

      val result = converter.callAndConvert("")

      result should be(Left(expectedResult))
    }
  }
}
