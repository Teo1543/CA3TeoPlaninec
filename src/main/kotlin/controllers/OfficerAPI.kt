package controllers

import models.Officer

class OfficerAPI {

    private var officers = ArrayList<Officer>();

    fun add(officer: Officer): Boolean {
        return officers.add(officer);
    }




}