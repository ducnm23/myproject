package javaframework.watch_manage.controller.admin;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface IAdminController<T>{
    String showList(Model model, HttpSession session,
                       @RequestParam(defaultValue = "1") int page,
                       @RequestParam(defaultValue = "5") int limit,
                       @RequestParam(defaultValue = "") String search);
    String doAdd(Model model, @ModelAttribute T obj);
    String doEdit(Model model,@ModelAttribute T obj);
    List<T> doDelete(@RequestBody T obj);
    String showEditOrAddPage(Model model, @PathVariable(name = "id", required = false) Long id);
}
