package controllers

import models.Officer

class OfficerAPI {

    private var officers = ArrayList<Officer>();

    fun add(officer: Officer): Boolean {
        return officers.add(officer);
    }

    fun listAllOfficers(): String {
        return if(officers.isEmpty()) {
            "No officers stored";
        } else {
            var listOfOfficers = "";
            for(i in officers.indices) {
                listOfOfficers += "${i}: ${officers[i]} \n";
            }
            listOfOfficers
        }
    }

    fun numberOfficers(): Int {
        return officers.size;
    }

    fun deleteOfficer(indexToDelete: Int) : Officer? {
        return if(isValidListIndex(indexToDelete, officers)) {
            officers.removeAt(indexToDelete);
        } else null
    }

    fun updateOfficer(indexToUpdate: Int, officer: Officer?): Boolean {
        val foundOfficer = findNote(indexToUpdate)

        if((foundOfficer != null) && (officer != null)) {
            foundOfficer.name = officer.name
            foundOfficer.dateOfBirth = officer.dateOfBirth
            foundOfficer.officerType = officer.officerType
            foundOfficer.crimesSolved = officer.crimesSolved
            foundOfficer.unsolvedCrimes = officer.unsolvedCrimes
            foundOfficer.salary = officer.salary
            foundOfficer.peopleSaved = officer.peopleSaved
            return true
        }
        return false
    }

    fun findNote(index: Int): Officer? {
        return if(isValidListIndex(index, officers)) {
            officers[index]
        } else null
    }


    fun isValidListIndex(index: Int, list: List<Any>): Boolean {
        return (index >= 0 && index < list.size);
    }

    fun isValidIndex(index: Int): Boolean {
        return isValidListIndex(index, officers);
    }

    fun officerTypeFinder(officerType: String): String {
        var officersString = ""
        for(Officer in officers) {
            if(Officer.officerType == officerType) {
                officersString = officersString + Officer + "\n"
            }
        }
        return if(officersString.isEmpty()) {
            "No officers found"
        } else {
            officersString
        }
    }

    fun listCrimeByNumber(number: Int) {
        var officersString = "";
        for(Officer in officers) {
            if(Officer.crimesSolved >= 5) {
                officersString = officersString + Officer + "\n"
            }
        }
    }

    fun listUnsolvedCrime(): String {
        var officersString = "";
        for(Officer in officers) {
            if(Officer.unsolvedCrimes >= 5) {
                officersString = officersString + Officer  + "\n"
            }
        }
        return officersString
    }

    fun officersWithHigherSolvedCrimes() {
        var officersString = "";
        for(Officer in officers) {
            if(Officer.crimesSolved > Officer.unsolvedCrimes) {
                officersString = officersString + Officer + "\n"
            }
        }
    }




}