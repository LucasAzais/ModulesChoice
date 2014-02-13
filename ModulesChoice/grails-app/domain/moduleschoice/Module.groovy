package moduleschoice

import java.util.List;

class Module {
	
	int sequence
	String title
	String description
	String department
	String ensicaName
	List<Module> requirements
	static hasMany = [applications : Application]
	static belongsTo = [headTeacher : Teacher]
	
	String toString(){
		"$title" + " (Sequence : $sequence)"
	}
	
	void addApplication(Application app){
		this.addToApplications(app)
	}
	
	void removeApplication(String studentName){
		for(Application a : applications){
			if(a.getStudentName()==studentName) applications.remove(a)
		}
	}

    static constraints = {
		title(blank : false)
		sequence(min : 1, max : 7)
		department(blank:false)
		ensicaName(blank:false)
		description(blank : false)
				
    }
}

