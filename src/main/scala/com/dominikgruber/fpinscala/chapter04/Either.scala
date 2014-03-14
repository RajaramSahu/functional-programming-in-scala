package com.dominikgruber.fpinscala.chapter04

sealed trait Either[+E, +A] {

  /**
   * Exercise 06
   * Implement versions of map, flatMap, orElse, and map2 on Either that operate
   * on the Right value.
   */
  def map[B](f: A => B): Either[E, B] = this match {
    case Left(e) => Left(e)
    case Right(x) => Right(f(x))
  }

  def flatMap[EE >: E, B](f: A => Either[EE, B]): Either[EE, B] = map(f) match {
    case Left(e) => Left(e)
    case Right(x) => x
  }

  def orElse[EE >: E,B >: A](b: => Either[EE, B]): Either[EE, B] = this match {
    case Left(_) => b
    case Right(_) => this
  }

  def map2[EE >: E, B, C](b: Either[EE, B])(f: (A, B) => C): Either[EE, C] =
    this flatMap(aa => b map (bb => f(aa, bb)))
}

case class Left[+E](value: E) extends Either[E, Nothing]
case class Right[+A](value: A) extends Either[Nothing, A]