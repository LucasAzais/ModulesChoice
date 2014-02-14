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
		student.addApplication(params.choice.toInteger(), params.preference.toInteger(), Module.findByTitle(params.module))
	 }
	
}
