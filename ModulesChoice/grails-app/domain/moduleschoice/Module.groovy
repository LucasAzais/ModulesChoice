package moduleschoice

import java.util.List;

class Module {
	
	int sequence
	String title
	String description
	String department
	String ensicaName
	static hasMany = [applications : Application,requirements: Module]
	static belongsTo = [headTeacher : Teacher, generalTeacher : Teacher]
	static mappedBy = [headTeacher:'modules',generalTeacher:'allModules']
	
	Module(String _title, int _sequence, String _department, String _ensicaName,String _description, Teacher _t){
		title = _title
		sequence = _sequence
		department = _department
		ensicaName = _ensicaName
		description = _description
		headTeacher = _t
	}
	
	String toString(){
		"$title" + " (Sequence : $sequence)"
	}
	
	void addApplication(Application app){
		applications.add(app)
	}
	
	void removeApplication(String studentName){
		for(Application a : applications){
			if(a.getStudentName()==studentName) applications.remove(a)
		}
	}

    static constraints = {
		title(blank : false, validator : {return it.matches("[a-zA-Z0-9 -]+")})
		sequence(min : 1, max : 7)
		department(inList : ["MAS","MIN","MGM","MMF"])
		ensicaName(blank:false, validator : {return (it.matches("[0-9]+-[0-9]+[A-Z ]+[0-9]+")&&(Module.findAllByEnsicaName(it).size()==0))}) 
		description(blank : false)
		
				
    }
}

