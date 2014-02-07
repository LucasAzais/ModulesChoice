package moduleschoice

class Teacher {
	
	def name
	def surname
	def hasMany = [modules : Module]
	

    static constraints = {
    }
}
