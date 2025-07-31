package data
data class EmployeeData(
    val id: String,
    val firstName: String,
    val lastName: String,
    val role: Role,
    val contactNumber: Long,
    val reportTo: String
)
