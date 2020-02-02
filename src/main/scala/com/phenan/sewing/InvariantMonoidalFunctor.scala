package com.phenan.sewing

import com.phenan.generic._

trait InvariantMonoidalFunctor [F[_]] extends Monoidal[F] with InvariantFunctor[F] {
  def construct [T <: Tuple, R] (tuple: HKTuple[F, T])(given generic: Generic[R, T]): F[R] = {
    fromGeneric(given generic)(productAll(tuple))
  }
}
