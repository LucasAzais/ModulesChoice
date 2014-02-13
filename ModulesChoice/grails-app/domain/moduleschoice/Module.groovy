package moduleschoice

class Module {
	
	int sequence
	String group
	String id
	String title
	String description
	List<Module> requirements
	static hasMany = [applications : Application]
	static belongsTo = [headTeacher : Teacher]
	
	String toString(){
		"$title" + " (Sequence : $sequence)"
	}
	
	void addApplication(Application app){
		
	}
	
	void removeApplication(String studentName){
		
	}

    static constraints = {
		title(blank : false)
		group(blank : false)
		id(blank : false)
		sequence(min : 1, max : 7)
		description(blank : false)
		
    }
}
