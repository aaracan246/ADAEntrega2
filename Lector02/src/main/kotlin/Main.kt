package org.example

import java.io.File

fun main(){

    val file = File("ficheros/calificaciones.csv")
    val gestorFich = GestorFich()
    val listaAlumnos = gestorFich.FichReader(file)

    gestorFich.calcularNotaFinal(listaAlumnos)

    val(aprobados, suspensos) = gestorFich.clasificarAlumnos(listaAlumnos)

    println("Aprobados: ")
    aprobados.forEach{ println("${it.nombre}, ${it.apellidos}, nota final: ${it.notaFinal}, asistencia: ${it.asistencia}%.") }

    println("")
    println(". . .")
    println("")

    println("Suspensos: ")
    suspensos.forEach{ println("${it.nombre}, ${it.apellidos}, nota final: ${it.notaFinal}, asistencia: ${it.asistencia}%.") }
}