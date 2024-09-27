package org.example

import java.io.File

class GestorFich {

    fun FichReader(file: File): List<Alumno> {
        val listaAlumno = mutableListOf<Alumno>()

        file.forEachLine { linea ->
            val contenido = linea.split(";")
            if (contenido[0] != "Apellidos") { // <- Ignoramos la primera línea
                val apellidos = contenido[0]
                val nombre = contenido[1]
                val asistencia = contenido[2].replace("%", "").toDoubleOrNull() ?: 0.0

                var parcial1 = contenido[3].replace(",", ".").toDoubleOrNull() ?: 0.0
                var parcial2 = contenido[4].replace(",", ".").toDoubleOrNull() ?: 0.0


                val ordinario1 = contenido[5].replace(",", ".").toDoubleOrNull()
                val ordinario2 = contenido[6].replace(",", ".").toDoubleOrNull()


                var practicas = contenido[7].replace(",", ".").toDoubleOrNull() ?: 0.0
                val ordinarioPracticas = contenido[8].replace(",", ".").toDoubleOrNull()

                // Si el alumno recupera, se actualiza la nota:
                if (ordinario1 != null) { parcial1 = ordinario1 }
                if (ordinario2 != null) { parcial2 = ordinario2 }
                if (ordinarioPracticas != null) { practicas = ordinarioPracticas }


                listaAlumno.add(Alumno(apellidos, nombre, asistencia, parcial1, parcial2, ordinario1, ordinario2, practicas, ordinarioPracticas))
            }
        }

        return listaAlumno.sortedBy { it.apellidos }
    }

    fun calcularNotaFinal(listaAlumnos: List<Alumno>){
        listaAlumnos.forEach{ alumno ->
            val notaFinal = (alumno.parcial1 * 0.30) + (alumno.parcial2 * 0.30) + (alumno.practicas * 0.40)
            alumno.notaFinal = notaFinal
        }
    }

    fun clasificarAlumnos(listaAlumnos: List<Alumno>): Pair<List<Alumno>, List<Alumno>>{
        val aprobados = mutableListOf<Alumno>()
        val suspensos = mutableListOf<Alumno>()

        listaAlumnos.forEach{ alumno ->
            val asistencia = alumno.asistencia >= 75.0
            val examenes = alumno.parcial1 >= 4.0 && alumno.parcial2 >= 4.0 && alumno.practicas >= 4.0
            val notaFinal = alumno.notaFinal >= 5

            if (asistencia && examenes && notaFinal){ aprobados.add(alumno) } else { suspensos.add(alumno) }
        }
        return Pair(aprobados, suspensos)
    }
}

