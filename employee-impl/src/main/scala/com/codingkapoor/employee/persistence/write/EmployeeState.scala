package com.codingkapoor.employee.persistence.write

import java.time.LocalDate
import java.time.LocalDateTime

import play.api.libs.json.{Format, Json}

case class EmployeeState(id: String, name: String, gender: String, doj: LocalDate, pfn: String)

object EmployeeState {
  implicit val format: Format[EmployeeState] = Json.format[EmployeeState]

  def startingState: EmployeeState = EmployeeState(None, None, None, LocalDateTime.now().toString, None)
}
