package moduleschoice

class Teacher {
	
	String name
	String surname
	static hasMany = [modules : Module]

	String toString(){
		"$surname" + " $name"
	}	

    static constraints = {
		surname(blank : false)
		name(blank : false)
    }
}
