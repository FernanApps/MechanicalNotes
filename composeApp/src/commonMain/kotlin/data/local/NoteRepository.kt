package data.local

import domain.model.Anomaly
import domain.model.Note
import domain.repository.NoteRepository
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toLocalDateTime

private fun generateSampleNotes(): List<Note> {
    return listOf(
        Note(
            date = LocalDateTime.parse("2024-07-01T00:00:00"),
            vehicle = "EGE 178 VW compacta",
            anomalies = listOf(
                Anomaly(
                    description = "por consumo",
                    accessoriesOrSupplies = listOf("01 gl. de aceite para motor", "1/2 gl de aceite hidraulico ATF"),
                    worksDone = listOf("agregado de fluidos")
                ),
                Anomaly(
                    description = "reparar caja de dirección",
                    accessoriesOrSupplies = emptyList(),
                    worksDone = listOf("reparar caja de dirección")
                )
            )
        ),
        Note(
            date = LocalDateTime.parse("2024-07-04T00:00:00"),
            vehicle = "EAA 662 JAC baranda",
            anomalies = listOf(
                Anomaly(
                    description = "por consumo",
                    accessoriesOrSupplies = listOf("1/4 de aceite p/ motor"),
                    worksDone = emptyList()
                )
            )
        ),
        Note(
            date = LocalDateTime.parse("2024-07-04T00:00:00"),
            vehicle = "EGZ 671 VW costelacion",
            anomalies = listOf(
                Anomaly(
                    description = "por consumo",
                    accessoriesOrSupplies = listOf("01 gl de aceite p/ motor"),
                    worksDone = emptyList()
                )
            )
        ),
        Note(
            date = LocalDateTime.parse("2024-07-04T00:00:00"),
            vehicle = "EGB 804 INTER compacta",
            anomalies = listOf(
                Anomaly(
                    description = "contaminacion del petroleo con aceite",
                    accessoriesOrSupplies = listOf("06 juegos de sellos p/ inyectores", "04 gl de aceite p/ motor"),
                    worksDone = listOf("cambio de sellos de los inyectores", "limpieza de tanque de combustible", "limpieza de filtro y separador de agua")
                ),
                Anomaly(
                    description = "cambio de filtros de petroleo",
                    accessoriesOrSupplies = emptyList(),
                    worksDone = listOf("cambio de filtros de petroleo")
                )
            )
        ),
        Note(
            date = LocalDateTime.parse("2024-07-07T00:00:00"),
            vehicle = "EGB 244 MERCEDES cisterna",
            anomalies = listOf(
                Anomaly(
                    description = "los cambios no pasan con facilidad",
                    accessoriesOrSupplies = listOf("perno de 3/8 X 1 pulgada"),
                    worksDone = listOf("regulacion de varilla de bomba de embrague", "cambio de perno")
                )
            )
        ),
        Note(
            date = LocalDateTime.parse("2024-07-09T00:00:00"),
            vehicle = "EGE 178 VW compacta",
            anomalies = listOf(
                Anomaly(
                    description = "frenos en mal estado fuga de aire",
                    accessoriesOrSupplies = listOf("cañeria plastica de 3mt"),
                    worksDone = listOf("cañeria de la unidad ege 180")
                )
            )
        ),
        Note(
            date = LocalDateTime.parse("2024-07-11T00:00:00"),
            vehicle = "INT 027 KOMATSU motoniveladora",
            anomalies = listOf(
                Anomaly(
                    description = "sin freno",
                    accessoriesOrSupplies = listOf("accesorio de bomba de freno", "04 liquido de freno"),
                    worksDone = listOf("reparación de la bomba de freno", "purgado general del sistema de freno", "regulacion de varilla de freno")
                )
            )
        ),
        Note(
            date = LocalDateTime.parse("2024-07-13T00:00:00"),
            vehicle = "EGB 804 INTERNATIONAL compacta",
            anomalies = listOf(
                Anomaly(
                    description = "por consumo",
                    accessoriesOrSupplies = listOf("05 gl de aceite hidraulico"),
                    worksDone = emptyList()
                )
            )
        ),
        Note(
            date = LocalDateTime.parse("2024-07-13T00:00:00"),
            vehicle = "EGB 244 MERCEDES cisterna",
            anomalies = listOf(
                Anomaly(
                    description = "no activa la alta",
                    accessoriesOrSupplies = listOf("04 abrazaderas de plastico", "1/8 de aceite p/ sistema de dirección"),
                    worksDone = listOf("mantenimiento valvula alta baja", "regulación del pedal de embrague")
                )
            )
        ),
        Note(
            date = LocalDateTime.parse("2024-07-14T00:00:00"),
            vehicle = "EGZ 680 VW compacta",
            anomalies = listOf(
                Anomaly(
                    description = "vibración en eje cardan",
                    accessoriesOrSupplies = listOf("soporte de cardan completo"),
                    worksDone = emptyList()
                )
            )
        )
    )
}


class NoteRepositoryImp(): NoteRepository {
    override fun getAllNotes(): List<Note>{
        return generateSampleNotes()
    }
}