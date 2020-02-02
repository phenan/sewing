package com.phenan.sewing

trait Monoidal [F[_]] {
  def product [A, B <: Tuple] (fa: => F[A], fb: => F[B]): F[A *: B]
  def pure [A] (a: => A): F[A]

  def productAll [T <: Tuple] (tuple: HKTuple[F, T]): F[T] = {
    productAllUnsafe(tuple).asInstanceOf[F[T]]
  }

  private def productAllUnsafe (tuple: Any): Any = tuple match {
    case e *: es => productUnsafe(e, productAllUnsafe(es))
    case _       => pure[Unit](())
  }

  private def productUnsafe (fa: => Any, fb: => Any): Any = product(fa.asInstanceOf, fb.asInstanceOf)
}
