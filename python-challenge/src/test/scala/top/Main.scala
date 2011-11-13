package top

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.{Tag, Spec}

class Main extends ShouldMatchers with Spec {

  it("zero", Tag("0")) {
    println(Zero.result)
  }

  it("one", Tag("1")) {
    println(One.result)
  }

  it("two", Tag("2")) {
    println(Two.result)
  }

  it("three", Tag("3")) {
    println(Three.result)
  }

  it("four", Tag("4")) {
    println(Four.result)
  }

}

