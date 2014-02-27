package coo.mvc.blank.actions.company;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import coo.core.message.MessageConfig;
import coo.core.model.SearchModel;
import coo.mvc.blank.entity.Employee;
import coo.mvc.blank.service.CompanyService;
import coo.mvc.blank.service.EmployeeService;
import coo.mvc.util.DwzResultUtils;

@Controller
@RequestMapping("/company")
public class EmployeeAction {
	@Resource
	private EmployeeService employeeService;
	@Resource
	private CompanyService companyService;
	@Resource
	private MessageConfig messageConfig;

	/**
	 * 查看职员列表。
	 * 
	 * @return 返回职员列表页面。
	 */
	@RequestMapping("employee-list")
	public void list(Model model, SearchModel searchModel) {
		model.addAttribute("employeePage",
				employeeService.searchEmployee(searchModel));
	}

	/**
	 * 新增职员。
	 * 
	 * @return 返回新增职员页面。
	 */
	@RequestMapping("employee-add")
	public void add(Model model) {
		model.addAttribute(new Employee());
		model.addAttribute("companys", companyService.getAllCompany());
	}

	/**
	 * 保存职员。
	 * 
	 * @return 返回提示信息。
	 */
	@RequestMapping("employee-save")
	public ModelAndView save(Employee employee) {
		employeeService.createEmployee(employee);
		return DwzResultUtils.close(
				messageConfig.getString("employee.add.success"),
				"employee-list");
	}

	/**
	 * 编辑职员。
	 * 
	 * @return 返回编辑职员页面。
	 */
	@RequestMapping("employee-edit")
	public void edit(String employeeId, Model model) {
		model.addAttribute(employeeService.getEmployee(employeeId));
		model.addAttribute("companys", companyService.getAllCompany());
	}

	/**
	 * 更新职员。
	 * 
	 * @return 返回提示信息。
	 */
	@RequestMapping("employee-update")
	public ModelAndView update(Employee employee) {
		employeeService.updateEmployee(employee);
		return DwzResultUtils.close(
				messageConfig.getString("employee.edit.success"),
				"employee-list");
	}

	/**
	 * 删除职员。
	 * 
	 * @return 返回提示信息。
	 */
	@RequestMapping("employee-delete")
	public ModelAndView delete(String employeeId) {
		employeeService.deleteEmployee(employeeId);
		return DwzResultUtils.refresh(
				messageConfig.getString("employee.delete.success"),
				"employee-list");
	}
}
