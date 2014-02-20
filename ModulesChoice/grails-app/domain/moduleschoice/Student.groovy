package moduleschoice

class Student {

	String name
	String surname
	static hasMany = [choices : Application]

	String toString(){
		"$surname" + " $name"
	}

	void addApplication(int _choice, int _preference,String _module){
		Application app = new Application(choice:_choice,preference : _preference, module:Module.findByEnsicaName(_module),student:this)
//		app.module = Module.findByEnsicaName(_module)
//		app.student = this
		choices.add(app)
	}

	void removeApplication(String moduleName){
		for(Application a : choices){
			if(a.getModuleName()==moduleName) choices.remove(a)
			
		}
	}



	static constraints = {
		surname(blank : false)
		name(blank : false)

	}
}
