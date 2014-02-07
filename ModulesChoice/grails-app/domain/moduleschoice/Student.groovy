package moduleschoice

class Student {

	String name
	String surname
	static hasMany = [choices : Application]
	
	String toString(){
		"$surname" + " $name"
	}
	
    static constraints = {
		surname(blank : false)
		name(blank : false)
		
    }
}
