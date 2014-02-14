package moduleschoice

class StudentController {

    def scaffold = true

	def test(){
		int maxSeq = -1;
		for(Module m : Module.findAll()){
			if(m.sequence>maxSeq){
				maxSeq = m.sequence;
			}
		}
		[modules : Module.findAll(), nbrOfSequences : maxSeq];
	}
	
	def setChoice(){
		def student = Student.get(session.id)
		student.addApplication(session.choice.toInteger(), session.preference.toInteger(), Module.findByTitle(session.module))
	 }
	
}
