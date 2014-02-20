package moduleschoice

class Application {

	int choice
	int preference
	static belongsTo = [student: Student]
	static hasOne = [module : Module]
	
	String toString(){
		student.toString() + "'s choice number ${choice} is " + module.toString()
	}
    
	String getStudentName(){
		student.getName()
	}
	
	String getModuleName(){
		module.getTitle()
	}
	
	static constraints = {
		choice(min:1,max:2)
		preference(min:0,max:7)
    }
}
