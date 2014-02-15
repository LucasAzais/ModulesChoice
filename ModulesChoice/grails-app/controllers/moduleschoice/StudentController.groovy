package moduleschoice

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
		_student.addApplication(params.choice.toInteger(), params.preference.toInteger(), params.module)
		_student.save()
		render "Creation done"
		//redirection ajax ???
	}

	def seeTendency(){
		for(Module m : Module.findAll()){
			params.(m.getTitle()) = m.applications.size()
		}
		return params
	}
}
