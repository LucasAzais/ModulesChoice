package moduleschoice

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.dao.DataIntegrityViolationException

class TeacherController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index() {
		redirect(action: "list", params: params)
	}

	def list(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		[teacherInstanceList: Teacher.list(params), teacherInstanceTotal: Teacher.count()]
	}

	def create() {
		[teacherInstance: new Teacher(params)]
	}

	def save() {
		def teacherInstance = new Teacher(params)
		if (!teacherInstance.save(flush: true)) {
			render(view: "create", model: [teacherInstance: teacherInstance])
			return
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'teacher.label', default: 'Teacher'), teacherInstance.id])
		redirect(action: "show", id: teacherInstance.id)
	}

	def show(Long id) {
		def teacherInstance = Teacher.get(id)
		if (!teacherInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'teacher.label', default: 'Teacher'), id])
			redirect(action: "list")
			return
		}

		[teacherInstance: teacherInstance]
	}

	def edit(Long id) {
		def teacherInstance = Teacher.get(id)
		if (!teacherInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'teacher.label', default: 'Teacher'), id])
			redirect(action: "list")
			return
		}

		[teacherInstance: teacherInstance]
	}

	def update(Long id, Long version) {
		def teacherInstance = Teacher.get(id)
		if (!teacherInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'teacher.label', default: 'Teacher'), id])
			redirect(action: "list")
			return
		}

		if (version != null) {
			if (teacherInstance.version > version) {
				teacherInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						[message(code: 'teacher.label', default: 'Teacher')] as Object[],
						"Another user has updated this Teacher while you were editing")
				render(view: "edit", model: [teacherInstance: teacherInstance])
				return
			}
		}

		teacherInstance.properties = params

		if (!teacherInstance.save(flush: true)) {
			render(view: "edit", model: [teacherInstance: teacherInstance])
			return
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'teacher.label', default: 'Teacher'), teacherInstance.id])
		redirect(action: "show", id: teacherInstance.id)
	}

	def delete(Long id) {
		def teacherInstance = Teacher.get(id)
		if (!teacherInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'teacher.label', default: 'Teacher'), id])
			redirect(action: "list")
			return
		}

		try {
			teacherInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'teacher.label', default: 'Teacher'), id])
			redirect(action: "list")
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'teacher.label', default: 'Teacher'), id])
			redirect(action: "show", id: id)
		}
	}

	def exportExcel(){
		def mod = Module.findByTitle(params.module)
		ArrayList<ArrayList<String>> students = new ArrayList<ArrayList<String>>()
		ArrayList<String> applicant
		for(Application a : mod.applications){
			applicant = new ArrayList<String>()
			applicant.add(a.student.getName())
			applicant.add(a.student.getSurname())
			applicant.add(a.choice.toString())
			applicant.add(a.preference.toString())
			students.add(applicant)
		}

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(params.module+" Candidatures");

		HSSFFont bold = workbook.createFont();
		bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		bold.setFontHeightInPoints((short) 12);
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFont(bold);
		style.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
		style.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
		style.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
		style.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);

		HSSFFont bold1 = workbook.createFont();
		bold1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		bold1.setFontHeightInPoints((short) 10);
		HSSFCellStyle style1 = workbook.createCellStyle();
		style1.setFont(bold1);
		style1.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
		style1.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
		style1.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
		style1.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);

		HSSFCellStyle style2 = workbook.createCellStyle();
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);

		HSSFRow row0 = sheet.createRow(0);
		Cell cell0 = row0.createCell(0);
		cell0.setCellValue("Liste des candidats de   ");
		cell0.setCellStyle(style);
		Cell cell01 = row0.createCell(1);
		cell01.setCellValue(params.module+"   ");
		cell01.setCellStyle(style);
		HSSFRow row1 = sheet.createRow(2);
		Cell cell1 = row1.createCell(0);
		cell1.setCellValue("Name");
		cell1.setCellStyle(style1);
		Cell cell2 = row1.createCell(1);
		cell2.setCellValue("Surname");
		cell2.setCellStyle(style1);
		Cell cell3 = row1.createCell(2);
		cell3.setCellValue("Choice  ");
		cell3.setCellStyle(style1);
		Cell cell4 = row1.createCell(3);
		cell4.setCellValue("Preference  ");
		cell4.setCellStyle(style1);

		for(int i=0;i<students.size();i++){
			HSSFRow row2 = sheet.createRow(3+i);
			for(int j=0;j<4;j++) {
				Cell cell = row2.createCell(j);
				cell.setCellValue(students.get(i).get(j));
				cell.setCellStyle(style2);
			}
		}
		sheet.autoSizeColumn(0);
		sheet.autoSizeColumn(1);
		sheet.autoSizeColumn(2);
		sheet.autoSizeColumn(3);


		try {
			FileOutputStream out =
					new FileOutputStream(new File('web-app/tmpfiles/'+params.module+'.xls'));
			workbook.write(out);
			out.close();
			System.out.println("Excel written successfully..");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return [fileName:params.module]
	}

	def download = {
		def file = new File('web-app/tmpfiles/'+params.module+'.xls'); //<-- you'll probably want to pass in the file name dynamically with the 'params' map
		response.setContentType("application/excel")
		response.setHeader("Content-disposition", "attachment;filename=${file.getName()}")
		response.outputStream << file.newInputStream()
	}

	def frontPageTeacher(){
		return
	}

	def reviewResultsPage(){
		return
	}

}
