package com.phenan.sewing.instances

import com.phenan.coproduct._
import com.phenan.sewing._

import com.phenan.coproduct.given

object option {
  given optionSemiringal: Semiringal[Option] {
    def product [A, B <: Tuple] (fa: => Option[A], fb: => Option[B]): Option[A *: B] = {
      for {
        a <- fa
        b <- fb
      } yield a *: b
    }
    def pure [A] (a: => A): Option[A] = Some(a)
    def sum [A, B <: Coproduct] (fa: => Option[A], fb: => Option[B]): Option[A +: B] = {
      fa.map(InL(_)).orElse(fb.map(InR(_)))
    }
    def zero: Option[CNil] = None
  }
}