import moduleschoice.Module
import moduleschoice.Student
import moduleschoice.Teacher

class BootStrap {

	def init = { servletContext ->
		environments {
			development {
				def teacher = new Teacher(surname: "Mr", name: "prof")
				def module = new Module(title: "poney", department:"mhslk", description: "khdskld", ensicaName:"hsk", sequence:"1")
				module.headTeacher = teacher
				def student = new Student(surname:"toto", name:"tata")
				student.save()
				teacher.save()
				module.save()
				if(teacher.hasErrors()) {
					println teacher.errors
				}
				if(student.hasErrors()) {
					println student.errors
				}
			}
		}
	}
	def destroy = {
	}
}
