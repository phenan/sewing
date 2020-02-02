package com.phenan.sewing

import com.phenan.coproduct._

import com.phenan.coproduct.given
import com.phenan.sewing.instances.option.given

import org.junit.Test
import org.junit.Assert._

class OptionTest {
  @Test def testMonoidal(): Unit = {
    val monoidal = summon[Monoidal[Option]]

    val x: Option[(Int, String)] = monoidal.productAll[(Int, String)]((Some(1), Some("hoge")))

    assertEquals(x, Some((1, "hoge")))
  }

  @Test def testSemiringal(): Unit = {
    val semiringal = summon[Semiringal[Option]]

    val x: Option[Int +: String +: CNil] = semiringal.sumAll[(Int, String)]((None, Some("hoge")))

    assertEquals(x, Some(Coproduct[Int +: String +: CNil]("hoge")))
  }
}