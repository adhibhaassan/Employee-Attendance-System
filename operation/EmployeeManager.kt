package operation
import data.EmployeeData
import data.Role

class EmployeeManager : ArrayList<EmployeeData>() {
    override fun add(element: EmployeeData): Boolean {
        // Ensure contact is 10 digits
        if (element.contactNumber.toString().length != 10) {
            println("Contact number must be 10 digits.")
            return false
        }
        // Ensure Manager exists for non-manager roles
        if (element.role != Role.MANAGER && !any { it.id == element.reportTo && it.role == Role.MANAGER }) {
            println("Manager ID ${element.reportTo} not valid.")
            return false
        }

        return super.add(element)
    }

    override fun toString(): String {
        return joinToString("\n") {
            "ID: ${it.id}, Name: ${it.firstName} ${it.lastName}, Role: ${it.role}, Reports To: ${it.reportTo}"
        }
    }

    fun getById(id: String): EmployeeData? = find { it.id == id }
}
