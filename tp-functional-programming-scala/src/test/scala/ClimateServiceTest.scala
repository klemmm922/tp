import com.github.polomarcus.utils.ClimateService
import com.github.polomarcus.model.CO2Record
import org.scalatest.funsuite.AnyFunSuite

//@See https://www.scalatest.org/scaladoc/3.1.2/org/scalatest/funsuite/AnyFunSuite.html
class ClimateServiceTest extends AnyFunSuite {
  test("containsWordGlobalWarming - non climate related words should return false") {
    assert(!ClimateService.isClimateRelated("pizza"))
    assert(!ClimateService.isClimateRelated("IPCC"))
  }

  test("isClimateRelated - climate related words should return true") {
    assert(ClimateService.isClimateRelated("climate change"))
    
  }

  test("parseRawData") {
    val list1 = List((2003, 1, 355.2), (2004, 1, 375.2), (2004, 1, -375.2))
    val output = List(Some(CO2Record(2003, 1, 355.2)), Some(CO2Record(2004, 1, 375.2)), None)
    assert(ClimateService.parseRawData(list1) == output)
  }

  test("filterDecemberData") {
    val list2 = List(Some(CO2Record(2003, 1, 355.2)),Some(CO2Record(2004, 12, 375.2)))
    val output = List(CO2Record(2003, 1, 355.2))
    assert(ClimateService.filterDecemberData(list2) == output)
  }
}
