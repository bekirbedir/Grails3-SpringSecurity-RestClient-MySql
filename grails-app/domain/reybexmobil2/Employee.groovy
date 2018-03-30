package reybexmobil2


class Employee {
    String firstName
    String lastName
    Date dob
    String title

    static constraints = {
        firstName nullable: false, blank: false, matches: /.{1,30}$/
        lastName nullable: false, blank: false, matches: /.{1,30}$/
        dob nullable: false, blank: false
        title nullable: false, blank: false, matches: /.{1,30}$/
    }
}