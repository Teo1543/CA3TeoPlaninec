package models

data class Officer(var name: String, var dateOfBirth: String, var officerType: String, var crimesSolved: Int, val unsolvedCrimes: Int, var salary: Double,
                   var peopleSaved: Int) {
}