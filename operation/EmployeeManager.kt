package operation
import data.EmployeeData
import data.Role
class EmployeeManager : ArrayList<EmployeeData>() {

    override fun add(element: EmployeeData): Boolean {
        if (element.role != Role.MANAGER &&
            !any { it.id == element.reportTo && it.role == Role.MANAGER }) {
            println("Invalid manager ID '${element.reportTo}'\nMust be an existing manager.")
            return false
        }
        return super.add(element)
    }

    override fun toString(): String {
        return if (isEmpty()) "No employees." else joinToString("\n") {
            val reportInfo = if (it.role == Role.MANAGER) "" else " | Reports To: ${it.reportTo}"
            "ID: ${it.id} | Name: ${it.firstName} ${it.lastName} | Role: ${it.role}$reportInfo"
        }
    }

//    fun getById(id: String): EmployeeData? = find { it.id == id }
}
