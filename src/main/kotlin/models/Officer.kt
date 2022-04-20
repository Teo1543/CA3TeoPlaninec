package models

data class Officer(var name: String, var dateOfBirth: String, var officerType: String, var crimesSolved: Int, var unsolvedCrimes: Int, var salary: Double,
                   var peopleSaved: Int) {
}