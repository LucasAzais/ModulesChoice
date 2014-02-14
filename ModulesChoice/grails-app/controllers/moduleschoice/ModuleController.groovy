package moduleschoice

//import java.Export
import java.util.ArrayList;

class ModuleController {

	def scaffold = true

	def exportResult(){
		def Module = Module.getByTitle(params.title)
		ArrayList<ArrayList<String>> eleves = new ArrayList<ArrayList<String>>(module.getApplications.size());

		for(Application a : module.getApplications()){
			ArrayList<String> eleve = new ArrayList<String>(4);
			String name = a.getStudentName();
			eleve.add(name);
			String surname = a.getStudentSurname();
			eleve.add(surname);
			String choice = a.getChoice();
			eleve.add(choice);
			String preference = a.getPreference();
			eleve.add(preference);
			eleves.add(eleve);
		}
	}
	//def exp = new Export().exportExcel(module.getTitle(),eleves)
}

