package moduleschoice

class Application {

	int choice
	int preference
	static belongsTo = [student: Student, module : Module]
	
	String toString(){
		student.toString() + " choice number ${choice} is " + module.toString()
	}
    
	String getStudentName(){
		student.getName()
	}
	
	static constraints = {
		choice(min:1,max:2)
		preference(min:1,max:7)
    }
}
