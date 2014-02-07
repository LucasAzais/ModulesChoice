package moduleschoice

class Student {

	String name
	def surname
	def hasMany = [choices : Application]
	
    static constraints = {
    }
}
