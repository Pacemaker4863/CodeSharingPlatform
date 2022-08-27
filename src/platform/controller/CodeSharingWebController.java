package platform.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import platform.business.CodeSnippet;
import platform.persistence.dto.CodeSnippetDto;

@Controller
@RequestMapping("code")
public class CodeSharingWebController {

    private final CodeSharingAPI codeSharingApi;

    public CodeSharingWebController(CodeSharingAPI apiRestService) {
        this.codeSharingApi = apiRestService;
    }

    @RequestMapping("/error")
    public String handleError() {
        return "error";
    }

    @GetMapping(path = "/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public String findCodeSnippetById(Model model, @PathVariable String id) {
        CodeSnippetDto codeSnippetDto = codeSharingApi.findCodeSnippetById(id).getBody();
        System.out.println(codeSnippetDto);
        model.addAttribute("codeSnippet", codeSnippetDto);
        return "code";
    }

    @GetMapping(path = "/new", produces = MediaType.TEXT_HTML_VALUE)
    public String createCodeSnippet(Model ignoredModel) {
        return "create";
    }

    @GetMapping(path = "/latest", produces = MediaType.TEXT_HTML_VALUE)
    public String findLatestCodeSnippet(Model model) {
        model.addAttribute("codeSnippets", codeSharingApi.getLatestPublicCodeSnippets().getBody());
        return "latest";
    }
}
