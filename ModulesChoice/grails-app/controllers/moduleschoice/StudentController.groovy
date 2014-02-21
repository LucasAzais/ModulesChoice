package moduleschoice

import grails.converters.JSON
import org.hibernate.transform.ToListResultTransformer;
import org.springframework.dao.DataIntegrityViolationException

class StudentController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index() {
		redirect(action: "list", params: params)
	}

	def list(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		[studentInstanceList: Student.list(params), studentInstanceTotal: Student.count()]
	}

	def create() {
		def student = new Student(params)
		session[userName] = student.getName()
		[studentInstance: student]
	}

	def save() {
		def studentInstance = new Student(params)
		if (!studentInstance.save(flush: true)) {
			render(view: "create", model: [studentInstance: studentInstance])
			return
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'student.label', default: 'Student'), studentInstance.id])
		redirect(action: "show", id: studentInstance.id)
	}

	def show(Long id) {
		def studentInstance = Student.get(id)
		if (!studentInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'student.label', default: 'Student'), id])
			redirect(action: "list")
			return
		}

		[studentInstance: studentInstance]
	}

	def edit(Long id) {
		def studentInstance = Student.get(id)
		if (!studentInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'student.label', default: 'Student'), id])
			redirect(action: "list")
			return
		}

		[studentInstance: studentInstance]
	}

	def update(Long id, Long version) {
		def studentInstance = Student.get(id)
		if (!studentInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'student.label', default: 'Student'), id])
			redirect(action: "list")
			return
		}

		if (version != null) {
			if (studentInstance.version > version) {
				studentInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						[message(code: 'student.label', default: 'Student')] as Object[],
						"Another user has updated this Student while you were editing")
				render(view: "edit", model: [studentInstance: studentInstance])
				return
			}
		}

		studentInstance.properties = params

		if (!studentInstance.save(flush: true)) {
			render(view: "edit", model: [studentInstance: studentInstance])
			return
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'student.label', default: 'Student'), studentInstance.id])
		redirect(action: "show", id: studentInstance.id)
	}

	def delete(Long id) {
		def studentInstance = Student.get(id)
		if (!studentInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'student.label', default: 'Student'), id])
			redirect(action: "list")
			return
		}

		try {
			studentInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'student.label', default: 'Student'), id])
			redirect(action: "list")
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'student.label', default: 'Student'), id])
			redirect(action: "show", id: id)
		}
	}

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
		
		def _student = Student.findByName(params.user)
		boolean modif=false
		//check if there is a similar application (same sequence)
		for(Application a: _student.choices){
//			if(a.module.getEnsicaName().equals(params.module)){
//				a.choice=params.choice.toInteger()
//				a.save()
//				modif=true
//			}
			if(a.module.getSequence()==(Module.findByEnsicaName(params.module)).getSequence()){
				a.module=Module.findByEnsicaName(params.module)
				a.save()
				modif=true
			}
		}
		if(!modif){
			_student.addApplication(params.choice.toInteger(),1, params.module)
		}
		_student.save()
		
		def forbidden = [];
		//add all first choices
		def chosen = [];
		for(Application a: student.choices){
			chosen.add(a.module);
			forbidden.add(a.module.ensicaName);
		}
		
		//add all modules that requirements are not completed
		for(Module m : Module.findAll()){
			if(m.requirements!=null&&!chosen.containsAll(m.requirements)){
				forbidden.add(m.ensicaName);
			}
		}
		render "${[forbidden : forbidden]}"
	}

	def seeTendency(){
		println Module.findAll().toArray()
		params.moduleList = Module.findAll().toArray()
		println "\t"+params.moduleList.toString()
		ArrayList<String[]> newData = new ArrayList<String[]>()
		newData.add(["",0])
		ArrayList<Integer> dataApplicant = new ArrayList<Integer>()
		for(Module m : Module.findAll()){
			println "\t\t\t"+m.toString()
			//params.(m.getTitle()) = m.applications.size()
			dataApplicant.add(m.applications.size())
			newData.add([m.getTitle(),m.applications.size()])
		}
		params.data = dataApplicant.toArray()
		params.newData = newData
		println "\t\t"+params.data.toString()
		println "new data format"+newData.toString()
		return params
		//return newData
	}

	def setPreference(){
		def student=Student.findByName(params.user)
		for(Application a : student.choices){
			if(a.choice==1){
				a.preference=(params.(a.module.getSequence())).toInteger()
				a.save()
			}
		}
		render "preference OK"
	}
	
	def frontPageStudent(){
		return
	}
}
