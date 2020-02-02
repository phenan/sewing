package com.phenan.sewing

import com.phenan.generic.Generic

trait InvariantFunctor [F[_]] {
  def xmap [A, B] (f: A => B, g: B => A): F[A] => F[B]

  def fromGeneric [A, B] (given generic: Generic[A, B]): F[B] => F[A] = xmap(generic.from, generic.to)
  def fromGenericReverse [A, B] (given generic: Generic[A, B]): F[A] => F[B] = xmap(generic.to, generic.from)
}
