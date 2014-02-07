package moduleschoice

class Module {
	
	int sequence
	String title
	String description
	List<Module> requirements
	static hasMany = [applications : Application]
	static belongsTo = [headTeacher : Teacher]
	
	String toSring(){
		"$title" + " " + "(Séquence : $sequence)"
	}

    static constraints = {
		title(blank : false)
		sequence(min : 1, max : 7)
		description(blank : false)
		
    }
}
