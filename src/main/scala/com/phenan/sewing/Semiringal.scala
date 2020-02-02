package com.phenan.sewing

import com.phenan.coproduct._

type CoproductOfElements[T <: NonEmptyTuple] = CoproductOfElements.Impl[T]

object CoproductOfElements {
  type Impl [T <: Tuple] = T match {
    case Unit    => CNil
    case e *: es => e +: Impl[es]
  }
}

trait Semiringal [F[_]] extends Monoidal[F] {
  def sum [A, B <: Coproduct] (fa: => F[A], fb: => F[B]): F[A +: B]
  def zero: F[CNil]

  def sumAll [T <: NonEmptyTuple] (tuple: HKTuple[F, T]): F[CoproductOfElements[T]] = {
    sumAllUnsafe(tuple).asInstanceOf[F[CoproductOfElements[T]]]
  }

  private def sumAllUnsafe (tuple: Any): Any = tuple match {
    case e *: es => sumUnsafe(e, sumAllUnsafe(es))
    case _       => zero
  }

  private def sumUnsafe (fa: => Any, fb: => Any): Any = sum(fa.asInstanceOf, fb.asInstanceOf)
}