package domain.model

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import utils.TimeExt.now


@Serializable
data class Note(
    @SerialName("id") val id: Long  = 0L,
    @SerialName("date") val date: LocalDateTime = LocalDateTime.now(),
    // plate of vehicle
    @SerialName("vehicle")  val vehicle: String = "",
    @SerialName("anomalies") val anomalies: List<Anomaly> = emptyList()
)

@Serializable
data class Anomaly(
    @SerialName("description") val description: String,
    @SerialName("accessoriesOrSupplies") val accessoriesOrSupplies: List<String>,
    @SerialName("worksDone") val worksDone: List<String>
)


fun main() {
    val note = Note()
    println(note.hashCode())
    println(note.toString())
}
