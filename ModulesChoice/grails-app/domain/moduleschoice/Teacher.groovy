package moduleschoice

class Teacher {
	
	String name
	String surname
	static hasMany = [modules : Module,allModules: Module]
	static mappedBy = [modules:'headTeacher',allModules:'generalTeacher']
	

	String toString(){
		"$surname" + " $name"
	}	

    static constraints = {
		surname(blank : false, validator : {return it.matches("[a-zA-Z -]+")})
		name(blank : false, validator : {return it.matches("[a-zA-Z -]+")})
    }
}
