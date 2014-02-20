import moduleschoice.Application
import moduleschoice.Module
import moduleschoice.Student
import moduleschoice.Teacher

class BootStrap {

	def init = { servletContext ->
		environments {
			development {
				def t1 = new Teacher(surname:'Jurczynski',name:'Thomas', modules : [])
				t1.save();
				if(t1.hasErrors()){
					println t1.errors
				}
				def t2 = new Teacher(surname:'The White',name:'Gandalf',modules : [])
				t2.save();
				if(t2.hasErrors()){
					println t2.errors
				}
				def t3 = new Teacher(surname: 'Sidius',name: 'Darth',modules : [])
				t3.save();
				if(t3.hasErrors()){
					println t3.errors
				}

				def s1 = new Student(surname:'Azais',name:'Lucas')
				s1.save()
				if(s1.hasErrors()){
					println s1.errors
				}
				def s2 = new Student(surname:'Vador',name: 'Darth')
				s2.save()
				if(s2.hasErrors()){
					println s2.errors
				}
				def s3 = new Student(surname: 'The Gray', name: 'Gandalf')
				s3.save()
				if(s3.hasErrors()){
					println s3.errors
				}

				def m1 = new Module(title:'How to become a God',sequence:1,department:'MGM',ensicaName:'4-1MGM11',description:'I can help you',headTeacher:t1)
				m1.save()
				if(m1.hasErrors()){
					println m1.errors
				}
				def m2 = new Module(title:'The Dark Side of The Force',sequence:2,department:'MMF',ensicaName:'4-2MMF21',description:'Raise your anger, my apprentice',headTeacher:t2)
				m2.save()
				if(m2.hasErrors()){
					println m2.errors
				}
				def m3 = new Module(title:'The Two Towers',sequence:3,department:'MIN',ensicaName:'4-3MIN31',description:'We will crush the Rohan',headTeacher:t3 )
				m3.save()
				if(m3.hasErrors()){
					println m3.errors
				}

				def a1 = new Application(choice : 1, preference : 1, student:s1,module:m1 )
				a1.save()
				if(a1.hasErrors()){
					println a1.errors
				}
			}
		}
	}
	def destroy = {
	}
}
