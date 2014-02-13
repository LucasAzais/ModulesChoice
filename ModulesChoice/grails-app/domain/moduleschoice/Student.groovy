package moduleschoice

class Student {

	String name
	String surname
	static hasMany = [choices : Application]
	
	String toString(){
		"$surname" + " $name"
	}
	
	void addApplication(int choice, int preference, Module module){
		
	}
	
	void removeApplication(String moduleName){
		
	}
	
	
	
    static constraints = {
		surname(blank : false)
		name(blank : false)
		
    }
}
