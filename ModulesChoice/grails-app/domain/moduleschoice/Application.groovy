package moduleschoice

class Application {
	
	int choice
	int preference
	static belongsTo = [student : Student, module : Module]
	

    static constraints = {
		student(blank : false)
		module(blank : false)
		preference(min : 1, max: 7)
		choice(min : 1, max: 2)
    }
}
