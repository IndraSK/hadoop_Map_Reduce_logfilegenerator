package Generation

import com.typesafe.config.{Config, ConfigFactory}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class Testcase extends AnyFlatSpec with Matchers {
  behavior of "Configuration parameters module"

  it should "obtain frequency" in {
    val config: Config = ConfigFactory.load("application.conf")
    config.getDouble("randomLogGenerator.Frequency") shouldBe 0.07
  }
  it should "obtain MaxCount" in {
    val config: Config = ConfigFactory.load("application.conf")
    config.getDouble("randomLogGenerator.MaxCount") shouldBe 500
  }
  it should "obtain DurationMinutes" in {
    val config: Config = ConfigFactory.load("application.conf")
    config.getInt("randomLogGenerator.DurationMinutes") shouldBe 10
  }
  it should "obtain MaxSymbol" in {
    val config: Config = ConfigFactory.load("application.conf")
    config.getInt("randomLogGenerator.MaxSymbol") shouldBe 126
  }
}