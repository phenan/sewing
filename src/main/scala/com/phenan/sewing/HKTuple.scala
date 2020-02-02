package com.phenan.sewing

type HKTuple [F[_], T <: Tuple] = HKTuple.Impl[F, T]

object HKTuple {
  type Impl [F[_], T <: Tuple] = T match {
    case Unit    => Unit
    case e *: es => F[e] *: Impl[F, es]
  }
}
