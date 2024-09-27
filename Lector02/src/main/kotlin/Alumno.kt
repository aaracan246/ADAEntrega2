package org.example

data class Alumno(val apellidos: String,
                  val nombre: String,
                  val asistencia: Double,
                  val parcial1: Double,
                  val parcial2: Double,
                  val ordinario1: Double?,
                  val ordinario2: Double?,
                  val practicas: Double,
                  val ordinarioPracticas: Double?,
                  var notaFinal: Double = 0.0)


