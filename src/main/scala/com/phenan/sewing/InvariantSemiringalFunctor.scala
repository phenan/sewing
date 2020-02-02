package com.phenan.sewing

import com.phenan.coproduct._
import com.phenan.generic._

trait InvariantSemiringalFunctor [F[_]] extends Semiringal[F] with InvariantMonoidalFunctor[F] {
  def select [T <: NonEmptyTuple, R] (tuple: HKTuple[F, T])(given generic: Generic[R, CoproductOfElements[T]]): F[R] = {
    fromGeneric(given generic)(sumAll(tuple))
  }
}
