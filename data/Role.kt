package data
enum class Role(val role: String) {
    INTERN("Intern"), EMPLOYEE("Employee"), MANAGER("Manager");
    override fun toString(): String = role
}
