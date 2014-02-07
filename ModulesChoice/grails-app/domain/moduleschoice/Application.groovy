package moduleschoice

class Application {
	
	def choice
	def preference
	def belongsTo = [student : Student, module : Module]
	

    static constraints = {
    }
}
