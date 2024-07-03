package domain.model

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Note(
    @SerialName("date") val date: LocalDateTime,
    // plate of vehicle
    @SerialName("vehicle")  val vehicle: String,
    @SerialName("anomalies") val anomalies: List<Anomaly>
)

@Serializable
data class Anomaly(
    @SerialName("description") val description: String,
    @SerialName("accessoriesOrSupplies") val accessoriesOrSupplies: List<String>,
    @SerialName("worksDone") val worksDone: List<String>
)
