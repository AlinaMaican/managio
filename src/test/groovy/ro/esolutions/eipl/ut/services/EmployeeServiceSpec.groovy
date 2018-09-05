package ro.esolutions.eipl.ut.services

import ro.esolutions.eipl.generators.EmployeeModelGenerator
import ro.esolutions.eipl.repositories.EmployeeRepository
import ro.esolutions.eipl.services.EmployeeService
import spock.lang.Specification

import static ro.esolutions.eipl.generators.EmployeeGenerator.aEmployee
import static ro.esolutions.eipl.generators.EmployeeModelGenerator.aEmployeeModel
import static ro.esolutions.eipl.generators.EmployeeModelGenerator.aEmployeeModel


class EmployeeServiceSpec extends Specification {

    def employeeRepository = Mock(EmployeeRepository)
    def employeeService = new EmployeeService(employeeRepository)

    def "getAllEmployees"() {
        when:
        def result = employeeService.getAllEmployees()

        then:
        result == [aEmployeeModel()]

        and:
        1 * employeeRepository.findAll() >> [aEmployee()]
        0 * _
    }
    def "getEmployeesByQuery"(){
        given:
        def  s1='Nam'

        when:
        def result = employeeService.getEmployeesByQuery(s1)

        then:
        result == [aEmployeeModel()] as Set

        and:
        1* employeeRepository.findByFirstNameContainingIgnoreCase(s1) >> [aEmployee()]
        1* employeeRepository.findByLastNameContainingIgnoreCase(s1) >> [aEmployee()]
        0* _

    }

}
