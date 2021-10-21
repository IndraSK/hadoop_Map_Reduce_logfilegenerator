package Generation

import com.typesafe.config.{Config, ConfigFactory}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class Patterntest extends AnyFlatSpec with Matchers {
  behavior of "Configuration parameters module"

  it should "obtain the pattern" in {
    val config: Config = ConfigFactory.load("application.conf")
    config.getString("randomLogGenerator.Pattern") shouldBe "([a-c][e-g][0-3]|[A-Z][5-9][f-w]){5,15}"
  }
}
