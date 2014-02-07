package moduleschoice

class Module {
	
	def sequence
	def title
	def description
	def requirements
	def hasMany = [applications : Application]
	def belongsTo = [headTeacher : Teacher]

    static constraints = {
    }
}
