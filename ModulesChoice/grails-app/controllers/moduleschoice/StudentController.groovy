package moduleschoice

class StudentController {

    def scaffold = true

	def test(){
		int maxSeq = -1;
		def _groups = [];
		for(Module m : Module.findAll()){
			if(m.sequence>maxSeq){
				maxSeq = m.sequence;
			}
			if(!_groups.contains(m.department)){
				_groups.add(m.department);
			}
		}
		def nbrByGroups = [];
		int max;
		for(String group : _groups){
			max = -1;
			for(int i=1; i<=maxSeq; i++){
				if(Module.countByDepartmentAndSequence(group, i)>max){
					max = Module.countByDepartmentAndSequence(group, i);
				}
			}
			nbrByGroups.add(max);
		}
		def modules = [];
		for(int i=1; i<=maxSeq; i++){
			for(String group : _groups){
				for(Module m : Module.findAllByDepartmentAndSequence(group, i)){
					modules.add(m);
				}
			}
		}
		[modules : modules , nbrOfSequences : maxSeq, groups : _groups, nbrByGroups : nbrByGroups];
	}
	
	def setChoice(){
		def student = Student.get(session.id)
		student.addApplication(params.choice.toInteger(), params.preference.toInteger(), Module.findByTitle(params.module))
	 }
	
	def quit(){
		def student = Student.get(session.id) 
		for(Application a : student.hasMany){
			Module.getByName(a.getModuleName()).addApplication(a)
		}
	}
	
	
	
	
	
}
